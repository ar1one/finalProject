package output;

import CompareAndSearch.MyBinarySearch;
import collections.MyCustomCollection;
import model.Person;


import java.io.*;
import java.util.*;

public class WriteDown {

    //функционал для записи отсортированных коллекций
    public static <T> void MyOutput(MyCustomCollection<T> collection, String filename /*название файла*/) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (T item : collection) {
                writer.write(item.toString()+"\n");
            }
            writer.write(" = = = = =\n");
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

}