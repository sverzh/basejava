package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListStorageTest extends AbstractStorageTest{

    public ListStorageTest() { super(new ListStorage());
    }
}