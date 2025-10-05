package sorting;

import collections.MyCustomCollection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//TODO rename to MergeSort
public class MultiThreadSorting<T extends Comparable<T>> implements SortStrategy<T> {

    private Comparator<T> comparator = null;

    @Override
    public void sort(MyCustomCollection<T> list) {
        comparator = Comparator.naturalOrder();
        execute(list);
    }

    @Override
    public void sort(MyCustomCollection<T> list, Comparator<T> comparator) {
        this.comparator = comparator;
        execute(list);
    }

    private void execute(MyCustomCollection<T> list) {
        if (list == null || list.size() <= 1) return;

        T[] array = toArray(list);

        T[] left = Arrays.copyOfRange(array, 0, array.length / 2);
        T[] right = Arrays.copyOfRange(array, array.length / 2, array.length);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<T[]> f1 = executor.submit(() -> sortArray(left));
            Future<T[]> f2 = executor.submit(() -> sortArray(right));
            T[] sortedLeft = f1.get();
            T[] sortedRight = f2.get();
            T[] merged = merge(sortedLeft, sortedRight);

            fillCollection(list, merged);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private T[] sortArray(T[] arr) {
        if (arr.length <= 1) return arr;
        if (arr.length == 2) {
            if (comparator.compare(arr[0], arr[1]) > 0) {
                T tmp = arr[0];
                arr[0] = arr[1];
                arr[1] = tmp;
            }
            return arr;
        }
        T[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        T[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        return merge(sortArray(left), sortArray(right));
    }

    private T[] merge(T[] a1, T[] a2) {
        T[] result = Arrays.copyOf(a1, a1.length + a2.length);
        int i = 0, i1 = 0, i2 = 0;
        while (i1 < a1.length && i2 < a2.length) {
            if (comparator.compare(a1[i1], a2[i2]) <= 0) {
                result[i++] = a1[i1++];
            } else {
                result[i++] = a2[i2++];
            }
        }
        while (i1 < a1.length) result[i++] = a1[i1++];
        while (i2 < a2.length) result[i++] = a2[i2++];
        return Arrays.copyOf(result, i);
    }

    private T[] toArray(MyCustomCollection<T> collection) {
        T[] array = (T[]) new Comparable[collection.size()];
        int i = 0;
        for (T t : collection) {
            array[i++] = t;
        }
        return array;
    }

    private void fillCollection(MyCustomCollection<T> collection, T[] array) {

        for (int i = 0; i < array.length; i++) {
            collection.set(i, array[i]);
        }
    }
}
