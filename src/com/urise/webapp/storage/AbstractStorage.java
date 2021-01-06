package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            addResume(resume, index);
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            deleteResume(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object index = getIndex(uuid);
        if (isExist(index)) {
            updateResume(resume, index);
            System.out.println("Резюме с uuid = " + uuid + " - обновлено");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        Object index = getIndex(uuid);

        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    protected abstract boolean isExist(Object index);

    protected abstract Resume getResume(Object index);

    protected abstract void updateResume(Resume resume, Object index);

    protected abstract void deleteResume(Object index);

    protected abstract void addResume(Resume resume, Object index);

    protected abstract Object getIndex(String uuid);

}
