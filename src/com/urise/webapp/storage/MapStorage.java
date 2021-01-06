package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
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
        ArrayList<Resume> list = new ArrayList<>();
        for (Map.Entry<String, Resume> resumeEntry : map.entrySet()) {
            list.add(resumeEntry.getValue());
        }
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object index) {
        return map.containsKey(index);
    }

    @Override
    protected Resume getResume(Object index) {
        return map.get(index);
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    protected void deleteResume(Object index) {
        map.remove(index);
    }

    protected void addResume(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    protected String getIndex(String uuid) {
        return uuid;
    }
}
