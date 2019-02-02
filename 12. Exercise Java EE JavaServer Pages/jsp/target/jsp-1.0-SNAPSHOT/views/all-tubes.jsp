<%@ page import="java.util.List" %>
<%@ page import="app.dtos.TubeServiceDto" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="/styles/style.css"/>
</head>
<body>
<div align="center">
    <div class="rectangle" align="center">
        <% String welcomeMessage = "All Tubes"; %>
        <h1><%=welcomeMessage%>
        </h1>
        <hr/>
        <%! private String info = "Check out tubes below"; %>
        <h3><%=info%>
        </h3>
        <hr/>
        <% List<TubeServiceDto> tubeServiceDtos = (List<TubeServiceDto>) request.getAttribute("tubes"); %>
        <% if (tubeServiceDtos == null || tubeServiceDtos.size() == 0) { %>
        <h3>No Tubes</h3>
        <% } else { %>
        <ul>
            <% for (TubeServiceDto tubeServiceDto : tubeServiceDtos) {%>
            <a href="<%=String.format("/tubes/details?tubeName=%s", tubeServiceDto.getTitle())%>">
                <%=tubeServiceDto.getTitle()%>
            </a>
            <br/>
            <% } %>
        </ul>
        <% } %>
    </div>


</div>
<div class="rectangle">
    <a class="button pad-left" href="/tubes/create">Create tube</a>
    <a class="button" href="/tubes/all">All tubes</a>
</div>

<br/>
<div align="center">
    &copy; CopyRight Java Web Team 2019. All rights reserved.
</div>
</body>
</html>