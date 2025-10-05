import collections.MyCustomCollection;
import inputStrategy.PersonFillStrategy.PersonFiller;
import inputStrategy.PersonFillStrategy.RandomPersonFill;
import model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RandomPersonFillStrategyTest {
    static MyCustomCollection<Person> list;
    static PersonFiller bf;

    @BeforeAll
    public static void init() {
        bf = new PersonFiller();
        bf.setStrategy(new RandomPersonFill());
        list = bf.fill(48);
    }

    @Test
    public void sizeTest() {
        int expected = 48;
        int result = list.size();
        Assertions.assertEquals(expected, result);
    }

}