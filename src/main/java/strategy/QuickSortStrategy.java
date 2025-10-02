package strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class QuickSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {

    @Override
    public void sort(List<T> list) {
        if (list.size() <= 1) {
            return;
        }
        List<T> copy = new ArrayList<>(list);
        quickSortAsync(copy, 0, copy.size() - 1).join();
        Collections.copy(list, copy);
    }

    private CompletableFuture<Void> quickSortAsync(List<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            int finalPivotIndex = pivotIndex;

            CompletableFuture<Void> leftFuture = CompletableFuture.runAsync(() -> {
                try {
                    quickSortAsync(list, low, finalPivotIndex - 1).join();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });

            CompletableFuture<Void> rightFuture = CompletableFuture.runAsync(() -> {
                try {
                    quickSortAsync(list, finalPivotIndex + 1, high).join();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });

            return CompletableFuture.allOf(leftFuture, rightFuture);
        } else {
            return CompletableFuture.completedFuture(null);
        }
    }

    private int partition(List<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}