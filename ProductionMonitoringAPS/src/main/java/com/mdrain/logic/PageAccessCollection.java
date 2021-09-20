package com.mdrain.logic;

import java.util.ArrayList;

public final class PageAccessCollection {
	
	
	public static ArrayList<String> roleAdminCollection(){
		
		ArrayList<String> pageCollectionRoleAdmin = new ArrayList<String>();
		
		pageCollectionRoleAdmin.add("login.jsp");
		
		return pageCollectionRoleAdmin;
	}
	
	public static ArrayList<String> roleUserCollection() {
		
		ArrayList<String> pageCollectionRoleUser = new ArrayList<String>();
			
		pageCollectionRoleUser.add("user_registration.jsp");
		pageCollectionRoleUser.add("login.jsp");
	
		return pageCollectionRoleUser;
	}
	
	public static ArrayList<String> roleGuestCollection() {
		
		ArrayList<String> pageCollectionRoleGuest = new ArrayList<String>();
			
		pageCollectionRoleGuest.add("include_operators.jsp");
		pageCollectionRoleGuest.add("user_registration.jsp");
		pageCollectionRoleGuest.add("edit_operators.jsp");
		pageCollectionRoleGuest.add("absents_operators.jsp");
		pageCollectionRoleGuest.add("login.jsp");
		pageCollectionRoleGuest.add("input_production_card.jsp");
		pageCollectionRoleGuest.add("input_test_card.jsp");
		
		
		
		
		return pageCollectionRoleGuest;
	}
	
	
	public static ArrayList<String> roleWithOutLoginCollection() {
		
		ArrayList<String> pageCollectionRoleWithOutLogin = new ArrayList<String>();
			
		pageCollectionRoleWithOutLogin.add("display_operator_info.jsp");
		pageCollectionRoleWithOutLogin.add("include_operators.jsp");
		pageCollectionRoleWithOutLogin.add("user_registration.jsp");
		pageCollectionRoleWithOutLogin.add("edit_operators.jsp");
		pageCollectionRoleWithOutLogin.add("absents_operators.jsp");
		pageCollectionRoleWithOutLogin.add("monthly_presence_blank.jsp");
		pageCollectionRoleWithOutLogin.add("printers_production.jsp");
		pageCollectionRoleWithOutLogin.add("production_capacity.jsp");
		pageCollectionRoleWithOutLogin.add("input_production_card.jsp");
		pageCollectionRoleWithOutLogin.add("input_test_card.jsp");
		
		
		return pageCollectionRoleWithOutLogin;
	}

}
