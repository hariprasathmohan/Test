//$Id$
package com.bank.bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.database.AccountDAO;
import com.bank.database.RecurringDepositAccountDAO;
import com.bank.database.TransactionsDAO;
import com.bank.models.AccountModel;
import com.bank.models.RecurringDepositModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;

public class RecurringDepositAccount implements AccountOperations {
	AccountModel customer;
	RecurringDepositModel rdModel;
	RecurringDepositAccountDAO database;
	AccountDAO account;
	public RecurringDepositAccount(AccountModel customer) {
		this.customer=customer;
		account=new AccountDAO();
		database=new RecurringDepositAccountDAO();
		try {
			ResultSet rs=database.getData(customer.getAccountNumber());
			while(rs.next()) {
				rdModel=new RecurringDepositModel(rs.getString("accountNumber"), rs.getString("rdAccountNumber")
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
		if(rdModel!=null) {
			if(rdModel.getBalance()==0) {
				return "Your account balance is ZERO[0]";
			}
			else if(rdModel.getBalance()<amount) {
				return "Insuffecient funds\n your available balance is "+rdModel.getBalance();
			}
			else {
				String date=Utils.currentDate();
				 double creditAmount=0;
				 double debitAmount=amount;
				 double balance=rdModel.getBalance()-amount;
				 double accountBalance=customer.getBalance()+amount;
				 rdModel.setBalance(balance);
				 customer.setBalance(accountBalance);
				 TransactionModel transaction=new TransactionModel(date,rdModel.getrdAccountNumber(),rdModel.getAccountNumber(), creditAmount, debitAmount);
				 database.updateBalance(rdModel.getrdAccountNumber(), balance);
				 TransactionsDAO transactions=new TransactionsDAO();
				 transactions.insertTransaction(transaction);
				 account.updateBalance(rdModel.getAccountNumber(), account.getBalance(rdModel.getAccountNumber())+amount);
				 return "success";
			}
		}
		else {
			return "No Account found";
		}
	}
	public String createAccount(double depositAmount,int noOfMonths) {
		String rdAccountNumber;
		rdAccountNumber="RD"+customer.getAccountNumber();
		System.out.println("you are successfully created your Fixed Deposit account");
		System.out.println("Your Fixed Deposit account number is "+rdAccountNumber);
		RecurringDepositModel rdModel=new RecurringDepositModel(customer.getAccountNumber(), rdAccountNumber, depositAmount, Utils.currentDate(),
				Utils.currentDate(), Utils.FD_INTEREST, noOfMonths, depositAmount);
		RecurringDepositAccountDAO rdAccount=new RecurringDepositAccountDAO();
		rdAccount.insertRecurringAccountDetails(rdModel);
		TransactionModel trModel=new TransactionModel(Utils.currentDate(), rdModel.getrdAccountNumber() , customer.getAccountNumber(), depositAmount, 0);
		TransactionsDAO transaction=new TransactionsDAO();
		transaction.insertTransaction(trModel);
		return rdAccountNumber;
	}
}