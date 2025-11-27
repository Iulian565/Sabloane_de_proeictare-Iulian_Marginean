package command;

import model.Book;
import service.BooksService;

import java.util.Optional;

public class UpdateBookCommand implements Command<Optional<Book>> {

    private final BooksService booksService;
    private final Long id;
    private final Book updatedBook;

    public UpdateBookCommand(BooksService booksService, Long id, Book updatedBook) {
        this.booksService = booksService;
        this.id = id;
        this.updatedBook = updatedBook;
    }

    @Override
    public Optional<Book> execute() {
        return booksService.updateBook(id, updatedBook);
    }
}
