//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.models.CustomerModel;
import com.bank.utils.MyDbConnection;
import com.bank.utils.Utils;

public class CustomerDAO {
	
	public int insertCustomerDetails(CustomerModel customer) {
		int numOfRowsAffected=0;
		String query="insert into customer (name,address,email,mobile) values(?,?,?,?)";
		try {
			MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
			Connection con=connection.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,customer.getName());
			statement.setString(2,customer.getAddress());
			statement.setString(3,customer.getEmail());
			statement.setString(4,customer.getMobile());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("insert"+e);
		}
		return numOfRowsAffected;
	}
	public ResultSet getData(int cusId) throws SQLException {
		MyDbConnection connection=new MyDbConnection(Utils.connectionClass, Utils.connectionUrl, Utils.connectionUsername, Utils.connectionPassword);
		Connection con=connection.getConnection();
		String query="select * from customer where id = ?";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setInt(1,cusId);
		ResultSet rs=statement.executeQuery();
		con.close();
		return rs;
	}
}
