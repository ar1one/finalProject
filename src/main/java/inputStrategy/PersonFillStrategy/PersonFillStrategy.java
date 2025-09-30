package inputStrategy.PersonFillStrategy;

import collections.MyCustomCollection;
import model.Person;

import java.util.List;

public interface PersonFillStrategy {
    MyCustomCollection<Person> fill(int size);

}
