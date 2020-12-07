package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
     int size = 0;

    public void clear() {
        Arrays.fill(storage,null);
        size = 0;
    }
    public void update(Resume r) {
        if (uuidPresent(r.getUuid()) > -1) {
            storage[uuidPresent(r.getUuid())] = r;
            System.out.println("Резюме с uuid=" + r.getUuid() + " - обновлено");
        }
        else {
            System.out.println("Резюме с uuid=" + r.getUuid() + " - отсутствует в базе ");
        }
    }

    public void save(Resume r) {
        if (size!=storage.length){
            if (uuidPresent(r.getUuid())==-1 ){
                 storage[size] = r;
                 size++;
            }
        else {
            System.out.println("Резюме с uuid=" + r.getUuid() + " уже есть в базе ");
        }
    }
        else  System.out.println("Сохранение невозможно. База переполнена!");
    }

    public Resume get(String uuid) {

        Resume resume = null;
        if (uuidPresent(uuid)>-1)
         {
                resume = storage[uuidPresent(uuid)];
            }
        if (resume == null) {
            System.out.println("Резюме c uuid=" + uuid + " - не найдено");
        }
        return resume;
    }

    public void delete(String uuid) {

        if (uuidPresent(uuid)>-1) {
            int deleted = uuidPresent(uuid);
        for (int j = deleted; j < size - 1; j++) {
            storage[j] = storage[j + 1];
        }
        if (deleted != size) {
            size--;
        }
        }
        else {
            System.out.println("Резюме c uuid=" + uuid + " не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = Arrays.copyOf(storage,size);
        return resumes;
    }

    public int size() {
        return size;
    }

    private int uuidPresent (String uuid){
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
    }
        return -1;
}
}
