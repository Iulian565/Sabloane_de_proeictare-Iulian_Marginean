package controller;

import command.*;
import model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BooksService;
import java.util.List;

import service.AsyncRequestService;
import model.AsyncRequestStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final AsyncRequestService asyncRequestService;
    public BooksController(BooksService booksService,
                           AsyncRequestService asyncRequestService) {
        this.booksService = booksService;
        this.asyncRequestService = asyncRequestService;
    }
    // GET /books
    @GetMapping
    public List<Book> getAllBooks() {
        return new GetAllBooksCommand(booksService).execute();
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new GetBookByIdCommand(booksService, id)
                .execute()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/requests/{requestId}")
    public ResponseEntity<AsyncRequestStatus> getRequestStatus(
            @PathVariable String requestId) {

        AsyncRequestStatus status = asyncRequestService.getStatus(requestId);
        if (status == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(status);
    }

    // POST /books
    @PostMapping
    public ResponseEntity<Map<String, String>> createBook(@RequestBody Book book) {
        CreateBookCommand command = new CreateBookCommand(booksService, book);

        // trimitem comanda la executorul asincron
        String requestId = asyncRequestService.submitCreateBook(command);

        Map<String, String> body = new HashMap<>();
        body.put("requestId", requestId);

        // 202 Accepted cu un ID prin care clientul poate verifica statusul
        return ResponseEntity.accepted().body(body);
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = new DeleteBookCommand(booksService, id).execute();
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
