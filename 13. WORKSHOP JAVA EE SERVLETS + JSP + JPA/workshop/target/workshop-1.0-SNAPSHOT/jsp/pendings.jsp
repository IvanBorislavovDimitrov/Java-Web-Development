<%@ page import="app.model.TubeBindingEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="app.model.views.UserViewModel" %>
<%@ page import="app.model.views.TubeViewModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pendings</title>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="templates/navbar.jsp"/>
</header>
<main>
    <hr class="my-3"/>
    <p style="color: blue;" class="h5 display-5 text-center">Pending Tubes</p>
    <hr class="my-3"/>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Author</th>
            <th scope="col">Status</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%int count = 0; %>
        <% for (TubeViewModel tubeViewModel : (List<TubeViewModel>) request.getAttribute("pendings")) { %>
        <tr class="table-light">
            <th scope="row"><%= ++count %>
            </th>
            <td><%=tubeViewModel.getTitle()%>
            </td>
            <td><%=tubeViewModel.getAuthor()%>
            </td>
            <td>
                <a class="btn btn-success" href="/tube/status?tubeUuid=<%=tubeViewModel.getUuid()%>&approve=true">Approve</a>
                <a class="btn btn-danger" href="/tube/status?tubeUuid=<%=tubeViewModel.getUuid()%>&approve=false">Decline</a>
            </td>
            <td><a href="/tube/details/<%=tubeViewModel.getUuid()%>">Details</a></td>
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
