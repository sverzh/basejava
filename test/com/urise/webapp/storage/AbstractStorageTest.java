package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";


    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1, "Petrov"));
        storage.save(new Resume(UUID_2, "Ivanov"));
        storage.save(new Resume(UUID_3, "Sidorov"));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_2, "Ivanov");
        storage.update(newResume);
        Assert.assertTrue(newResume == storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void save() {
        int sizeBefore = storage.size();
        Resume newResume = new Resume(UUID_4, "Жуков");
        storage.save(newResume);
        Assert.assertEquals(sizeBefore + 1, storage.size());
        Assert.assertEquals(newResume, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1, "Petrov"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        int sizeBefore = storage.size();
        storage.delete(UUID_1);
        Assert.assertEquals(sizeBefore - 1, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        Assert.assertTrue(list.contains(new Resume(UUID_1, "Petrov")));
        Assert.assertTrue(list.contains(new Resume(UUID_2, "Ivanov")));
        Assert.assertTrue(list.contains(new Resume(UUID_3, "Sidorov")));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_2, "Ivanov"), storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_4);
    }
}
