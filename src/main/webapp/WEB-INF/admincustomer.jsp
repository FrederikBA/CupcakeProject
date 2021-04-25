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

        <h3>Her kan admin se kunder, deres credit og ændre deres credit</h3>
        <form method="post">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Bruger ID</th>
                    <th scope="col">Email</th>
                    <th scope="col">Tidspunkt</th>
                    <th scope="col">Balance</th>
                    <th scope="col">Se ordrer</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.accountBalance.timestamp}</td>
                        <td>${user.accountBalance.balance}</td>
                        <td>
                            <!<a href="${pageContext.request.contextPath}/fc/userorderpage">
                            <button class="btn btn-primary btn-sm" type="submit" name="userorders"
                                    value="${user.id}">
                                Se indhold
                            </button>
                        </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <div align="center" class="align-content-center">
                <label for="userId">Bruger ID:</label>
                <input type="text" id="userId" name="userId">
                <label for="balance">Balance:</label>
                <input type="text" id="balance" name="balance">
                <td><input type="submit" value="Opdater Balance" name="update"></td>
            </div>
        </form>
    </jsp:body>
</t:genericpage>