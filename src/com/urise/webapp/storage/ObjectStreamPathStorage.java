package com.urise.webapp.storage;

public class ObjectStreamPathStorage extends PathStorage {

    public ObjectStreamPathStorage(String directory) {
        super(directory, new StreamSerialize());
    }
}
