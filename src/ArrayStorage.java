import java.util.ArrayList;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private static int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        Resume get = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) get = storage[i];
        }
        if (get == null) System.out.println("Резюме c uuid " + uuid + " не найдено");

        return get;


    }

    void delete(String uuid) {
        int deleted = size;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) deleted = i;
        }
        for (int j = deleted; j < size - 1; j++) {
            storage[j] = storage[j + 1];
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] filled = new Resume[size];
        for (int i = 0; i < size; i++) {
            filled[i] = storage[i];
        }
        return filled;
    }

    int size() {
        return size;
    }
}
