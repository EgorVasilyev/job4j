<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit user by id</title>
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
        <h3>Edit user:</h3>
    </div>
    <div class="container">
        <form class="form-horizontal" action='${pageContext.servletContext.contextPath}/edit' method='post'>
            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
            <div class="form-group">
                <label class="col-sm-2 control-label">ID:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${user.id}</p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Name:</label>
                <div class="col-sm-10">
                    <input class="form-control" type='text' placeholder='input name' id='name'
                           name='name' value=  ${user.name}>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Login:</label>
                <div class="col-sm-10">
                    <input class="form-control" type='text' placeholder='input login' id='login'
                           name='login' value=  ${user.login}>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Email:</label>
                <div class="col-sm-10">
                    <input class="form-control" type='text' placeholder='input email' id='email'
                           name='email' value=  ${user.email}>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Password:</label>
                <div class="col-sm-10">
                    <input class="form-control" type='password'
                           placeholder='input password' name='password' id='password' value= ${user.password}>
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
                            <option value="${country.id}"
                                    <c:if test="${user.countryId == country.id}">
                                        selected
                                    </c:if>>
                                    ${country.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">City:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="city" name="city">
                        <c:forEach items="${cities}" var="city">
                            <option value="${city.id}"
                                    <c:if test="${user.cityId == city.id}">
                                        selected
                                    </c:if>>
                                   ${city.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <h4 align="center">
                    <button onclick='return checkInput();' type="submit" class="btn-primary">
                        <span class="glyphicon glyphicon-check"></span> Edit
                    </button><br/>
                </h4>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${activeUser.role!='admin'}">
    <c:if test="${activeUser.id==user.id}">
        <div class="jumbotron text-center">
            <h3>Edit user:</h3>
        </div>
        <div class="container">
            <form class="form-horizontal" action='${pageContext.servletContext.contextPath}/edit' method='post'>
                <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                <div class="form-group">
                    <label class="col-sm-2 control-label">ID:</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" >${user.id}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Name:</label>
                    <div class="col-sm-10">
                        <input class="form-control" type='text' placeholder='input name' id='name'
                               name='name' value=  ${user.name}>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Login:</label>
                    <div class="col-sm-10">
                        <input class="form-control" type='text' placeholder='input login' id='login'
                               name='login' value=  ${user.login}>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Email:</label>
                    <div class="col-sm-10">
                        <input class="form-control" type='text' placeholder='input email' id='email'
                               name='email' value=  ${user.email}>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Password:</label>
                    <div class="col-sm-10">
                        <input class="form-control" type='password'
                               placeholder='input password' id='password'
                               name='password' value=  ${user.password}>
                    </div>
                </div>
                <input type='hidden' name='role' value=${user.role}>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Country:</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="country" name="country"
                                onchange="var t = this.options[this.selectedIndex]; fillCities(t.value);">
                            <option value=""></option>
                            <c:forEach items="${countries}" var="country">
                                <option value="${country.id}"
                                        <c:if test="${user.countryId == country.id}">
                                            selected
                                        </c:if>>
                                        ${country.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">City:</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="city" name="city">
                            <c:forEach items="${cities}" var="city">
                                <option value="${city.id}"
                                        <c:if test="${user.cityId == city.id}">
                                            selected
                                        </c:if>>
                                        ${city.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <h4 align="center">
                        <button onclick='return checkInput();' type="submit" class="btn-primary">
                            <span class="glyphicon glyphicon-check"></span> Edit
                        </button><br/>
                    </h4>
                </div>
            </form>
        </div>
    </c:if>
    <c:if test="${activeUser.id!=user.id}">
        <div class="jumbotron text-center">
            <h3>Oops!</h3>
        </div>
        <div align="center" class="alert alert-warning">
            Edit another users can only admin!
        </div>
    </c:if>
</c:if>
</body>
</html>
