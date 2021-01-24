<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>REJSTRACJA UŻYTKOWNIKA</title>

</head>
<body style="background-color: lightgray;position: static">

    <div align="center" style="background-color: wheat;position: relative">
        <h2 style="color: blue">ZAREJESTRUJ SIĘ W SYSTEMIE</h2>


        <form method="post" action="/registration" style="background-color: lightgray">
            <table>
            <form:form method="post" modelAttribute="user">
                <tr><td>IMIĘ</td> <td>  <form:input path="firstName"/> </td></tr><br/>
                <tr><td></td><td><form:errors path="firstName" cssStyle="color: red"/></td> </tr><br/>

                <tr><td>NAZWISKO</td> <td>  <form:input path="lastName"/> </td></tr><br/>
                <tr><td></td><td><form:errors path="lastName" cssStyle="color: red"/></td> </tr><br/>

                <tr><td>HASŁO</td> <td>  <form:input path="password" type="password"/> </td></tr><br/>
                <tr><td></td><td><form:errors path="password" cssStyle="color: red"/></td> </tr><br/>

                <tr><td>EMAIL</td> <td>  <form:input path="email" type="email"/> </td></tr><br/>

                <tr><td>TELEFON</td> <td>  <form:input path="phoneNumber"/> </td></tr><br/>
                <tr><td></td><td><form:errors path="phoneNumber" cssStyle="color: red"/></td> </tr><br/>
            </form:form>

                <tr><td>NAZWA FIRMY</td> <td>  <input type="text" name="companyName"/> </td></tr><br/>
                <tr></tr><td></td><br/>
                <tr><td></td><td>  <input style="color: blue" type="submit" value="STWÓRZ PROFIL"> </td></tr><br/>
            </table>
        </form>


    </div>
    <div align="center" style="background-color: wheat;position:relative">
        <br/><br/>
        <table>
        <tr><td><a href="/login"><button style="color: green">PRZEJDŹ DO LOGOWANIA</button> </a></td>
            <td width="20px"></td>
            <td><a href="/"><button style="color: green" >WRÓĆ DO STRONY GŁÓWNEJ</button> </a></td></tr>
        </table>
    </div>
</body>
</html>