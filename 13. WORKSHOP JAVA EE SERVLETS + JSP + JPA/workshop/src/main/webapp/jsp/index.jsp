<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="templates/navbar.jsp"/>
</header>
<div class="container-fluid">

    <hr class="my-3">
    <main>
        <div class="jumbotron">
            <p class="h1 display-3">Welcome to MeTube&trade;!</p>
            <p class="h3">The simplest, easiest to use, most comfortable Multimedia Application.</p>
            <div>
                <a href="/login">Login</a> if you have an account or <a href="/register">Register</a> now and start
                tubing.
            </div>
        </div>
    </main>
    <hr class="my-3">
</div>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>