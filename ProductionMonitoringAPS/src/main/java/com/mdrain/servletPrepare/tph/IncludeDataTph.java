package com.mdrain.servletPrepare.tph;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.DoneStatus;
import com.mdrain.singletons.Singleton;

public class IncludeDataTph {

	public static void includeScrapAfterEpoxy(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();

		String table = "tb_tph_scrap_epixy";
		String date;
		String order;
		String operator;
		String notFilled;
		String epoxiOnTheCeramic;
		String epoxiOnThePcb;
		String withoutEpoxy;
		String airBubbles;
		String notGetHard;
		String changeColor;
		String shift;
		String producedQty;
		String user                        = (String) session.getAttribute("user_name");
		DataBaseActivities dbActivities    = Singleton.getInstance();
		String inputDate                   = LocalDate.now().toString();

		date     = req.getParameter("tph_input_scrap_after_epoxy_date");
		order    = req.getParameter("tph_input_scrap_after_epoxy_order");
		operator = req.getParameter("tph_input_scrap_after_epoxy_name");

		if (req.getParameter("tph_input_scrap_after_epoxy_not_filled_epoxy").equals("")) {
			notFilled = "0";
		} else {
			notFilled = req.getParameter("tph_input_scrap_after_epoxy_not_filled_epoxy");
		}

		if (req.getParameter("tph_input_scrap_after_epoxy_epoxy_on_the_ceramic").equals("")) {
			epoxiOnTheCeramic = "0";
		} else {
			epoxiOnTheCeramic = req.getParameter("tph_input_scrap_after_epoxy_epoxy_on_the_ceramic");
		}

		if (req.getParameter("tph_input_scrap_after_epoxy_epoxy_on_the_pcb").equals("")) {
			epoxiOnThePcb = "0";
		} else {
			epoxiOnThePcb = req.getParameter("tph_input_scrap_after_epoxy_epoxy_on_the_pcb");
		}

		if (req.getParameter("tph_input_scrap_after_epoxy_tph_without_epoxy").equals("")) {
			withoutEpoxy = "0";
		} else {
			withoutEpoxy = req.getParameter("tph_input_scrap_after_epoxy_tph_without_epoxy");
		}

		if (req.getParameter("tph_input_scrap_after_epoxy_bubbles_after_drying").equals("")) {
			airBubbles = "0";
		} else {
			airBubbles = req.getParameter("tph_input_scrap_after_epoxy_bubbles_after_drying");
		}

		if (req.getParameter("tph_input_scrap_after_epoxy_epoxy_is_not_get_hard").equals("")) {
			notGetHard = "0";
		} else {
			notGetHard = req.getParameter("tph_input_scrap_after_epoxy_epoxy_is_not_get_hard");
		}

		if (req.getParameter("tph_input_scrap_after_epoxy_change_color").equals("")) {
			changeColor = "0";
		} else {
			changeColor = req.getParameter("tph_input_scrap_after_epoxy_change_color");
		}

		shift = req.getParameter("tph_input_scrap_after_epoxy_shift");
		producedQty = req.getParameter("tph_input_scrap_after_epoxy_total_inspected");

		String [] valueCollection = {
				date,
				order,
				operator,
				notFilled,
				epoxiOnTheCeramic,
				epoxiOnThePcb,
				withoutEpoxy,
				airBubbles,
				notGetHard,
				changeColor,
				shift,
				producedQty,
				user,
				inputDate
		};
		
		String[] fieldsCollection = {
				"tb_tph_scrap_epixy_date",
				"tb_tph_scrap_epixy_order",
				"tb_tph_scrap_epixy_operator",
				"tb_tph_scrap_epixy_not_filled",
				"tb_tph_scrap_epixy_epoxy_on_the_ceramic",
				"tb_tph_scrap_epixy_epoxy_on_the_pcb",
				"tb_tph_scrap_epixy_without_epoxy",
				"tb_tph_scrap_epixy_air_bubles",
				"tb_tph_scrap_epixy_not_get_hard",
				"tb_tph_scrap_epixy_change_the_color",
				"tb_tph_scrap_epixy_shift",
				"tb_tph_scrap_epixy_produced_qty",
				"tb_tph_scrap_epixy_user",
				"tb_tph_scrap_date_input"
		};
		

		boolean isOrderAvailable = isOrderAvailable(req, "tph_input_scrap_after_epoxy_order");
	    String sessionName       = "include_scrap_after_epoxy_done_status";
	    
	    if (isOrderAvailable == true) {
	    	dbActivities.insert(table, fieldsCollection, valueCollection);
	    	
	    	DoneStatus.doneStatusSuccess(session, sessionName);
	    	
	    } else {
	    	 	
	    	DoneStatus.doneStatusNotSuccess(session, sessionName);
	    }
		
		

		resp.sendRedirect("tph_input_scrap_after_epoxy.jsp");

	}

