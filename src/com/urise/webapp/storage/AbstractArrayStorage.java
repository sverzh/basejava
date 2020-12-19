package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10_000;

    protected int size = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = resume;
            System.out.println("Резюме с uuid = " + uuid + " - обновлено");
        } else {
            System.out.println("Резюме с uuid = " + uuid + " - отсутствует в базе ");
        }
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (size != storage.length) {
            if (index < 0) {
                insertElement(resume, index);
                size++;
            } else {
                System.out.println("Резюме с uuid = " + uuid + " уже есть в базе ");
            }
        } else System.out.println("Сохранение невозможно. База переполнена!");
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            if (size - 1 - index >= 0) {
                deleteElement(index);
                size--;
            }
        } else {
            System.out.println("Резюме c uuid = " + uuid + " не найдено");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        Resume resume = null;
        if (index > -1) {
            resume = storage[index];
        } else {
            System.out.println("Резюме c uuid = " + uuid + " - не найдено");
        }
        return resume;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);


}
