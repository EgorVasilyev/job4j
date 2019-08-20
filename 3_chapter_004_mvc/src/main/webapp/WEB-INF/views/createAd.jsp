<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Create ad</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        <%@include file="/WEB-INF/js/functions.js"%>
    </script>
    <style>
        blockquote {
            background: url(https://www.axa.de/site/axa-de/get/params_E-35817624/8222246/geparkte-autos-in-reihe.jpg.pagespeed.ce.7CTHh14teL.jpg); /* Фоновый цвет и фоновый рисунок*/
            background-repeat: no-repeat;
            background-size: 100%;
            color: white;
        }
    </style>
</head>
<body>
<c:if test="${activeUser.role=='admin' || activeUser.role=='user'}">
    <blockquote>
        <h6 align="right">
            <form>
            <span class="glyphicon glyphicon-user"></span>&ensp;<sec:authentication property="principal.username" />
            , you are on the page of new ad creating
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
            <br/><br/><br/><br/><br/>
        </h6>
    </blockquote>
    <div class="container">
        <form class="form-horizontal" action='${pageContext.servletContext.contextPath}/ads/saveOrUpdate' method='post'
              enctype="multipart/form-data">
            <input type='hidden' name='action' value='save'/>
            <input type='hidden' name='id' value='${activeUser.id}'/>
            <div class="form-group">
                <label class="col-sm-2 control-label">Model:</label>
                <div class="col-sm-6">
                    <input class="form-control" type='text' placeholder='input model' name='model' id='model'>
                </div>
            </div>
            <div class="form-group">
                <td>
                    <label class="col-sm-2 control-label">Year of car:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="year" name="year" onchange="showAdditionalField(this)">
                            <option value=""></option>
                            <c:forEach items="${years}" var="year">
                                <option value="${year.value}">
                                        ${year.value}
                                </option>
                            </c:forEach>
                            <option value="other...">other...</option>
                        </select>
                    </div>
                </td>
                <td>
                    <div id="yearField" class="col-sm-2">
                    </div>
                </td>
            </div>
            <div class="form-group">
                <td>
                    <label class="col-sm-2 control-label">Color of car:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="color" name="color" onchange="showAdditionalField(this)">
                            <option value=""></option>
                            <c:forEach items="${colors}" var="color">
                                <option value="${color.name}">
                                        ${color.name}
                                </option>
                            </c:forEach>
                            <option value="other...">other...</option>
                        </select>
                    </div>
                </td>
                <td>
                    <div id="colorField" class="col-sm-2">
                    </div>
                </td>
            </div>
            <div class="form-group">
                <td>
                    <label class="col-sm-2 control-label">Engine of car:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="engine" name="engine" onchange="showAdditionalField(this)">
                            <option value=""></option>
                            <c:forEach items="${engines}" var="engine">
                                <option value="${engine.name}">
                                        ${engine.name}
                                </option>
                            </c:forEach>
                            <option value="other...">other...</option>
                        </select>
                    </div>
                </td>
                <td>
                    <div id="engineField" class="col-sm-2">
                    </div>
                </td>
            </div>
            <div class="form-group">
                <td>
                    <label class="col-sm-2 control-label">Body of car:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="body" name="body" onchange="showAdditionalField(this)">
                            <option value=""></option>
                            <c:forEach items="${bodies}" var="body">
                                <option value="${body.name}">
                                        ${body.name}
                                </option>
                            </c:forEach>
                            <option value="other...">other...</option>
                        </select>
                    </div>
                </td>
                <td>
                    <div id="bodyField" class="col-sm-2">

                    </div>
                </td>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">Description:</label>
                <div class="col-sm-6">
                    <textarea id="description" name="description"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Load new photo:</label>
                <div class="col-sm-6">
                    <input name="file" type="file">
                </div>
            </div>
            <div class="form-group">
                <h5 align="center">
                    <button onclick='return checkAd();' type="submit" class="btn-primary">
                        <span class="glyphicon glyphicon-check"></span> Create
                    </button><br/>
                </h5>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${activeUser.role=='guest'}">
    <div class="jumbotron text-center">
        <h2><strong>The guest can not create ad! Please, sign in or sing up.</strong></h2><br/>
        <blockquote>
            <h5>
                <br/><br/><br/><br/><br/>
                <form action='${pageContext.servletContext.contextPath}/logout' method="post">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button type="submit" class="btn-info">
                        <span class="glyphicon glyphicon-log-in"></span> Sign in / Sign up
                    </button>
                </form>
                <br/><br/><br/>><br/>
            </h5>
        </blockquote>
    </div>
</c:if>
</body>
</html>
