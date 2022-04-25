<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error page</title>
<link rel="stylesheet" href="MyStyle.css">
</head>
<body>
<div class="sign"><h2><%=request.getAttribute("error") %></h2></div>
<button class="submit" onclick="history.back()">Go Back</button>
</body>
</html>