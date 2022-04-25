//$Id$
package com.bank.models;

public class TransactionModel {
	private String date;
	private String fromAccount;
	private String toAccount;
	private double creditAmount;
	private double debitAmount;
		
	public TransactionModel(String date, String fromAccount, String toAccount, double creditAmount, double debitAmount) {
		this.date = date;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.creditAmount = creditAmount;
		this.debitAmount = debitAmount;
	}
	
	/*
	 * public TransactionModel(int id, String date, double creditAmount, double debitAmount, double balance) { this.id = id; this.date = date; this.creditAmount = creditAmount; this.debitAmount =
	 * debitAmount; this.balance = balance; }
	 */
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}
}
