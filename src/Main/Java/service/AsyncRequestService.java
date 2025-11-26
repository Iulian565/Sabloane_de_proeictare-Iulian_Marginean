package service;

import command.CreateBookCommand;
import model.AsyncRequestStatus;
import model.Book;
import model.RequestState;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AsyncRequestService {

    // Thread pool = implementarea Thread Pool pattern
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    // memorează statusul request-urilor
    private final Map<String, AsyncRequestStatus> requests = new ConcurrentHashMap<>();

    public String submitCreateBook(CreateBookCommand command) {
        String requestId = UUID.randomUUID().toString();

        AsyncRequestStatus status =
                new AsyncRequestStatus(requestId, RequestState.PENDING,
                        "Request accepted, processing not finished yet.");
        requests.put(requestId, status);

        executorService.submit(() -> {
            try {
                Book created = command.execute();
                status.setState(RequestState.COMPLETED);
                status.setMessage("Book created successfully.");
                status.setCreatedBookId(created.getId());
            } catch (Exception ex) {
                status.setState(RequestState.FAILED);
                status.setMessage("Error while creating book: " + ex.getMessage());
            }
        });

        return requestId;
    }

    public AsyncRequestStatus getStatus(String requestId) {
        return requests.get(requestId);
    }
}
