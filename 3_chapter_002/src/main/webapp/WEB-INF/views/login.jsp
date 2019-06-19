<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        <%@include file="/WEB-INF/js/functions.js"%>
    </script>
</head>
<body>
${activeUser=null}
<c:if test="${error!=''}">
    <div style="background-color: red" >
        <c:out value="${error}">
        </c:out>
    </div>
</c:if>
<div class="jumbotron text-center">
    <h2><strong>Hello!</strong></h2><br/>
    <h4>Enter login and password:</h4>
</div>
<form class="form-horizontal" action='${pageContext.servletContext.contextPath}/signin' method='post'>
    <div class="form-group">
        <label class="col-sm-2 control-label">Login&ensp;<span class="glyphicon glyphicon-user"></span></label>
        <div class="col-sm-8">
            <input class="form-control" type='text' placeholder='input login' name='login' id="login"><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Password&ensp;<span class="glyphicon glyphicon-eye-close"></span></label>
        <div class="col-sm-8">
            <input class="form-control" type='password' placeholder='input password' name='password' id="password"><br/>
        </div>
    </div>
    <h5 align="center">
        <button onclick='return checkAuthentication();' type="submit" class="btn-primary">
            <span class="glyphicon glyphicon-log-in"></span> Enter
        </button><br/>
        <br/></h5>
</form>
</body>
</html>
