package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    //private final static ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    // private final static SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();
    private final static ListStorage ARRAY_STORAGE = new ListStorage();
    //private final static MapStorage ARRAY_STORAGE = new MapStorage();


    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1", "Ivanov");
        final Resume r2 = new Resume("uuid2", "Petrov");
        final Resume r3 = new Resume("uuid3", "Sidorov");
        final Resume r4 = new Resume("uuid4", "Petrov1");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.update(r3);
        ARRAY_STORAGE.update(r4);
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
