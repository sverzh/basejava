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
    private static final String fullName_1 = "Petrov";
    private static final String UUID_2 = "uuid2";
    private static final String fullName_2 = "Ivanov";
    private static final String UUID_3 = "uuid3";
    private static final String fullName_3 = "Sidorov";
    private static final String UUID_4 = "uuid4";
    private static final String fullName_4 = "Ivanov";

    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1, fullName_1));
        storage.save(new Resume(UUID_2, fullName_2));
        storage.save(new Resume(UUID_3, fullName_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_2, fullName_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test
    public void save() {
        int sizeBefore = storage.size();
        storage.save(new Resume(UUID_4, fullName_4));
        Assert.assertEquals(sizeBefore + 1, storage.size());
        Assert.assertEquals(new Resume(UUID_4, fullName_4), storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1, fullName_1));
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
        System.out.println(list);
        Assert.assertTrue(list.contains(new Resume(UUID_1, fullName_1)));
        Assert.assertTrue(list.contains(new Resume(UUID_2, fullName_2)));
        Assert.assertTrue(list.contains(new Resume(UUID_3, fullName_3)));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_2, fullName_2), storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}
