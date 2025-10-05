
import collections.MyCustomCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.MyBinarySearch;

public class MyBinarySearchTests {

    private MyCustomCollection<Integer> intList;
    private MyBinarySearch<Integer> binarySearch;

    @BeforeEach
    public void init() {
        intList = new MyCustomCollection<>(7);
        intList.add(1);
        intList.add(3);
        intList.add(5);
        intList.add(7);
        intList.add(9);
        intList.add(11);
        intList.add(13);

        binarySearch = new MyBinarySearch<>(intList);
    }

    @Test
    public void testFindExistingElementFirst() {
        int index = binarySearch.getIndexedBinarySearch(1);
        Assertions.assertEquals(0, index, "Первый элемент должен находиться по индексу 0");
    }

    @Test
    public void testFindExistingElementMiddle() {
        int index = binarySearch.getIndexedBinarySearch(7);
        Assertions.assertEquals(3, index, "Средний элемент должен находиться по индексу 3");
    }

    @Test
    public void testFindExistingElementLast() {
        int index = binarySearch.getIndexedBinarySearch(13);
        Assertions.assertEquals(6, index, "Последний элемент должен находиться по индексу 6");
    }

    @Test
    public void testElementNotFoundBetweenValues() {
        int index = binarySearch.getIndexedBinarySearch(4);
        Assertions.assertEquals(-3, index, "Элемент 4 отсутствует, ожидаемый индекс для вставки: -3");
    }

    @Test
    public void testEmptyCollectionReturnsMinusOne() {
        MyCustomCollection<Integer> empty = new MyCustomCollection<>(0);
        MyBinarySearch<Integer> search = new MyBinarySearch<>(empty);
        int result = search.getIndexedBinarySearch(10);
        Assertions.assertEquals(-1, result, "Для пустой коллекции ожидается -1 (вставка на позицию 0)");
    }
}
