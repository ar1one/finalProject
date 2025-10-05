
import collections.MyCustomCollection;
import model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sorting.MultiThreadSorting;

import java.util.Comparator;

public class MultiThreadSortingTests {

    private MyCustomCollection<Integer> numbers;
    private MyCustomCollection<Person> people;
    private MultiThreadSorting<Integer> intSorter;
    private MultiThreadSorting<Person> personSorter;

    @BeforeEach
    public void init() {
        numbers = new MyCustomCollection<>(6);
        numbers.add(5);
        numbers.add(1);
        numbers.add(8);
        numbers.add(3);
        numbers.add(2);
        numbers.add(7);

        people = new MyCustomCollection<>(3);
        people.add(Person.builder().id(3).name("Ivan").age(25).build());
        people.add(Person.builder().id(1).name("Masha").age(22).build());
        people.add(Person.builder().id(2).name("Artur").age(30).build());

        intSorter = new MultiThreadSorting<>();
        personSorter = new MultiThreadSorting<>();
    }

    @Test
    public void testIntegerSortNaturalOrder() {
        intSorter.sort(numbers);

        MyCustomCollection<Integer> expected = new MyCustomCollection<>(6);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(7);
        expected.add(8);

        Assertions.assertEquals(expected, numbers);
    }

    @Test
    public void testPersonSortById() {
        Comparator<Person> byId = Comparator.comparingInt(Person::getId);

        personSorter.sort(people, byId);

        MyCustomCollection<Person> expected = new MyCustomCollection<>(3);
        expected.add(Person.builder().id(1).name("Masha").age(22).build());
        expected.add(Person.builder().id(2).name("Artur").age(30).build());
        expected.add(Person.builder().id(3).name("Ivan").age(25).build());

        Assertions.assertEquals(expected, people);
    }


}
