package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> getList() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        list.set((Integer) searchKey, resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected void addResume(Resume resume, Object searchKey) {
        list.add(resume);
    }
}
