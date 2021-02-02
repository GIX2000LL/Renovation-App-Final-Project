<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>DETALE PRACOWNIKA</title>
</head>
<body style="background-color: lightgray">
<header style="width: 100%;background-color: wheat;height: 10%">

    <sec:authorize access="isAuthenticated()">

    <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" />
    </h4><h4 style="color: green">FIRMA: <sec:authentication property="principal.User.company.name" />
    </h4></div>

</header>
<br/><br/>
<div style="width: 100%">
    <table align="center" style="background-color: bisque;width: 50%;height: 30%; text-align: center;font-max-size: large">
        <th><h3 style="color: red">DANE PRACOWNIKA</h3></th>
        <tr><td>IMIĘ: </td><td>${employee.firstName}</td></tr>
        <tr><td>NAZWISKO: </td><td>${employee.lastName}</td></tr>
        <tr><td>STAWKA GODZINOWA: </td><td>${employee.hourlyRate} zł brutto</td></tr>
        <tr><td>FIRMA: </td><td>${employee.company.name}</td></tr>
        <tr><td>UMIEJETNOŚCI:</td></tr><c:forEach items="${employee.skills}" var="skill">
           <tr> <td></td><td> ${skill.name}</td></tr>
        </c:forEach>
        <tr><td></td></tr>

        <tr><td>ADRES PRYWATNY: </td> <td>
            <c:if test="${not empty address}">
            ${address.street} ${address.streetNumber}<br/>
            ${address.zipCode} ${address.town}</td></tr>
             </c:if>
        <tr><td></td></tr>

    </table>
    <br/><br/>
</div>

<p></p>

<div align="center">
    <form method="get" action="/employees"><button style="color: darkred" >WRÓĆ</button></form>

    <a href="/employees/edit/${employee.id}"><button style="color: blue" > EDYTUJ PROFIL PRACOWNIKA</button> </a><br/><br/>
    <a href="/employees/addressEdition/${employee.id}"><button style="color: blue">
        DODAJ DANE ADRESOWE PRACOWNIKA</button> </a>
</div>
</sec:authorize>
</body>
</html>