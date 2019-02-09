<%@ page import="static app.constants.Constants.LOGGED_USER" %>
<%@ page import="app.model.views.TubeViewModel" %>
<%@ page import="java.util.List" %>
<%@ page import="app.model.views.UserSessionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="templates/navbar.jsp"/>
</header>
<div class="container-fluid">

    <hr class="my-3">
    <main>
        <hr class="my-3">
        <h3 class="text-center blue">
            Welcome, <%= ((UserSessionEntity) request.getSession().getAttribute(LOGGED_USER)).getUsername() %>
        </h3>
        <hr class="my-3">
    </main>
    <div class="container">
        <div class="row">
            <% List<TubeViewModel> tubeViewModels = (List<TubeViewModel>) request.getAttribute("tubes"); %>
            <% for (TubeViewModel tubeViewModel : tubeViewModels) { %>
            <div class="col-sm-4">
                <div class="text-center">
                    <iframe width="360px" height="240px"
                            src="https://img.youtube.com/vi/<%=tubeViewModel.getYoutubeId()%>/default.jpg"
                            frameborder="1"></iframe>
                    <p><%=tubeViewModel.getTitle()%>
                        <br/>
                        <%=tubeViewModel.getAuthor()%>
                    </p>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>