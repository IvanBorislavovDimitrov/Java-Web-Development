<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="templates/navbar.jsp"/>
</header>

<main>
    <p class="h1 display-3 text-center">Upload</p>
    <hr class="my-3">
    <div class="text-center form-group">
        <form action="/tube/upload" method="post">
            <label>
                Title<br/>
                <input type="text" name="title"/>
            </label>
            <br/>
            <label>
                Author<br/>
                <input type="text" name="author"/>
            </label>
            <br/>
            <label>
                YouTube Link<br/>
                <input type="text" name="youtubeId"/>
            </label>
            <br/>
            <label>
                Description<br/>
                <textarea cols="50" rows="5" type="text" name="description"></textarea>
            </label>
            <br/>
            <input class="btn btn-success" type="submit" value="Upload"/>
        </form>
    </div>
    <hr class="my-3">
</main>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
