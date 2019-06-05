<%@ page import="ru.job4j.servlets.datamodel.User" %>
<%@ page import="ru.job4j.servlets.logic.ValidateService" %>
<%@ page import="java.util.concurrent.ConcurrentHashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show users</title>
</head>
<body>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    Hello, <c:out value="${activeUser.name}"/><br/>
    List of available users:
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Role</th>

        <c:if test="${error!=''}">
            <div style="background-color: red" >
                <c:out value="${error}">
                </c:out>
            </div>
        </c:if>

    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.role}"/></td>

            <c:if test="${activeUser.role=='admin'}">
                <td><form action='${pageContext.servletContext.contextPath}/edit?id=${user.id}' method='post'>
                    <input type='submit' value ='Edit'></form></td>
                <td><form action='${pageContext.servletContext.contextPath}/users' method='post'>
                    <input type='hidden' name='action' value='delete'>
                    <input type='hidden' name='id' value='${user.id}'/>
                    <input type='submit' value ='Delete'></form>
                </td>
            </c:if>

            <c:if test="${activeUser.role!='admin'}">
                <c:if test="${activeUser.id==user.id}">
                    <td><form action='${pageContext.servletContext.contextPath}/edit?id=${user.id}' method='post'>
                        <input type='submit' value ='Edit'></form></td>
                    <td><form action='${pageContext.servletContext.contextPath}/users' method='post'>
                        <input type='hidden' name='action' value='delete'>
                        <input type='hidden' name='id' value='${user.id}'/>
                        <input type='submit' value ='Delete'></form>
                    </td>
                </c:if>
            </c:if>
        </tr>
    </c:forEach>
</table><br/>
<c:if test="${activeUser.role=='admin'}">
    <form action='${pageContext.servletContext.contextPath}/create'>
        <input type='submit' value='Create new user'>
    </form>
</c:if>
<form action='${pageContext.servletContext.contextPath}/signin'>
    <input type='submit' value='Exit'>
</form>
</body>
</html>
