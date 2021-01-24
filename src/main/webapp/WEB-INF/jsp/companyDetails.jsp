<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DANE ADRESOWE</title>
</head>
<body style="background-color: lightgray">
<header style="width: 100%;background-color: wheat;height: 10%">

    <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: ${currentUser.firstName}</h4></div>

</header>
<br/><br/>
<div style="width: 100%">
    <table align="center" style="background-color: bisque;width: 50%;height: 30%; text-align: center;font-max-size: large">
        <th><h3 style="color: red">DANE FIRMOWE</h3></th>
        <tr><td>NAZWA: </td><td>${currentUser.company.name}</td></tr>
        <tr><td>EMAIL: </td><td>${currentUser.company.email}</td></tr>
        <tr><td>TELEFON: </td><td>${currentUser.company.phoneNumber}</td></tr>
        <tr><td>NUMER TELEFONU: </td><td>${currentUser.company.phoneNumber}</td></tr>
        <tr><td>ADRES: </td> <td> ${currentUser.company.address.street} ${currentUser.company.address.streetNumber}<br/>
            ${currentUser.company.address.zipCode} ${currentUser.company.address.town}</td></tr>
        <tr><td></td></tr>


    </table>
    <br/><br/>
</div>
<p></p>

<div align="center">
    <form method="get" action="/company"><button style="color: blue" >WRÓĆ</button></form>
    <a href="/company/employees"><button style="color: blue"> PRZEJDŹ DO PRACOWNIKÓW</button> </a><br/><br/>
    <a href="/company/commissions"><button style="color: blue"> PRZEJDŹ DO ZLECEŃ</button> </a><br/><br/>
    <a href="/company/edit/${currentUser.id}"><button style="color: blue" >EDYTUJ DANE FIRMOWE</button> </a>
</div>

</body>
</html>