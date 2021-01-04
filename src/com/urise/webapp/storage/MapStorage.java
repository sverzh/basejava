package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> map = new HashMap<>();

    @Override
    public void save(Resume resume) {
        String key = resume.getUuid();
        if (map.containsKey(key)) {
            throw new ExistStorageException(key);
        } else {
            map.put(key, resume);
        }
    }

    @Override
    public void delete(String uuid) {
        if (!map.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            map.remove(uuid);
        }
    }

    @Override
    public void update(Resume resume) {
        String key = resume.getUuid();
        if (map.containsKey(key)) {
            map.put(resume.getUuid(), resume);
            System.out.println("Резюме с uuid = " + key + " - обновлено");
        } else {
            throw new NotExistStorageException(key);
        }
    }

    @Override
    public Resume get(String uuid) {
        if (map.get(uuid) == null) throw new NotExistStorageException(uuid);
        return map.get(uuid);
    }

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
    protected Resume getResume(int index) {
        return null;
    }


    @Override
    protected void updateResume(Resume resume, int index) {
    }

    protected void deleteResume(int index) {
    }

    protected void addResume(Resume resume, int index) {
    }

    protected int getIndex(String uuid) {
        return 0;
    }
}
