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

import com.bank.bank.AccountToAccountTransfer;
import com.bank.bank.Bank;
import com.bank.database.AccountDAO;
import com.bank.models.AccountModel;

public class AccountTransfer extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	String ifsc;
	String bankIfsc = null;
	String accountNumber;
	String name;
	String branchCode;
	double amount;
	AccountModel toAccount=null;
	AccountDAO database;
	Bank bankObj=Bank.getInstance();
	RequestDispatcher rd;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		database=new AccountDAO();
		HttpSession session=req.getSession();
		AccountModel myAccount=(AccountModel) session.getAttribute("myAccount");
		
		name=req.getParameter("name");
		accountNumber=req.getParameter("accountNumber");
		if(accountNumber!=null) {
			ResultSet rs;
			try {
				rs = database.getData(accountNumber);
				while(rs.next()) {
					toAccount=new AccountModel(rs.getString("accountNumber"));
					branchCode=rs.getString("branchCode");
					ResultSet rs1=bankObj.getIfscCode(branchCode);
					while(rs1.next()) 
						bankIfsc=rs1.getString("ifsc");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}if(toAccount!=null) {
				ifsc=req.getParameter("ifsc");
				if(bankIfsc.equals(ifsc)) { 
					amount=Double.parseDouble(req.getParameter("amount"));
					AccountToAccountTransfer transfer=new AccountToAccountTransfer(accountNumber, myAccount); 
					String messege=transfer.withdraw(amount);
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
				}
				else {
					req.setAttribute("error", "Invalid Ifsc Code");
					rd=req.getRequestDispatcher("Error.jsp");
					rd.forward(req, resp);
				}
			}
			else {
				req.setAttribute("error", "Invalid Account number");
				rd=req.getRequestDispatcher("Error.jsp");
				rd.forward(req, resp);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
