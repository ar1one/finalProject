package strategy;

import collections.CustomList;
import model.Person;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFillStrategy implements FillStrategy<Person> {

    private final String fileName;

    public FileFillStrategy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Person> fill(int size) {
        try {
            return Files.lines(Paths.get(fileName))
                    .limit(size)
                    .map(line -> {
                        String[] parts = line.split(",");
                        if (parts.length == 4) {
                            try {
                                Integer id = Integer.parseInt(parts[0].trim());
                                String name = parts[1].trim();
                                Integer age = Integer.parseInt(parts[2].trim());
                                Boolean isStudent = Boolean.parseBoolean(parts[3].trim());

                                if (name == null || name.trim().isEmpty()) {
                                    System.out.println("Ошибка: Имя пустое в строке '" + line + "', пропускаем.");
                                    return null;
                                }
                                if (id == null) {
                                    System.out.println("Ошибка: ID null в строке '" + line + "', пропускаем.");
                                    return null;
                                }

                                return Person.builder()
                                        .id(id)
                                        .name(name)
                                        .age(age)
                                        .isStudent(isStudent)
                                        .build();
                            } catch (NumberFormatException | IllegalStateException e) {
                                System.out.println("Ошибка при разборе строки '" + line + "': " + e.getMessage());
                                return null;
                            }
                        } else {
                            System.out.println("Неправильный формат строки: '" + line + "'");
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(CustomList::new));
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new CustomList<>();
        }
    }
}