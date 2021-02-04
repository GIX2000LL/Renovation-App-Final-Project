<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>SZCEGÓŁY ZLECENIA</title>
</head>
<body style="background-color: lightgray">
<sec:authorize access="isAuthenticated()">

<header style="width: 100%;background-color: wheat;height: 10%">

    <table>
        <tr><td></td></tr>
        <tr><td><form method="get" action="/commissions"><button style="color: darkred" >WRÓĆ</button></form></td>
            <td width="1000px"></td>
            <td style="color: green"> ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" /> </td></tr>
            <tr><td></td><td width="1000px"></td><td style="color: green"> FIRMA: <sec:authentication property="principal.User.company.name" /> </td></tr>
    </table>

</header>
<br/><br/>
<div style="width: 100%">
    <table align="center" style="background-color: bisque;width: 50%;height: 30%; text-align: center;font-max-size: large">
        <th><h3 style="color: red">ZLECENIE: ${commission.name}</h3></th>
        <tr><td>DATA ROZPOCZĘCIA: </td><td>${commission.commissionStart}</td></tr>
        <tr><td>DATA ZAKOŃCZENIA: </td><td>${commission.commissionEnd}</td></tr>
        <tr><td>KIEROWNIK ZLECENIA: </td><td>${commission.leader.firstName} ${commission.leader.lastName}</td></tr>
        <tr><td>PRZYDZIELENI PRACOWNICY: </td> <tr/>
            <c:forEach items="${commission.employees}" var ="comEmp">
                <tr><td></td> <td>${comEmp.firstName} ${comEmp.lastName}
                    <a href="/commissions/deleteEmployeeFromCommission/${comEmp.id}/${commission.id}">
                        <button style="color: red">USUŃ PRACOWNIKA </button></a> </td></tr>
            </c:forEach>
        <tr><td>WYCENA ZLECENIA:</td><td></td><tr/>

    </table>
    <br/><br/>
</div>

<p></p>

<div align="center">
    <p style="background-color: white;width: 400px">DODAJ PRACOWNIKÓW DO ZLECENIA<br/>
        (Przytrzymaj ctr jeśli chcesz dodać kilku pracowników jednoczesnie)<br/></p>

        <form action="/commissions/addEmployees/${commission.id}" method="get">
            <select name="employee" multiple="multiple">

                <c:forEach items="${currentCompanyEmployees}" var="emp">
                    <option style="width: 200px" value="${emp.id}"> <span style="align-content: center">
                            ${emp.firstName} ${emp.lastName}</span></option>
                </c:forEach>
            </select>
            <input style="color: blue" type="submit" value="ZATWIERDŹ WYBÓR">
        </form>
    <br/>
            <a href="/commissions/addEstimate/${commission.id}"><button style="color: blue">
                DODAJ WYCENĘ ZLECENIA</button> </a>




</div>
</sec:authorize>
</body>
</html>