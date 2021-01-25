package com.urise.webapp.model;

import java.time.LocalDate;

public class PeriodSection {
    private String organization;
    private LocalDate beginDate;
    private LocalDate finishDate;
    private String title;
    private String description;

    public PeriodSection(String organization, LocalDate beginDate, LocalDate finishDate, String title, String description) {
        this.organization = organization;
        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return organization + '\n' + beginDate + " - " + finishDate + "\n" +
                title + '\n' + description + '\n';
    }

}
