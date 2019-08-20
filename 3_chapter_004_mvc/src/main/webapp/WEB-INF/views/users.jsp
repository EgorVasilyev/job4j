<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><%@ taglib prefix="sec"
                 uri="http://www.springframework.org/security/tags"%>
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
<blockquote>
    <h6 align="right">
        <c:if test="${activeUser.role=='admin'}">
            <form>
                <span class="glyphicon glyphicon-user"></span>&ensp;Hello, <sec:authentication property="principal.username" />
            </form>
            <form action='${pageContext.servletContext.contextPath}/users/updateUser' method="post">
                <input type='hidden' name='id' value='${activeUser.id}'/>
                <input type='hidden' name='login' value='${activeUser.login}'/>
                <input type='hidden' name='password' value='${activeUser.password}'/>
                <input type='hidden' name='phone' value='${activeUser.phone}'/>
                <input type='hidden' name='role' value='${activeUser.role}'/>
                <button type="submit" class="btn-primary">
                    <span class="glyphicon glyphicon-edit"></span> Edit my profile
                </button>
            </form>
            <form action='${pageContext.servletContext.contextPath}/ads/create' method="get">
                <input type='hidden' name='id' value='${activeUser.id}'/>
                <button type="submit" class="btn-primary">
                    <span class="glyphicon glyphicon-plus-sign"></span> Create new ad
                </button>
            </form>
            <form action='${pageContext.servletContext.contextPath}/ads/userAds' method="get">
                <input type='hidden' name='id' value='${activeUser.id}'/>
                <button type="submit" class="btn-info">
                    <span class="glyphicon glyphicon-list"></span> My ads
                </button>
            </form>
            <form action='${pageContext.servletContext.contextPath}/ads/show' method="get">
                <button type="submit" class="btn-info">
                    <span class="glyphicon glyphicon-list"></span> Show all ads
                </button>
            </form>
            <form action='${pageContext.servletContext.contextPath}/logout' method="post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button type="submit" class="btn-danger">
                    <span class="glyphicon glyphicon-log-out"></span> Exit
                </button>
            </form>
        </c:if>
    </h6>
</blockquote>

<c:if test="${activeUser.role!='admin'}">
    <div class="jumbotron text-center">
        <h2><strong>Only admin can see all users!</strong></h2><br/>
        <blockquote>
            <h5>
                <form action='${pageContext.servletContext.contextPath}/ads/show' method="get">
                    <button type="submit" class="btn-info">
                        <span class="glyphicon glyphicon-list"></span> Show all ads
                    </button>
                </form>
                <form action='${pageContext.servletContext.contextPath}/logout' method="post">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button type="submit" class="btn-danger">
                        <c:if test="${activeUser.role=='user'}">
                            <span class="glyphicon glyphicon-log-out"></span> Exit
                        </c:if>
                        <c:if test="${activeUser.role=='guest'}">
                            <span class="glyphicon glyphicon-log-in"></span> Sign in / Sign up
                        </c:if>
                    </button>
                </form>
            </h5>
        </blockquote>
    </div>
</c:if>
<div class="container">
    <c:if test="${activeUser.role=='admin'}">
        <h3><span class="glyphicon glyphicon-list"></span>&ensp;List of users:</h3>
        <table class="table" id='table'>
            <thead>
            <tr>
                <td width="10%">ID</td>
                <td width="20%">Login</td>
                <td width="20%">Phone</td>
                <td width="20%">Role</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.phone}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <c:if test="${user.login!='admin' && user.login!='guest'}">
                        <td><form action='${pageContext.servletContext.contextPath}/users/updateUser' method='post'>
                            <input type='hidden' name='id' value='${user.id}'/>
                            <input type='hidden' name='login' value='${user.login}'/>
                            <input type='hidden' name='password' value='${user.password}'/>
                            <input type='hidden' name='phone' value='${user.phone}'/>
                            <input type='hidden' name='role' value='${user.role}'/>
                            <button type="submit" class="btn-success">
                                <span class="glyphicon glyphicon-pencil"></span> Edit
                            </button>
                        </form></td>
                        <td><form action='${pageContext.servletContext.contextPath}/users/delete' method='post'>
                            <input type='hidden' name='action' value='deleteFromAll'>
                            <input type='hidden' name='id' value='${user.id}'/>
                            <button type="submit" class="btn-danger">
                                <span class="glyphicon glyphicon-remove"></span> Delete user and all his ads
                            </button>
                        </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table><br/>
    </c:if>
</div>
</body>
</html>
