package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getList() {
        List<Resume> list = new ArrayList<>(map.values());
        return list;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object resume) {
        return map.containsValue(resume);
    }

    @Override
    protected Resume getResume(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void updateResume(Resume resume, Object r) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        map.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected void addResume(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }
}

