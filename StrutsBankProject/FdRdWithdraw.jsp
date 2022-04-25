<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="main" align="center">
	<p class="sign" align="center">Deposit Account</p>
    <s:form class="form1" action="fdrdWithdraw">
    <s:textfield class="un" type="text" name="depositAccountNumber" placeholder="Deposit Account Number"></s:textfield>
    <s:textfield class="un" type="number" name="amount" placeholder="Amount"></s:textfield>
    <s:submit class="submit" value="Withdraw"></s:submit>
    </s:form>
</div>
</body>
</html>