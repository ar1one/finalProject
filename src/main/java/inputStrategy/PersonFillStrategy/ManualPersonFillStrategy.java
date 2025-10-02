package inputStrategy.PersonFillStrategy;

import collections.MyCustomCollection;
import model.Person;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class ManualPersonFillStrategy implements PersonFillStrategy {
    private Scanner scanner = new Scanner(System.in);


    @Override
    public MyCustomCollection<Person> fill(int size) {
        MyCustomCollection<Person> list = new MyCustomCollection<>(size);
        AtomicBoolean isValid = new AtomicBoolean(false);
        IntStream.range(0, size)
                .mapToObj(i -> {
                    String name = readNonEmptyName("Введите имя для Person " + i + ": ");
                    int id = isPositive("Введите Id для Person " + name + ": ");
                    int age = isPositive("Введите возраст для Person " + name + ": ");
                    boolean isStudent = Boolean.parseBoolean(scanner.nextLine());
                    return Person.builder()
                            .name(name)
                            .age(age)
                            .id(id)
                            .isStudent(isStudent)
                            .build();
                })
                .forEach(list::add);

        return list;
    }

    private int isPositive(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Значение не может быть отрицательным. Попробуйте снова.");
                } else return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private String readNonEmptyName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Имя не может быть пустым. Попробуйте снова.");
        }
    }
}
