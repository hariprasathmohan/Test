//$Id$
package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bank.FixedDepositAccount;
import com.bank.bank.RecurringDepositAccount;
import com.bank.database.FixedDepositAccountDAO;
import com.bank.database.RecurringDepositAccountDAO;
import com.bank.models.AccountModel;

public class OtherDepositServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int numOfMonth;
	double amount = 0;
	String type, action;
	RequestDispatcher rd;
	AccountModel myAccount;
	String accountNumber;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		HttpSession session=req.getSession();
		AccountModel myAccount=(AccountModel) session.getAttribute("myAccount");
		
		if(req.getParameter("type")!=null) {
			type = req.getParameter("type");
		}if(req.getParameter("action")!=null) {
			action = req.getParameter("action");
		}if(req.getParameter("numOfMonth")!="") {
			
		}
		if(type.equals("fixeddeposit")) {
			FixedDepositAccount fdAccount = new FixedDepositAccount(myAccount);
			if(action.equals("create")) {
				amount=Double.parseDouble(req.getParameter("amountxt"));
				numOfMonth = Integer.parseInt(req.getParameter("numtxt"));
				accountNumber=fdAccount.createAccount(amount, numOfMonth);
				RequestDispatcher rd=req.getRequestDispatcher("AccountOpen.jsp");
				req.setAttribute("action", "FD");
				req.setAttribute("accountNumber", accountNumber);
				rd.forward(req, resp);
			}else if(action.equals("withdraw")) {
				amount=Double.parseDouble(req.getParameter("amountxt"));
				String messege = fdAccount.withdraw(amount);
				if(!messege.equals("success"))
				{
					req.setAttribute("error", messege);
					rd=req.getRequestDispatcher("Error.jsp");
					rd.forward(req, resp);
				}else {
					PrintWriter out= resp.getWriter();
					out.println("<script language='Javascript'>");
					out.println("alert('Transfered Sucessfully'); window.location.href ='choice.jsp'");
					out.println("</script>");
				}
			}else if(action.equals("view")) {
				FixedDepositAccountDAO fdDatabase=new FixedDepositAccountDAO();
				ResultSet rs;
				try {
					rs = fdDatabase.getData(myAccount.getAccountNumber());
					if (rs.next()) {
						rd=req.getRequestDispatcher("AccountView.jsp?action=FD&accountNumber="+rs.getString("fdAccountNumber"));
						rd.forward(req, resp);
					}else {
						req.setAttribute("error", "No Fixed Deposit Account Found");
						rd=req.getRequestDispatcher("Error.jsp");
						rd.forward(req, resp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else if(type.equals("recurringdeposit")) {
			RecurringDepositAccount rdAccount = new RecurringDepositAccount(myAccount);
			if(action.equals("create")) {
				amount=Double.parseDouble(req.getParameter("amountxt"));
				numOfMonth = Integer.parseInt(req.getParameter("numtxt"));
				accountNumber=rdAccount.createAccount(amount, numOfMonth);
				RequestDispatcher rd=req.getRequestDispatcher("AccountOpen.jsp");
				req.setAttribute("action" , "RD");
				req.setAttribute("accountNumber" , accountNumber);
				rd.forward(req, resp);
			}else if(action.equals("withdraw")) {
				amount=Double.parseDouble(req.getParameter("amountxt"));
				String messege = rdAccount.withdraw(amount);
				
				if(!messege.equalsIgnoreCase("success"))
				{
					req.setAttribute("error", messege);
					rd=req.getRequestDispatcher("Error.jsp");
					rd.forward(req, resp);
				}else {
					PrintWriter out= resp.getWriter();
					out.println("<script language='Javascript'>");
					out.println("alert('Transfered Sucessfully'); window.location.href ='choice.jsp'");
					out.println("</script>");
				}
			}else if(action.equals("view")) {
				RecurringDepositAccountDAO rdDatabase=new RecurringDepositAccountDAO();
				ResultSet rs;
				try {
					rs = rdDatabase.getData(myAccount.getAccountNumber());
					if (rs.next()) {
						rd=req.getRequestDispatcher("AccountView.jsp?action=RD&accountNumber="+rs.getString("rdAccountNumber"));
						rd.forward(req, resp);
					}else {
						req.setAttribute("error" , "No Reccuring Deposit Account Found");
						rd=req.getRequestDispatcher("Error.jsp");
						rd.forward(req, resp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}