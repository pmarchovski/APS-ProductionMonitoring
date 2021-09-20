package com.mdrain.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataBaseConnectNew {

	

	    private static MysqlDataSource ds = null;

	    public static MysqlDataSource getConnection() {
	        if (ds == null) {
	            
	        	
	        	String db_url = "mysql3000.mochahost.com";
	        	String db_user = "mdraineu_petar";
	        	String db_password = "000000";
	        	int db_port = 3306;
	        	
	          getDataSource(db_url, db_user, db_password, db_port);
	        }
	        String db_name = "mdraineu_aps-printers?useUnicode=true&amp;characterEncoding=UTF-8";
	        ds.setDatabaseName(db_name);
	        return ds;
	    }

	    private static void getDataSource(String db_url, String db_user, String db_password, int db_port) {
	        try {
	            ds = new MysqlDataSource();
	            ds.setServerName(db_url);
	            ds.setUser(db_user);
	            ds.setPassword(db_password);
	            ds.setPort(db_port);
	        } catch (Exception e) {
	            System.out.println("MysqlDataSource err: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}

