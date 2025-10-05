package inputStrategy.BookFillStrategy;

import collections.MyCustomCollection;
import inputStrategy.DataFillStrategy;
import model.Book;
import model.Person;

import java.util.List;

public class BookFiller {
    private DataFillStrategy<Book> strategy;
    public void setStrategy(DataFillStrategy<Book> strategy) {
        this.strategy = strategy;
    }
    public MyCustomCollection<Book> fill(int size) {
        if(strategy == null) {
            throw new IllegalStateException("Стратегия заполнения не выбрана!");
        }
        return strategy.fill(size);
    }
}
