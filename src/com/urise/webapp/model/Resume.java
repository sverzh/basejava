package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;
    private EnumMap<ContactType, String> contactsMap = new EnumMap<>(ContactType.class);
    private EnumMap<SectionType, AbstractSection> sectionMap = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void addContact(ContactType type, String contact) {
        contactsMap.put(type, contact);
    }

    public void addSection(SectionType type, AbstractSection section) {
        sectionMap.put(type, section);
    }

    public EnumMap<ContactType, String> getContactsMap() {
        return contactsMap;
    }

    public EnumMap<SectionType, AbstractSection> getSectionMap() {
        return sectionMap;
    }

//    @Override
//    public String toString() {
//        StringBuilder fullResume = new StringBuilder();
//        fullResume.append(fullName+"\n");
//        for (Map.Entry<ContactType,String> entry: contactsMap.entrySet()){
//            fullResume.append(entry.getKey()+ " - "+ entry.getValue()+"\n");
//        }
//        fullResume.append("\n");
//        for (Map.Entry<SectionType,AbstractSection> entry: sectionMap.entrySet()
//             ) {
//            fullResume.append(entry.getKey().getTittle()+"\n");
//            fullResume.append(entry.getValue()+"\n");
//        }
//
//        return fullResume.toString();
//    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public int compareTo(Resume r) {
        int i = fullName.compareTo(r.getFullName());
        return i == 0 ? uuid.compareTo(r.uuid) : i;
    }
}
