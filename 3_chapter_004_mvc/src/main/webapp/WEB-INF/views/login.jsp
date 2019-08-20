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
<div class="jumbotron text-center">
    <strong>
        <c:if test="${param.msg != null}">
        <p style='color:red'>
                ${param.msg}
        </p>
    </c:if>
    <c:if test="${param.error != null}">
        <p style='color:red'>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p style='color:blue'>
            You have been logged out.
        </p>
    </c:if>
    </strong>
    <h2><strong>Welcome to car sale platform!</strong></h2><br/>
    <h4>Choose your way:</h4>
</div>
<div class="container">
    <table width="100%" align="center">
        <thead>
        <tr>
            <td width="33%" align="center">
                <form>
                    <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#singIn">
                        <span class="glyphicon glyphicon-log-in"></span> Sign in, if you are registered</button>
                </form>
            </td>
            <td width="33%" align="center">
                <form>
                    <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#singUp">
                        <span class="glyphicon glyphicon-new-window"></span> Sign up, if you are not registered</button>
                </form>
            </td>
            <td width="33%" align="center">
                <form action='${pageContext.servletContext.contextPath}/login' method='post'>
                    <input type='hidden' name='myLogin' value="guest">
                    <input type='hidden' name='myPassword' value="guest">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-info">
                        <span class="glyphicon glyphicon-user"></span> Visiting the site as a guest
                    </button>
                </form>
            </td>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td align="center">
                <form id="singIn" class="collapse" action='${pageContext.servletContext.contextPath}/login' method='post'>
                    <div class="form-group">
                        Login&ensp;<span class="glyphicon glyphicon-user"></span>
                        <input class="form-control" type='text' placeholder='input login' name='myLogin' id="myLogin"><br/>
                    </div>
                    <div class="form-group">
                        Password&ensp;<span class="glyphicon glyphicon-eye-close"></span>
                        <input class="form-control" type='password' placeholder='input password' name='myPassword' id="myPassword"><br/>
                    </div>
                    <div class="form-group" align="center">
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary" onclick='return checkAuthentication();' >
                            <span class="glyphicon glyphicon-log-in"></span> Enter
                        </button>
                    </div>
                </form>
            </td>
            <td align="center">
                <form id="singUp" class="collapse"
                      action='${pageContext.servletContext.contextPath}/registration' method='post'>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div class="form-group">
                        Login&ensp;<span class="glyphicon glyphicon-user"></span>
                        <input class="form-control" type='text' placeholder='input login' name='login' id="login"><br/>
                    </div>
                    <div class="form-group">
                        Password&ensp;<span class="glyphicon glyphicon-eye-close"></span>
                        <input class="form-control" type='password' placeholder='input password' name='password' id="password"><br/>
                    </div>
                    <div class="form-group">
                        Phone&ensp;<span class="glyphicon glyphicon-earphone"></span>
                        <input class="form-control" type='text' placeholder='input phone' name='phone' id="phone"><br/>
                    </div>
                    <div class="form-group" align="center">
                        <button type="submit" class="btn btn-success" onclick='return checkNewUser();' >
                            <span class="glyphicon glyphicon-new-window"></span> Create
                        </button>
                    </div>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
