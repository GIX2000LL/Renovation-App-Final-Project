<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>DANE ADRESOWE</title>
</head>
<body style="background-color: lightgray">
<sec:authorize access="isAuthenticated()">
<header style="width: 100%;background-color: wheat;height: 10%">

    <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName"/></h4>
        <h4 style="color: green">FIRMA: <sec:authentication property="principal.User.company.name" />
        </h4></div>

</header>
<br/><br/>
<div style="width: 100%">
    <table align="center" style="background-color: bisque;width: 50%;height: 30%; text-align: center;font-max-size: large">

        <th><h3 style="color: red">DANE FIRMOWE</h3></th>

        <tr><td>NAZWA: </td><td><sec:authentication property="principal.User.company.name"/></td></tr>
        <tr><td>EMAIL: </td><td> <c:if test="${not empty email}">
            <sec:authentication property="principal.User.company.email"/></td></tr> </c:if>
        <tr><td>TELEFON: </td><td> <c:if test="${not empty phoneNumber}">
            <sec:authentication property="principal.User.company.phoneNumber"/></td></tr> </c:if>

        <tr><td>ADRES: </td>
            <c:if test="${not empty address}">
            <td> <sec:authentication property="principal.User.company.address.street"/>
            <sec:authentication property="principal.User.company.address.streetNumber"/><br/>
            <sec:authentication property="principal.User.company.address.zipCode"/>
            <sec:authentication property="principal.User.company.address.town"/></td></tr>
             </c:if>
        <tr><td></td></tr>


    </table>
    <br/><br/>
</div>
<p></p>

<div align="center">
    <a href="/company"><button style="color: darkred"> WRÓĆ</button> </a><br/><br/>
    <a href="/company/edit/<sec:authentication property="principal.User.company.id"/>"><button style="color: blue" >EDYTUJ DANE FIRMOWE</button> </a><br/><br/>
    <a href="/company/companyAddressEdit/<sec:authentication property="principal.User.company.id"/>"><button style="color: blue" >EDYTUJ ADRES FIRMY</button> </a><br/><br/>
    <a href="/employees"><button style="color: green"> PRZEJDŹ DO PRACOWNIKÓW</button> </a>
    <a href="/company/commissions"><button style="color: green"> PRZEJDŹ DO ZLECEŃ</button> </a><br/><br/>

</div>
</sec:authorize>
</body>
</html>