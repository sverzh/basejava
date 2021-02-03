package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    protected List<Period> periodList = new ArrayList<>();

    public Organization(String organization, String url, LocalDate beginDate, LocalDate finishDate, String title, String description) {
        Objects.requireNonNull(organization, "organization must not be null");
        homePage = new Link(organization, url);
        Period period = new Period(beginDate, finishDate, title, description);
        periodList.add(period);
    }

    public String getOrganization() {
        return homePage.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return periodList.equals(that.periodList) &&
                homePage.equals(that.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodList, homePage);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(homePage.getName() + "\n");
        for (Period a : periodList
        ) {
            stringBuilder.append(a.getBeginDate() + " - " + a.getFinishDate() + "\n"
                    + a.getTitle() + "\n" + a.getDescription() + "\n");
        }
        return stringBuilder.toString();
    }
}
