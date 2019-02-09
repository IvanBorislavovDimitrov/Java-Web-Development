<%@ page import="app.model.TubeBindingEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="app.model.views.UserViewModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="templates/navbar.jsp"/>
</header>
<main>
    <hr class="my-3"/>
    <%UserViewModel userViewModel = (UserViewModel) request.getAttribute("user");%>
    <p style="color: blue;" class="h5 display-5 text-center">@<%=userViewModel.getUsername()%></p>
    <p style="color: blue;" class="h5 display-5 text-center">(<%=userViewModel.getEmail()%>)</p>
    <hr class="my-3"/>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Author</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%int count = 0; %>
        <% for (TubeBindingEntity tubeBindingEntity : (List<TubeBindingEntity>) request.getAttribute("tubes")) { %>
        <tr class="table-light">
            <th scope="row"><%= ++count %></th>
            <td><%=tubeBindingEntity.getTitle()%></td>
            <td><%=tubeBindingEntity.getAuthor()%></td>
            <td><a href="/tube/details/<%=tubeBindingEntity.getUuid()%>">Details</a></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <hr class="my-3"/>
</main>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
