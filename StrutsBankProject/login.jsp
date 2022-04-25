<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<title>Login</title>
</head>
<body>
<div class="main" align="center">
<p class="sign" align="center">Login</p>
<s:form action="login">
<s:textfield class="un" name="userId" placeholder="Username"></s:textfield>
<s:textfield class="un" name="password" type="password" placeholder="Password"></s:textfield>
<s:submit class="submit" name="submit" value="Login"></s:submit>
</s:form>
 <a href="register.jsp">     Dont have account? Register here</a> <br><br>
</div>
</body>
</html>