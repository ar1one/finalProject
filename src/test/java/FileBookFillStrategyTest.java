import collections.MyCustomCollection;
import inputStrategy.BookFillStrategy.BookFiller;
import inputStrategy.BookFillStrategy.FileBookFilStrategy;
import model.Book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileBookFillStrategyTest {
    static MyCustomCollection<Book> list;
    static BookFiller bf;

    @BeforeAll
    public static void init() {
        bf = new BookFiller();
        bf.setStrategy(new FileBookFilStrategy("books.csv"));
        list = bf.fill(3);
    }

    @Test
    public void sizeTest() {
        int expected = 3;
        int result = list.size();
        Assertions.assertEquals(expected, result);
    }


}
