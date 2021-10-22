package com.mdrain.database;

import java.util.Properties;

public final class DataBaseConfig {

	private static final String DRIVER   = "mysql";
	private static final String PORT     = "3306";
	private static final String DATABASE = "mdraineu_aps-printers?useUnicode=true&amp;characterEncoding=UTF-8";
	private static final String USER     = "mdraineu_petar";
	private static final String PASSWORD = "000000";
	private static final String FORNAME = "com.mysql.jdbc.Driver";
	
	public static String getUrl() {
		
		return "jdbc:" 
		       + DRIVER 
		       + "://mysql3000.mochahost.com:" 
		       + PORT + "/" 
		       + DATABASE;
 
	}

	public static Properties getCredentials() {
		
		Properties credentials = new Properties();
		
		if(USER != null) credentials.put("user", USER);
		if(PASSWORD != null) credentials.put("password", PASSWORD);
		
		return credentials;
	}
	
	public static String getForName() {
		
		String forName = (FORNAME);		
		return forName;

	}
}
