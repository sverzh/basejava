package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private SqlStorage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getSqlStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean responsed = false;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        boolean isAdd = (uuid.length() == 0);
        Resume r;
        if (isAdd) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            String[] organizations = request.getParameterValues("organization1");
            String addOrg = request.getParameter("addOrg");
            if (value != null && values.length >= 1 || organizations.length >= 1) {
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        r.addSection(type, new TextSection(String.join("\n", listFilter(value))));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = new ListSection(listFilter(value));
                        r.addSection(type, listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = new OrganizationSection();
                        String[] orgUrl = request.getParameterValues("orgUrl");
                        String[] startDate = request.getParameterValues("startDate");
                        String[] finishDate = request.getParameterValues("startDate");
                        String[] position = request.getParameterValues("position");
                        String[] description = request.getParameterValues("description");
                        String[] typename = request.getParameterValues("typename");
                        String[] orgOfPeriod = request.getParameterValues("orgOfPeriod");
                        String[] resultArray;
                        for (int i = 0; i < orgOfPeriod.length; i++) {
                            if (orgOfPeriod[i].equals("")) {
                                if (i < organizations.length - 1) {
                                    orgOfPeriod[i] = organizations[i];
                                } else orgOfPeriod[i] = organizations[organizations.length - 1];
                            }
                        }
                        if (organizations.length < orgOfPeriod.length) {
                            resultArray = orgOfPeriod.clone();
                        } else {
                            resultArray = organizations.clone();
                        }
                        for (int i = 0; i < resultArray.length; i++) {
                            Organization organization = new Organization(resultArray[i], orgUrl[i], LocalDate.parse(startDate[i]), LocalDate.parse(finishDate[i]), position[i], description[i]);
                            if (type.equals(SectionType.valueOf(typename[i]))) {
                                organizationSection.addOrganization(organization);
                            }
                        }
                        if (addOrg != null && type.equals(SectionType.valueOf(addOrg))) {
                            Organization emptyorganization = new Organization("", "http:\\\\", DateUtil.of(2000, Month.JANUARY), DateUtil.of(2000, Month.JANUARY), "", "");
                            organizationSection.addOrganization(emptyorganization);
                            responsed = true;
                        }
                        String addPos = request.getParameter("addPos");
                        if (addPos != null) {
                            String[] pos = addPos.split(" ");
                            if (pos[1] != null && type.equals(SectionType.valueOf(pos[0]))) {
                                Organization newPosition = new Organization(pos[1], "http:\\\\", DateUtil.of(2000, Month.JANUARY), DateUtil.of(2000, Month.JANUARY), "", "");
                                organizationSection.addOrganization(newPosition);
                                responsed = true;
                            }
                        }

                        r.addSection(type, organizationSection);
                        break;
                }
            } else {
                r.getSections().remove(type);
            }
        }
        if (isAdd) {
            if (fullName.length() != 0) {
                storage.save(r);
            }
        } else {
            storage.update(r);
        }
        if (!responsed) {
            response.sendRedirect("resume");
        } else response.sendRedirect("resume?uuid=" + r.getUuid() + "&action=edit");
    }

    private List<String> listFilter(String value) {
        String[] array = value.split("\\n");
        List<String> list = new LinkedList<>();
        for (String str : array) {
            if (!str.equals("\r")) {
                list.add(str);
            }
        }
        return list;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            case "add":
                Resume empty = new Resume("", "");
                for (SectionType type : SectionType.values()) {
                    switch (type) {
                        case PERSONAL:
                        case OBJECTIVE:
                            empty.addSection(type, new TextSection(""));
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            ListSection section = new ListSection();
                            section.addToListSection("");
                            empty.addSection(type, section);
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            OrganizationSection emptyOrganizationSection = new OrganizationSection();
                            Organization organization = new Organization("", "http:\\\\", DateUtil.of(2000, Month.JANUARY), DateUtil.of(2000, Month.JANUARY), "", "");
                            emptyOrganizationSection.addOrganization(organization);
                            empty.addSection(type, emptyOrganizationSection);
                            break;
                    }
                }
                r = empty;
                break;
            default:
                throw new IllegalArgumentException("Action " + action + "is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
                .forward(request, response);
    }
}
