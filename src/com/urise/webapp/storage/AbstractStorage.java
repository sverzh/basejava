package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        addResume(resume, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        String uuid = resume.getUuid();
        SK searchKey = getExistedSearchKey(uuid);
        updateResume(resume, searchKey);
        System.out.println("Резюме с uuid = " + uuid + " - обновлено");
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Резюме с uuid = " + uuid + " - уже есть в базе ");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Резюме с uuid = " + uuid + " - отсутствует в базе ");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getList();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> getList();

    protected abstract boolean isExist(SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void updateResume(Resume resume, SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract void addResume(Resume resume, SK searchKey);

    protected abstract SK getSearchKey(String uuid);

}
