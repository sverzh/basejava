package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

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
            throw new NotExistStorageException(uuid);
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
                throw new ExistStorageException(uuid);
            }
        } else throw new StorageException("Сохранение невозможно. База переполнена!", uuid);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            if (size - 1 - index >= 0) {
                deleteElement(index);
                storage[size - 1] = null;
                size--;
            }
        } else {
            throw new NotExistStorageException(uuid);
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
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);


}
