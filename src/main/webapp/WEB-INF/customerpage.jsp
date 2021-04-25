<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Olsker Cupcakes
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        <h4>Du er nu logged ind som ${sessionScope.role}.</h4>
        <br>
        <br>
        <br>
        <div class="row">
            <div class="col-md"></div>
            <div class="col-md " style="text-align:center;">
                <a href="${pageContext.request.contextPath}/fc/index">
                    <button style="width:150px;" class="btn btn-secondary" type="submit" name="return">
                        Retur til Forsiden
                    </button>
                </a>
            </div>
            <div class="col-md"></div>
        </div>
    </jsp:body>

</t:genericpage>

