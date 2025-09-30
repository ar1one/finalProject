package inputStrategy.BookFillStrategy;

import model.Book;

import java.util.List;

public interface BookFillStrategy {
    List<Book> fill(int size);
}
