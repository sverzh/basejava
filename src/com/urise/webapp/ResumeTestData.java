package com.urise.webapp;

import com.urise.webapp.Util.DateUtil;
import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.Month;

public class ResumeTestData {

    public static void main(String[] args) {
    }

    public Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "mailto:gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        TextSection textSection1 = new TextSection();
        textSection1.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        TextSection textSection2 = new TextSection();
        textSection2.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.addSection(SectionType.OBJECTIVE, textSection1);
        resume.addSection(SectionType.PERSONAL, textSection2);

        ListSection listSection1 = new ListSection();
        listSection1.addToListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listSection1.addToListSection("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        resume.addSection(SectionType.ACHIEVEMENT, listSection1);

        OrganizationSection organization = new OrganizationSection();
        Organization organization1 = new Organization("Java Online Projects", "url", DateUtil.of(2013, Month.OCTOBER), LocalDate.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization organization2 = new Organization("Java Online Projects", "url", DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2016, Month.JANUARY), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization organization3 = new Organization("RIT Center", "url", DateUtil.of(2012, Month.APRIL), DateUtil.of(2012, Month.MARCH), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Organization organization4 = new Organization("Java Online Projects", "url", DateUtil.of(2010, Month.OCTOBER), DateUtil.of(2011, Month.JANUARY), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization organization5 = new Organization("RIT Center", "url", DateUtil.of(2012, Month.APRIL), DateUtil.of(2012, Month.MARCH), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");

        organization.addOrganization(organization1);
        organization.addOrganization(organization2);
        organization.addOrganization(organization3);
        organization.addOrganization(organization4);
        organization.addOrganization(organization5);

        resume.addSection(SectionType.EXPERIENCE, organization);

        OrganizationSection study = new OrganizationSection();
        Organization organization6 = new Organization("Coursera", "url", DateUtil.of(2013, Month.MARCH), DateUtil.of(2013, Month.MAY), "", "\"Functional Programming Principles in Scala\" by Martin Odersky");
        study.addOrganization(organization6);
        resume.addSection(SectionType.EDUCATION, study);
        return resume;
    }

//        System.out.println(resume.getFullName());
//        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()
//        ) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
//        System.out.println();
//        for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()
//        ) {
//            System.out.println(entry.getKey().getTittle());
//            System.out.println();
//            System.out.println(entry.getValue());
//            System.out.println();
//        }
}
