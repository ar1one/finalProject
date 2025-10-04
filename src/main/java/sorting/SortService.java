package sorting;


import collections.MyCustomCollection;

import java.util.Comparator;

public class SortService<T> {
    private SortStrategy<T> strategy;

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void sort(MyCustomCollection<T> list) {
        strategy.sort(list);
    }

    public void sort(MyCustomCollection<T> list, Comparator<T> comparator) {
        strategy.sort(list, comparator);
    }

}
