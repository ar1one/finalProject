package inputStrategy.BookFillStrategy;

import collections.MyCustomCollection;
import model.Book;

import java.util.List;

public interface BookFillStrategy {
    MyCustomCollection<Book> fill(int size);
}
