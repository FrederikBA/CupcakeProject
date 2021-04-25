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

        <h3>Her kan du se informationer om kunder samt opdatere deres balance</h3>
        <form method="post" action="${pageContext.request.contextPath}/fc/admincustomer">
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
                <c:forEach var="user" items="${sessionScope.users}">
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
            <div style="text-align:center;" class="row">

                <div class="col-md">
                    <div class="form-group">
                        <label for="userId">Bruger ID:</label>
                        <input class="form-control" type="text" id="userId" name="userId">
                    </div>
                </div>

                <div class="col-md">
                    <div class="form-group">
                        <label for="balance">Balance:</label>
                        <input class="form-control" type="text" id="balance" name="balance">
                    </div>
                </div>

                <div class="col-md">
                    <div class="form-group">
                        <br>
                        <button style="width:100%;" type="submit" class="btn btn-secondary" name="update"
                                value="update">Opdater Balance
                        </button>
                    </div>
                </div>
            </div>
            <br>
        </form>
    </jsp:body>
</t:genericpage>