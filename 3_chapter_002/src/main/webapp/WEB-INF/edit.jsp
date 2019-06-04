<%@ page import="ru.job4j.servlets.datamodel.User" %>
<%@ page import="ru.job4j.servlets.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user by id</title>
</head>
<body>
<table>
    Edit user:
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        User user = ValidateService.getSingletonInstance().findById(id);
    %>
    <tr>
        <td><form action='<%=request.getContextPath()%>/edit?id=<%=user.getId()%>' method='post'>
            ID: <input type='text' name='id' value=<%=user.getId()%> size='25'>
            Name: <input type='text' name='name' value=<%=user.getName()%> size='25'>
            Login: <input type='text' name='login' value=<%=user.getLogin()%> size='25'>
            Email: <input type='text' name='email' value=<%=user.getEmail()%> size='25'>
            <input type='submit' value ='Edit'>
        </form></td>
    </tr>
</table>