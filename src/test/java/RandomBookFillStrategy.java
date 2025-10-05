import collections.MyCustomCollection;
import inputStrategy.BookFillStrategy.BookFiller;
import inputStrategy.BookFillStrategy.RandomBookFill;
import model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RandomBookFillStrategy {
    static MyCustomCollection<Book> list;
    static BookFiller bf;

    @BeforeAll
    public static void init() {
        bf = new BookFiller();
        bf.setStrategy(new RandomBookFill());
        list = bf.fill(355);
    }

    @Test
    public void sizeTest() {
        int expected = 355;
        int result = list.size();
        Assertions.assertEquals(expected, result);
    }


}