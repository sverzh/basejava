package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected int size = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public List<Resume> getList() {
        List<Resume> list = Arrays.asList(Arrays.copyOf(storage, size));
        return list;
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    protected void updateResume(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    protected void addResume(Resume resume, Object searchKey) {
        if (size == storage.length) {
            throw new StorageException("Сохранение невозможно. База переполнена!", resume.getUuid());
        }
        insertElement(resume, (Integer) searchKey);
        size++;
    }

    protected void deleteResume(Object searchKey) {
        if (size - 1 - (Integer) searchKey >= 0) {
            deleteElement((Integer) searchKey);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);


}
