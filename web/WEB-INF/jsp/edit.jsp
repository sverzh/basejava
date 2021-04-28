<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.TextSection" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sven
  Date: 13.04.2021
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.tittle}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <br>
        <h3>Секции:</h3>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>
            <h4>${type.tittle}</h4>
            <c:choose>
                <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
                    <textarea name="${type}" rows="5" cols="60"><%=((TextSection) section).getText()%></textarea>
                </c:when>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
            <textarea name="${type}" rows="5"
                      cols="60"><%=String.join("\n", ((ListSection) section).getListSection())%></textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="organizations" items="<%=((OrganizationSection) section).getOrganizations()%>">
                        <label> <strong>Организация:</strong></label>
                        <br>
                        <input type="hidden" name="typename" value="${type}">
                        <c:set var="organization" value="${organizations.organization}"/>
                        <input name="organization" type="text" value="${organization}" size="45">
                        <label> Url:</label>
                        <input name="organizationUrl" type="text" value="${organizations.homePage}" size="56">
                        <c:forEach var="period" items="${organizations.periodList}">
                            <table width="1033" border="1" align="left" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                    <td width="144">C:</td>
                                    <td width="144">По:</td>
                                    <td width="180">Должность</td>
                                    <td width="586">Описание</td>
                                </tr>
                                <tr>
                                    <td height="26"><input name="startDate" type="date" id="startDate"
                                                           value="${period.beginDate}"></td>
                                    <td><input name="finishdate" type="date" id="finishdate"
                                               value="${period.finishDate}"></td>
                                    <td><input name="position" type="text" value='${period.title}' size="30"></td>
                                    <td><textarea name="description" cols="90" rows="1">${period.description}</textarea>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <br>
                            <br>
                            <br>
                        </c:forEach>
                        <br>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
