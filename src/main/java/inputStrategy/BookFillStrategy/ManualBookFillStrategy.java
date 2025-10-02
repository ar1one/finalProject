package inputStrategy.BookFillStrategy;

import collections.MyCustomCollection;
import model.Book;

import java.time.Year;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ManualBookFillStrategy implements BookFillStrategy {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public MyCustomCollection<Book> fill(int size) {
        MyCustomCollection<Book> list = new MyCustomCollection<>(size);

        IntStream.range(0, size)
                .mapToObj(i -> {
                    String title = readNonEmptyTitle("Введите название книги: ");
                    int year = readYear("Введите год издания книги: ");
                    int numberOfPages = readPositiveInt("Введите количество страниц книги: ");

                    return Book.builder()
                            .title(title)
                            .age(year)
                            .numberOfPages(numberOfPages)
                            .build();
                })
                .forEach(list::add);

        return list;
    }

    private String readNonEmptyTitle(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Ошибка: строка не может быть пустой. Попробуйте снова.");
        }
    }

    private int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 1) {
                    System.out.println("Ошибка: значение должно быть >= " + 1 + ". Попробуйте снова.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private int readYear(String prompt) {
        int currentYear = Year.now().getValue();
        while (true) {
            System.out.print(prompt);
            try {
                int year = Integer.parseInt(scanner.nextLine().trim());
                if (year <= 0 || year > currentYear) {
                    System.out.println("Ошибка: год должен быть в диапазоне 1–" + currentYear + ". Попробуйте снова.");
                } else {
                    return year;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректный год.");
            }
        }
    }
}
