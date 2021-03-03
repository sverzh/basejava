package com.urise.webapp.storage.serialize;

import com.urise.webapp.util.XmlParser;
import com.urise.webapp.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class XmlStreamSerializer implements Serialize {
    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(
                Resume.class, Organization.class, Link.class, OrganizationSection.class,
                TextSection.class, ListSection.class, Organization.Period.class
        );
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        {
            try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
                xmlParser.marshall(resume, w);
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
