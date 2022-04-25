//$Id$
package com.bank.models;

public class RecurringDepositModel extends AccountModel{
	private String rdAccountNumber;
	private double balance;
	private String date;
	private String updatedAt;
	private double interest;
	private int noOfMonths;
	private double depositAmount;
	
	
	
	public RecurringDepositModel(String accountNumber, String rdAccountNumber, double balance, 
			String date, String updatedAt, double interest, int noOfMonths, double depositAmount) {
		super(accountNumber);
		this.rdAccountNumber = rdAccountNumber;
		this.balance = balance;
		this.date = date;
		this.updatedAt = updatedAt;
		this.interest = interest;
		this.noOfMonths = noOfMonths;
		this.depositAmount = depositAmount;
	}
	public int getNoOfMonths() {
		return noOfMonths;
	}
	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public String getrdAccountNumber() {
		return rdAccountNumber;
	}
	public void setrdAccountNumber(String rdAccountNumber) {
		this.rdAccountNumber = rdAccountNumber;
	}
	public String getaccountNumber() {
		return super.accountNumber;
	}
	public void setaccountNumber(String accountNumber) {
		super.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
