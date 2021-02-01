package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    protected List<Period> periodList = new ArrayList<>();
    protected Period period;
    private String organization;


    public Organization(String organization, String url, LocalDate beginDate, LocalDate finishDate, String title, String description) {
        Objects.requireNonNull(organization, "organization must not be null");
        this.homePage = new Link(organization, url);
        this.organization = organization;
        period = new Period(beginDate, finishDate, title, description);
        periodList.add(period);
    }


    public String getOrganization() {
        return organization;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(organization + "\n");
        for (Period a : periodList
        ) {
            stringBuilder.append(a.getBeginDate() + " - " + a.getFinishDate() + "\n"
                    + a.getTitle() + "\n" + a.getDescription() + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!organization.equals(that.organization)) return false;
        return periodList.equals(that.periodList);
    }

    @Override
    public int hashCode() {
        int result = organization.hashCode();
        result = 31 * result + periodList.hashCode();
        return result;
    }
}
