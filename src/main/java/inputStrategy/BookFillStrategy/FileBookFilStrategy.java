package inputStrategy.BookFillStrategy;

import collections.MyCustomCollection;
import inputStrategy.DataFillStrategy;
import model.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class FileBookFilStrategy implements DataFillStrategy<Book> {
    private final String filePath;

    public FileBookFilStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public MyCustomCollection<Book> fill(int size) {
        MyCustomCollection<Book> list = new MyCustomCollection<>(size);

        try {
            Files.lines(Paths.get(filePath))
                    .limit(size)
                    .map(this::parseBook)
                    .flatMap(Optional::stream)
                    .forEach(list::add);

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла");
        }
        return list;
    }

    private Optional<Book> parseBook(String line) {
        String[] parts = line.split(",");
        if (parts.length < 3) {
            System.out.println("Пропущена строка (не хватает данных): " + line);
            return Optional.empty();
        }

        String title = parts[0].trim();
        int age = Integer.parseInt(parts[1].trim());
        int numberOfPages = Integer.parseInt(parts[2].trim());

        if (title.isEmpty() || age < 0 || numberOfPages < 0) {
            System.out.println("Пропущена строка (некорректные данные): " + line);
            return Optional.empty();
        }

        return Optional.of(Book.builder()
                .title(title)
                .age(age)
                .numberOfPages(numberOfPages)
                .build());

    }
}
