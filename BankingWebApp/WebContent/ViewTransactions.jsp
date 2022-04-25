<%@page import="java.sql.Connection"%>
<%@page import="com.bank.utils.MyDbConnection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.bank.database.TransactionsDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%!String accountNumber;
	ResultSet rs;
	TransactionsDAO transactions = new TransactionsDAO();%>
 <%
		  	accountNumber=request.getParameter("accountNumber");
		  try {
				ResultSet rs=transactions.getTransactions(accountNumber);
				if(rs==null) {%>
					<%= "No Data Available" %>
				<%}
				else
				{%>
					<%= "<table border="+"1"+" >"%>
					<%=
					"<tr>"+
		 			  "<th>Date</th>"+
		  			  "<th>Description</th>"+
		  			  "<th>Credit</th>"+
		   			  "<th>Debit</th>"+
		  			"</tr>"
					%>
					<% while(rs.next()) {%>
					<%= "<tr>"%>
					<%= "<th>" + rs.getString("date") + "</th>"%>
					<%= "<th>" + rs.getString("toAccount") + "</th>"%>
					<%= "<th>" + rs.getString("credit") + "</th>"%>
					<%= "<th>" + rs.getString("debit") + "</th>"%>		 			
					<%= "</tr>"%>
					<% } %>
					<%= "</table>"%>
					<%
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  %>