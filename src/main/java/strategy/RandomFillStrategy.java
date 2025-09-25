package strategy;

import collections.CustomList;
import model.Person;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomFillStrategy implements FillStrategy<Person> {

    private final Random random = new Random();

    @Override
    public List<Person> fill(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> {
                    String name = "Person" + i;
                    Integer id = random.nextInt(1000) + 1;
                    Integer age = random.nextInt(100) + 1;
                    Boolean isStudent = random.nextBoolean();

                    if (name == null || name.trim().isEmpty()) {
                        name = "DefaultName";
                    }
                    if (id == null) {
                        id = 1;
                    }

                    return Person.builder()
                            .id(id)
                            .name(name)
                            .age(age)
                            .isStudent(isStudent)
                            .build();
                })
                .collect(Collectors.toCollection(CustomList::new));
    }
}