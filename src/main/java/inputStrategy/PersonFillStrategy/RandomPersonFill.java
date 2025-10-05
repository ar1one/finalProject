package inputStrategy.PersonFillStrategy;

import collections.MyCustomCollection;
import inputStrategy.DataFillStrategy;
import model.Person;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomPersonFill implements DataFillStrategy<Person> {
    private Random random = new Random();

    @Override
    public MyCustomCollection<Person> fill(int size) {
        if (size < 1) throw new RuntimeException("Размер коллекции для заполнения должен быть больше нуля");
        MyCustomCollection<Person> list = new MyCustomCollection<>(size);
        IntStream.range(0, size)
                .mapToObj(i -> Person.builder()
                        .id(random.nextInt(Math.abs(size)))
                        .age(random.nextInt(60))
                        .isStudent(random.nextBoolean())
                        .name("Person + " + random.nextInt(Math.abs(size)))
                        .build())
                .forEach(list::add);

        return list;
    }
}
