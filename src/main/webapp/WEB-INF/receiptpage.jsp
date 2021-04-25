<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div  align="center">
        <h3>Tak for købet.</h3>
        <h5>Kvittering:</h5>
        </div>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Ordrer ID</th>
                <th scope="col">Antal cupcakes</th>
                <th scope="col">Bund</th>
                <th scope="col">Topping</th>
                <th scope="col">Pris</th>
                <th scope="col">Tidspunkt</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="orderidbytime" items="${requestScope.orderList}">
                <tr>
                    <td>${orderb.userId}</td>
                    <td>${order.orderId}</td>
                    <td>${order.price}</td>
                    <td>${order.timestamp}</td>
                    <form method="post">
                   <td><button class="btn btn-danger btn-sm" type="submit" name="delete" value="${order.orderId}">Remove</button></td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>





    </jsp:body>
</t:genericpage>