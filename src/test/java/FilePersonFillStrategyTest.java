import collections.MyCustomCollection;
import inputStrategy.PersonFillStrategy.FilePersonFillStrategy;
import inputStrategy.PersonFillStrategy.PersonFiller;
import model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FilePersonFillStrategyTest {
    static MyCustomCollection<Person> list;
    static PersonFiller bf;

    @BeforeAll
    public static void init() {
        bf = new PersonFiller();
        bf.setStrategy(new FilePersonFillStrategy("persons.csv"));
        list = bf.fill(3);
    }

    @Test
    public void sizeTest() {
        int expected = 3;
        int result = list.size();
        Assertions.assertEquals(expected, result);
    }


}