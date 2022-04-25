<%@page import="java.sql.SQLException"%>
<%@page import="com.bank.models.BranchModel"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.bank.bank.Bank"%>
<%@page import="com.bank.utils.MyDbConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%= "<option disabled selected>Select Branch</option>" %>
<%
Bank bank=Bank.getInstance();	
try {
	ResultSet rs=bank.getBranchOfBank(request.getParameter("bank"));
	if(rs!=null){
		while(rs.next()){
			BranchModel branchModel=new BranchModel();
			branchModel.setBankName(rs.getString("bankName"));
			branchModel.setBranchCode(rs.getString("code"));
			branchModel.setBranchId(rs.getInt("id"));
			branchModel.setBranchName(rs.getString("name"));
			branchModel.setIFSC(rs.getString("ifsc"));
			String branchName=rs.getString("name");
			String branchCode=rs.getString("code");
			
			%>
		<%= "<option value=" + branchCode + ">"+ branchName + "</option>" %>
	<%}
	}
} catch (SQLException e) {
	e.printStackTrace();
}
%>
