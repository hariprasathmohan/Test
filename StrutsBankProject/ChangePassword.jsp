<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Change password</title>
</head>
<body>
<div class="main" align="center">
    <p class="sign" align="center">Change Password</p>
    <s:form class="form1" action="changePass">
    <s:textfield class="un " type="text" name="oldPassword" placeholder="Old Password"></s:textfield>
    <s:textfield class="un " type="text" name="newPassword" placeholder="Password"></s:textfield>
    <s:textfield class="un " type="text" name="password" placeholder="Confirm Password"></s:textfield>
    <s:submit class="submit" value="change"></s:submit>
    <s:reset class="submit" value="reset"></s:reset>
    </s:form> 
     <br><br>
</div>
</body>
</html>