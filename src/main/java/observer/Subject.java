package observer;

import model.Book;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void add(Book book);   // se apelează când se adaugă o carte nouă
}
