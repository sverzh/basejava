package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        addResume(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object searchKey = getExistedSearchKey(uuid);
        updateResume(resume, searchKey);
        System.out.println("Резюме с uuid = " + uuid + " - обновлено");
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object index);

    protected abstract Resume getResume(Object index);

    protected abstract void updateResume(Resume resume, Object index);

    protected abstract void deleteResume(Object index);

    protected abstract void addResume(Resume resume, Object index);

    protected abstract Object getSearchKey(String uuid);

}
