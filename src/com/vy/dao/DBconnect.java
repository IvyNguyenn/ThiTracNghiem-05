package com.vy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnect {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost/thi_trac_nghiem?characterEncoding=UTF-8";
			con = DriverManager.getConnection(connectionUrl,"root", "");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "thi_trac_nghiem";
		String userName = "root";
		String password = "123456";
		return getMySQLConnection(hostName, dbName, userName, password);

	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?characterEncoding=UTF-8";
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
}
