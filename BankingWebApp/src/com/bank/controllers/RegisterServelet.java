//$Id$
package com.bank.controllers;

import java.io.IOException; 
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.database.AccountDAO;
import com.bank.database.CustomerDAO;
import com.bank.database.DatabaseCreation;
import com.bank.models.AccountModel;
import com.bank.models.Address;
import com.bank.models.CustomerModel;

public class RegisterServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int customerId = DatabaseCreation.getSize();
		String name=req.getParameter("name");
		String addressLine1=req.getParameter("adderss1");
		String addressLine2=req.getParameter("address2");
		String city=req.getParameter("city");
		String state=req.getParameter("state");
		String mobile=req.getParameter("mobile");
		String email=req.getParameter("email");
		Address address=new Address(addressLine1, addressLine2, city, state);
		String branchCode=req.getParameter("branch");
		String accountNumber=branchCode+customerId;
		String userId=name+accountNumber;
		String password=req.getParameter("password");
		double balance=10000;
		System.out.println(branchCode + accountNumber + userId);
		PrintWriter out= resp.getWriter();
		out.println("<script language='Javascript'>");
		if(validateName(name)) {
			if(validateEmail(email)) {
				if(mobile.length()==10) {
					if(password.length()>=6) {
						if(!req.getParameter("branch").equals("none")) {
							AccountModel account = new AccountModel(customerId, branchCode, accountNumber, userId, password, balance);
							CustomerModel customer = new CustomerModel(name, address, mobile, email);
							AccountDAO accountDatabase = new AccountDAO();
							accountDatabase.insertAccountDetails(account, password);
							CustomerDAO customerDatabase = new CustomerDAO();
							customerDatabase.insertCustomerDetails(customer);
							RequestDispatcher rd=req.getRequestDispatcher("AccountOpen.jsp");
							req.setAttribute("action", "account");
							req.setAttribute("accountNumber", accountNumber);
							req.setAttribute("userId", userId);
							rd.forward(req, resp);
							
							
						}else {
							out.println("alert('please select bank and branch'); history.back();");
						}
					}else {
						out.println("alert('password must have atleast 6 letters'); history.back();");
					}
				}else {
					out.println("alert('Mobile number must have 10 digits'); history.back();");
				}
			}else {
				out.println("alert('Invalid Email'); history.back();");
			}
		}else {
			out.println("alert('Invalid name'); history.back();");
		}
		out.println("</script>");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	boolean validateName(String name) {
		Pattern namePattern = Pattern.compile("^([a-zA-Z]{3,})$"); 
		Matcher match=namePattern.matcher(name);
		return match.find();
	}
	boolean validateEmail(String email) {
		Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$"); 
		Matcher match=emailPattern.matcher(email);
		return match.find();
	}
}
