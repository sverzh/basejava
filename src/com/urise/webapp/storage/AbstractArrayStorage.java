package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;

    protected int size = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public List<Resume> getList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return storage[searchKey];
    }

    protected void updateResume(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    protected void addResume(Resume resume, Integer searchKey) {
        if (size == storage.length) {
            throw new StorageException("Сохранение невозможно. База переполнена!", resume.getUuid());
        }
        insertElement(resume, searchKey);
        size++;
    }

    protected void deleteResume(Integer searchKey) {
        if (size - 1 - searchKey >= 0) {
            deleteElement(searchKey);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);


}
