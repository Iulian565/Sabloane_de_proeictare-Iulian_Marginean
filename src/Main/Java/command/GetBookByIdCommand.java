package command;

import model.Book;
import service.BooksService;

import java.util.Optional;

public class GetBookByIdCommand implements Command<Optional<Book>> {

    private final BooksService booksService;
    private final Long id;

    public GetBookByIdCommand(BooksService booksService, Long id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public Optional<Book> execute() {
        return booksService.getBookById(id);
    }
}
