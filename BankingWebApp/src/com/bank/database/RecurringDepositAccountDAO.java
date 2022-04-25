//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

import com.bank.models.RecurringDepositModel;
import com.bank.models.TransactionModel;
import com.bank.utils.MyDbConnection;
import com.bank.utils.Utils;

public class RecurringDepositAccountDAO implements DataBaseOperations {
	public RecurringDepositAccountDAO() {
		//autoUpdate();
	}
	public int insertRecurringAccountDetails(RecurringDepositModel rdAccount) {
		int numOfRowsAffected=0;
		String query="insert into recurringDeposit (accountNumber,rdAccountNumber,createdAt,updatedAt,interest,balance,amount,numberOfMonths) values(?,?,?,?,?,?,?,?)";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,rdAccount.getAccountNumber());
			statement.setString(2,rdAccount.getrdAccountNumber());
			statement.setString(3,rdAccount.getDate());
			statement.setString(4,rdAccount.getUpdatedAt());
			statement.setDouble(5,rdAccount.getInterest());
			statement.setDouble(6,rdAccount.getBalance());
			statement.setDouble(7,rdAccount.getDepositAmount());
			statement.setDouble(8,rdAccount.getNoOfMonths());
			
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
		String query="update recurringDeposit set balance = ? where accountNumber = ?";
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
	
	public int updateBalance(String accountNumber, double balance, String updatedAt) {
		int numOfRowsAffected=0;
		String query="update recurringDeposit set balance = ?, updatedAt = ? where rdAccountNumber = ?";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setDouble(1, balance);
			statement.setString(2, Utils.currentDate());
			statement.setString(3, accountNumber);
			
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
		String query="select * from recurringDeposit where accountNumber = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,accountNumber);
		ResultSet rs=statement.executeQuery();
		con.close();
		return rs;
	}	
	public void autoUpdate() {
		Statement st;
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from recurringDeposit");
			while(rs.next()) {
				Period diff = Period.between(LocalDate.parse(rs.getString("updatedAt")),
						LocalDate.parse(Utils.currentDate()));
				long monthDifference=diff.toTotalMonths();
				if(monthDifference>0) {
					double balance=rs.getDouble("balance");
					balance+=balance*(rs.getDouble("interest")/100)*monthDifference;
					balance+=rs.getDouble("amount");
					TransactionModel trModel=new TransactionModel(Utils.currentDate(), rs.getString("rdAccountNumber"), rs.getString("accountNumber"),
							 rs.getDouble("amount"),0);
					
					AccountDAO accDao=new AccountDAO();
					ResultSet rs1=accDao.getData(rs.getString("accountNumber"));
					double balance1=rs1.getDouble("balance"); 
					balance1-=rs.getDouble("amount");
					accDao.updateBalance(rs.getString("accountNumber"), balance1);
					TransactionsDAO transactionsDAO=new TransactionsDAO();
					transactionsDAO.insertTransaction(trModel);
					updateBalance(rs.getString("rdAccountNumber"), balance,  Utils.currentDate());
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double getBalance(String accountNumber) throws SQLException {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con=connection.getConnection();
		String query="select balance from recurringDeposit where rdAccountNumber = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,accountNumber);
		ResultSet rs=statement.executeQuery();
		rs.next();
		con.close();
		return rs.getDouble("balance");
	}
}
