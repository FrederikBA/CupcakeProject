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

        <h3>Ordreoversigt</h3>

        <form method="post">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Bruger ID</th>
                    <th scope="col">Ordrer ID</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Tidspunkt</th>
                    <th scope="col">Indhold</th>
                    <th scope="col">Fjern</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${sessionScope.orderList}">
                    <tr>
                        <td>${order.userId}</td>
                        <td>${order.orderId}</td>
                        <td>${order.price}</td>
                        <td>${order.timestamp}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/fc/ordercontentpage">
                            <button class="btn btn-primary btn-sm" type="submit" name="content"
                                    value="${order.orderId}">
                                Se indhold
                            </button>
                            </a>
                        </td>
                        <td>
                            <button class="btn btn-danger btn-sm" type="submit" name="delete"
                                    value="${order.orderId}">
                                Fjern
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>


    </jsp:body>
</t:genericpage>