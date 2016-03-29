<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
    <title>AT Consulting Report</title>
</head>
<body>
<div class="container">
    <form:form id="task" method="get" action="task">
        <img width=50% src="<c:url value='/resources/logo.jpeg'/>" alt="AT CONSULTING">
    </form:form>
    <div class="header">
        <h1> Шаблоны </h1>
    </div>
    <table>
        <thead>
        <tr>
            <th>Имя шаблона</th>
            <th>Последняя модификация</th>
            <th>Размер,байт</th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="template" items="${templateList}">
            <tr class="content">
                <form:form id="formInfo" method="get" action="${template.name}">
                    <th id="name">
                            ${template.name}
                    </th>
                    <th id="lastModifiedDate">
                            ${template.lastModifiedDate}
                    </th>
                    <th id="length">
                            ${template.length}
                    </th>
                    <th>
                        <button id="buttonTemplateSave" type="submit" class="btn-success">Export</button>
                    </th>
                </form:form>
            </tr>
        </c:forEach>
    </table>

    <div class="footer">
        <p>Made by Tatiana Marchuk</p>
    </div>
</div>
</body>
</html>