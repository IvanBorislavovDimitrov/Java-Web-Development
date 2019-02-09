<%@ page import="static app.constants.Constants.LOGGED_USER" %>
<%@ page import="app.model.views.UserSessionEntity" %>
<%@ page import="app.enums.UserRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<nav class="navbar navbar-expand-lg navbar-dark bg-color-dark">

    <a class="navbar-brand" href="/">MeTube&trade;</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-end row" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/home">Home</a>
                <% if (request.getSession().getAttribute(LOGGED_USER) != null) { %>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/tube/upload">Upload</a>
            </li>
            <% if (((UserSessionEntity) request.getSession()
                    .getAttribute(LOGGED_USER)).getUserRole().equals(UserRole.ADMIN)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/tube/pending">Pending</a>
                </li>
            <% } %>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
            <% } else { %>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>
</html>
