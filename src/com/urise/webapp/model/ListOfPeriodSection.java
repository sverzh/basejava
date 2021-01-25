package com.urise.webapp.model;

import java.util.ArrayList;

public class ListOfPeriodSection extends AbstractSection {
    private String title;
    private ArrayList<PeriodSection> listOfPeriod = new ArrayList<>();

    public ListOfPeriodSection(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<PeriodSection> getListOfPeriod() {
        return listOfPeriod;
    }

    public void addToListOfPeriodSection(PeriodSection periodSection) {
        listOfPeriod.add(periodSection);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (PeriodSection periodSection : listOfPeriod
        ) {
            builder.append(periodSection + "\n");
        }
        return builder.toString();
    }
}
