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

        <h1>Admin customer page</h1>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Bruger ID</th>
                <th scope="col">Email</th>
                <th scope="col">Timestamp</th>
                <th scope="col">Kredit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.accountBalance.timestamp}</td>
                    <td>${user.accountBalance.balance}</td>

                    <td><input type="submit" value="Fjern"></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <form method="post" action="${pageContext.request.contextPath}/fc/admincustomer">
            <div align="center" class="align-content-center">
                <label for="id">ID:</label>
                <input type="text" id="id" name="id">
                <input type="hidden" id="action" name="action" value="changebalance"/>
                <label for="credit">Kredit:</label>
                <input type="text" id="credit" name="credit">
                <td><input type="submit" value="Tilføj" name="update"></td>
                <!-- If money needed display error msg "Ikke nok penge, ordren koster (penge). Du har (kredit) tilbage"-->
            </div>
        </form>
    </jsp:body>
</t:genericpage>