<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>EDYCJA PRACOWNIKA</title>
</head>

<body style="background-color: lightgray">
<sec:authorize access="isAuthenticated()">
    <header style="width: 100%;background-color: wheat">

        <div align="right"><h4 style="color: green">ZALOGOWANY UŻYTKOWNIK: <sec:authentication property="principal.User.firstName" />
        </h4><h4 style="color: green">FIRMA: <sec:authentication property="principal.User.company.name" />
        </h4></div>

    </header>
    <h3 align="center" style="color: blue">EDYTUJ PRACOWNIKA</h3>


    <table align="center">
        <form:form method="post" modelAttribute="employee">
            <tr><td><form:input path="company.id" type="hidden"/></td></tr>

            <tr><td>IMIĘ</td> <td>  <form:input path="firstName"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="firstName" cssStyle="color: red"/></td> </tr><br/>
            <tr><td>NAZWISKO</td> <td>  <form:input path="lastName"/> </td></tr><br/>
            <tr><td></td><td><form:errors path="lastName" cssStyle="color: red"/></td> </tr>
            <tr><td>STAWKA GODZINOWA</td> <td>  <form:input path="hourlyRate"/> </td></tr><br/>
            <tr><td>UMIEJETNOŚCI</td><td width="200px" height="auto"><form:checkboxes path="skills" items="${skills}" itemLabel="name" itemValue="id" /></td></tr>

            <tr><td></td><td><input style="color: blue" type="submit" value="ZATWIERDŹ ZMIANY"></td></tr>

        </form:form>
    </table>

    <br/><br/>

    <div align="center"><a href="/employees"><button style="color: darkred">WRÓĆ</button> </a></div>
</sec:authorize>
</body>
</body>
</html>