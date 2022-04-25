<%@page import="com.bank.database.AccountDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String accountNumber = request.getParameter("accountNumber");
	AccountDao account = new AccountDao();
%><%= account.getBalance(accountNumber) %>