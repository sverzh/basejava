package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {

    private List<String> listSection = new ArrayList<>();

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
