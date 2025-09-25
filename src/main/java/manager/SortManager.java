package manager;

import strategy.SortStrategy;
import model.Person;
import java.util.List;
import java.util.concurrent.*;

public class SortManager {

    private final ExecutorService executorService;

    public SortManager() {
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public void sortAsync(List<Person> list, SortStrategy<Person> strategy) {
        Future<?> future = executorService.submit(() -> {
            strategy.sort(list);
            System.out.println("Сортировка завершена.");
        });

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Ошибка при сортировке: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
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