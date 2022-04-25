<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" href="MyStyle.css">
</head>
<body>
<%! String action;%>
<% 
	action= request.getAttribute("action").toString();
	if(action.equals("account")){%>
	
	<div class="sign"><%="Your Account Number is : "+request.getAttribute("accountNumber") %><br></div>
	<div class="sign"><%="Your User Name is : "+request.getAttribute("userId") %><br></div>
	<a href="login.html">Login</a><br>
		<%		
	} else if(action.equals("FD")) {%>
		
	<div class="sign">	<%="Your Fixed Deposit Account Number is : "+request.getAttribute("accountNumber") %><br></div>
		<a href="choice.jsp">Home</a><br>
	<%
	} else if(action.equals("RD")) {%>
	
	<div class="sign"><%="Your Recurring Deposit Account Number is : "+request.getAttribute("accountNumber") %><br></div>
	<a href="choice.jsp">Home</a><br>
<%}
%>
</body>
</html>