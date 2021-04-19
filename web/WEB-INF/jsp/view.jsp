<%@ page import="java.util.List" %>
<%@ page import="com.urise.webapp.model.*" %>
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
<h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a> </h2>
    <p>
    <c:forEach var="contactEntry" items="${resume.contacts}">
        <jsp:useBean id="contactEntry" type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
        <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <br>
<c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry" type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.AbstractSection>"/>
    <h3><%=sectionEntry.getKey().getTittle()%></h3>
    <c:set var="type" value="${sectionEntry.key}"/>
    <c:set var="section" value="${sectionEntry.value}"/>
    <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>

    <c:choose>
        <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
            <%=((TextSection) section).getText()%>
        </c:when>
        <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
           <%=String.join("\n",((ListSection) section).getListSection())%>
        </c:when>
        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
        <%
           List<Organization> organizationList = ((OrganizationSection) sectionEntry.getValue()).getOrganizations();
           request.setAttribute("orgList", organizationList);
        %>
        <c:forEach var="org" items="${orgList}">
    <h4><a href="${org.homePage}">${org.organization}</a></h4>
            <c:forEach var="period" items="${org.periodList}">
                <p>${period.beginDate} - ${period.finishDate}</p>
                <strong>${period.title}</strong>
                <p>${period.description}</p>
            </c:forEach>
        </c:forEach>

        </c:when>

    </c:choose>
    <br>
    </c:forEach>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
