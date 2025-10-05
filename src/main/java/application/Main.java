package application;

import collections.MyCustomCollection;
import inputStrategy.BookFillStrategy.*;
import inputStrategy.DataFillStrategy;
import inputStrategy.PersonFillStrategy.*;
import model.Book;
import model.Person;
import sorting.*;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Поля для хранения коллекций
    private static MyCustomCollection<Person> personCollection = null;
    private static MyCustomCollection<Book> bookCollection = null;

    // Поля для фасадов заполнения
    private static final PersonFiller personFiller = new PersonFiller();
    private static final BookFiller bookFiller = new BookFiller();

    // Поля для сервисов сортировки
    private static final SortService<Person> personSortService = new SortService<>();
    private static final SortService<Book> bookSortService = new SortService<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int mainChoice = getChoice();

            switch (mainChoice) {
                case 1: // Работа с Person
                    handlePersonMenu();
                    break;
                case 2: // Работа с Book
                    handleBookMenu();
                    break;
                case 3: // Выход
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- Главное меню ---");
        System.out.println("1. Работа с Person");
        System.out.println("2. Работа с Book");
        System.out.println("3. Выйти");
        System.out.print("Выберите действие: ");
    }

    private static void handlePersonMenu() {
        boolean personMenuRunning = true;
        while (personMenuRunning) {
            printEntityMenu("Person", personCollection);
            int choice = getChoice();

            switch (choice) {
                case 1: // Заполнить Person
                    fillCollection(personCollection, personFiller, "Person");
                    break;
                case 2: // Сортировать Person
                    sortCollection(personCollection, personSortService, "Person");
                    break;
                case 3: // Найти Person
                    searchData(personCollection, "Person");
                    break;
                case 4: // Показать Person
                    printCollection(personCollection, "Person");
                    break;
                case 5: // Подсчет вхождений Person
                    occurrenceCounter(personCollection, "Person");
                    break;
                case 6: // Вернуться к главному меню
                    personMenuRunning = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void handleBookMenu() {
        boolean bookMenuRunning = true;
        while (bookMenuRunning) {
            printEntityMenu("Book", bookCollection);
            int choice = getChoice();

            switch (choice) {
                case 1: // Заполнить Book
                    fillCollection(bookCollection, bookFiller, "Book");
                    break;
                case 2: // Сортировать Book
                    sortCollection(bookCollection, bookSortService, "Book");
                    break;
                case 3: // Найти Book
                    searchData(bookCollection, "Book");
                    break;
                case 4: // Показать Book
                    printCollection(bookCollection, "Book");
                    break;
                case 5: // Подсчет вхождений Book
                    occurrenceCounter(bookCollection, "Book");
                    break;
                case 6: // Вернуться к главному меню
                    bookMenuRunning = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void printEntityMenu(String entityType, MyCustomCollection<?> collection) {
        System.out.println("\n--- Меню " + entityType + " ---");
        System.out.println("1. Заполнить данные");
        System.out.println("2. Сортировать данные");
        System.out.println("3. Найти элемент (бинарный поиск)");
        System.out.println("4. Показать текущий список");
        System.out.println("5. Подсчет вхождений");
        System.out.println("6. Назад");
        System.out.print("Выберите действие: ");
    }

    private static void fillCollection(MyCustomCollection<?> collection, Object filler, String entityType) {
        System.out.println("Выберите способ заполнения для " + entityType + ":");
        System.out.println("1. Вручную");
        System.out.println("2. Случайно");
        System.out.println("3. Из файла");
        System.out.print("Выбор: ");
        int fillChoice = getChoice();

        System.out.print("Введите размер коллекции: ");
        int size = Integer.parseInt(scanner.nextLine());

        DataFillStrategy strategy = null;
        switch (entityType) {
            case "Person":
                switch (fillChoice) {
                    case 1:
                        strategy = new ManualPersonFillStrategy();
                        break;
                    case 2:
                        strategy = new RandomPersonFill();
                        break;
                    case 3:
                        System.out.print("Введите имя файла: ");
                        String fileName = scanner.nextLine();
                        strategy = new FilePersonFillStrategy(fileName);
                        break;
                    default:
                        System.out.println("Неверный выбор.");
                        return;
                }
                personFiller.setStrategy((DataFillStrategy<Person>) strategy);
                personCollection = personFiller.fill(size);
                System.out.println("Коллекция Person заполнена. Размер: " + personCollection.size());
                break;
            case "Book":
                switch (fillChoice) {
                    case 1:
                        strategy = new ManualBookFillStrategy();
                        break;
                    case 2:
                        strategy = new RandomBookFill();
                        break;
                    case 3:
                        System.out.print("Введите имя файла: ");
                        String fileNameBook = scanner.nextLine();
                        strategy = new FileBookFilStrategy(fileNameBook);
                        break;
                    default:
                        System.out.println("Неверный выбор.");
                        return;
                }
                bookFiller.setStrategy((DataFillStrategy<Book>) strategy);
                bookCollection = bookFiller.fill(size);
                System.out.println("Коллекция Book заполнена. Размер: " + bookCollection.size());
                break;
        }
    }

    private static void sortCollection(MyCustomCollection<?> collection, SortService<?> sortService, String entityType) {
        if (collection == null || collection.size() == 0) {
            System.out.println("Сначала заполните данные для " + entityType + ".");
            return;
        }

        System.out.println("Выберите алгоритм сортировки для " + entityType + ":");
        System.out.println("1. Быстрая сортировка");
        // Добавить другие алгоритмы
        System.out.print("Выбор: ");
        int sortChoice = getChoice();

        Object strategy = null;
        switch (sortChoice) {
            case 1:
                if ("Person".equals(entityType)) {
                    strategy = new QuickSort<Person>();
                    personSortService.setStrategy((SortStrategy<Person>) strategy);
                    personSortService.sort((MyCustomCollection<Person>) collection);
                } else if ("Book".equals(entityType)) {
                    strategy = new QuickSort<Book>();
                    bookSortService.setStrategy((SortStrategy<Book>) strategy);
                    bookSortService.sort((MyCustomCollection<Book>) collection);
                }
                System.out.println("Сортировка " + entityType + " завершена.");
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }
    }

    // Пример метода для печати коллекции
    private static void printCollection(MyCustomCollection<?> collection, String entityType) {
        if (collection == null) {
            System.out.println("Коллекция " + entityType + " пуста (не инициализирована).");
        } else {
            System.out.println("Текущая коллекция " + entityType + ":");
            for (int i = 0; i < collection.size(); i++) {
                System.out.println(collection.get(i));
            }
        }
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}