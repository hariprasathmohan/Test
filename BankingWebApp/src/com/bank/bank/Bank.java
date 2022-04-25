/*
 * Singleton class 
 */
package com.bank.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bank.models.BranchModel;
import com.bank.utils.MyDbConnection;
import com.bank.utils.Utils;


public class Bank {
	Scanner scan=new Scanner(System.in);
	private static Bank bank=null;		
	private Bank() {
		
		/*
		 * DatabaseCreation.createTbl(); BranchModel branch1=new BranchModel("SBI",1,1,"Madurai","SBI11","11"); BranchModel branch2=new BranchModel("SBI",1,2,"chennai","SBI12","12"); BranchModel
		 * branch3=new BranchModel("SBI",1,3,"Tenkasi","SBI13","13");
		 * 
		 * BranchModel branch4=new BranchModel("HDFC",2,1,"Madurai","HDFC21","21"); BranchModel branch5=new BranchModel("HDFC",2,2,"chennai","HDFC22","22"); BranchModel branch6=new
		 * BranchModel("HDFC",2,3,"Tenkasi","HDFC23","23");
		 * 
		 * BranchModel branch7=new BranchModel("TMB",3,1,"Madurai","TMB31","31"); BranchModel branch8=new BranchModel("TMB",3,2,"chennai","TMB32","32"); BranchModel branch9=new
		 * BranchModel("TMB",3,3,"Tenkasi","TMB33","33");
		 * 
		 * 
		 * insertBank(branch1); insertBank(branch4); insertBank(branch7); insertBranch(branch1); insertBranch(branch2); insertBranch(branch3); insertBranch(branch4); insertBranch(branch5);
		 * insertBranch(branch6); insertBranch(branch7); insertBranch(branch8); insertBranch(branch9);
		 */

	}
	
	public static Bank getInstance() {
		if (bank == null) {
			return new Bank();
		}
		return bank;
	}
	public ResultSet getIfscCode(String branchCode) throws SQLException {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con =connection.getConnection();
		String query="select ifsc from branch where code = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,branchCode);
		ResultSet rs=statement.executeQuery();
		con.close();
		return rs;
	}
	public int insertBranch(BranchModel bank) {
		int numOfRowsAffected=0;
		String query="insert into branch (bankName,code,name,ifsc) values(?,?,?,?)";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con =connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,bank.getBankName());
			statement.setString(2,bank.getBranchCode());
			statement.setString(3,bank.getBranchName());
			statement.setString(4,bank.getIFSC());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("insert"+e);
		}
		
		return numOfRowsAffected;
	}
	
	public int insertBank(BranchModel bank) {
		int numOfRowsAffected=0;
		String query="insert into bank (name) values(?)";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con =connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,bank.getBankName());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("insert"+e);
		}
		
		return numOfRowsAffected;
	}
	
	public ResultSet getAllBanks() throws SQLException {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con =connection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select name from bank");
		con.close();
		return rs;
	}

	public ResultSet getBranchOfBank(String bank) throws SQLException {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con =connection.getConnection();
		String query="select * from branch where bankName = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1,bank);
		ResultSet rs=statement.executeQuery();
		con.close();
		return rs;
	}
	
}
