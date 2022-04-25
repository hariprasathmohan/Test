<%@page import="java.sql.Connection"%>
<%@page import="com.bank.utils.MyDbConnection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.bank.database.CustomerDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.bank.models.CustomerModel"%>
<%@page import="com.bank.models.AccountModel"%>
<%@page import="com.bank.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript">
	function showBal() {
		document.getElementById("showBal").style.display = "block";
		document.getElementById("ViewBalance").style.display = "none";
	}
	function hideBal() {
		document.getElementById("showBal").style.display = "none";
		document.getElementById("ViewBalance").style.display = "block";
	}
</script>
<link rel="stylesheet" href="MyStyle.css">

<title>Home</title>
</head>
<body onload="hideBal()">
<%!String name; %>
<% 
	AccountModel myAccount = (AccountModel) request.getSession().getAttribute("myAccount");
	CustomerModel customer = new CustomerModel();
	try{
		ResultSet rs=new CustomerDAO().getData(myAccount.getCustomerId()+1);
		while(rs.next()){
			name=rs.getString("name");
		}
	}
	catch(SQLException e){
		System.out.println(e.getMessage());
	}
%>
<h2 class="sign" >Welcome <%= name %></h2>
	<input class="submit" type="submit" id="ViewBalance" value="Show balance" onclick="showBal()"/>
<div id="showBal">
	<h3>Your Balance is <%=myAccount.getBalance() %></h3>
	<input class="submit" type="submit" id="hideBalance" value="Hide balance" onclick="hideBal()"/>
</div><br>
<div>
	<form class="form1" method="post" action="AccountView.jsp?action=savings&accountNumber=<%= myAccount.getAccountNumber()%>">
		<input class="submit" type="submit" id="myAccount" value="My Account"/>
	</form>
</div><br>
<div>
	<form class="form1" method="post" action="AccountTransfer.jsp">
		<input class="submit" type="submit" id="accountTransfer" value="Transfer to others"/>
	</form>
</div><br>
<div>
	<form class="form1" method="post" action="OtherDeposit.jsp">
		<input class="submit" type="submit" id="otherDeposits" value="Other Deposits"/>
	</form>
</div><br>
<div>
	<form class="form1" method="post" action="logout">
		<input class="submit" type="submit" id="logout" value="Logout" />
	</form>
</div><br>
</body>
</html>