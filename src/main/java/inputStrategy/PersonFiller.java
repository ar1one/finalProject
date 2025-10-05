package inputStrategy;

import model.Person;

import java.util.List;

public class PersonFiller  {
    private PersonFillStrategy strategy;

    public void setStrategy(PersonFillStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Person> fill(int size) {
        if(strategy == null) throw new IllegalStateException("Стратегия заполнения не выбрана!");
        return strategy.fill(size);
    }
}
