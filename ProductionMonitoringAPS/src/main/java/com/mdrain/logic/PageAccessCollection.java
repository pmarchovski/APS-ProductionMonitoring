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
		pageCollectionRoleUser.add("planed_labour_cost.jsp");
	
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
		pageCollectionRoleGuest.add("tph_production.jsp");
		pageCollectionRoleGuest.add("finance_acounting.jsp");
		pageCollectionRoleGuest.add("finance_protokol_dma.jsp");
		pageCollectionRoleGuest.add("finance_protokol_dma_update.jsp");
		pageCollectionRoleGuest.add("finance_protokol_dma_display_update.jsp");
		pageCollectionRoleGuest.add("tph_input_final_test.jsp");
		pageCollectionRoleGuest.add("tph_input_scrap_after_epoxy.jsp");
		pageCollectionRoleGuest.add("tph_input_scrap_BE.jsp");
		pageCollectionRoleGuest.add("tph_input_scrap_pulse_trimmer.jsp");
		pageCollectionRoleGuest.add("wardrob_info.jsp");
		pageCollectionRoleGuest.add("skills_matrix.jsp");
		pageCollectionRoleGuest.add("planed_labour_cost.jsp");
		pageCollectionRoleGuest.add("absence_report.jsp");
		pageCollectionRoleGuest.add("display_materials_type.jsp");
		pageCollectionRoleGuest.add("production_plan_generate.jsp");
		pageCollectionRoleGuest.add("generate_serial_number.jsp");
		
		
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
		pageCollectionRoleWithOutLogin.add("tph_production.jsp");
		pageCollectionRoleWithOutLogin.add("hole_operators_info.jsp");
		pageCollectionRoleWithOutLogin.add("orders_info.jsp");
		pageCollectionRoleWithOutLogin.add("production_plan_generate.jsp");
		pageCollectionRoleWithOutLogin.add("public_holiday.jsp");
		pageCollectionRoleWithOutLogin.add("finance_acounting.jsp");
		pageCollectionRoleWithOutLogin.add("finance_protokol_dma.jsp");
		pageCollectionRoleWithOutLogin.add("finance_protokol_dma_update.jsp");
		pageCollectionRoleWithOutLogin.add("finance_protokol_dma_display_update.jsp");
		pageCollectionRoleWithOutLogin.add("display_reported_production_time.jsp");	
		pageCollectionRoleWithOutLogin.add("tph_input_final_test.jsp");
		pageCollectionRoleWithOutLogin.add("tph_input_scrap_after_epoxy.jsp");
		pageCollectionRoleWithOutLogin.add("tph_input_scrap_BE.jsp");
		pageCollectionRoleWithOutLogin.add("tph_input_scrap_pulse_trimmer.jsp");
		pageCollectionRoleWithOutLogin.add("tph_production.jsp");
		pageCollectionRoleWithOutLogin.add("wardrob_info.jsp");
		pageCollectionRoleWithOutLogin.add("skills_matrix.jsp");
		pageCollectionRoleWithOutLogin.add("planed_labour_cost.jsp");
		pageCollectionRoleWithOutLogin.add("absence_report.jsp");
		pageCollectionRoleWithOutLogin.add("display_materials_type.jsp");
		pageCollectionRoleWithOutLogin.add("generate_serial_number.jsp");
		pageCollectionRoleWithOutLogin.add("finance_acaunting.jsp");
		
		
		return pageCollectionRoleWithOutLogin;
	}

}
