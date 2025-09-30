package inputStrategy.BookFillStrategy;

import model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileBookFilStrategy implements BookFillStrategy {
    private final String filePath;

    public FileBookFilStrategy(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public List<Book> fill(int size) {
        List<Book> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null && count < size) {
                String[] parts = line.split(",");
                int age = Integer.parseInt(parts[1].trim());
                String title = parts[0].trim();
                int numberOfPages = Integer.parseInt(parts[2].trim());

                list.add(Book.builder()
                        .title(title)
                        .age(age)
                        .numberOfPages(numberOfPages)
                        .build());
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}