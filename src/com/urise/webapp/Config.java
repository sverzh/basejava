package com.urise.webapp;


import com.urise.webapp.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final String PROPS = ("/resumes.properties");
    private static final Config INSTANCE = new Config();
    private Properties props = new Properties();
    private File storageDir;
    private SqlStorage sqlStorage;

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS);
        }
    }

    public static Config get() {
        return INSTANCE;
    }

    public SqlStorage getSqlStorage() {
        sqlStorage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        return sqlStorage;
    }

    public File getStorageDir() {
        return storageDir;
    }


}
