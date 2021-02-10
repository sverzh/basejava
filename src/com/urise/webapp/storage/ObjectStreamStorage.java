package com.urise.webapp.storage;

import java.io.File;

public class ObjectStreamStorage extends FileStorage {

    public ObjectStreamStorage(File directory) {
        super(directory, new StreamSerialize());
    }
}
