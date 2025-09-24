package inputStrategy.PersonFillStrategy;

import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPersonFill implements PersonFillStrategy {
    private Random random = new Random();

    @Override
    public List<Person> fill(int size) {
        List<Person> list = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            list.add(Person.builder()
                    .id(random.nextInt(Math.abs(size)))
                    .age(random.nextInt(60))
                    .isStudent(random.nextBoolean())
                    .name("Person + " + random.nextInt(Math.abs(size)))
                    .build());
        }
        return list;
    }
}
