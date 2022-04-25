<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Welcome</title>
</head>
<body>
<div class="main">
	<p class="title"><%="Your Account Number is : "+request.getAttribute("accountNumber") %><br></p>
	<p class="title"><%="Your User Name is : "+request.getAttribute("userId") %><br></p>
	<p class="title"><%="Your Password is : "+request.getAttribute("password") %><br></p>
	<a href="login.jsp">         Login</a><br>
</div>
</body>
</html>