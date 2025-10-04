package inputStrategy;

import collections.MyCustomCollection;

public interface DataFillStrategy <T> {
    MyCustomCollection<T> fill(int size);
}
