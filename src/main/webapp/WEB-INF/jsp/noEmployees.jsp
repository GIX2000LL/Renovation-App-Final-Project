<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>NIE MASZ PRACOWNIKÓW</title>

</head>
<body>
<sec:authorize access="isAuthenticated()">
    <div class="container">
        <h1>W TWOJEJ FIRMIE NIE MA JESZCZE ŻADNEGO PRACOWNIKA, CZY CHCESZ ICH DODAĆ ?</h1>
        <a href="/employees/add"><button>STWÓRZ PRACOWNIKA</button></a>
        <a href="/company"><button>WRÓĆ</button></a>
    </div>
</sec:authorize>
</body>

</html>