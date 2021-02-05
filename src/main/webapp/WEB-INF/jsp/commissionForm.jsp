<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NOWE ZLECENIE</title>
</head>
<body style="background-color: lightgray">
<header style="width: 100%;background-color: wheat">

    <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" /></h4>
        <h4 style="color: green">FIRMA: <sec:authentication property="principal.User.company.name" />
        </h4></div>

</header>
<h3 align="center" style="color: blue">DODAWANIE NOWEGO ZLECENIA</h3>

<div align="top">
    <table align="center">
        <form:form method="post" modelAttribute="commission">

            <tr><td>NAZWA</td> <td>  <form:input path="name"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="name" cssStyle="color: red"/></td> </tr><br/>
            <tr><td>DATA ROZPOCZĘCIA ZLECENIA</td> <td>  <form:input path="commissionStart" type="date"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="commissionStart" cssStyle="color: red"/></td> </tr>
            <tr><td>DATA ZAKOŃCZENIA ZLECENIA</td> <td>  <form:input path="commissionEnd" type="date"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="commissionEnd" cssStyle="color: red"/></td> </tr>
            <tr><td>WYBIERZ KIEROWNIKA ZLECENIA</td><td><form:radiobuttons items="${currentCompanyEmployees}" itemLabel="lastName" itemValue="id" path="leader"/></td></tr>

            <tr><td></td><td><input type="submit" style="color: blue" value="ZATWIERDŹ"></td></tr>

        </form:form>

    </table>
</div>
<br/><br/>

<div align="center"><a href="/commissions"><button style="color: darkred">WRÓĆ</button> </a></div>

</body>
</html>