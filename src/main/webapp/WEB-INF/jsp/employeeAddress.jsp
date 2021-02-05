<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>EDYCJA ADRESU PRACOWNIKA</title>
</head>

<body style="background-color: lightgray">
<sec:authorize access="isAuthenticated()">
    <header style="width: 100%;background-color: wheat">

        <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" />
        </h4></div>

    </header>
    <h3 align="center" style="color: blue">ADRES PRACOWNIKA</h3>


    <table align="center">
        <form:form method="post" modelAttribute="address">

            <tr><td><form:input path="id" type="hidden"/></td></tr>

            <tr><td>ULICA</td> <td>  <form:input path="street"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="street" cssStyle="color: red"/></td> </tr><br/>
            <tr><td>NUMER ULICY</td> <td>  <form:input path="streetNumber"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="streetNumber" cssStyle="color: red"/></td> </tr>
            <tr><td>MIASTO</td> <td>  <form:input path="town"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="town" cssStyle="color: red"/></td> </tr>
            <tr><td>KOD POCZTOWY</td> <td>  <form:input path="zipCode"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="zipCode" cssStyle="color: red"/></td> </tr><br/>

            <tr><td></td><td><input style="color: blue" type="submit" value="ZATWIERDŹ DANE"></td></tr>

        </form:form>
    </table>

    <br/><br/>

    <div align="center"><a href="/employees"><button style="color: darkred">WRÓĆ</button> </a></div>
</sec:authorize>
</body>
</body>
</html>