	public static void includeScrapBackEnd(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		DataBaseActivities dbActivities = Singleton.getInstance();
		HttpSession session             = req.getSession();
		String dateInput                = LocalDate.now().toString();
		String table                    = "tb_tph_scrap_be";
		String userName                 = (String) session.getAttribute("user_name");

		String[] userNameAndDateInput = { userName, dateInput };

		String[] dbTableColumnName = { "tb_tph_scrap_be_date", "tb_tph_scrap_be_order", "tb_tph_scrap_be_operator",
				"tb_tph_scrap_be_shift", "tb_tph_scrap_be_visual_control_ok", "tb_tph_scrap_be_visual_control_nok",
				"tb_tph_scrap_be_assembly_ok", "tb_tph_scrap_be_assembly_nok", "tb_tph_scrap_be_bonder_chip_ok",
				"tb_tph_scrap_be_bonder_chip_nok", "tb_tph_scrap_be_bonder_wire_ok", "tb_tph_scrap_be_bonder_wire_nok",
				"tb_tph_scrap_be_flooding_ок", "tb_tph_scrap_be_flooding_nok", "tb_tph_scrap_be_drying_ok",
				"tb_tph_scrap_be_drying_nok", "tb_tph_scrap_be_ungluing_ok", "tb_tph_scrap_be_ungluing_nok",
				"tb_tph_scrap_be_cleaning_ok", "tb_tph_scrap_be_cleaning_nok", "tb_tph_scrap_be_test_ok",
				"tb_tph_scrap_be_test_nok", "tb_tph_scrap_be_user", "tb_tph_scrap_be_test_date_input" };

		String[] webAppFieldsName = { "tph_input_scrap_be_date", "tph_input_scrap_be_order",
				"tph_input_scrap_be_operators_name", "tph_input_scrap_be_shift", "tph_input_scrap_be_visual_control_ok",
				"tph_input_scrap_be_visual_control_nok", "tph_input_scrap_be_assembly_ok",
				"tph_input_scrap_be_assembly_nok", "tph_input_scrap_be_bonder_chip_ok",
				"tph_input_scrap_be_bonder_chip_nok", "tph_input_scrap_be_bonder_wire_ok",
				"tph_input_scrap_be_bonder_wire_nok", "tph_input_scrap_be_flooding_ok",
				"tph_input_scrap_be_flooding_nok", "tph_input_scrap_be_drying_ok", "tph_input_scrap_be_drying_nok",
				"tph_input_scrap_be_ungluing_ok", "tph_input_scrap_be_ungluing_nok", "tph_input_scrap_be_cleaning_ok",
				"tph_input_scrap_be_cleaning_nok", "tph_input_scrap_be_test_ok", "tph_input_scrap_be_test_nok" };

		String[] webAppFieldsData = new String[dbTableColumnName.length];

		for (int i = 0; i < dbTableColumnName.length; i++) {

			if (webAppFieldsName.length <= i) {
				webAppFieldsData[i] = (userNameAndDateInput[i - webAppFieldsName.length]);
				
			} else {
				
				if (req.getParameter(webAppFieldsName[i]).equals("")) {
					webAppFieldsData[i] = ("0");
				} else {
					webAppFieldsData[i] = (req.getParameter(webAppFieldsName[i]));
				}
			}
		}

		 boolean isOrderAvailable = isOrderAvailable(req, "tph_input_scrap_be_order");
		 String sessionName       = "include_scrap_be_done_status";
		    
		    if (isOrderAvailable == true) {
		    	dbActivities.insert(table, dbTableColumnName, webAppFieldsData);
		    	
		    	DoneStatus.doneStatusSuccess(session, sessionName);
		    	
		    } else {
		    	 	
		    	DoneStatus.doneStatusNotSuccess(session, sessionName);
		    }

		resp.sendRedirect("tph_input_scrap_BE.jsp");
	}
	
