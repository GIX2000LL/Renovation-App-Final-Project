<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>PRACOWNICY</title>
</head>

<body style="background-color: lightgray">
<sec:authorize access="isAuthenticated()">
    <header style="width: 100%;background-color: wheat">

        <a href="employees/add">
            <button style="color: blue">DODAJ NOWEGO PRACOWNIKA </button></a>
        <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" />
        </h4><h4 style="color: green">FIRMA: <sec:authentication property="principal.User.company.name" />
        </h4</div>

    </header>
    <h3 align="center" style="color: blue">PRACOWNICY</h3>


    <table align="center">
        <c:forEach items="${employees}" var="emp">
            <tr>
                <td><p style="color: black;background-color: white;">${emp.firstName} ${emp.lastName}</p></td>
                <td style="width: 30px"></td>
                <td><a href="/employees/details/${emp.id}"><button style="color: blue">SZCZEGÓŁY</button></a> </td>
                <td><a href="/employees/edit/${emp.id}"><button style="color: green">EDYTUJ</button></a> </td>
                <td><a href="/employees/delete/${emp.id}"><button style="color: red">USUŃ </button></a> </td>
            </tr>
        </c:forEach>
    </table>

    <br/><br/>

    <div align="center"><a href="/company"><button style="color: darkred">WRÓĆ</button> </a></div>
</sec:authorize>
</body>
</body>
</html>