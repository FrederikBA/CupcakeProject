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

        <div class="row">
            <div class="col-sm-11">
                <h2 style="font-weight:bold;">Velkommen ombord hos Olsker Cupcakes!</h2>
                <br>
                <h4>Vi tilbyder øens bedste cupcakes til øens bedste priser.</h4>
                <br>
                <h4>Vælg mellem vores skarpe udvalg af forskellige bunde og toppings
                    og sammensæt din helt egen cupcake.<h4>
            </div>
        </div>
        <!--fjern action, men så tilføjer den ikke tingene til kurven. -->
        <form method="post" action="${pageContext.request.contextPath}/fc/addtocart">
            <div class="row">
                <div class="col-md">
                    <div class="form-group">
                        <br>
                        <select class="form-select" name="bottom" id="bottom">
                            <option disabled selected value> Vælg Bund:</option>
                            <c:forEach var="bottom" items="${applicationScope.bottomList}">
                                <option value="${bottom.id}">${bottom.name} DKK ${bottom.price},-</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="col-md">
                    <div class="form-group">
                        <br>
                        <select class="form-select" name="topping" id="topping">
                            <option disabled selected value> Vælg Topping:</option>
                            <c:forEach var="topping" items="${applicationScope.toppingList}">
                                <option value="${topping.id}">${topping.name} DKK ${topping.price},-</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="col-md">
                    <div class="form-group" style="text-align:center;">
                        <label for="quantity">Vælg antal: </label>
                        <input class="form-control" type="text" id="quantity" name="quantity">
                        <br>
                        <br>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md"></div>
                <div class="col-md">
                    <div style="text-align:center;">
                        <button style="width:150px;" class="btn btn-secondary" name="put" type="submit">Læg i kurv</button>
                    </div>
                    <br>
                </div>
                <div class="col-md"></div>
            </div>
        </form>

    </jsp:body>
</t:genericpage>