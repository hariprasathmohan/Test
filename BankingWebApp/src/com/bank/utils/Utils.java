//$Id$
package com.bank.utils;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
	public static final double FD_INTEREST=5;
	public static final double RD_INTEREST=7;
	public static boolean DB_EXISTS=false;
	public static Connection conn;
	public static String connectionClass;
	public static String connectionUrl;
	public static String connectionUsername;
	public static String connectionPassword;
	
	public static String currentDate() {
		String date;		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		   LocalDateTime now = LocalDateTime.now(); 
		   date=dtf.format(now);
		return date;
	}
}