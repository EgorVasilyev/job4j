<%@ page import="ru.job4j.servlets.datamodel.User" %>
<%@ page import="ru.job4j.servlets.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit user by id</title>
</head>
<body>
<c:if test="${activeUser.role=='admin'}">
    Edit user:<br/>
    <form action='${pageContext.servletContext.contextPath}/edit?id=${user.id}' method='post'>
        ID: <input type='text' name='id' value=  ${user.id}><br/>
        Name: <input type='text' name='name' value=${user.name}><br/>
        Login: <input type='text' name='login' value=${user.login}><br/>
        Email: <input type='text' name='email' value=${user.email}><br/>
        Password: <input type='password' name='password' value=${user.password}><br/>
        Role:
        <select name="role">
            <option value="user">user</option>
            <option value="admin">admin</option>
        </select>
        <input type='submit' value ='Edit'><br/>
    </form>
</c:if>
<c:if test="${activeUser.role!='admin'}">
    <c:if test="${activeUser.id==user.id}">
        Edit user:<br/>
        <form action='${pageContext.servletContext.contextPath}/edit?id=${user.id}' method='post'>
            ID: <input type='text' name='id' value=  ${user.id}><br/>
            Name: <input type='text' name='name' value=${user.name}><br/>
            Login: <input type='text' name='login' value=${user.login}><br/>
            Email: <input type='text' name='email' value=${user.email}><br/>
            Password: <input type='password' name='password' value=${user.password}><br/>
            <input type='hidden' name='role' value=${user.role}>
            <input type='submit' value ='Edit'>
        </form>
    </c:if>
    <c:if test="${activeUser.id!=user.id}">
        Edit another users can only admin!
    </c:if>
</c:if>
<form action='${pageContext.servletContext.contextPath}/users'>
    <input type='submit' value='Show list of users'>
</form>
<form action='${pageContext.servletContext.contextPath}/signin'>
    <input type='submit' value='Exit'>
</form>
</body>
</html>
