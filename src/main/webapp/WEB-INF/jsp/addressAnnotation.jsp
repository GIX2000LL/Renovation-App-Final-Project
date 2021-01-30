<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>NIE MA ADRESU</title>

</head>
<body>
<sec:authorize access="isAuthenticated()">
<div class="container">
    <h1>NIE MASZ ADRESU, CZY CHCESZ GO DODAĆ ?</h1>
    <a href="/user/addressEdition/<sec:authentication property="principal.User.id" />"><button>DODAJ ADRES</button></a>
    <a href="/user/userDetails"><button>WRÓĆ</button></a>
</div>
</sec:authorize>
</body>

</html>