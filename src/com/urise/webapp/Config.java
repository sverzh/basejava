package com.urise.webapp;


import com.urise.webapp.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File(getHomeDir(),"config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private Properties props = new Properties();
    private File storageDir;
    private SqlStorage sqlStorage;

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
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
    private static File getHomeDir(){
         String prop = System.getProperty("homeDir");
         File homeDir = new File(prop == null ? "." : prop);
         if (!homeDir.isDirectory()){
             throw new IllegalStateException(homeDir + " is not dyrectory");
         }
         return homeDir;
    }
}
