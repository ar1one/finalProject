package inputStrategy.BookFillStrategy;

import collections.MyCustomCollection;
import model.Book;

import java.util.List;

public class BookFiller {
    private BookFillStrategy strategy;
    public void setStrategy(BookFillStrategy strategy) {
        this.strategy = strategy;
    }
    public MyCustomCollection<Book> fill(int size) {
        if(strategy == null) {
            throw new IllegalStateException("Стратегия заполнения не выбрана!");
        }
        return strategy.fill(size);
    }
}
