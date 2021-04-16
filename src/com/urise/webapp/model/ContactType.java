package com.urise.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Скайп"){
        @Override
        public String toHtml0(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    EMAIL("E-Mail"){
        @Override
        public String toHtml0(String value) {
            return "<a href='" + value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverFlow"),
    HOMEPAGE("Домашнаяя страница");

    private String tittle;

    ContactType(String tittle) {
        this.tittle = tittle;
    }

    public String getTittle() {
        return tittle;
    }

    protected String toHtml0(String value) {
        return  tittle + ": "+value;
    }

    public String toHtml(String value) {
        return (value ==null ) ? "" : toHtml0(value);
    }
}
