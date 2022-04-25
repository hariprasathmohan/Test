//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.models.AccountModel;
import com.bank.utils.MyDbConnection;
import com.bank.utils.Utils;

public class AccountDAO implements DataBaseOperations {
	
	
	public int insertAccountDetails(AccountModel account,String password) {
		int numOfRowsAffected=0;
		String query="insert into account(accountNumber,branchCode,customerId,userId,password,balance) values(?,?,?,?,?,?)";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,account.getAccountNumber());
			statement.setString(2,account.getBranchCode());
			statement.setInt(3,account.getCustomerId());
			statement.setString(4,account.getUserId());
			statement.setString(5,password);
			statement.setDouble(6, account.getBalance());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("insert"+e);
		}
		return numOfRowsAffected;
	}
	@Override
	public int updateBalance(String accountNumber, double balance) {
		int numOfRowsAffected=0;
		String query="update account set balance = ? where accountNumber = ?";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setDouble(1, balance);
			statement.setString(2, accountNumber);
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOfRowsAffected;
	}
	@Override
	public ResultSet getData(String accountNumber) throws SQLException {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con=connection.getConnection();
		String query="select * from account where accountNumber = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,accountNumber);
		ResultSet rs=statement.executeQuery();
		con.close();
		return rs;
	}
	public ResultSet getUser(String userId) throws SQLException {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con=connection.getConnection();
		String query="select * from account where userId = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,userId);
		ResultSet rs=statement.executeQuery();
		con.close();
		return rs;
	}
	@Override
	public double getBalance(String accountNumber)  {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con=connection.getConnection();
		String query="select balance from account where accountNumber = ?";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			rs.next();
			con.close();
			return rs.getDouble("balance");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	
		
	}
}