package com.mdrain.logic;

import javax.servlet.http.HttpSession;

public class DoneStatus {

	
    public static void doneStatusSuccess(HttpSession session, String sessionName) {
		
		String doneStatus = "<h3 style=" + "\"font-size: 14pt; color: green\"" + ">"
				+ "Информацията е записана успешно" + "</h3>";
		
		session.setAttribute(sessionName, doneStatus);
		
	}
	
	
    public static void doneStatusNotSuccess(HttpSession session, String sessionName) {
		
    	String doneStatus = "<h3 style=" + "\"font-size: 14pt; color: red\"" + ">"
				+ "Не съществува такава поръчка!!! Информацията не е записана!" + "</h3>";
		
    	session.setAttribute(sessionName, doneStatus);
	}
	
}
