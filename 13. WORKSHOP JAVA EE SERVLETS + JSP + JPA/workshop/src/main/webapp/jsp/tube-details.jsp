<%@ page import="app.model.views.TubeViewModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="templates/navbar.jsp"/>
</header>
<main>
    <% TubeViewModel tubeViewModel = (TubeViewModel) request.getAttribute("tube"); %>
    <div class="container-fluid">
        <h2 class="text-center"><%=tubeViewModel.getTitle()%>
        </h2>
        <div class="row">
            <div class="col-md-6 my-5">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item"
                            src="https://www.youtube.com/embed/<%=tubeViewModel.getYoutubeId()%>"
                            allowfullscreen frameborder="0"></iframe>
                </div>
            </div>
            <div class="col-md-6 my-5">
                <h1 class="text-center text-info"><%=tubeViewModel.getAuthor()%></h1>
                <h3 class="text-center text-info"><%=tubeViewModel.getViews()%> Views</h3>
                <div class="h5 my-5 text-center"><%=tubeViewModel.getDescription()%></div>
            </div>
        </div>
    </div>
</main>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
