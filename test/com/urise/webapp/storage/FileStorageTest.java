package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.StreamSerialize;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new StreamSerialize()));
    }
}
