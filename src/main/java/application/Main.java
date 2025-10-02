package application;

import model.Person;
import strategy.*;
import manager.SortManager;
import utils.BinarySearch;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SortManager sortManager = new SortManager();
    private static List<Person> currentList = null;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    fillData();
                    break;
                case 2:
                    sortData();
                    break;
                case 3:
                    searchData();
                    break;
                case 4:
                    printCurrentList();
                    break;
                case 5:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        sortManager.shutdown();
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Заполнить данные");
        System.out.println("2. Сортировать данные");
        System.out.println("3. Найти элемент (бинарный поиск)");
        System.out.println("4. Показать текущий список");
        System.out.println("5. Выйти");
        System.out.print("Выберите действие: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void fillData() {
        System.out.println("Выберите способ заполнения:");
        System.out.println("1. Вручную");
        System.out.println("2. Случайно");
        System.out.println("3. Из файла");
        System.out.print("Выбор: ");
        int fillChoice = getChoice();

        System.out.print("Введите размер коллекции: ");
        int size = Integer.parseInt(scanner.nextLine());

        FillStrategy<Person> strategy = null;
        switch (fillChoice) {
            case 1:
                strategy = new ManualFillStrategy(scanner);
                break;
            case 2:
                strategy = new RandomFillStrategy();
                break;
            case 3:
                System.out.print("Введите имя файла: ");
                String fileName = scanner.nextLine();
                strategy = new FileFillStrategy(fileName);
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        if (strategy != null) {
            currentList = strategy.fill(size);
            System.out.println("Данные заполнены. Количество элементов: " + currentList.size());
        }
    }

    private static void sortData() {
        if (currentList == null || currentList.isEmpty()) {
            System.out.println("Сначала заполните данные.");
            return;
        }

        System.out.println("Выберите алгоритм сортировки:");
        System.out.println("1. Быстрая сортировка");
        System.out.println("2. Сортировка по четным возрастам");
        System.out.print("Выбор: ");
        int sortChoice = getChoice();

        SortStrategy<Person> strategy = null;
        switch (sortChoice) {
            case 1:
                strategy = new QuickSortStrategy<>();
                break;
            case 2:
                strategy = new CustomSortStrategy();
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        if (strategy != null) {
            sortManager.sort(currentList, strategy);
        }
    }

    private static void searchData() {
        if (currentList == null || currentList.isEmpty()) {
            System.out.println("Сначала заполните и отсортируйте данные.");
            return;
        }

        System.out.print("Введите ID для поиска: ");
        try {
            Integer searchId = Integer.parseInt(scanner.nextLine());
            Person searchTarget = Person.builder()
                    .id(searchId)
                    .name("")
                    .age(0)
                    .isStudent(false)
                    .build();

            int index = BinarySearch.search(currentList, searchTarget);
            if (index != -1) {
                System.out.println("Элемент найден на позиции " + index + ": " + currentList.get(index));
            } else {
                System.out.println("Элемент не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID.");
        }
    }

    private static void printCurrentList() {
        if (currentList == null) {
            System.out.println("Список пуст.");
        } else {
            System.out.println("Текущий список:");
            for (Person p : currentList) {
                System.out.println(p);
            }
        }
    }
}