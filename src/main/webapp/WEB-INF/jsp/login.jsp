
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body style="background-color: lightgray">

    <div style="background-color: wheat" align="center">
    <h1 style="color: green">ZALOGUJ SIĘ</h1>
       <h3 style="color: red">${SPRING_SECURITY_LAST_EXCEPTION.message}</h3>
    </div>

    <div style="background-color: bisque" align="center">
        <form action="/login" method="post">
            <table>
                <tr><td>EMAIL: </td><td><input type="text" name="username"></td></tr><br/>
                <tr><td>HASŁO: </td><td><input type="password" name="password"></td></tr><br/><br/>
                <tr><td></td><td><input style="color: green" name="submit" type="submit" value="ZALOGUJ SIĘ"></td></tr>
            </table>

        </form>

    </div>

    <div align="center"> <a href="/"><button style="color: green">WRÓĆ DO STRONY GŁÓWNEJ</button> </a> </div>


</body>
</html>