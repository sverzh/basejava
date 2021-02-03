package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

class Period {
    private LocalDate beginDate;
    private LocalDate finishDate;
    private String title;
    private String description;

    public Period(LocalDate beginDate, LocalDate finishDate, String title, String description) {
        Objects.requireNonNull(beginDate, "beginDate must not be null");
        Objects.requireNonNull(finishDate, "finishDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.title = title;
        this.description = description;
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
