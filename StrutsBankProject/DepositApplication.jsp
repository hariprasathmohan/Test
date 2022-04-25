<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="main" align="center">
	<p class="sign" align="center">Loan Application</p>
	<s:form class="form1" action="applyDepositAccount">
		<s:radio name="type" list="#{'FD':'Fixed Deposit','RD':'Recurring Deposit'}"></s:radio>
		<s:textfield class="un" type="text" name="accountNumber" placeholder="Account Number"></s:textfield>
		<s:textfield class="un" type="number" name="numOfMonth" placeholder="Number of Month"></s:textfield>
		<s:textfield class="un" type="number" name="amount" placeholder="Amount"></s:textfield>
		<s:submit class="submit" value="Create Account"></s:submit>
	</s:form>
</div>
</body>
</html>