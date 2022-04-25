<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="header.jsp" %>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Add Beneficiery</title>
</head>
<body>
<div class="main" align="center">
<p class="sign" align="center">Pay</p>
	<s:form class="form1" action="addBene">
	<s:textfield class="un" type="text" name="name" placeholder="Name"></s:textfield>
	<s:textfield class="un" type="text" name="accountNumber" placeholder="Account Number"></s:textfield>
	<s:textfield class="un" type="text" name="ifsc" placeholder="IFSC code"></s:textfield>
	<s:submit class="submit" value="Add"></s:submit>
	</s:form>
    <br>     
</div>
</body>
</html>