<%@ page import="ru.job4j.servlets.datamodel.User" %>
<%@ page import="ru.job4j.servlets.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<table>
    Create new user:
    <tr>
        <td><form action='<%=request.getContextPath()%>/create' method='post'>
            Name: <input type='text' placeholder='input name' name='name' size='25'>
            Login: <input type='text' placeholder='input login' name='login' size='25'>
            Email: <input type='text' placeholder='input email' name='email' size='25'>
            <input type='submit' value='Create'>
        </form></td>
    </tr>
</table>
</body>
</html>
