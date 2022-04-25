//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bank.utils.MyDbConnection;
import com.bank.utils.Utils;

public class DatabaseCreation {
	public static int getSize() {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con=connection.getConnection();
		int size=0;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) as size from account");
			rs.next();
			size=rs.getInt("size");
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return size;
	}
	public static void createTbl() {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con=connection.getConnection();
		if(true) {
			try {
				
				if(con!=null) {
					System.out.println("con ok--------------------------------------------------------------------------------");
				}
				
				  con.createStatement().executeUpdate("DROP TABLE IF EXISTS customer");
				  con.createStatement().executeUpdate("DROP TABLE IF EXISTS account");
				  con.createStatement().executeUpdate("DROP TABLE IF EXISTS transactionHistory"); 
				  con.createStatement().executeUpdate("DROP TABLE IF EXISTS fixedDeposit");
				  con.createStatement().executeUpdate("DROP TABLE IF EXISTS bank"); 
				  con.createStatement().executeUpdate("DROP TABLE IF EXISTS branch");
				  con.createStatement().executeUpdate("DROP TABLE IF EXISTS recurringDeposit");
				  con.createStatement().executeUpdate("DROP TABLE IF EXISTS atm");
				 
				con.createStatement().executeUpdate("create table customer("
						+ "id SERIAL PRIMARY KEY," 
						+ "  name VARCHAR(50) NOT NULL," 
						+ "  address VARCHAR(250) NOT NULL,"
						+ " email VARCHAR(50) NOT NULL,"
						+ " mobile VARCHAR(50) NOT NULL)");
				con.createStatement().executeUpdate("create table account("
						+ "accountNumber VARCHAR(50) NOT NULL,"
						+ "branchCode VARCHAR(5) NOT NULL,"
						+ "customerId INT,"
						+ "userId  VARCHAR(50) NOT NULL,"
						+ "password  VARCHAR(50) NOT NULL,"
						+ "balance FLOAT(50))");
				con.createStatement().executeUpdate("create table transactionHistory("
						+ "id SERIAL PRIMARY KEY,"
						+ "date VARCHAR(50) NOT NULL,"
						+ "fromAccount VARCHAR(50) NOT NULL,"
						+ "toAccount VARCHAR(50),"
						+ "debit FLOAT(50),"
						+ "credit FLOAT(50))");
				con.createStatement().executeUpdate("create table fixedDeposit("
						+ "id SERIAL PRIMARY KEY,"
						+ "accountNumber VARCHAR(50) NOT NULL,"
						+ "fdAccountNumber VARCHAR(50) NOT NULL,"
						+ "createdAt VARCHAR(50) NOT NULL,"
						+ "updatedAt VARCHAR(50) NOT NULL,"
						+ "interest FLOAT(50),"
						+ "balance FLOAT(50),"
						+ "amount FLOAT(50),"
						+ "numberOfMonths INT)");
				con.createStatement().executeUpdate("create table recurringDeposit("
						+ "id SERIAL PRIMARY KEY,"
						+ "accountNumber VARCHAR(50) NOT NULL,"
						+ "rdAccountNumber VARCHAR(50) NOT NULL,"
						+ "createdAt VARCHAR(50) NOT NULL,"
						+ "updatedAt VARCHAR(50) NOT NULL,"
						+ "interest FLOAT(50),"
						+ "balance FLOAT(50),"
						+ "amount FLOAT(50),"
						+ "numberOfMonths INT)");
				con.createStatement().executeUpdate("create table bank("
						+ "id SERIAL PRIMARY KEY,"
						+ "name VARCHAR(50) NOT NULL)");
				con.createStatement().executeUpdate("create table branch("
						+ "id SERIAL PRIMARY KEY,"
						+ "bankName VARCHAR(50),"
						+ "code VARCHAR(5) NOT NULL,"
						+ "name VARCHAR(50) NOT NULL,"
						+ "ifsc VARCHAR(50) NOT NULL)");
				Utils.DB_EXISTS=true;
				con.close();
			}catch (SQLException e) {
				System.out.println("create"+e.getMessage());
				Utils.DB_EXISTS=true;
			}
		}
	}
}