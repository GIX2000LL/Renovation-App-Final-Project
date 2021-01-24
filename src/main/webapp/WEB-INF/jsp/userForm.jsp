<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EDYCJA UŻYTKOWNIKA</title>
</head>
<body style="background-color: lightgray">
    <header style="width: 100%;background-color: wheat">

        <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: ${currentUser.firstName}</h4></div>

    </header>
    <h3 align="center" style="color: blue">EDYCJA PROFILU UŻYTKOWNIKA</h3>

    <div align="top">
        <table align="center">
            <form:form method="post" modelAttribute="userToEdit">

                <tr><td><form:input path="id" type="hidden"/></td></tr>
                <tr><td><form:input path="securityRole" type="hidden"/></td></tr>
                <tr><td><form:input path="company.id" type="hidden"/></td></tr>
                <tr><td><form:input path="company.name" type="hidden"/></td></tr>
                <tr><td>IMIĘ</td> <td>  <form:input path="firstName"/> </td></tr><br/>
                <tr><td></td><td><form:errors path="firstName" cssStyle="color: red"/></td> </tr><br/>
                <tr><td>NAZWISKO</td> <td>  <form:input path="lastName"/> </td></tr><br/>
                <tr><td></td><td><form:errors path="lastName" cssStyle="color: red"/></td> </tr>
                <tr><td><form:input path="password" type="hidden"/> </td></tr>
                <tr><td>EMAIL</td> <td>  <form:input path="email" type="email"/> </td></tr><br/>
                <tr><td>TELEFON</td> <td>  <form:input path="phoneNumber"/> </td></tr><br/>
                <tr><td></td><td><form:errors path="phoneNumber" cssStyle="color: red"/></td> </tr><br/>
                <tr><td>DANE ADRESOWE</td></tr><br/>
                <tr><td><form:input path="address.id" type="hidden"/></td></tr>
                <tr><td>ULICA</td> <td>  <form:input path="address.street" type="text"/> </td></tr><br/>
                <tr><td></td> <td>  <form:errors path="address.street"/> </td></tr><br/>
                <tr><td>NR ULICY</td> <td>  <form:input path="address.streetNumber" type="text"/> </td></tr><br/>
                <tr><td></td> <td>  <form:errors path="address.streetNumber"/> </td></tr><br/>
                <tr><td>KOD POCZTOWY</td> <td>  <form:input path="address.zipCode" type="text"/> </td></tr><br/>
                <tr><td></td> <td>  <form:errors path="address.zipCode"/> </td></tr><br/>
                <tr><td>MIASTO</td> <td>  <form:input path="address.town" type="text"/> </td></tr><br/><br/>
                <tr><td></td> <td>  <form:errors path="address.town"/> </td></tr><br/><br/>
                <tr><td></td><td><input style="color: blue" type="submit" value="ZATWIERDŹ DANE"></td></tr>
            </form:form>

        </table>
    </div>
        <br/><br/>

    <div align="center"><a href="/user/userDetails"><button style="color: green">WRÓĆ</button> </a></div>

</body>
</html>