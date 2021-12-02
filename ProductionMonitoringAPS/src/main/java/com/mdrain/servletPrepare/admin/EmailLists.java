package com.mdrain.servletPrepare.admin;

import java.util.ArrayList;

import com.mdrain.objects.Users;

public class EmailLists {
	
	private static final String PETAR_MARCHOVSKI_APS   = "pmarchovski@aps-printers-bg.com";
	private static final String PETAR_MARCHOVSKI_GMAIL = "petar.marchovski@gmail.com";
	
	public static String[] dmaProtocolEmailList() {
		
		String[] recepients = {PETAR_MARCHOVSKI_APS, 
				PETAR_MARCHOVSKI_GMAIL};	
		return recepients;
	}
	
    public static String[] newOperatorEmailList() {
		
		String[] recepients = {PETAR_MARCHOVSKI_APS};	
		return recepients;
	}
    
    
    public static String[] absenceEmailList() {
		
 		String[] recepients = {PETAR_MARCHOVSKI_APS};
 		return recepients;
 	}
    
     public static String[] createUserEmailList(String userMail) {
		
 		String[] recepients = {userMail, PETAR_MARCHOVSKI_GMAIL};
 		return recepients;
 	}
     
     public static String[] changeUserPassword(String userMail) {
 		
  		String[] recepients = {userMail, PETAR_MARCHOVSKI_GMAIL};
  		return recepients;
  	}
     
     public static String[] taskEmailList(ArrayList<Users> users) {
 		
    	String[] recepients = new String[users.size()];
    	for (int i = 0; i < users.size(); i++) {
    		recepients[i] = users.get(i).getEmail();
    	}
 		return recepients;
 	}
     
     public static String[] taskEmailList(String ownerMail) {
 		
  		String[] recepients = {ownerMail, PETAR_MARCHOVSKI_GMAIL};
  		return recepients;
  	}
}
