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
            <tr>
                <th scope="row">7</th>
                <td>bruger@mail.dk</td>
                <td>11:57</td>
                <td>Dkk 5.-</td>
            </tr>
            <tr>
                <th scope="row">4</th>
                <td>bruger@mail.dk</td>
                <td>11:57</td>
                <td>Dkk 5.-</td>
            </tr>
            <tr>
                <th scope="row">5</th>
                <td>bruger@mail.dk</td>
                <td>Kl: 11:57</td>
                <td>Dkk 5.-</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>bruger@mail.dk</td>
                <td>Kl: 11:57</td>
                <td>Dkk 5.-</td>
            </tr>
            </tbody>
        </table>
        <br>
        <div align="center" class="align-content-center">
            <label for="id">ID:</label>
            <input type="text" id="id" name="id">
        <label for="credit">Kredit:</label>
        <input type="text" id="credit" name="credit">
            <td><input type="submit" value="Tilføj"></td>
            <!-- If money needed display error msg "Ikke nok penge, ordren koster (penge). Du har (kredit) tilbage"-->
        </div>

    </jsp:body>
</t:genericpage>