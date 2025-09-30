package inputStrategy.PersonFillStrategy;

import collections.MyCustomCollection;
import model.Person;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ManualPersonFillStrategy implements PersonFillStrategy {
    private Scanner scanner = new Scanner(System.in);


    @Override
    public MyCustomCollection<Person> fill(int size) {
        MyCustomCollection<Person> list = new MyCustomCollection<>(size);
        IntStream.range(0, size)
                .mapToObj(i -> {
                    System.out.println("Введите имя для Person " + i);
                    String name = scanner.nextLine();
                    System.out.println("Введите Id для Person " + name);
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите возраст для Person " + name);
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.println(name + " студент? true/false");
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
}
