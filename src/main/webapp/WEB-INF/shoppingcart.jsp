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

        <h1>This is the shopping cart page</h1>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Antal</th>
                <th scope="col">Topping</th>
                <th scope="col">Bottom</th>
                <th scope="col">pris</th>
                <th scope="col">fjern</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">7</th>
                <td>jordbær</td>
                <td>vanilje</td>
                <td>Dkk 5.-</td>
                <td><input type="submit" value="Fjern"></td>
            </tr>
            <tr>
                <th scope="row">4</th>
                <td>jordbær</td>
                <td>vanilje</td>
                <td>Dkk 5.-</td>
                <td><input type="submit" value="Fjern"></td>
            </tr>
            <tr>
                <th scope="row">5</th>
                <td>jordbær</td>
                <td>vanilje</td>
                <td>Dkk 5.-</td>
                <td><input type="submit" value="Fjern"></td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>jordbær</td>
                <td>vanilje</td>
                <td>Dkk 5.-</td>
                <td><input type="submit" value="Fjern"></td>
            </tr>
            </tbody>
        </table>


        <br>
        <div align="center" class="align-content-center"> Total pris 50kr.-</div>

        <div align="center" class="align-content-center">
        <td><input type="submit" value="Køb"></td>
        <!-- If money needed display error msg "Ikke nok penge, ordren koster (penge). Du har (kredit) tilbage"-->
        </div>

    </jsp:body>
</t:genericpage>