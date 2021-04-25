<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <script src="https://kit.fontawesome.com/c5d38df5c3.js" crossorigin="anonymous"></script>
    <meta name="theme-color" content="#7952b3">
</head>
<body>
<!--
    This header is inspired by this bootstrap
    example: https://getbootstrap.com/docs/5.0/examples/pricing/
-->
<header>
    <div class="container banner-container mt-3"
         style="background-image:url(${pageContext.request.contextPath}/img/olskercupcakes.png);">
    </div>
    <div class="container pb-2 mb-2 mt-2 bg-lightgrey">
        <!--  <nav class="navbar-brand navbar-expand-lg"> -->
        <nav class="navbar navbar-expand-lg navbar-light">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active" style="padding-right:30px;">
                        <a class="nav-link" style="margin-top:10px; font-size:14pt;"
                           href="${pageContext.request.contextPath}/fc/index">Hjem</a>
                    </li>
                    <c:if test="${sessionScope.user.role.equals('employee')}">
                        <li class="nav-item active" style="padding-right:30px;">
                            <a href="${pageContext.request.contextPath}/fc/admincustomer"
                               class="nav-link" style="margin-top:10px; font-size:14pt;" ;>Kunder</a>
                        </li>
                        <li class="nav-item active" style="padding-right:30px;">
                            <a href="${pageContext.request.contextPath}/fc/adminorder"
                               class="nav-link" style="margin-top:10px; font-size:14pt;">Ordrer</a>
                        </li>
                    </c:if>
                    <li class="nav-item active" style="padding-right:30px;">
                        <a href="${pageContext.request.contextPath}/fc/shoppingcart">
                            <i class="fas fa-shopping-cart nav-link px-2"
                               style="scale:150%; margin-top:18px; ">&nbsp;</i>
                        </a>
                    </li>
                </ul>
                <div class="ms-auto">
                    <c:if test="${sessionScope.user != null }">
                        <p class="right px-2"
                           style="margin-top:16px; font-size:14pt; opacity:65%; font-weight:bold;">${sessionScope.user.email}</p>
                    </c:if>
                    <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
                    <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'loginpage.jsp')}"/>
                    <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'registerpage.jsp')}"/>

                    <c:if test="${isNotLoginPage && isNotRegisterPage}">
                        <c:if test="${sessionScope.user != null }">
                            <a type="button" class="btn p-2 btn-outline-secondary px-2 right"
                               href="${pageContext.request.contextPath}/fc/logoutcommand"
                               style="margin-top: 10px;">Logout</a>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <a type="button" class="btn btn-outline-secondary mx-2 right"
                               style="margin-top: 10px;"
                               href="${pageContext.request.contextPath}/fc/loginpage">Login</a>
                            <a type="button" class="btn btn-outline-secondary mx-2 right"
                               style="margin-top: 10px" ;
                               href="${pageContext.request.contextPath}/fc/registerpage">Sign up</a>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </nav>
    </div>
</header>


<div id="body" class="container bg-lightgrey" style="min-height: 20vh;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container">
    <br>
    <hr>
    <br>
    <jsp:invoke fragment="footer"/>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>