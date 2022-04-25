//$Id$
package com.bank.bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.database.AccountDAO;
import com.bank.database.FixedDepositAccountDAO;
import com.bank.database.TransactionsDAO;
import com.bank.models.AccountModel;
import com.bank.models.FixedDepositModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;


public class FixedDepositAccount implements AccountOperations {
	AccountModel customer;
	FixedDepositModel fdModel;
	FixedDepositAccountDAO database;
	AccountDAO account;
	public FixedDepositAccount(AccountModel customer) {
		this.customer=customer;
		account= new AccountDAO();
		database=new FixedDepositAccountDAO();
		try {
			ResultSet rs=database.getData(customer.getAccountNumber());
			while(rs.next()) {
				fdModel=new FixedDepositModel(rs.getString("accountNumber"), rs.getString("fdAccountNumber")
						,rs.getDouble("balance"), rs.getString("createdAt"), rs.getString("updatedAt"), 
						rs.getDouble("interest"), rs.getInt("numberOfMonths"), rs.getDouble("amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deposit(double amount) {
	}
	@Override
	public String withdraw(double amount) {
		if(fdModel!=null) {
			if(fdModel.getBalance()==0) {
				return "Your account balance is ZERO[0]";
			}
			else if(fdModel.getBalance()<amount) {
				return "Insuffecient funds\n your available balance is "+fdModel.getBalance();
			}
			else {
				String date=Utils.currentDate();
				 double creditAmount=0;
				 double debitAmount=amount;
				 double balance=fdModel.getBalance()-amount;
				 double accountBalance=customer.getBalance()+amount;
				 fdModel.setBalance(balance);
				 customer.setBalance(accountBalance);
				 TransactionModel transaction=new TransactionModel(date,fdModel.getfdAccountNumber(),fdModel.getAccountNumber(), creditAmount, debitAmount);
				 database.updateBalance(fdModel.getfdAccountNumber(), balance);
				 TransactionsDAO transactions=new TransactionsDAO();
				 transactions.insertTransaction(transaction);
				 account.updateBalance(fdModel.getAccountNumber(), account.getBalance(fdModel.getAccountNumber())+amount);
				 return "success";
			}
		}
		else {
			return "No Account found";
		}
	}
	public String createAccount(double depositAmount,int noOfMonths) {
		String fdAccountNumber;
		
		fdAccountNumber="FD"+customer.getAccountNumber();
		System.out.println("you are successfully created your Fixed Deposit account");
		System.out.println("Your Fixed Deposit account number is "+fdAccountNumber);
		FixedDepositModel fdModel=new FixedDepositModel(customer.getAccountNumber(), fdAccountNumber, depositAmount, Utils.currentDate(),
				Utils.currentDate(), Utils.FD_INTEREST, noOfMonths, depositAmount);
		FixedDepositAccountDAO rdAccount=new FixedDepositAccountDAO();
		rdAccount.insertFixedAccountDetails(fdModel);
		TransactionModel trModel=new TransactionModel(Utils.currentDate(), fdModel.getfdAccountNumber(), customer.getAccountNumber(), depositAmount,0);
		TransactionsDAO transaction=new TransactionsDAO();
		transaction.insertTransaction(trModel);
		return fdAccountNumber;
	}
}