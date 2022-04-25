//$Id$
package com.bank.bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.database.AccountDAO;
import com.bank.database.TransactionsDAO;
import com.bank.models.AccountModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;

public class AccountToAccountTransfer implements AccountOperations {
	AccountModel myAccount;
	AccountModel toAccount;
	AccountDAO database;
	
	public AccountToAccountTransfer(String toAccountNumber,AccountModel myAccount) {
		database= new AccountDAO();
		this.myAccount=myAccount;
		try {
			ResultSet rs=database.getData(toAccountNumber);
			while(rs.next()) {
				toAccount= new AccountModel(rs.getInt("customerId"), rs.getString("branchCode"), toAccountNumber, rs.getString("userId"), rs.getString("password"), rs.getDouble("balance"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deposit(double amount) {
		String date=Utils.currentDate();
		String fromAccount=myAccount.getAccountNumber();
		String toAccountNumber=toAccount.getAccountNumber();
		double creditAmount=amount;
		double debitAmount=0;
		double balance=toAccount.getBalance()+amount;
		toAccount.setBalance(balance);
		TransactionModel transaction=new TransactionModel(date, toAccountNumber, fromAccount, creditAmount, debitAmount);
		TransactionsDAO transactions=new TransactionsDAO();
		transactions.insertTransaction(transaction);
		database.updateBalance(toAccountNumber, balance);
	}

	@Override
	public String withdraw(double amount) {
		if(myAccount.getBalance()==0) {
			return "Your account balance is ZERO[0]";
		}
		else if(myAccount.getBalance()<amount) {
			return "Insuffecient funds\n your available balance is "+myAccount.getBalance();
		}
		else {
			String date=Utils.currentDate();
			String fromAccount=myAccount.getAccountNumber();
			String toAccountNumber=toAccount.getAccountNumber();
			double creditAmount=0;
			double debitAmount=amount;
			double balance=myAccount.getBalance()-amount;
			myAccount.setBalance(balance);
			TransactionModel transaction=new TransactionModel(date, fromAccount, toAccountNumber, creditAmount, debitAmount);
			TransactionsDAO transactions=new TransactionsDAO();
			transactions.insertTransaction(transaction);
			database.updateBalance(fromAccount, balance);
			deposit(amount);
			return "success";
		}
	}
}
