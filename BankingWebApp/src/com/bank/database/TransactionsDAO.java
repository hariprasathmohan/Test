//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.models.TransactionModel;
import com.bank.utils.MyDbConnection;
import com.bank.utils.Utils;

public class TransactionsDAO {
	
	public int insertTransaction(TransactionModel transaction) {
		int numOfRowsAffected=0;
		String query="insert into transactionHistory (date,fromAccount,toAccount,debit,credit) values(?,?,?,?,?)";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,transaction.getDate());
			statement.setString(2,transaction.getFromAccount());
			statement.setString(3,transaction.getToAccount());
			statement.setDouble(4,transaction.getDebitAmount());
			statement.setDouble(5,transaction.getCreditAmount());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("insert"+e);
		}
		return numOfRowsAffected;
	}
	public ResultSet getTransactions(String accountNumber) throws SQLException {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con=connection.getConnection();
		String query="select * from transactionHistory where fromAccount = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,accountNumber);
		ResultSet rs=statement.executeQuery();
		con.close();
		return rs;
	}
}
