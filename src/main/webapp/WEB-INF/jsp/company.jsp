<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>FIRMA</title>
</head>
<body style="background-color: lightgray">
<sec:authorize access="isAuthenticated()">
<header style="width: 100%;background-color: wheat">
    <table align="center" style="width: 90%">
        <tr>
            <td><a href="/company/companyDetails">
                <button style="color: blue" >DANE FIRMOWE</button>
            </a> </td>
            <td style="width: 20px;"></td>
            <td><a href="/employees"><button style="color: blue" >PRACOWNICY</button>
            </a> </td>
            <td style="width: 50px;"></td>
            <td><a href="/commissions"><button style="color: blue" >ZLECENIA</button>
            </a> </td>
            <td><a href="/finance"><button style="color: blue" >FINANSE</button>
            </a> </td>
            <td style="color: green; text-align: right"><h4>ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" /></h4>
                <h4><a href="/user"><button style="color: darkred" value="WRÓĆ">WRÓĆ</button></a> </h4>
                <h4><a href="/logout"><button style="color: darkred" value="WYLOGUJ SIĘ">WYLOGUJ SIĘ</button></a> </h4>
            </td>
        </tr>
    </table>
</header>
<div align="center">

    <p style="color: blue;background-color: lightgray">
    <h1 style="color: darkblue"><sec:authentication property="principal.User.company.name"/></h1>
    <h2 style="color: blue">ZARZĄDZAJ MĄDRZE I SKUTECZNIE Z RENOVATION APP</h2>
    </p>
    <br/><br/>

    <div>
        <c:if test="${primeComplete!=null}">
            <table width="500 px">
                <tr><td></td></tr>
                <tr><td style="color: red"><h3>TO ZLECENIE KOŃCZY SIE JAKO PIERWSZE !!!</h3> </td></tr>
                <tr><td>NAZWA: </td><td>${primeComplete.name}</td></tr>
                <tr><td>DATA ZAKOŃCZENIA: </td><td>${primeComplete.commissionEnd}</td></tr>
                <tr><td> KIEROWNIK ZLECENIA: </td><td>${primeComplete.leader.firstName} ${primeComplete.leader.lastName}</td></tr>
                <tr><td></td></tr>
                <tr><td></td><td><a href="/commissions/details/${primeComplete.id}"><button style="color: blue">
                    PRZEJDŹ DO TEGO ZLECENIA</button></a></td></tr>
            </table>
        </c:if>
    </div>

</div>
</sec:authorize>
</body>
</html>