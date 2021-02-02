<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>KASOWANIE PRACOWNIKA</title>

</head>
<body>
<sec:authorize access="isAuthenticated()">
    <div class="container">
        <h1>CZY NAPEWNO CHCESZ USUNĄC PRACOWNIKA</h1>
        <a href="/user/addressEdition/${employeeId}"><form method="post"><button>USUŃ</button></form> </a>
        <a href="/employees"><button>WRÓĆ</button></a>
    </div>
</sec:authorize>
</body>

</html>