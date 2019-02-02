<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create a tube</title>
    <link rel="stylesheet" type="text/css" href="/styles/style.css"/>
</head>
<body>
<center>
    <div class="rectangle">
        <h1>Create a product</h1>
        <hr/>
        <form method="post" action="/tubes/create">
            <div>
                <label>Title:</label>
                <input name="title" type="text"/>
            </div>
            <hr/>
            <div>
                <label>Description:</label>
                <textarea rows="5" name="description"></textarea>
            </div>
            <hr/>
            <div>
                <label>YouTubeLink:</label>
                <input name="youtubeLink" type="text"/>
            </div>
            <hr/>
            <div>
                <label>Uploader:</label>
                <input name="uploader" type="text"/>
            </div>
            <hr/>
            <input style="margin: 20px" class="buttonButton" type="submit" value="Create Product"/>
        </form>
        <hr/>
        <a href="/">Back to Home page.</a>
    </div>
</center>
<div align="center">
    &copy; CopyRight Java Web Team 2019. All rights reserved.
</div>
</body>
</html>