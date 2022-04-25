//$Id$
package com.bank.controllers;

import java.io.IOException; 
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.database.AccountDAO;
import com.bank.models.AccountModel;

public class LoginServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userId,password;
		AccountDAO database = new AccountDAO();
		userId=req.getParameter("userId");
		password=req.getParameter("password");
		ResultSet rs;
		AccountModel myAccount = null;
		
		if(userId != null && password != null) {
			try {
				rs=database.getUser(userId);
				while(rs.next()) {
					myAccount= new AccountModel();
					myAccount.setAccountNumber(rs.getString("accountNumber"));
					myAccount.setBalance(rs.getDouble("balance"));
					myAccount.setPassword(rs.getString("password"));
					myAccount.setBranchCode(rs.getString("branchCode"));
					myAccount.setUserId(rs.getString("userId"));
					myAccount.setCustomerId(rs.getInt("customerId"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(myAccount!=null) {
				if(myAccount.Validateassword(password)) {
					 HttpSession session=req.getSession(); 
				     session.setAttribute("myAccount",myAccount);
				     resp.sendRedirect("choice.jsp");
				}else {
					req.setAttribute("error", "Your password is invalid");
					RequestDispatcher rd=req.getRequestDispatcher("/Error.jsp");
					rd.forward(req, resp);
				}
			}else {
				req.setAttribute("error", "Your UserId is invalid");
				RequestDispatcher rd=req.getRequestDispatcher("Error.jsp");
				rd.forward(req, resp);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
