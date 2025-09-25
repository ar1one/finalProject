package strategy;

import collections.CustomList;
import model.Person;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManualFillStrategy implements FillStrategy<Person> {

    private final Scanner scanner;

    public ManualFillStrategy(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Person> fill(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> {
                    System.out.println("Введите данные для Person #" + (i + 1) + ":");
                    System.out.print("ID: ");
                    Integer id = null;
                    try {
                        id = scanner.nextInt();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Ошибка: Введите корректное число для ID.");
                        scanner.nextLine();
                        return null;
                    }
                    scanner.nextLine();

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Age: ");
                    Integer age = null;
                    try {
                        age = scanner.nextInt();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Ошибка: Введите корректное число для возраста.");
                        scanner.nextLine();
                        return null;
                    }
                    scanner.nextLine();

                    System.out.print("Is Student (true/false): ");
                    Boolean isStudent = null;
                    try {
                        isStudent = scanner.nextBoolean();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Ошибка: Введите true или false для статуса студента.");
                        scanner.nextLine();
                        return null;
                    }
                    scanner.nextLine();

                    // Валидация
                    if (name == null || name.trim().isEmpty()) {
                        System.out.println("Ошибка: Имя не может быть пустым. Пропускаем этот элемент.");
                        return null;
                    }
                    if (id == null) {
                        System.out.println("Ошибка: ID не может быть null. Пропускаем этот элемент.");
                        return null;
                    }

                    try {
                        return Person.builder()
                                .id(id)
                                .name(name)
                                .age(age)
                                .isStudent(isStudent)
                                .build();
                    } catch (IllegalStateException e) {
                        System.out.println("Ошибка при создании Person: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(CustomList::new));
    }
}