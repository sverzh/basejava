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
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    protected void updateResume(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    protected void addResume(Resume resume, Object index) {
        if (size == storage.length) {
            throw new StorageException("Сохранение невозможно. База переполнена!", resume.getUuid());
        }
        insertElement(resume, (Integer) index);
        size++;
    }

    protected void deleteResume(Object index) {
        if (size - 1 - (Integer) index >= 0) {
            deleteElement((Integer) index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);
}
