<%@ page import="app.dtos.TubeServiceDto" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Details</title>
    <link rel="stylesheet" type="text/css" href="/styles/style.css"/>
</head>
<body>
<div align="center">
    <div class="rectangle" align="center">
        <% TubeServiceDto tubeServiceDto = (TubeServiceDto) request.getAttribute("tube"); %>
        <h1><%=tubeServiceDto.getTitle()%>
        </h1>
        <hr/>
        <h3><%=tubeServiceDto.getDescription() + ": " + tubeServiceDto.getYoutubeLink()%>
        </h3>
        <hr/>
    </div>
</div>
<div class="rectangle">
    <a class="pad-left" href="<%=tubeServiceDto.getYoutubeLink()%>">Link to video</a>
    <a><%=tubeServiceDto.getUploader()%></a>
    <br/>
    <div align="center">
        <a href="/">Back to Home page.</a>
    </div>
</div>

<br/>
<div align="center">
    &copy; CopyRight Java Web Team 2019. All rights reserved.
</div>
</body>
</html>