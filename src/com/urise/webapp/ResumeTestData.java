package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;

public class ResumeTestData {

    public Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.EMAIL, "mailto:gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        TextSection textSection1 = new TextSection();
        textSection1.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        TextSection textSection2 = new TextSection();
        textSection2.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSection(SectionType.OBJECTIVE, textSection1);
        resume.setSection(SectionType.PERSONAL, textSection2);

        ListSection listSection1 = new ListSection();
        listSection1.addToListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listSection1.addToListSection("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        ListSection listSection2 = new ListSection();
        listSection2.addToListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listSection2.addToListSection("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        resume.setSection(SectionType.ACHIEVEMENT, listSection1);
        resume.setSection(SectionType.QUALIFICATIONS, listSection2);


        OrganizationSection organizationSection = new OrganizationSection();
        Organization organization1 = new Organization("Java Online Projects", "url", DateUtil.of(2013, Month.OCTOBER), LocalDate.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization organization2 = new Organization("Java Online Projects", "url", DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2016, Month.JANUARY), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization organization3 = new Organization("RIT Center", "url", DateUtil.of(2012, Month.APRIL), DateUtil.of(2012, Month.MARCH), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Organization organization4 = new Organization("Java Online Projects", "url", DateUtil.of(2010, Month.OCTOBER), DateUtil.of(2011, Month.JANUARY), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization organization5 = new Organization("RIT Center", "url", DateUtil.of(2012, Month.APRIL), DateUtil.of(2012, Month.MARCH), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");

        organizationSection.addOrganization(organization1);
        organizationSection.addOrganization(organization2);
        organizationSection.addOrganization(organization3);
        organizationSection.addOrganization(organization4);
        organizationSection.addOrganization(organization5);

        resume.setSection(SectionType.EXPERIENCE, organizationSection);

        OrganizationSection study = new OrganizationSection();
        Organization organization6 = new Organization("Coursera", "url", DateUtil.of(2013, Month.MARCH), DateUtil.of(2013, Month.MAY), "\"Functional Programming Principles in Scala\" by Martin Odersky", null);
        study.addOrganization(organization6);
        resume.setSection(SectionType.EDUCATION, study);
        return resume;
    }
}
