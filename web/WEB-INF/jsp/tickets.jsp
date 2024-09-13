<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 4821016
  Date: 11.09.2024
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Tickets</title>
</head>
<body>
    <h1>Купленные билеты:</h1>э
    <ul>
        <c:forEach var="ticket" items="${requestScope.tickets}">
            <li>
                <p>${ticket.description}</p>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
