<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>ZLECENIA</title>
</head>

<body style="background-color: lightgray">
<sec:authorize access="isAuthenticated()">
    <header style="width: 100%;background-color: wheat">
        <table align="center" style="width: 90%">
            <tr>

                <td style="width: 20px;"></td>
                <td><a href="/commissions/add">
                    <button style="color: blue">DODAJ NOWE ZLECENIE </button></a> </td>
                <td style="width: 50px;"></td>
                <td><a href="/commissions/archive"><button style="color: blue" >ZAKOŃCZONE ZLECENIA</button>
                </a> </td>
                <td style="color: green; text-align: right"><h4>ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" /></h4>
                    <h4 style="color: green">FIRMA: <sec:authentication property="principal.User.company.name" /></h4>
                    <h4><a href="/company"><button style="color: darkred" value="WRÓĆ">WRÓĆ</button></a> </h4>
                </td>
            </tr>
        </table>
    </header>
    <h3 align="center" style="color: blue">ZLECENIA</h3>


    <table align="center" >
        <c:forEach items="${commissions}" var="com">
            <tr>
                <td>NAZWA ZLECENIA: ${com.name}</td>
                <td></td><td style="color: red">DATA ZAKOŃCZENIA: ${com.commissionEnd}</td>
                <td  style="width: 30px"></td>
                <td><a href="/commissions/details/${com.id}"><button style="color: blue">SZCZEGÓŁY</button></a> </td>
<%--                <td><a href="/commissions/edit/${com.id}"><button style="color: green">EDYTUJ</button></a> </td>--%>
                <td><a href="/commissions/archive/${com.id}"><button style="color: green">ZARCHIWIZUJ</button></a> </td>
<%--                <td><a href="/employees/delete/${com.id}"><button style="color: red">USUŃ </button></a> </td>--%>
            </tr>
        </c:forEach>
    </table>

</sec:authorize>
</body>
</body>
</html>