package sorting;

import collections.MyCustomCollection;

import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QuickSort<T extends Comparable<? super T>> implements SortStrategy<T> {
    private ExecutorService threadPool = Executors.newFixedThreadPool(100);
    private final int THRESHOLD = 100; //минимальный размер подмассива для запуска в многопоточке


    private void sort(MyCustomCollection<T> list, int leftIndex, int rightIndex, Comparator<? super T> comparator) {
        if (leftIndex >= rightIndex) return;
        T pivot = list.get((leftIndex + rightIndex) / 2);
        int leftMarker = leftIndex;
        int rightMarker = rightIndex;

        while (leftMarker <= rightMarker) {
            while (comparator.compare(list.get(leftMarker), pivot) < 0) leftMarker++;
            while (comparator.compare(list.get(rightMarker), pivot) > 0) rightMarker--;
            if (leftMarker <= rightMarker) {
                T temp = list.get(leftMarker);
                list.set(leftMarker, list.get(rightMarker));
                list.set(rightMarker, temp);
                leftMarker++;
                rightMarker--;
            }
        }

        if (rightMarker - leftIndex > THRESHOLD && rightIndex - leftMarker > THRESHOLD) {
            int finalRightMarker = rightMarker;
            Future<?> leftTask = threadPool.submit(() -> sort(list, leftIndex, finalRightMarker, comparator));
            int finalLeftMarker = leftMarker;
            Future<?> rightTask = threadPool.submit(() -> sort(list, finalLeftMarker, rightIndex, comparator));

            try {
                leftTask.get();
                rightTask.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        } else {
            if (leftIndex < rightMarker) sort(list, leftIndex, rightMarker, comparator);
            if (rightIndex > leftMarker) sort(list, leftMarker, rightIndex, comparator);
        }
    }

    @Override
    public void sort(MyCustomCollection<T> list) {
        sort(list, 0, list.size() - 1, Comparator.naturalOrder());
    }

    @Override
    public void sort(MyCustomCollection<T> list, Comparator<T> comparator) {
        sort(list, 0, list.size() - 1, comparator);
    }

    public void shutdown() {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

