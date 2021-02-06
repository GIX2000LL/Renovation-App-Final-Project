<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>FINANSE</title>
</head>
<body style="background-color: lightgray">
<sec:authorize access="isAuthenticated()">
    <header style="width: 100%;background-color: wheat">
        <table align="center" style="width: 90%">
            <tr>
<%--                <td><a href="/company/companyDetails">--%>
<%--                    <button style="color: blue" >DANE FIRMOWE</button>--%>
<%--                </a> </td>--%>
<%--                <td style="width: 20px;"></td>--%>
<%--                <td><a href="/employees"><button style="color: blue" >PRACOWNICY</button>--%>
<%--                </a> </td>--%>
<%--                <td style="width: 50px;"></td>--%>
<%--                <td><a href="/commissions"><button style="color: blue" >ZLECENIA</button>--%>
<%--                </a> </td>--%>
                <td style="color: green; text-align: right"><h4>ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" /></h4>
                    <h4>FIRMA: <sec:authentication property="principal.User.company.name" /></h4>
                    <h4><a href="/company"><button style="color: darkred" value="WRÓĆ">WRÓĆ</button></a> </h4>
                </td>
            </tr>
        </table>
    </header>
    <div align="center">

        <p style="color: blue;background-color: lightgray">
        <h2 style="color: blue">ZESTAWIENIE FINANSOWE ${currentMonth}</h2>
        </p>
        <br/><br/>

        <div>
                <table width="800 px">
                    <tr><td></td></tr>
                    <tr><td style="color: green"><h3>PRACOWNICY</h3> </td></tr>
                    <c:forEach items="${employeesInMonth}" var="emp">
                        <tr><td></td><td>${emp.firstName} ${emp.lastName}</td><td>${emp.hourlyRate} zł</td></tr>
                    </c:forEach>
                    <tr><td></td></tr>
                    <tr><td style="color: red">RAZEM PRACOWNICY DO WYPŁATY W ${currentMonth}:</td><td></td><td style="color: red">
                            ${monthForAllEmployees} zł brutto</td></tr>
                    <tr><td></td></tr>
                    <tr><td></td></tr>
                    <tr><td style="color: green"><h3>AKTYWNE ZLECENIA</h3> </td></tr>
                    <tr><td>CENA CAŁKOWITA ZA WSZYSTKIE AKTYWNE ZLECENIA</td><td></td><td>${totalPrice} zł </td></tr>
                    <tr><td>CAŁKOWITE KOSZTA MATERIAŁÓW</td><td></td><td>${totalMaterialsCost} zł </td></tr>
                    <tr><td>CAŁKOWITE KOSZTA PRACOWNICZE ZE ZLECEŃ</td><td></td><td>${totalWorkersCost} zł </td></tr>
                    <tr><td></td></tr>
                    <tr><td style="color: red">CAŁKOWITY ZYSK ZE ZLECEŃ W ${currentMonth}</td><td></td><td style="color: red">
                            ${totalProfit=totalPrice-(totalMaterialsCost+totalWorkersCost)} zł brutto</td></tr>
                </table>
        </div>

    </div>
</sec:authorize>
</body>
</html>