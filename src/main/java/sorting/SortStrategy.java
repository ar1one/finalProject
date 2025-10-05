package sorting;

import collections.MyCustomCollection;

import java.util.Comparator;

public interface SortStrategy<T> {
    void sort (MyCustomCollection<T> list);

    void sort(MyCustomCollection<T> list, Comparator<T> comparator);
}
