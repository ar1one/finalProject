package inputStrategy.BookFillStrategy;

import collections.MyCustomCollection;
import model.Book;


import java.util.Random;
import java.util.stream.IntStream;

public class RandomBookFill implements BookFillStrategy {
    private Random random = new Random();

    @Override
    public MyCustomCollection<Book> fill(int size) {
        if (size < 1) throw new RuntimeException("Размер коллекции для заполнения должен быть больше нуля");
        MyCustomCollection<Book> list = new MyCustomCollection<>(size);
        IntStream.range(0, size)
                .mapToObj(i -> Book.builder()
                        .numberOfPages(Math.abs(random.nextInt(2000)))
                        .age(Math.abs(random.nextInt(2025)))
                        .title("Book - " + random.nextInt(Math.abs(size)))
                        .build())
                .forEach(list::add);

        return list;
    }
}
