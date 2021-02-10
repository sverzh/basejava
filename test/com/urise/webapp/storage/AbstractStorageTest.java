package com.urise.webapp.storage;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorageTest {
    protected static  final File STORAGE_DIR = new File("D:\\Test\\");
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    protected Storage storage;
    Resume resume1 = new ResumeTestData().fillResume(UUID_1, "Petrov");
    Resume resume2 = new ResumeTestData().fillResume(UUID_2, "Ivanov");
    Resume resume3 = new ResumeTestData().fillResume(UUID_3, "Sidorov");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
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
        Assert.assertEquals(newResume, storage.get(UUID_2));
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
        storage.save(new ResumeTestData().fillResume(UUID_1, "Petrov"));
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
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        List<Resume> list1 = Arrays.asList(resume1, resume2, resume3);
        Collections.sort(list1);
        Assert.assertEquals(list, list1);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new ResumeTestData().fillResume(UUID_2, "Ivanov"), storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_4);
    }
}
