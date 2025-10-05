package search;

import collections.MyCustomCollection;

public class MyBinarySearch<T> {
    private final MyCustomCollection<? extends Comparable<? super T>> list;

    public MyBinarySearch(MyCustomCollection<? extends Comparable<? super T>> list) {
        this.list = list;
    }

    public int getIndexedBinarySearch(T target) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            Comparable<? super T> midValue = list.get(middle);
            int comparison = midValue.compareTo(target);

            if (comparison < 0) {
                low = middle + 1;
            } else if (comparison > 0) {
                high = middle - 1;
            } else {
                return middle; // элемент найден
            }
        }

        return -(low + 1); // элемент не найден
    }
}
