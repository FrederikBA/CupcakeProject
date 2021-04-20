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
        <nav class="navbar-brand">

            <a class="header-link">Ordrer</a>
            <a href="${pageContext.request.contextPath}/fc/admincustomer" class="header-link">Kunder</a>

            <a href="${pageContext.request.contextPath}/fc/shoppingcart">
                <i class="fas fa-shopping-cart right header-link shopping-cart"></i>
            </a>
            <c:if test="${sessionScope.user != null }">
                <p class="right ">${sessionScope.user.email}</p>
            </c:if>
            <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
            <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'loginpage.jsp')}"/>
            <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'registerpage.jsp')}"/>

            <c:if test="${isNotLoginPage && isNotRegisterPage}">
                <c:if test="${sessionScope.user != null }">
                    <a type="button" class="btn p-2 btn-outline-secondary right login-system"
                       href="${pageContext.request.contextPath}/fc/logoutcommand" style="margin-right: 10px;">Logout</a>
                </c:if>
                <c:if test="${sessionScope.user == null }">
                    <a type="button" class="btn btn-outline-secondary right login-system" style="margin-left: 10px;"
                       href="${pageContext.request.contextPath}/fc/loginpage">Login</a>
                    <a type="button" class="btn btn-outline-secondary right login-system"
                       href="${pageContext.request.contextPath}/fc/registerpage">Sign up</a>
                </c:if>
            </c:if>


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

</body>
</html>