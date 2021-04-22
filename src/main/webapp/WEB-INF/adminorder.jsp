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

        <h3>Her kan admin se kundens ordrer og fjerne den</h3>


        <table class="table">
            <thead>
            <tr>
                <th scope="col">Bruger ID</th>
                <th scope="col">Ordrer ID</th>
                <th scope="col">Price</th>
                <th scope="col">Timestamp</th>
                <th scope="col">Fjern</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.orderList}">
                <tr>
                    <td>${order.userId}</td>
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