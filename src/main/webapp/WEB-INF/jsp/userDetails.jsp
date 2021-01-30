<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>DETALE UZYTKOWNIKA</title>
</head>
<body style="background-color: lightgray">
<header style="width: 100%;background-color: wheat;height: 10%">

    <sec:authorize access="isAuthenticated()">

    <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" />
    </h4></div>

</header>
        <br/><br/>
    <div style="width: 100%">
        <table align="center" style="background-color: bisque;width: 50%;height: 30%; text-align: center;font-max-size: large">
            <th><h3 style="color: red">DANE WŁAŚCICIELA</h3></th>
            <tr><td>IMIĘ: </td><td><sec:authentication property="principal.User.firstName" /></td></tr>
            <tr><td>NAZWISKO: </td><td><sec:authentication property="principal.User.lastName" /></td></tr>
            <tr><td>EMAIL: </td><td><sec:authentication property="principal.User.email" /></td></tr>
            <tr><td>NUMER TELEFONU: </td><td><sec:authentication property="principal.User.phoneNumber" /></td></tr>
            <tr><td>NAZWA FIRMY: </td><td><sec:authentication property="principal.User.company.name" /></td></tr>
            <tr><td>ADRES: </td> <td>
                <c:if test="${not empty address}">
                <sec:authentication property="principal.User.address.street" />
                <sec:authentication property="principal.User.address.streetNumber" /><br/>
                <sec:authentication property="principal.User.address.zipCode" />
                <sec:authentication property="principal.User.address.town" /></td></tr>
                </c:if>
            <tr><td></td></tr>

        </table>
        <br/><br/>
    </div>

        <p></p>

        <div align="center">
            <form method="get" action="/user"><button style="color: blue" >WRÓĆ</button></form>
            <a href="/company"><button style="color: blue"> PRZEJDŹ DO SWOJEJ FIRMY</button> </a><br/><br/>
            <a href="/user/edit/<sec:authentication property="principal.User.id" />"><button style="color: blue" >
                EDYTUJ SWÓJ PROFIL</button> </a><br/><br/>
            <a href="/user/addressEdition/<sec:authentication property="principal.User.id" />"><button style="color: blue">EDYTUJ DANE ADRESOWE</button> </a>
        </div>
    </sec:authorize>
</body>
</html>