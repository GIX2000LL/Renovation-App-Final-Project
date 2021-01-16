<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PERSON FORM</title>
</head>
<body>

    <div>
        <h3 style="color: blue">ZAREJESTRUJ SIĘ W SYSTEMIE</h3>
        <form method="post" action="/registration">
            <table>
            <form:form method="post" modelAttribute="user">
                <tr><td>IMIĘ</td> <td>  <form:input path="firstName"/> </td></tr><br/>
                <tr><td>NAZWISKO</td> <td>  <form:input path="lastName"/> </td></tr><br/>
                <tr><td>HASŁO</td> <td>  <form:input path="password" type="password"/> </td></tr><br/>
                <tr><td>EMAIL</td> <td>  <form:input path="email" type="email"/> </td></tr><br/>
                <tr><td>TELEFON</td> <td>  <form:input path="phoneNumber"/> </td></tr><br/>
            </form:form>

                <tr><td>NAZWA FIRMY</td> <td>  <input type="text" name="companyName"/> </td></tr><br/>
                <tr></tr><td></td><br/>
                <tr><td></td><td>  <input type="submit" value="WYŚLIJ"> </td></tr><br/>

            </table>
        </form>

    </div>

</body>
</html>