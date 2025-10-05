package sorting;

import collections.MyCustomCollection;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadSorting<T extends Comparable<T>> {
    private final MyCustomCollection<? extends Comparable<T>> list;
    private T[] sorted;
    private MyCustomCollection<T> sortedList;

    public MultiThreadSorting(MyCustomCollection<? extends Comparable<T>> list) {
        this.list = list;
        execute();
    }

    public MyCustomCollection<T> getSortedList() {
        sortedList.addAll(Arrays.asList(sorted));
        return sortedList;
    }

    private void execute() {
        T[] array = (T[]) list.toArray();
        assert array != null;
        if (array.length == 1) {
            sorted = array;
        }
        T[] left = (T[]) new Object[array.length / 2];
        T[] right = (T[]) new Object[array.length - array.length / 2];
        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, left.length, right, 0, right.length);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<T[]> task1 = executor.submit(() -> sort(left));
            Future<T[]> task2 = executor.submit(() -> sort(right));
            T[] sortedLeft = task1.get();
            T[] sortedRight = task2.get();
            sorted = merge(sortedLeft,sortedRight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private T[] sort(T[] arr) {
        if (arr.length == 1) return arr;
        if (arr.length == 2) return merge((T[]) new Object[]{arr[0]}, (T[]) new Object[]{arr[1]});
        T[] left = (T[]) new Object[arr.length / 2];
        T[] right = (T[]) new Object[arr.length - arr.length / 2];
        System.arraycopy(arr, 0, left, 0, left.length);
        System.arraycopy(arr, left.length, right, 0, right.length);
        return merge(sort(left), sort(right));
    }

    private T[] merge(T[] a1, T[] a2) {
        T[] a = (T[]) new Object[a1.length + a2.length];
        int i = 0, i1 = 0, i2 = 0;
        while (i1 < a1.length && i2 < a2.length) a[i++] = a1[i1].compareTo(a2[i2]) > 0 ? a1[i1++] : a2[i2++];
        while (i1 < a1.length) a[i++] = a1[i1++];
        while (i2 < a2.length) a[i++] = a2[i2++];
        return a;
    }
}
