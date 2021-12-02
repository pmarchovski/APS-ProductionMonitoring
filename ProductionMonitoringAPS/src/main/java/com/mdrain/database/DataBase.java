package com.mdrain.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DataBase {

	private static DataBase dbInstance;
	private static Connection connection;
	private static int connectionCount;
	private static int connectionCountInstance;
	
	private DataBase() {
		
	}
	
	
	public static DataBase getInstance() {
		
		if (dbInstance == null) {
			
			System.out.println("Number connections from instance = " + connectionCountInstance++);
			dbInstance = new DataBase();
		}
		
		return dbInstance;
	}
	
	public Connection getConnection() throws SQLException {
		
		
		try {
			Class.forName(DataBaseConfig.getForName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (connection == null || connection.isClosed()) {
			try {
				connection = DriverManager.getConnection(DataBaseConfig.getUrl(), DataBaseConfig.getCredentials());
				System.out.println("Number connections = " + connectionCount++);
				
			} catch (SQLException e) {
				Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
			}
		}	
		return connection;
	}
}
