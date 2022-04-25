//$Id$
package com.bank.bank;

public interface AccountOperations {
	public abstract void deposit(double amount);
	public abstract String withdraw(double amount);
}