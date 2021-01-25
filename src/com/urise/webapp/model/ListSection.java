package com.urise.webapp.model;

import java.util.ArrayList;

public class ListSection extends AbstractSection {
    private String title;
    private ArrayList<String> listSection = new ArrayList<>();

    public ListSection(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getListSection() {
        return listSection;
    }

    public void addToListSection(String string) {
        listSection.add(string);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (String string : listSection
        ) {
            builder.append(string);
        }
        return builder.toString();
    }
}
