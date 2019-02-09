<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="templates/navbar.jsp"/>
</header>

<main>
    <p class="h1 display-3 text-center">Login</p>
    <hr class="my-3">
    <div class="text-center form-group">
        <form action="/login" method="post">
            <label>
                Username<br/>
                <input type="text" name="username"/>
            </label>
            <br/>
            <label>
                Password<br/>
                <input type="password" name="password"/>
            </label>
            <br/>
            <input class="btn btn-success" type="submit" value="Login"/>
        </form>
    </div>
    <hr class="my-3">

</main>

<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
