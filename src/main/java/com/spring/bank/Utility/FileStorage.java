package com.spring.bank.Utility;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    public static <T> List<T> readObjects(String filename, Class<T> clazz) {
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<?> rawList = (List<?>) ois.readObject();
            for (Object obj : rawList) {
                if (clazz.isInstance(obj)) {
                    objects.add(clazz.cast(obj));
                }
            }
        } catch (FileNotFoundException e) {
            // File not found is acceptable for first run
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public static <T> void writeObjects(String filename, List<T> objects) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}