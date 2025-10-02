package inputStrategy.PersonFillStrategy;

import collections.MyCustomCollection;
import model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


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
                    .map(this::parsePerson)
                    .flatMap(Optional::stream)
                    .forEach(list::add);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла");
        }
        return list;
    }

    private Optional<Person> parsePerson(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) {
            System.out.println("Пропущена строка (не хватает данных): " + line);
            return Optional.empty();
        }

        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        int age = Integer.parseInt(parts[2].trim());
        String isAStudent = parts[3].trim().toLowerCase();

        if (id < 0 || age < 0 || name.isEmpty()) {
            System.out.println("Пропущена строка (некорректные данные): " + line);
            return Optional.empty();
        }

        boolean isStudent;
        if ("true".equals(isAStudent)) {
            isStudent = true;
        } else if ("false".equals(isAStudent)) {
            isStudent = false;
        } else {
            System.out.println("Пропущена строка (некорректный isStudent): " + line);
            return Optional.empty();
        }

        return Optional.of(Person.builder()
                .id(id)
                .name(name)
                .age(age)
                .isStudent(isStudent)
                .build());
    }
}
