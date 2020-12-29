package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected int size = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    protected void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    protected void addResume(Resume resume, int index) {
        if (size == storage.length) {
            throw new StorageException("Сохранение невозможно. База переполнена!", resume.getUuid());
        }
        insertElement(resume, index);
        size++;
    }

    protected void deleteResume(int index) {
        if (size - 1 - index >= 0) {
            deleteElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);


}
