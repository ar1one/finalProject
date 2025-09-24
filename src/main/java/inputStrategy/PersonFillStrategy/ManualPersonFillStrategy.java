package inputStrategy.PersonFillStrategy;

import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualPersonFillStrategy implements PersonFillStrategy {
    private Scanner scanner = new Scanner(System.in);
    List<Person> list = new ArrayList<>();

    @Override
    public List<Person> fill(int size) {
        for (int i = 0; i < size; i++) {
            System.out.println("Введите имя для Person " + i);
            String name = scanner.nextLine();
            System.out.println("Введите Id для Person " + name);
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите возраст для Person " + name);
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println(name + " студент? true/false");
            boolean isStudent = Boolean.parseBoolean(scanner.nextLine());
            list.add(Person.builder()
                    .name(name)
                    .age(age)
                    .id(id)
                    .isStudent(isStudent)
                    .build());
        }

        return list;
    }
}
