package repository;

import model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookCrudRepositoryAdapter implements CrudRepository<Book, Long> {

    private final BooksRepository booksRepository;

    public BookCrudRepositoryAdapter(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    @Override
    public Book save(Book entity) {
        return booksRepository.save(entity);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return booksRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        booksRepository.deleteById(id);
    }
}