	public static void includeScrapPulseTrimmer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		
		DataBaseActivities dbActivities = Singleton.getInstance();
		HttpSession session             = req.getSession();
		String dateInput                = LocalDate.now().toString();
		String table                    = "tb_tph_scrap_pulse_trimmer";
		String userName                 = (String) session.getAttribute("user_name");
		String[] userNameAndDateInput   = { userName, dateInput };	
		
		String[] dbTableColumnName = {"tb_tph_scrap_pulse_trimmer_date", "tb_tph_scrap_pulse_trimmer_order", "tb_tph_scrap_pulse_trimmer_operator",
				"tb_tph_scrap_pulse_trimmer_shift", "tb_tph_scrap_pulse_trimmer_serie_one_ok", "tb_tph_scrap_pulse_trimmer_serie_one_nok",
				"	tb_tph_scrap_pulse_trimmer_serie_two_ok", "tb_tph_scrap_pulse_trimmer_serie_two_nok", "tb_tph_scrap_pulse_trimmer_serie_three_ok",
				"tb_tph_scrap_pulse_trimmer_serie_three_nok", "tb_tph_scrap_pulse_trimmer_serie_four_ok", "tb_tph_scrap_pulse_trimmer_serie_four_nok",
				"tb_tph_scrap_pulse_trimmer_serie_five_ok", "tb_tph_scrap_pulse_trimmer_serie_five_nok", "tb_tph_scrap_pulse_trimmer_serie_six_ok",
				"tb_tph_scrap_pulse_trimmer_serie_six_nok", "tb_tph_scrap_pulse_trimmer_serie_seven_ok", "tb_tph_scrap_pulse_trimmer_serie_seven_nok",
				"tb_tph_scrap_pulse_trimmer_serie_eight_ok", "tb_tph_scrap_pulse_trimmer_serie_eight_nok", "tb_tph_scrap_pulse_trimmer_user", 
				"tb_tph_scrap_pulse_trimmer_date_input"};
		
		String[] webAppFieldsName = {"tph_input_scrap_pulse_trimmer_date", "tph_input_scrap_pulse_trimmer_order", "tph_input_scrap_pulse_trimmer_name",
				"tph_input_scrap_pulse_trimmer_shift", "tph_input_scrap_pulse_trimmer_serie_one_ok", "tph_input_scrap_pulse_trimmer_serie_one_nok",
				"tph_input_scrap_pulse_trimmer_serie_two_ok", "tph_input_scrap_pulse_trimmer_serie_two_nok",
				"tph_input_scrap_pulse_trimmer_serie_three_ok", "tph_input_scrap_pulse_trimmer_serie_three_nok",
				"tph_input_scrap_pulse_trimmer_serie_four_ok", "tph_input_scrap_pulse_trimmer_serie_four_nok",
				"tph_input_scrap_pulse_trimmer_serie_five_ok", "tph_input_scrap_pulse_trimmer_serie_five_nok",
				"tph_input_scrap_pulse_trimmer_serie_six_ok", "tph_input_scrap_pulse_trimmer_serie_six_nok",
				"tph_input_scrap_pulse_trimmer_serie_seven_ok", "tph_input_scrap_pulse_trimmer_serie_seven_nok",
				"tph_input_scrap_pulse_trimmer_serie_eight_ok", "tph_input_scrap_pulse_trimmer_serie_eight_nok"};	
		
		
		String[] webAppFieldsData = new String[dbTableColumnName.length];

		for (int i = 0; i < dbTableColumnName.length; i++) {

			if (webAppFieldsName.length <= i) {
				webAppFieldsData[i] = (userNameAndDateInput[i - webAppFieldsName.length]);
				
			} else {
				
				if (req.getParameter(webAppFieldsName[i]).equals("")) {
					webAppFieldsData[i] = ("0");
				} else {
					webAppFieldsData[i] = (req.getParameter(webAppFieldsName[i]));
				}
			}
		}

