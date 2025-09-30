package inputStrategy.BookFillStrategy;

import model.Book;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBookFill implements BookFillStrategy {
    private Random random = new Random();

    @Override
    public List<Book> fill(int size) {
        List<Book> list = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            list.add(Book.builder()
                    .title("Book + " + random.nextInt(Math.abs(size)))
                    .age(Math.abs(random.nextInt(2025)))
                    .numberOfPages(Math.abs(random.nextInt(2000)))
                    .build());
        }
        return list;
    }
}
