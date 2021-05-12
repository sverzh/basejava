package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    public static final Organization EMPTY = new Organization("", "", Period.EMPTY);
    private final static long serialVersionUID = 1L;
    protected List<Period> periodList = new ArrayList<>();
    private Link homePage;

    public Organization() {
    }

    public Organization(String name, String url, Period... periods) {
        this(new Link(name, url), Arrays.asList(periods));
    }

    public Organization(Link homePage, List<Period> periods) {
        this.homePage = homePage;
        this.periodList = periods;
    }

    public Organization(String organization, String url, LocalDate beginDate, LocalDate finishDate, String title, String description) {
        Objects.requireNonNull(organization, "organization must not be null");
        homePage = new Link(organization, url);
        Period period = new Period(beginDate, finishDate, title, description);
        periodList.add(period);
    }

    public Link getHomePage() {
        return homePage;
    }

    public String getOrganization() {
        return homePage.getName();
    }

    public List<Period> getPeriodList() {
        return periodList;
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
        for (Period a : periodList) {
            stringBuilder.append(a.getBeginDate() + " - " + a.getFinishDate() + "\n"
                    + a.getTitle() + "\n" + a.getDescription() + "\n");
        }
        return stringBuilder.toString();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        public static final Period EMPTY = new Period();
        private final static long serialVersionUID = 1L;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate beginDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finishDate;
        private String title;
        private String description;

        public Period() {
        }

        public Period(LocalDate beginDate, LocalDate finishDate, String title, String description) {
            Objects.requireNonNull(beginDate, "beginDate must not be null");
            Objects.requireNonNull(finishDate, "finishDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.beginDate = beginDate;
            this.finishDate = finishDate;
            this.title = title;
            this.description = description == null ? "" : description;
        }

        public LocalDate getBeginDate() {
            return beginDate;
        }

        public LocalDate getFinishDate() {
            return finishDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return beginDate.equals(period.beginDate) &&
                    finishDate.equals(period.finishDate) &&
                    title.equals(period.title) &&
                    Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(beginDate, finishDate, title, description);
        }
    }
}
