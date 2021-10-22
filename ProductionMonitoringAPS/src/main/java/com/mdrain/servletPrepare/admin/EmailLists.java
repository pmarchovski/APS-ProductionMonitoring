package com.mdrain.servletPrepare.admin;

public class EmailLists {

	
	public static String[] dmaProtocolEmailList() {
		
		String[] recepients = {"pmarchovski@aps-printers-bg.com", 
				               "petar.marchovski@gmail.com"};	
		return recepients;
	}
	
    public static String[] newOperatorEmailList() {
		
		String[] recepients = {"pmarchovski@aps-printers-bg.com"};	
		return recepients;
	}
    
    
    public static String[] absenceEmailList() {
		
 		String[] recepients = {"pmarchovski@aps-printers-bg.com"};
 		return recepients;
 	}
    
     public static String[] createUserEmailList(String userMail) {
		
 		String[] recepients = {userMail, "pmarchovski@aps-printers-bg.com"};
 		return recepients;
 	}
}