		  boolean isOrderAvailable = isOrderAvailable(req, "tph_input_scrap_pulse_trimmer_order");
		    String sessionName = "include_scrap_pulse_trimmer_done_status";
		    
		    if (isOrderAvailable == true) {
		    	dbActivities.insert(table, dbTableColumnName, webAppFieldsData);
		    	
		    	DoneStatus.doneStatusSuccess(session, sessionName);
		    	
		    } else {
		    	 	
		    	DoneStatus.doneStatusNotSuccess(session, sessionName);
		    }

		resp.sendRedirect("tph_input_scrap_pulse_trimmer.jsp");
		
	}
	
	
	public static void includeFinalTestInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		
		DataBaseActivities dbActivities = Singleton.getInstance();
		HttpSession session             = req.getSession();
		String dateInput                = LocalDate.now().toString();
		String table                    = " tb_tph_final_test";
		String userName                 = (String) session.getAttribute("user_name");
		String[] userNameAndDateInput   = {userName, dateInput};
		
		String[] dbTableColumnName = {"tb_tph_final_test_date", "tb_tph_final_test_order", "tb_tph_final_test_operator", "tb_tph_final_test_shift",
				"tb_tph_final_test_tph_01", "tb_tph_final_test_tph_e02", "tb_tph_final_test_tph_e03", "tb_tph_final_test_tph_e04", 
				"tb_tph_final_test_tph_e08", "tb_tph_final_test_thm_01", "tb_tph_final_test_thm_02", "tb_tph_final_test_thm_04",
				"tb_tph_final_test_others", "tb_tph_final_test_total_qty", "tb_tph_final_test_user", "tb_tph_final_test_date_input"};
		
		String[] webAppFieldsName = {"tph_input_final_test_date", "tph_input_scrap_final_test_order", "tph_input_final_test_name",
				"tph_input_final_test_shift", "tph_input_final_test_tph_01", "tph_input_final_test_tph_e02", "tph_input_final_test_tph_e03",
				"tph_input_final_test_tph_e04", "tph_input_final_test_tph_e08", "tph_input_final_test_thm_01", "tph_input_final_test_thm_02",
				"tph_input_final_test_thm_04", "tph_input_final_test_others", "tph_input_final_test_quantity"};	
		
		
		String[] webAppFieldsData = new String[dbTableColumnName.length];

		for (int i = 0; i < dbTableColumnName.length; i++) {

			if (webAppFieldsName.length <= i) {
				webAppFieldsData[i] = (userNameAndDateInput[i - webAppFieldsName.length]);
				
			} else {
		
				if (req.getParameter(webAppFieldsName[i]).equals("")) {
					webAppFieldsData[i] = ("0");
				} else {
					webAppFieldsData[i] = (req.getParameter(webAppFieldsName[i]));
				}
			}
		}

		    boolean isOrderAvailable = isOrderAvailable(req, "tph_input_scrap_final_test_order");
		    String sessionName = "include_final_test_information_done_status";
		    
		    if (isOrderAvailable == true) {
		    	dbActivities.insert(table, dbTableColumnName, webAppFieldsData);
		    	
		    	DoneStatus.doneStatusSuccess(session, sessionName);
    	
		    } else {
		    	 	
		    	DoneStatus.doneStatusNotSuccess(session, sessionName);
		    }

		resp.sendRedirect("tph_input_final_test.jsp");

	}

	
	private static boolean isOrderAvailable(HttpServletRequest req, String webAppField) {

		String table                    = "tb_coois_prod";
		DataBaseActivities dbActivities = Singleton.getInstance();
		ArrayList<String> order         = new ArrayList<String>();
        boolean isOrderAvailable        = false;
		
        ResultSet result = dbActivities.select(table);

		try {
			while (result.next()) {

				order.add(result.getString("tb_coois_prod_order"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < order.size(); i++) {
			if (req.getParameter(webAppField).equals(order.get(i))) {
				isOrderAvailable = true;
				return isOrderAvailable;
			} 
		}

		return isOrderAvailable;
	}
	
}
