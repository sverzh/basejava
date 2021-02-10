package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final static long serialVersionUID = 1L;
    private List<String> listSection = new ArrayList<>();

    public void addToListSection(String string) {
        listSection.add(string);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return listSection.equals(that.listSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listSection);
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
