package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void updateResume(Resume resume, String searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected void deleteResume(String searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected void addResume(Resume resume, String searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
