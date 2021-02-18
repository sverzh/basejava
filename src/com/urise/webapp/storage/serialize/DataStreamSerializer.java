package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serialize {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                if (entry.getValue() instanceof TextSection) {
                    dos.writeUTF("TextSection");
                    dos.writeUTF(((TextSection) entry.getValue()).getText());
                }
                if (entry.getValue() instanceof ListSection) {
                    dos.writeUTF("ListSection");
                    List<String> list = ((ListSection) entry.getValue()).getListSection();
                    dos.writeInt(list.size());
                    for (String str : list) {
                        dos.writeUTF(str);
                    }
                }
                if (entry.getValue() instanceof OrganizationSection) {
                    dos.writeUTF("OrganizationSection");
                    List<Organization> organizationslist = ((OrganizationSection) entry.getValue()).getOrganizations();
                    dos.writeInt(organizationslist.size());
                    for (Organization org : organizationslist) {
                        dos.writeUTF(org.getOrganization());
                        dos.writeUTF(org.getHomePage());
                        List<Organization.Period> periods = org.getPeriodList();
                        dos.writeInt(periods.size());
                        for (Organization.Period per : periods) {
                            dos.writeUTF(per.getBeginDate().toString());
                            dos.writeUTF(per.getFinishDate().toString());
                            dos.writeUTF(per.getTitle());
                            dos.writeUTF(per.getDescription());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                String section = dis.readUTF();
                String str = dis.readUTF();
                if (str.equals("TextSection")) {
                    TextSection textSection = new TextSection();
                    textSection.setText(dis.readUTF());
                    resume.addSection(SectionType.valueOf(section), textSection);
                }
                if (str.equals(("ListSection"))) {
                    int size1 = dis.readInt();
                    ListSection listSection = new ListSection();
                    for (int j = 0; j < size1; j++) {
                        listSection.addToListSection(dis.readUTF());
                    }
                    resume.addSection(SectionType.valueOf(section), listSection);
                }
                if (str.equals(("OrganizationSection"))) {
                    int size1 = dis.readInt();
                    System.out.println(size1);
                    OrganizationSection organizationSection = new OrganizationSection();
                    for (int j = 0; j < size1; j++) {
                        String organizationName = dis.readUTF();
                        String url = dis.readUTF();
                        int periodSize = dis.readInt();
                        for (int k = 0; k < periodSize; k++) {
                            Organization organization = new Organization(organizationName, url, LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF());
                            organizationSection.addOrganization(organization);
                        }
                    }
                    resume.addSection(SectionType.valueOf(section), organizationSection);
                }
            }
            return resume;
        }
    }
}
