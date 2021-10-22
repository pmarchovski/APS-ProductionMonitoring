package com.mdrain.database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataBaseConnect {
	
    int counter1 = 0;
   static int counter = 0;
    
	public Connection connection;
	public static DataBaseConnect instance = null;

	public Connection connect() {
		
		try {
			Class.forName(DataBaseConfig.getForName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(DataBaseConfig.getUrl(), DataBaseConfig.getCredentials());
		
			counter1++;
			System.out.println("Counter1 " + counter1);
			
			return connection;
		} catch (SQLException e1) {
		
			e1.printStackTrace();
		}

		return null;
	}
	
	
	public static DataBaseConnect getInstance() {

		if(instance == null) {
			instance = new DataBaseConnect();
			counter++;
			System.out.println("Counter2 " + counter);
		}
		
		return instance;
	}
}
