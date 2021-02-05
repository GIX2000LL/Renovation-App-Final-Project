<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WYCENA</title>
</head>
<body style="background-color: lightgray">
<header style="width: 100%;background-color: wheat">

    <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: ${currentUser.firstName}</h4>
        <h4 style="color: green">FIRMA: <sec:authentication property="principal.User.company.name" />
        </h4></div>

</header>
<h3 align="center" style="color: blue">DODAWANIE WYCENY DLA ZLECENIA: ${estimate.commission.name} </h3>

<div align="top">
    <table align="center">
        <form:form method="post" modelAttribute="estimate">

            <tr><td><form:input path="commission" type="hidden"/></td></tr>
            <tr><td><form:input path="workersCost" type="hidden"/></td></tr>

            <tr><td>PRZYBLIŻONA KWOTA MATERIAŁÓW BRUTTO: </td> <td>  <form:input path="materialsCost"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="materialsCost" cssStyle="color: red"/></td> </tr><br/>
            <tr><td>CAŁKOWITA KWOTA ZLECENIA BRUTTO </td> <td>  <form:input path="totalPrice"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="totalPrice" cssStyle="color: red"/></td> </tr>
            <tr><td>KOSZTA PRACOWNICZE ZOSTANĄ DODANE AUTOMATYCZNIE</td></tr>
            <tr><td>BIORĄC POD UWAGĘ PRACOWNIKÓW PRZYDZIELONYCH DO ZLECENIA</td></tr>
            <tr><td>ICH STAWKĘ GODZINOWĄ ORAZ 8-GODZINNY CZAS PRACY <td></tr>

            <tr><td></td><td><input type="submit" style="color: blue" value="ZATWIERDŹ"></td></tr>

        </form:form>

    </table>
</div>
<br/><br/>

<div align="center"><a href="/commissions"><button style="color: darkred">WRÓĆ</button> </a></div>

</body>
</html>

