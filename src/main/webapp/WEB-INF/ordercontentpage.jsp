<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Olsker Cupcakes
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <h3>Her kan du se en oversigt over ordrenummer: ${requestScope.orderId}</h3>
        <form method="post" action="${pageContext.request.contextPath}/fc/adminorder">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Antal</th>
                    <th scope="col">Bund</th>
                    <th scope="col">Topping</th>
                    <th scope="col">Pris</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cartItem" items="${requestScope.orderContent}">
                    <tr>
                        <td>${cartItem.quantity}</td>
                        <td>${cartItem.bottom}</td>
                        <td>${cartItem.topping}</td>
                        <td>${cartItem.price},-</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="row">
                <div class="col-md"></div>
                <div class="col-md " style="text-align:center;">
                    <button style="width:150px;" class="btn btn-secondary" type="submit" name="return">Tilbage</button>
                </div>
                <div class="col-md"></div>
            </div>
        </form>
        <br>
    </jsp:body>
</t:genericpage>