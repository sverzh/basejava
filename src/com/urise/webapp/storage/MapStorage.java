package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        Collection<Resume> collection = map.values();
        return collection.toArray(new Resume[collection.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected void addResume(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
