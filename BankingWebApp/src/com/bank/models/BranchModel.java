//$Id$
package com.bank.models;

public class BranchModel extends BankModel {
	private int branchId;
	private String branchName;
	private String IFSC;
	private String branchCode;
	
	public BranchModel(String bankName, int bankId, int branchId, String branchName, String iFSC, String branchCode) {
		super(bankName, bankId);
		this.branchId = branchId;
		this.branchName = branchName;
		IFSC = iFSC;
		this.branchCode = branchCode;
	}
	
	public BranchModel() {	}

	public int getBranchId() {
		return branchId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getIFSC() {
		return IFSC;
	}
	public int getBankId() {
		return super.bankId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBankName() {
		return super.bankName;
	}
	public void setBankName(String bankName) {
		super.bankName=bankName;
	}
}
