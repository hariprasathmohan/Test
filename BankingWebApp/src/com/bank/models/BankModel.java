//$Id$
package com.bank.models;

public class BankModel {
	protected int bankId;
	protected String bankName;
	public BankModel(String bankName,int bankId) {
		this.bankId=bankId;
		this.bankName=bankName;
	}
	public BankModel() {}
}
