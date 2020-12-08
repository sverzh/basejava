package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void update(Resume resume) {
        String resumeSearchId = resume.getUuid();
        if (uuidPresent(resumeSearchId) > -1) {
            storage[uuidPresent(resumeSearchId)] = resume;
            System.out.println("Резюме с uuid=" + resumeSearchId + " - обновлено");
        } else {
            System.out.println("Резюме с uuid=" + resumeSearchId + " - отсутствует в базе ");
        }
    }

    public void save(Resume resume) {
        String resumeSearchId = resume.getUuid();
        if (size != storage.length) {
            if (uuidPresent(resumeSearchId) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Резюме с uuid=" + resumeSearchId + " уже есть в базе ");
            }
        } else System.out.println("Сохранение невозможно. База переполнена!");
    }

    public Resume get(String uuid) {
        Resume resume = null;
        if (uuidPresent(uuid) > -1) {
            resume = storage[uuidPresent(uuid)];
        }
        if (resume == null) {
            System.out.println("Резюме c uuid=" + uuid + " - не найдено");
        }
        return resume;
    }

    public void delete(String uuid) {
        if (uuidPresent(uuid) > -1) {
            int deleted = uuidPresent(uuid);
            if (size - 1 - deleted >= 0) System.arraycopy(storage, deleted + 1, storage, deleted, size - 1 - deleted);
            if (deleted != size) {
                size--;
            }
        } else {
            System.out.println("Резюме c uuid=" + uuid + " не найдено");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int uuidPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
