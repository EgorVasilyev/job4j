<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
${activeUser=null}
<c:if test="${error!=''}">
    <div style="background-color: red" >
        <c:out value="${error}">
        </c:out>
    </div>
</c:if>
Enter login and password:<br/>
<form action='${pageContext.servletContext.contextPath}/signin' method='post'>
    Login: <input type='text' placeholder='input name' name='login' size='25'><br/>
    Password: <input type='password' placeholder='input password' name='password' size='25'><br/>
    <input type='submit' value='Enter'>
</form>
</body>
</html>
