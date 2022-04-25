//$Id$
package com.bank.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDbConnection {
	Connection con;
	
	public MyDbConnection(String dbClass,String url,String uName,String password) {
		try {
			Class.forName(dbClass);
			con=DriverManager.getConnection(url,uName,password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return con;
	}
}
