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
        <h3>Your balance: ${sessionScope.currentBalance}</h3>
        <form method="post">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Antal</th>
                    <th scope="col">Bund</th>
                    <th scope="col">Topping</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Fjern</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                    <tr>
                        <td>${cartItem.quantity}</td>
                        <td>${cartItem.bottom}</td>
                        <td>${cartItem.topping}</td>
                        <td>${cartItem.price},-</td>
                        <td>
                            <button class="btn btn-danger btn-sm" type="submit" name="delete"
                                    value="${sessionScope.cart.cartItems.indexOf(cartItem)}">Fjern
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>

            <div style="text-align:center; font-size:14pt;" class="row">
                <div class="col-md"></div>
                <div class="col-md">
                    Total pris: ${sessionScope.totalPrice}.-
                    <br>
                    <a href="${pageContext.request.contextPath}/fc/receiptpage">
                        <button style="width:150px;" type="button" name="buy" id="buy" class="btn btn-success">Køb
                        </button>
                    </a>
                </div>
                <div class="col-md"></div>
            </div>


        </form>
    </jsp:body>
</t:genericpage>