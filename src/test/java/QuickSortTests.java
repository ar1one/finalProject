
import collections.MyCustomCollection;
import model.Person;
import org.junit.jupiter.api.*;
import sorting.QuickSort;

import java.util.Comparator;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuickSortTests {

    private QuickSort<Integer> intSorter;
    private QuickSort<Person> personSorter;

    private MyCustomCollection<Integer> numbers;
    private MyCustomCollection<Person> people;

    @BeforeEach
    public void init() {
        intSorter = new QuickSort<>();
        personSorter = new QuickSort<>();

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
    }

    @AfterEach
    public void tearDown() {
        intSorter.shutdown();
        personSorter.shutdown();
    }

    @Test
    @Order(1)
    public void testIntegerSortNaturalOrder() {
        intSorter.sort(numbers);

        MyCustomCollection<Integer> expected = new MyCustomCollection<>(6);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(7);
        expected.add(8);

        Assertions.assertEquals(expected, numbers, "Числа должны быть отсортированы по возрастанию");
    }

    @Test
    @Order(2)
    public void testIntegerSortReverseOrder() {
        Comparator<Integer> reverse = Comparator.reverseOrder();
        intSorter.sort(numbers, reverse);

        MyCustomCollection<Integer> expected = new MyCustomCollection<>(6);
        expected.add(8);
        expected.add(7);
        expected.add(5);
        expected.add(3);
        expected.add(2);
        expected.add(1);

        Assertions.assertEquals(expected, numbers, "Числа должны быть отсортированы по убыванию");
    }

    @Test
    @Order(3)
    public void testPersonSortById() {
        Comparator<Person> byId = Comparator.comparingInt(Person::getId);
        personSorter.sort(people, byId);

        MyCustomCollection<Person> expected = new MyCustomCollection<>(3);
        expected.add(Person.builder().id(1).name("Masha").age(22).build());
        expected.add(Person.builder().id(2).name("Artur").age(30).build());
        expected.add(Person.builder().id(3).name("Ivan").age(25).build());

        Assertions.assertEquals(expected, people, "Объекты Person должны быть отсортированы по id");
    }

    @Test
    @Order(4)
    public void testPersonSortByName() {
        Comparator<Person> byName = Comparator.comparing(Person::getName);
        personSorter.sort(people, byName);

        MyCustomCollection<Person> expected = new MyCustomCollection<>(3);
        expected.add(Person.builder().id(2).name("Artur").age(30).build());
        expected.add(Person.builder().id(3).name("Ivan").age(25).build());
        expected.add(Person.builder().id(1).name("Masha").age(22).build());

        Assertions.assertEquals(expected, people, "Объекты Person должны быть отсортированы по имени");
    }

    @Test
    @Order(5)
    public void testEmptyCollectionNoCrash() {
        MyCustomCollection<Integer> empty = new MyCustomCollection<>(0);
        intSorter.sort(empty);
        Assertions.assertEquals(0, empty.size(), "Сортировка пустой коллекции не должна вызывать ошибок");
    }

}
