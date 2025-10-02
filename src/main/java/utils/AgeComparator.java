package utils;

import model.Person;
import java.util.Comparator;

public class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        // Сравниваем по возрасту
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}