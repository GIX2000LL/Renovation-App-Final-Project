<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DETALE UZYTKOWNIKA</title>
</head>
<body style="background-color: lightgray">
<header style="width: 100%;background-color: wheat;height: 10%">

    <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: ${currentUser.firstName}</h4></div>

</header>
        <br/><br/>
    <div style="width: 100%">
        <table align="center" style="background-color: bisque;width: 50%;height: 30%; text-align: center;font-max-size: large">
            <th><h3 style="color: red">DANE WŁAŚCICIELA</h3></th>
            <tr><td>IMIĘ: </td><td>${currentUser.firstName}</td></tr>
            <tr><td>NAZWISKO: </td><td>${currentUser.lastName}</td></tr>
            <tr><td>EMAIL: </td><td>${currentUser.email}</td></tr>
            <tr><td>NUMER TELEFONU: </td><td>${currentUser.phoneNumber}</td></tr>
            <tr><td>NAZWA FIRMY: </td><td>${currentUser.company.name}</td></tr>
            <tr><td>ADRES: </td> <td> ${currentUser.address.street} ${currentUser.address.streetNumber}<br/>
                ${currentUser.address.zipCode} ${currentUser.address.town}</td></tr>
            <tr><td></td></tr>

        </table>
        <br/><br/>
    </div>
        <p></p>

        <div align="center">
            <form method="get" action="/user"><button style="color: blue" >WRÓĆ</button></form>
            <a href="/company"><button style="color: blue"> PRZEJDŹ DO SWOJEJ FIRMY</button> </a><br/><br/>
            <a href="/user/edit/${currentUser.id}"><button style="color: blue" >EDYTUJ SWÓJ PROFIL</button> </a>
        </div>

</body>
</html>