package strategy;

import java.util.List;

public interface FillStrategy<T> {
    List<T> fill(int size);
}