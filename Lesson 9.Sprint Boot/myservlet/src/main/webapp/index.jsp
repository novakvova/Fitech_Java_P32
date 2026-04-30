<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<a href="martin-servlet">Hello Martin</a>
<br/>
<h2>Введіть число:</h2>
<form action="multiplication" method="get">
    <input type="number" name="number" required>
    <button type="submit">Показати</button>
</form>
</body>
</html>