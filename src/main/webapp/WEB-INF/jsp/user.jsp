<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UZYTKOWNIK</title>
</head>
<body style="background-color: lightgray">
    <header style="width: 100%;background-color: wheat">
        <table align="center" style="width: 90%">
            <tr>
                <td><a href="/user/userDetails">
                    <button style="color: blue" >TWÓJ PROFIL</button>
                </a> </td>
                <td style="width: 20px;"></td>
                <td><a href="/company"><button style="color: blue" >ZARZADZAJ FIRMĄ</button>
                </a> </td>
                <td style="width: 50px;"></td>
                <td style="color: green; text-align: right"><h4>ZALOGOWANY UŻYTKOWNIK: ${currentUser.firstName}</h4>
                    <h4><a href="/logout"><button style="color: darkred" value="WYLOGUJ SIĘ">WYLOGUJ SIĘ</button></a> </h4></td>
            </tr>
        </table>
    </header>
    <div align="center">

        <p style="color: blue;background-color: lightgray">
            <h2 style="color: blue">WITAJ W APLIKACJI ZARZĄDZAJĄCEJ TWOJA FIRMĄ</h2>
        </p>

    </div>
</body>
</html>