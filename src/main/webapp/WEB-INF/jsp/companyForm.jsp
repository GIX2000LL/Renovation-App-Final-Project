<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EDYCJA FIRMY</title>
</head>
<body style="background-color: lightgray">
<header style="width: 100%;background-color: wheat">

    <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: ${currentUser.firstName}</h4></div>

</header>
<h3 align="center" style="color: blue">EDYCJA DANYCH ADRESOWYCH FIRMY</h3>

<div align="top">
    <table align="center">
        <form:form method="post" modelAttribute="companyToEdit">

            <tr><td><form:input path="id" type="hidden"/></td></tr>
            <tr><td>NAZWA</td> <td>  <form:input path="name"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="name" cssStyle="color: red"/></td> </tr><br/>
            <tr><td>EMAIL</td> <td>  <form:input path="email"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="email" cssStyle="color: red"/></td> </tr>
            <tr><td>TELEFON</td> <td>  <form:input path="phoneNumber"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="phoneNumber" cssStyle="color: red"/></td> </tr><br/>
            <tr><td>DANE ADRESOWE</td></tr><br/>
            <tr><td><form:input path="address.id" type="hidden"/></td></tr>
            <tr><td>ULICA</td> <td>  <form:input path="address.street" type="text"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="address.street" cssStyle="color: red"/></td> </tr><br/>
            <tr><td>NR ULICY</td> <td>  <form:input path="address.streetNumber" type="number"/> </td></tr><br/>
            <tr><td>KOD POCZTOWY</td> <td>  <form:input path="address.zipCode" type="text"/> </td></tr><br/>
            <tr><td>MIASTO</td> <td>  <form:input path="address.town" type="text"/> </td></tr><br/><br/>
            <tr><td></td><td><input style="color: blue" type="submit" value="ZATWIERDŹ DANE"></td></tr>
        </form:form>

    </table>
</div>
<br/><br/>

<div align="center"><a href="/company/companyDetails"><button style="color: green">WRÓĆ</button> </a></div>

</body>
</html>