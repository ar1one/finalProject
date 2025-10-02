package strategy;

import model.Person;
import utils.AgeComparator;
import java.util.List;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;

public class CustomSortStrategy implements SortStrategy<Person> {

    private final Comparator<Person> ageComparator = new AgeComparator();

    @Override
    public void sort(List<Person> list) {

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            // Ищем индекс минимального элемента среди четных возрастов, начиная с i
            int minIndex = i;
            boolean foundEven = (list.get(i).getAge() % 2 == 0);

            if (foundEven) {
                for (int j = i + 1; j < n; j++) {
                    // Проверяем, что элемент j имеет четный возраст
                    if (list.get(j).getAge() % 2 == 0) {
                        // Если не нашли четный элемент на i-й позиции, ищем первый четный для minIndex
                        if (!foundEven) {
                            minIndex = j;
                            foundEven = true;
                        } else if (ageComparator.compare(list.get(j), list.get(minIndex)) < 0) {
                            minIndex = j;
                        }
                    }
                }
                // Если был найден четный элемент и он не на своей позиции, меняем местами
                if (foundEven && minIndex != i) {
                    swap(list, i, minIndex);
                }
            }
            // Если элемент на позиции i имел нечетный возраст, просто переходим к i++
        }
    }

    private void swap(List<Person> list, int i, int j) {
        Person temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}