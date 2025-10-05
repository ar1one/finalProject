package inputStrategy.PersonFillStrategy;

import collections.MyCustomCollection;
import inputStrategy.DataFillStrategy;
import model.Person;

import java.util.List;

public class PersonFiller  {
    private DataFillStrategy<Person> strategy;

    public void setStrategy(DataFillStrategy<Person> strategy) {
        this.strategy = strategy;
    }

    public MyCustomCollection<Person> fill(int size) {
        if(strategy == null) throw new IllegalStateException("Стратегия заполнения не выбрана!");
        return strategy.fill(size);
    }
}
