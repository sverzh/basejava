package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

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
    public List<Resume> getAllSorted() {
        List<Resume>list = getList();
        Collections.sort(list);
        return list;
    }


    protected abstract List<Resume> getList();

    protected abstract boolean isExist(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract void addResume(Resume resume, Object searchKey);

    protected abstract Object getSearchKey(String uuid);

}
