<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show users</title>
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
<form action='${pageContext.servletContext.contextPath}/signin'>
    <h4 align="right">
        <blockquote>
            <span class="glyphicon glyphicon-user"></span>&ensp;Hello, <c:out value="${activeUser.name} ">
        </c:out>
            <h5>
                <button type="submit" class="btn-info">
                    <span class="glyphicon glyphicon-log-out"></span> Exit
                </button>
            </h5>
        </blockquote>
    </h4>
</form>
<div class="container">
    <h3><span class="glyphicon glyphicon-list"></span>&ensp;List of available users:</h3>
    <table class="table" id='table'>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Role</th>
            <th>Country</th>
            <th>City</th>

            <c:if test="${error!=''}">
                <div style="background-color: red" >
                    <c:out value="${error}">
                    </c:out>
                </div>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td>
                    <c:forEach items="${countries}" var="country">
                        <c:if test="${user.countryId == country.id}">
                            ${country.name}
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${cities}" var="city">
                        <c:if test="${user.cityId == city.id}">
                            ${city.name}
                        </c:if>
                    </c:forEach>
                </td>

                <c:if test="${activeUser.role=='admin'}">
                    <td><form action='${pageContext.servletContext.contextPath}/edit' method='post'>
                        <input type='hidden' name='id' value='${user.id}'/>
                        <button type="submit" class="btn-success">
                            <span class="glyphicon glyphicon-pencil"></span> Edit
                        </button>
                    </form></td>
                    <td><form action='${pageContext.servletContext.contextPath}/users' method='post'>
                        <input type='hidden' name='action' value='delete'>
                        <input type='hidden' name='id' value='${user.id}'/>
                        <button type="submit" class="btn-danger">
                            <span class="glyphicon glyphicon-remove"></span> Delete
                        </button>
                    </form>

                    </td>
                </c:if>

                <c:if test="${activeUser.role!='admin'}">
                    <c:if test="${activeUser.id==user.id}">
                        <td><form action='${pageContext.servletContext.contextPath}/edit' method='post'>
                            <input type='hidden' name='id' value='${user.id}'/>
                            <button type="submit" class="btn-success">
                                <span class="glyphicon glyphicon-pencil"></span> Edit
                            </button>
                        </form></td>
                        <td><form action='${pageContext.servletContext.contextPath}/users' method='post'>
                            <input type='hidden' name='action' value='delete'>
                            <input type='hidden' name='id' value='${user.id}'/>

                            <button type="submit" class="btn-danger">
                                <span class="glyphicon glyphicon-remove"></span> Delete
                            </button>
                        </form>
                        </td>
                    </c:if>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table><br/>
    <c:if test="${activeUser.role=='admin'}">
        <form action='${pageContext.servletContext.contextPath}/create'>
            <button type="submit" class="btn-primary">
                <span class="glyphicon glyphicon-plus-sign"></span> Create new user
            </button>
        </form>
    </c:if>
</div>
</body>
</html>
