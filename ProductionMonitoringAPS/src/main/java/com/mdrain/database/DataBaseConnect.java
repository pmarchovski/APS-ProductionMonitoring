package com.mdrain.database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataBaseConnect {

	
	public Connection connection;
	public DataBaseConnect instance = null;

	public Connection connect() {
  
		try {
			Class.forName(DataBaseConfig.getForName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(DataBaseConfig.getUrl(), DataBaseConfig.getCredentials());
		
			return connection;
		} catch (SQLException e1) {
		
			e1.printStackTrace();
		}

		return null;
	}
	
	
	public DataBaseConnect getInstance() {
		
		if(instance == null) {
			this.instance = new DataBaseConnect();
		}
		
		return instance;
	}
	
	
	
	
	
}
