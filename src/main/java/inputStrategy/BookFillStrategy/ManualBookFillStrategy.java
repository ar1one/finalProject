package inputStrategy.BookFillStrategy;

import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualBookFillStrategy implements BookFillStrategy {
    private List<Book> list = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public List<Book> fill(int size) {
        for (int i = 0; i < size; i++) {
            System.out.println("Введите название книги: ");
            String title = scanner.nextLine();
            System.out.println("Введите год издания книги: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите количество страниц книги: ");
            int numberOfPages = Integer.parseInt(scanner.nextLine());
            list.add(Book.builder()
                    .title(title)
                    .age(age)
                    .numberOfPages(numberOfPages)
                    .build());
        }
        return list;
    }
}
