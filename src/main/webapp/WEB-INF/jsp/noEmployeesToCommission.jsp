<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>NIE MA PRACOWNIKÓW</title>

</head>
<body>
<sec:authorize access="isAuthenticated()">
    <div class="container">
        <h1>W TWOJEJ FIRMIE NIE MA JESZCZE ŻADNEGO PRACOWNIKA, DODAJ NAJPIERW PRACOWNIKA ŻEBY MÓC STWORZYĆ PIERWSZE ZLECENIE</h1>
        <table>
            <tr>
        <td><a href="/employees/add"><button style="color: blue">STWÓRZ PRACOWNIKA</button></a></td></tr>
       <tr><td><a href="/company"><button style="color: darkred"> WRÓĆ </button></a></td> </tr>
        </table>
    </div>
</sec:authorize>
</body>

</html>