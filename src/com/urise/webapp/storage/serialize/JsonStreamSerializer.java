package com.urise.webapp.storage.serialize;


import com.urise.webapp.util.JsonParser;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamSerializer implements Serialize {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        {
            try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
                JsonParser.write(resume, w);
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.read(r, Resume.class);
        }
    }
}
