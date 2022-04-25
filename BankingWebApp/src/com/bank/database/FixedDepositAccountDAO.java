//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

import com.bank.models.FixedDepositModel;
import com.bank.utils.MyDbConnection;
import com.bank.utils.Utils;

public class FixedDepositAccountDAO implements DataBaseOperations{
	public FixedDepositAccountDAO() {
		//autoUpdate();
	}

	public int insertFixedAccountDetails(FixedDepositModel fdAccount) {
		int numOfRowsAffected=0;
		String query="insert into fixedDeposit (accountNumber,fdAccountNumber,createdAt,updatedAt,interest,balance,amount,numberOfMonths) values(?,?,?,?,?,?,?,?)";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,fdAccount.getAccountNumber());
			statement.setString(2,fdAccount.getfdAccountNumber());
			statement.setString(3,fdAccount.getDate());
			statement.setString(4,fdAccount.getUpdatedAt());
			statement.setDouble(5,fdAccount.getInterest());
			statement.setDouble(6,fdAccount.getBalance());
			statement.setDouble(7,fdAccount.getDepositAmount());
			statement.setDouble(8,fdAccount.getNoOfMonths());
			
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
		String query="update fixedDeposit set balance = ? where fdAccountNumber = ?";
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
		String query="select * from fixedDeposit where accountNumber = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,accountNumber);
		ResultSet rs=statement.executeQuery();
		con.close();
		return rs;
	}
	
	void autoUpdate() {
		Statement st;
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from fixedDeposit");
			while(rs.next()) {
				Period diff = Period.between(LocalDate.parse(rs.getString("updatedAt")),
						LocalDate.parse(Utils.currentDate()));
				long monthDifference=diff.toTotalMonths();
				if(monthDifference>0) {
					double balance=rs.getDouble("balance");
					balance+=balance*(rs.getDouble("interest")/100)*monthDifference;
					String query="update fixedDeposit set balance = ?, updatedAt = ? where fdAccountNumber = ?";
					PreparedStatement statement=Utils.conn.prepareStatement(query);
					statement.setDouble(1, balance);
					statement.setString(2, Utils.currentDate());
					statement.setString(3, rs.getString("fdAccountNumber"));
					statement.executeUpdate();
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
		String query="select balance from fixedDeposit where fdAccountNumber = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,accountNumber);
		ResultSet rs=statement.executeQuery();
		rs.next();
		con.close();
		return rs.getDouble("balance");
		
	}

}
