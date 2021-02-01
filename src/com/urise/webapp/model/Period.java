package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

 class Period {
    private LocalDate beginDate;
    private LocalDate finishDate;
    private String title;
    private String description;

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

    public Period(LocalDate beginDate, LocalDate finishDate, String title, String description) {
        Objects.requireNonNull(beginDate, "beginDate must not be null");
        Objects.requireNonNull(finishDate, "finishDate must not be null");
        Objects.requireNonNull(title, "title must not be null");

        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.title = title;
        this.description = description;

    }
}
