import java.util.ArrayList;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
            }
        }
        if (resume == null) {
            System.out.println("Резюме c uuid " + uuid + " не найдено");
        }
        return resume;
    }

    void delete(String uuid) {
        int deleted = size;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) deleted = i;
        }
        for (int j = deleted; j < size - 1; j++) {
            storage[j] = storage[j + 1];
        }
        if (deleted != size) {
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
