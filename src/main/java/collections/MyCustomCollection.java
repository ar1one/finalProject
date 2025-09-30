package collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyCustomCollection<T> implements Iterable<T>{
    private T[] array; //массив
    private int size; //индекс элемента для добавления

    public MyCustomCollection(int length) {
        array = (T[]) new Object[length];  //костыли
        size = 0;
    }

    public void add(T value) { //добавить элемент
        ensureCapacity();
        array[size++] = value;
    }

    public T get(int index) { //получить элемент
        checkIndex(index);
        return array[index];
    }

    public void remove(int index) { //удалить элемент
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public void addAll(MyCustomCollection<T> list) { //добавить другой лист
        for (int i = 0; i < list.size; i++) {
            this.add(list.get(i));
        }
    }


    private void ensureCapacity() { //проверить емкость, при необходимости увеличить
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Индекс вне диапазона");
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }

    public Stream<T> stream() {
        return StreamSupport.stream(
                java.util.Spliterators.spliterator(array, 0, size, Spliterator.ORDERED),
                false
        );
    }
}