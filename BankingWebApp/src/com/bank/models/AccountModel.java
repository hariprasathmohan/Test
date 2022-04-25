/*
 * Account details and operations.
 */
package com.bank.models;

public class AccountModel {
	private int customerId;
	private BranchModel bank;
	private String branchCode;
	protected String accountNumber;
	private String userId;
	private String password;
	private double balance;
	public AccountModel() {}
	public AccountModel(int customerId , String branchCode , String accountNumber , String userId, String password , double balance) {
		this.accountNumber=accountNumber;
		this.balance= balance;
		this.branchCode=branchCode;
		this.customerId=customerId;
		this.password=password;
		this.userId=userId;
	}

	public AccountModel( String accountNumber)
	{
		this.accountNumber=accountNumber;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getBank() {
		return bank.getBranchCode();
	}
	public void setBank(BranchModel bank) {
		this.bank = bank;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean Validateassword(String password) {
		if(this.password.equals(password))
			return true;
		else 
			return false;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	}