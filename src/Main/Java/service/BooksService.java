package service;

import model.Book;
import org.springframework.stereotype.Service;
import repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final CrudRepository<Book, Long> booksRepository;

    public BooksService(CrudRepository<Book, Long> booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return booksRepository.findById(id);
    }

    public Book createBook(Book book) {
        book.setId(null);
        return booksRepository.save(book);
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
