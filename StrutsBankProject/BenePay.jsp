<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="header.jsp" %>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Beneficiery Pay</title>
</head>
<body>
<div class="main" align="center">
<p class="sign" align="center">Pay</p>
	<s:form class="form1" action="quickPay">
	<s:textfield class="un" type="text" name="name" value="%{name}" placeholder="Name" readonly="true"></s:textfield>
	<s:textfield class="un" type="text" name="accountNumber" value="%{accountNumber}" placeholder="Account Number" readonly="true"></s:textfield>
	<s:textfield class="un" type="text" name="ifsc" value="%{ifsc}" placeholder="IFSC code" readonly="true"></s:textfield>
	<s:textfield class="un" type="text" name="purpose" placeholder="purpose" ></s:textfield>
	<s:textfield class="un" type="number" name="amount" placeholder="amount"></s:textfield>
	<s:submit class="submit" value="Pay"></s:submit>
	</s:form> 
    <br>     
</div>
</body>
</html>