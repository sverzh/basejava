package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "mailto:gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        TextSection textSection1 = new TextSection("Позиция");
        textSection1.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        TextSection textSection2 = new TextSection("Личные качества");
        textSection2.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.addSection(SectionType.OBJECTIVE, textSection1);
        resume.addSection(SectionType.PERSONAL, textSection2);


        ListSection listSection1 = new ListSection("Достижения");
        listSection1.addToListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listSection1.addToListSection("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        resume.addSection(SectionType.ACHIEVEMENT, listSection1);

        ListOfPeriodSection listOfPeriodSection = new ListOfPeriodSection("Опыт работы");
        PeriodSection periodSection = new PeriodSection("Java Online Projects", LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        PeriodSection periodSection1 = new PeriodSection("Wrike", LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        listOfPeriodSection.addToListOfPeriodSection(periodSection);
        listOfPeriodSection.addToListOfPeriodSection(periodSection1);

        resume.addSection(SectionType.EXPERIENCE, listOfPeriodSection);

        //System.out.println(resume);

        System.out.println(resume.getFullName());
        for (Map.Entry<ContactType, String> entry : resume.getContactsMap().entrySet()
        ) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println();
        for (Map.Entry<SectionType, AbstractSection> entry : resume.getSectionMap().entrySet()
        ) {
            System.out.println(entry.getKey().getTittle());
            System.out.println();
            System.out.println(entry.getValue());
            System.out.println();
        }

    }
}
