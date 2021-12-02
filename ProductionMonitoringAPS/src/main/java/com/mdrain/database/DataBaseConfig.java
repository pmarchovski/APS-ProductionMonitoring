package com.mdrain.database;

import java.util.Properties;

  public final class DataBaseConfig {

	private static final String DRIVER   = "mysql://";
	private static final String PORT     = ":3306";
	private static final String DATABASE = "pmarchovski_aps?autoReconnect=true&amp;autoReconnectForPools=true&amp;interactiveClient=true&amp;characterEncoding=UTF-8&amp;maxReconnects=10";
	private static final String USER     = "pmarchov_eu";
	private static final String PASSWORD = "pe1pe2tar81";
	private static final String FORNAME = "com.mysql.jdbc.Driver";
	
	
	public static String getUrl() {
		  String url = "jdbc:" 
	                   + DRIVER 
	                   + "127.0.0.1" 
	                   + PORT + "/" 
	                   + DATABASE;
		return url;
   
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
	
	
//	public final class DataBaseConfig {
//
//		private static final String DRIVER   = "mysql";
//		private static final String PORT     = "3306";
//		private static final String DATABASE = "mdraineu_aps-printers?autoReconnect=true&amp;autoReconnectForPools=true&amp;interactiveClient=true&amp;characterEncoding=UTF-8&amp;maxReconnects=10";
//		private static final String USER     = "mdraineu_petar";
//		private static final String PASSWORD = "000000";
//		private static final String FORNAME = "com.mysql.jdbc.Driver";
//		
//		public static String getUrl() {
//			 System.out.println(DATABASE);
//			return "jdbc:" 
//			       + DRIVER 
//			       + "://mysql3000.mochahost.com:" 
//			       + PORT + "/" 
//			       + DATABASE;
//	   
//		}
//
//		public static Properties getCredentials() {
//			
//			Properties credentials = new Properties();
//			
//			if(USER != null) credentials.put("user", USER);
//			if(PASSWORD != null) credentials.put("password", PASSWORD);
//			
//			return credentials;
//		}
//		
//		public static String getForName() {
//			
//			String forName = (FORNAME);		
//			return forName;
//
//		}
}
