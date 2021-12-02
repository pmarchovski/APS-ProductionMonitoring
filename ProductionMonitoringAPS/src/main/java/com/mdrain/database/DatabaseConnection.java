package com.mdrain.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DatabaseConnection {

	
	public static Connection connection = null;
	
	public static Connection getConnection() {
		
		int count = 0;
		if (connection != null) {
			
			System.out.println("connection instance " + count++);
			
			return connection;
		} else
			try {
				if (connection == null || connection.isClosed()){
					return createConnection();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
		
	}
	
	
	private static Connection createConnection() {
		
		int count = 0;
		try {
			Class.forName(DataBaseConfig.getForName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		try {
			connection = DriverManager.getConnection(DataBaseConfig.getUrl(), DataBaseConfig.getCredentials());
			
			System.out.println("connection create " + count++);
			
			
		} catch (SQLException e1) {
		
			e1.printStackTrace();
		}
		
		return connection;		
	}	
}
