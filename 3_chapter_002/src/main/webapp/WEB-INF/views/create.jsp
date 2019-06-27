<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
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
<h4 align="right">
    <blockquote>
        <form action='${pageContext.servletContext.contextPath}/users'>
            <h5>
                <button type="submit" class="btn-success">
                    <span class="glyphicon glyphicon-home"></span> Show list of users
                </button>
            </h5>
        </form>
        <form action='${pageContext.servletContext.contextPath}/signin'>
            <h5>
                <button type="submit" class="btn-info">
                    <span class="glyphicon glyphicon-log-out"></span> Exit
                </button>
            </h5>
        </form>
    </blockquote>
</h4>
<c:if test="${activeUser.role=='admin'}">
    <div class="jumbotron text-center">
        <h3>Create new user:</h3>
    </div>
    <div class="container">
        <form class="form-horizontal" action='${pageContext.servletContext.contextPath}/create' method='post'>
            <div class="form-group">
                <label class="col-sm-2 control-label">Name:</label>
                <div class="col-sm-10">
                    <input class="form-control" type='text' placeholder='input name' name='name' id='name'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Login:</label>
                <div class="col-sm-10">
                    <input class="form-control" type='text' placeholder='input login' name='login' id='login'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Email:</label>
                <div class="col-sm-10">
                    <input class="form-control" type='text' placeholder='input email' name='email' id='email'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Password:</label>
                <div class="col-sm-10">
                    <input class="form-control" type='password' placeholder='input password' name='password' id='password'>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Role:</label>
                <div class="col-sm-10">
                    <select class="form-control" name="role">
                        <option value="user">User</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Country:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="country" name="country"
                            onchange="var t = this.options[this.selectedIndex]; fillCities(t.value);">
                        <option value=""></option>
                        <c:forEach items="${countries}" var="country">
                            <option value="${country.id}">${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">City:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="city" name="city">
                            <%--there will be some options here from a response of an ajax query--%>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <h4 align="center">
                    <button onclick='return checkInput();' type="submit" class="btn-primary">
                        <span class="glyphicon glyphicon-check"></span> Create
                    </button><br/>
                </h4>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${activeUser.role!='admin'}">
    <div class="jumbotron text-center">
        <h3>Oops!</h3>
    </div>
    <div align="center" class="alert alert-warning">
        Create new users can only admin!
    </div>
</c:if>
</body>
</html>
