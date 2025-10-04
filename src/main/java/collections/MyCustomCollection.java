package collections;

import jdk.internal.util.ArraysSupport;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyCustomCollection<T> extends AbstractList<T> implements Iterable<T>, Collection<T> {
    private T[] array; //массив
    private int size; //индекс элемента для добавления
    private Object[] elementData;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static int DEFAULT_CAPACITY = 10;

//    public MyCustomCollection(int length) {
//        array = (T[]) new Object[length];  //костыли
//        size = 0;
//    }

    public MyCustomCollection (int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public boolean add(T value) { //добавить элемент
        ensureCapacity();
        array[size++] = value;
        return false;
    }

    private void fastRemove(Object[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    @Override
    public boolean remove(Object o) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found: {
            if (o == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(es[i]))
                        break found;
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        modCount++;
        final Object[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }

    public T get(int index) { //получить элемент
        checkIndex(index);
        return array[index];
    }

//    public T remove(int index) { //удалить элемент
//        checkIndex(index);
//        for (int i = index; i < size - 1; i++) {
//            array[i] = array[i + 1];
//        }
//        size--;
//        return null;
//    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        @SuppressWarnings("unchecked") T oldValue = (T) es[index];
        fastRemove(es, index);

        return oldValue;
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
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size < 1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
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

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public Stream<T> stream() {
        return StreamSupport.stream(
                Spliterators.spliterator(array, 0, size, Spliterator.ORDERED),
                false
        );
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    public void getOccurrenceCounter(T target){ //многопоточный подсчет количества вхождений Объекта в коллекцию
        long count = this.parallelStream()
                .filter(obj -> obj.equals(target))
                .count();
        System.out.println("Количество вхождений искомого элемента в коллекцию = " + count);
    }
}
