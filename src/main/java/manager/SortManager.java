package manager;

import strategy.SortStrategy;
import model.Person;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortManager {

    private final ExecutorService executorService;

    public SortManager() {
        this.executorService = Executors.newFixedThreadPool(2); // Пул для стратегий, если нужно
    }

    public <T extends Comparable<T>> void sort(List<T> list, SortStrategy<T> strategy) {
        if (strategy instanceof MultiThreadedSortStrategy) {
            ((MultiThreadedSortStrategy<T>) strategy).sort(list, executorService);
        } else {
            strategy.sort(list);
        }
        System.out.println("Сортировка завершена.");
    }

    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}