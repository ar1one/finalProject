package inputStrategy.PersonFillStrategy;

import collections.MyCustomCollection;
import model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FilePersonFillStrategy implements PersonFillStrategy {
    private final String filePath;

    public FilePersonFillStrategy(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public MyCustomCollection<Person> fill(int size) {
        MyCustomCollection<Person> list = new MyCustomCollection<>(size);

        try {
            Files.lines(Paths.get(filePath))
                    .limit(size)
                    .map(line -> {
                        String[] parts = line.split(",");
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        int age = Integer.parseInt(parts[2].trim());
                        boolean isStudent = Boolean.parseBoolean(parts[3].trim());
                        return Person.builder()
                                .name(name)
                                .age(age)
                                .id(id)
                                .isStudent(isStudent)
                                .build();
                    })
                    .forEach(list::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
