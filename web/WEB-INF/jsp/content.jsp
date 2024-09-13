<%--
  Created by IntelliJ IDEA.
  User: 4821016
  Date: 11.09.2024
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Content</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <h2>Content. Привет я страница Контента</h2>
    <%--<p>Size: ${requestScope.flights.size()}</p>
    <p>Size: ${requestScope.flights[1].id}</p>--%>
    <p>JssessionId: ${cookie["JSESSIONID"]}</p>
    <p>Header: ${header["Cookie"]}</p>
    <p>param id: ${param.id}</p>
    <p>param test: ${param.test}</p>
    <%@ include file="Footer.jsp"%>
</body>
</html>
