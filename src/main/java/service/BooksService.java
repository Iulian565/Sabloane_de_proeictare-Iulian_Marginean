package service;

import model.Book;
import org.springframework.stereotype.Service;
import repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import observer.Subject;

@Service
public class BooksService {
    private final CrudRepository<Book, Long> booksRepository;
    private final Subject allBooksSubject;

    public BooksService(CrudRepository<Book, Long> booksRepository,
                        Subject allBooksSubject) {
        this.booksRepository = booksRepository;
        this.allBooksSubject = allBooksSubject;
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return booksRepository.findById(id);
    }

    public Book createBook(Book book) {
        Book created = booksRepository.save(book);
        allBooksSubject.add(created);
        return created;
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        if (booksRepository.findById(id).isEmpty()) {
            return Optional.empty();
        }
        updatedBook.setId(id);
        Book saved = booksRepository.save(updatedBook);
        return Optional.of(saved);
    }

    public boolean deleteBook(Long id) {
        if (booksRepository.findById(id).isEmpty()) {
            return false;
        }
        booksRepository.deleteById(id);
        return true;
    }
}
