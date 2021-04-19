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

        <h1>This is the order page</h1>


        <table class="table">
            <thead>
            <tr>
                <th scope="col">bruger ID</th>
                <th scope="col">ordre ID</th>
                <th scope="col">Bruger Email</th>
                <th scope="col">Fjern ordre</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>2</td>
                <td>Lars@mail.dk</td>
                <td><input type="submit" value="Fjern"></td>

            </tr>
            <tr>
                <th scope="row">2</th>
                <td>6</td>
                <td>Otto@mail.dk</td>
                <td><input type="submit" value="Fjern"></td>

            </tr>
            <tr>
                <th scope="row">3</th>
                <td> 9</td>
                <td>Emil@mail.dk</td>
                <td><input type="submit" value="Fjern"></td>
            </tr>
            <tr>
                <th scope="row">4</th>
                <td>1   </td>
                <td>Mads@mail.com  </td>
                <td><input type="submit" value="Fjern"></td>
            </tr>
            </tbody>
        </table>


    </jsp:body>
</t:genericpage>