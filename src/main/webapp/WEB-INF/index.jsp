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

        <div class="row">
            <div class="col"></div>
            <div class="col-sm-11">
                <h1 class="">Velkommen ombord</h1>

                <h2>Øens bedste cupcakes. Vælg og bestil her:</h2>

                <c:if test="${sessionScope.role == 'employee' }">
                <p style="font-size: larger">This is what you can do,
                    since your are logged in as an employee</p>
                <p><a href="fc/employeepage">Employee Page</a>
                    </c:if>

                    <c:if test="${sessionScope.role == 'customer' }">
                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>
                    </c:if>
            </div>
            <div class="col"></div>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/fc/shoppingcart">
            <div class="row">
                <div class="col">
                    <label for="bottom">Vælg bund</label>
                    <select name="bottom" id="bottom">
                        <c:forEach var="bottom" items="${applicationScope.bottomList}">
                            <option value="${bottom.id}">${bottom.name} DKK ${bottom.price},-</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col">
                    <label for="topping">Vælg topping</label>
                    <select name="topping" id="topping">
                        <c:forEach var="topping" items="${applicationScope.toppingList}">
                            <option value="${topping.id}">${topping.name} DKK ${topping.price},-</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col">
                    <label for="fname">Vælg antal: </label>
                    <input type="text" id="fname" name="fname">
                    <input type="submit" value="Submit">
                </div>
            </div>
        </form>

    </jsp:body>
</t:genericpage>