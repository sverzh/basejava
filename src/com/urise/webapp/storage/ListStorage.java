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
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Integer getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected Resume getResume(Object index) {
        return list.get((Integer) index);
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        list.set((Integer) index, resume);
    }

    protected void deleteResume(Object index) {
        list.remove(((Integer) index).intValue());
    }

    protected void addResume(Resume resume, Object index) {
        list.add(resume);
    }
}
