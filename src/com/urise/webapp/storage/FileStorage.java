package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialize.Serialize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private Serialize streamSerialize;
    private File directory;

    protected FileStorage(File directory, Serialize streamSerialize) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamSerialize = streamSerialize;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite())
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        this.directory = directory;
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> list = new ArrayList<>();
        for (File file : getFilesList()) {
            list.add(getResume(file));
        }
        return list;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return streamSerialize.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            streamSerialize.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected void addResume(Resume resume, File file) {
        try {
            file.createNewFile();
            updateResume(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public void clear() {
        for (File file : getFilesList()) {
            deleteResume(file);
        }
    }

    @Override
    public int size() {
        return getFilesList().length;
    }

    private File[] getFilesList() {
        if (directory.listFiles() == null) {
            throw new StorageException("Directory read error", (String) null);
        }
        return directory.listFiles();
    }
}

