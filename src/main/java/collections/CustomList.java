package collections;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class CustomList<E> extends AbstractList<E> {
    private final List<E> internalList = new ArrayList<>();

    @Override
    public E get(int index) {
        return internalList.get(index);
    }

    @Override
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean add(E element) {
        return internalList.add(element);
    }

}