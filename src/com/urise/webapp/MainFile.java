package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        File filepath = new File(".\\.gitignore");
        try {
            System.out.println(filepath.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        File[] list = dir.listFiles();
        if (list != null) {
            PrintFiles(dir);
        }
        try (FileInputStream fis = new FileInputStream(filepath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void PrintFiles(File file) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File a : list) {
                System.out.println(a);
                if (a.isDirectory()) {
                    PrintFiles(a);
                }
            }
        }
    }
}
