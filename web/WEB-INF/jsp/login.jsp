<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 4821016
  Date: 12.09.2024
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="email"> Email
            <input type="text" name="email" id="email">
        </label><br>
        <label for="password"> Password
            <input type="password" name="password" id="password">
        </label>
        <button type="submit">Login</button>
        <a href="${pageContext.request.contextPath}/registration">
            <button type="button">Don't have an account?</button>
        </a>
        <c:if test="${param.error != null}">
            <div style="color: red">
                <span>Email or password is not correct</span>
            </div>
        </c:if>
    </form>
</body>
</html>
