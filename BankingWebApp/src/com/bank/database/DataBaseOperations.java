//$Id$
package com.bank.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBaseOperations {
	public int updateBalance(String accountNumber,double balance);
	public ResultSet getData(String accountNumber) throws SQLException;
	public double getBalance(String accountNumber) throws SQLException;
}
