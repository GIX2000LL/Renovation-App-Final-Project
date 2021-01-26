<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FIRMA</title>
</head>
<body style="background-color: lightgray">
<header style="width: 100%;background-color: wheat">
    <table align="center" style="width: 90%">
        <tr>
            <td><a href="/company/companyDetails">
                <button style="color: blue" >DANE ADRESOWE</button>
            </a> </td>
            <td style="width: 20px;"></td>
            <td><a href="/company/employees"><button style="color: blue" >PRACOWNICY</button>
            </a> </td>
            <td style="width: 50px;"></td>
            <td><a href="/company/commissions"><button style="color: blue" >ZLECENIA</button>
            </a> </td>
            <td style="color: green; text-align: right"><h4>ZALOGOWANY UŻYTKOWNIK: ${currentUser.firstName}</h4>
                <h4><a href="/user"><button style="color: darkred" value="WRÓĆ">WRÓĆ</button></a> </h4>
                <h4><a href="/logout"><button style="color: darkred" value="WYLOGUJ SIĘ">WYLOGUJ SIĘ</button></a> </h4>
            </td>
        </tr>
    </table>
</header>
<div align="center">

    <p style="color: blue;background-color: lightgray">
    <h1 style="color: darkblue">${currentUser.company.name}</h1>
    <h2 style="color: blue">ZARZĄDZAJ MĄDRZE I SKUTECZNIE Z RENOVATION APP</h2>
    </p>

</div>
</body>
</html>