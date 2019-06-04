<%@ page import="ru.job4j.servlets.datamodel.User" %>
<%@ page import="ru.job4j.servlets.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    List of available users:
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
    </tr>
    <%for (User userValue : ValidateService.getSingletonInstance().findAll().values()) {%>
    <tr>
        <td><%=userValue.getId()%></td>
        <td><%=userValue.getName()%></td>
        <td><%=userValue.getLogin()%></td>
        <td><%=userValue.getEmail()%></td>
        <td><form action='<%=request.getContextPath()%>/edit?id=<%=userValue.getId()%>' method='post'>
            <input type='submit' value ='Edit'></form></td>
        <td><form action='<%=request.getContextPath()%>/users' method='post'>
            <input type='hidden' name='action' value='delete'>
            <input type='hidden' name='id' value='<%=userValue.getId()%>'/>
            <input type='submit' value ='Delete'></form>
        </td>
    </tr>
    <% } %>
</table>
