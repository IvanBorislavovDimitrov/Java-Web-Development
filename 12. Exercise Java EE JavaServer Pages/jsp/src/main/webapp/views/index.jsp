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
        <%! private String welcomeMessage = "Welcome to MeTube"; %>
        <h1><%=welcomeMessage%>
        </h1>
        <hr/>
        <%! private String info = "Cool app in beta version"; %>
        <h3><%=info%>
        </h3>
        <hr/>

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