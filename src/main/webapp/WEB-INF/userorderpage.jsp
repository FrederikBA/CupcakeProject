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

        <h3>Her kan du se en oversigt over denne brugers ordrer: ${requestScope.userId}</h3>
        <form method="post" action="${pageContext.request.contextPath}/fc/admincustomer">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Ordre ID</th>
                    <th scope="col">Bruger ID</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Tidspunkt</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${requestScope.userOrders}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.userId}</td>
                        <td>${order.price},-</td>
                        <td>${order.timestamp}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <button class="btn btn-primary" type="submit" name="return">Tilbage</button>
        </form>
    </jsp:body>
</t:genericpage>