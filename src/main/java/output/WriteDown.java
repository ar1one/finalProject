package output;

import CompareAndSearch.MyBinarySearch;
import collections.MyCustomCollection;
import model.Person;


import java.io.*;
import java.util.*;

public class WriteDown {

    //функционал для записи отсортированных коллекций
    public static <T> void MyOutput(MyCustomCollection<T> collection, String filename /*название файла*/,int type/*тип сортировки*/) {
        collection=collection.sort(type); // Сортировка по выбранному полю (год или имя или т.п.)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (T item : collection) {
                writer.write(filename+"\n"+ item.toString()+"\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

    //функционал для записи найденных значений
    public static <T> void MyOutput(MyCustomCollection<T> collection, String filename/*название файла*/,int type/*тип сортировки*/ int index) {
        collection=collection.sort(type); // Сортировка по выбранному полю (год или имя или т.п.)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(filename+"\n"+ collection.get(index).toString()+"\n");
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

}