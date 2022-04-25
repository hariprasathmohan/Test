<%@page import="com.bank.models.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Bank</title>
</head>
<body>
	<nav class="navbar">
	<div class="logo"><a href="accountSummary">BANK</a></div>
		<ul class="nav-links">
		<div class="menu">
			<li class="services">
			<a href="accountSummary">Accounts and profile</a>
			<ul class="dropdown">
				<li><a href="accountSummary">Account Summary</a></li>
				<li><a href="viewStatement">Account Satement</a></li>
				<li><a href="ChangePassword.jsp">Change Password</a></li>
				<li><a href="profile">Profile</a></li>
			</ul>
			</li>
			<li class="services">
			<a href="#">Payments and transfers</a>
			<ul class="dropdown">
				<li><a href="quickPay.jsp">Quick pay </a></li>
				<li><a href="beneList">Beneficiary pay</a></li>
				<li><a href="AddBene.jsp">Add Beneficiary</a></li>
			</ul>
			</li>
			<li class="services">
			<a href="#">Loan Accounts</a>
			<ul class="dropdown">
				<li><a href="applyLoan.jsp">Apply Loan </a></li>
				<li><a href="payLoan.jsp">Pay loan</a></li>
				<li><a href="viewLoans">View Statement</a></li>
			</ul>
			</li>
			<li class="services">
			<a href="#" onclick="load('homes','Register.jsp')">Deposit Accounts</a>
			<ul class="dropdown">
				<li><a href="DepositApplication.jsp">Create account</a></li>
				<li><a href="FdRdWithdraw.jsp">Withdraw</a></li>
				<li><a href="viewDeposits">View Accounts</a></li>
			</ul>
			</li>
			<li><a href="logout.action">Logout</a></li>
		</div>
		</ul>
	</nav>
</body>
</html>