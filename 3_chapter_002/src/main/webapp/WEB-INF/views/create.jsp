<%@ page import="ru.job4j.servlets.datamodel.User" %>
<%@ page import="ru.job4j.servlets.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<c:if test="${activeUser.role=='admin'}">
    Create new user:<br/>
    <form action='${pageContext.servletContext.contextPath}/' method='post'>
        Name: <input type='text' placeholder='input name' name='name'><br/>
        Login: <input type='text' placeholder='input login' name='login'><br/>
        Email: <input type='text' placeholder='input email' name='email'><br/>
        Password: <input type='password' placeholder='input password' name='password'><br/>
        Role:
        <select name="role">
            <option value="user">user</option>
            <option value="admin">admin</option>
        </select>
            <%--Role: <input type='text' placeholder='input role' name='role'><br/>--%>
        <input type='submit' value='Create'><br/>
    </form>
</c:if>
<c:if test="${activeUser.role!='admin'}">
    Create new users can only admin!
</c:if>
<form action='${pageContext.servletContext.contextPath}/users'>
    <input type='submit' value='Show list of users'>
</form>
<form action='${pageContext.servletContext.contextPath}/signin'>
    <input type='submit' value='Exit'>
</form>
</body>
</html>
