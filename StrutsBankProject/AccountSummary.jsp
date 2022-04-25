<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ include file="header.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="main">
<table border="1">
<tr align="center">
<th>Account no.</th>
<th>Bank name</th>
<th>Balance</th>
<th>Show Transactions</th>
</tr>
<tr align="center">
<td><s:property value="#session.myAccount.accountNumber"/> </td>
<td> <s:property value="bank.name"/> </td>
<td id="balance"> <a href="#" id="balancelink" onclick="showBalance(<s:property value="#session.myAccount.accountNumber"/>)">Click here for balance</a> </td>
<td> <a href="viewStatement">Click for transactions</a> </td>
</tr>
</table>
</div>
<script type="text/javascript">
function showBalance(accountNumber) {
	var url = 'GetBalance.jsp?accountNumber=' + accountNumber;
	console.log(accountNumber);
	var ajax = new XMLHttpRequest();
	ajax.open('GET', url);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			var bal = document.getElementById("balance");
			console.log(ajax.responseText);
			bal.innerHTML = ajax.responseText;
			bal.style.display='inline';
		}
	}
	ajax.send();
}
</script>
</body>
</html>