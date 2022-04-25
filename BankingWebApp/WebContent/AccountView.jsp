<%@page import="com.bank.database.RecurringDepositAccountDAO"%>
<%@page import="com.bank.database.FixedDepositAccountDAO"%>
<%@page import="com.bank.database.AccountDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.bank.database.TransactionsDAO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.bank.models.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
<link rel="stylesheet" href="MyStyle.css"> 	
</head>
<body>
<%String accountNumber; 
TransactionsDAO transactions = new TransactionsDAO();
AccountDAO account=new AccountDAO();
FixedDepositAccountDAO fd=new FixedDepositAccountDAO();
RecurringDepositAccountDAO rd=new RecurringDepositAccountDAO();
double balance=10000000;
String action;

		accountNumber = request.getParameter("accountNumber");
		action = request.getParameter("action");
		
	if(action.equals("savings"))
	{
		balance = account.getBalance(accountNumber);
		out.print("<div class='sign'>Savings Account</div>");
	} else if(action.equals("FD"))
	{
		balance = fd.getBalance(accountNumber);
		out.print("<div class='sign'>Fixed Deposit Account</div>");
	} else if(action.equals("RD"))
	{
		balance = rd.getBalance(accountNumber);
		out.print("<div class='sign'>Recurring Deposit Account</div>");
	}
%><br><br>
	<div class="sign">Account number : <%= accountNumber%><br><br></div>
	<div class="sign">Account Balance : <%= balance %><br><br></div>
	<input class="forgot" type="submit" id="show" value="Show Transactions" onclick="showTransactions('<%= accountNumber%>')"><br><br><br>
	<div id="showTrans">
		  <br> <br> <br>
	</div>
	<script type="text/javascript">
		function showTransactions(accountNumber) {
			console.log('Im IN' + accountNumber)
			var show = document.getElementById("show");
			var ajax = new XMLHttpRequest();
			var url = 'ViewTransactions.jsp?accountNumber=' + accountNumber;
			ajax.open('GET', url , true);
			ajax.onreadystatechange = function(){
				console.log(ajax.readyState+'     '+ajax.status);
				if(ajax.readyState == 4 && ajax.status == 200){
					console.log(ajax.responseText);
					var showTrans = document.getElementById("showTrans");
					showTrans.innerHTML = ajax.responseText;
					//showTrans.style.display = 'inline';
					show.disabled = true;
				}
			}
			ajax.send();
		}
	</script>
</body>
</html>