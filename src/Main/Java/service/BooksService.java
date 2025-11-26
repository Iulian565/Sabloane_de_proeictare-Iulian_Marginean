package service;

import model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BooksService {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public Book createBook(Book book) {
        long id = idGenerator.incrementAndGet();
        book.setId(id);
        books.put(id, book);
        return book;
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        if (!books.containsKey(id)) {
            return Optional.empty();
        }
        updatedBook.setId(id);
        books.put(id, updatedBook);
        return Optional.of(updatedBook);
    }

    public boolean deleteBook(Long id) {
        return books.remove(id) != null;
    }
}
