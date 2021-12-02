package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.singletons.Singleton;

public class GenerateList {

	static DataBaseActivities dbActivities = Singleton.getInstance();
	
	public static void listGenerate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		teamLeaderListGenerate(req, resp);
		operatrsNameListGenerate(req, resp);
		operationsNameListGenerate(req, resp);
		productNumberListGenerate(req, resp);
		freeWardrobeListGenerate(req, resp);
		userListGenerate(req, resp);
	
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	
	}
	
	private static void teamLeaderListGenerate(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession sessionTeamLeadersName = req.getSession();
		ResultSet teamLeaderlist           = dbActivities.select("tb_team_leaders");
		ArrayList<String> teamLeadersList  = new ArrayList<String>();
		
		try {
			while (teamLeaderlist.next()) {

				teamLeadersList.add(teamLeaderlist.getString("tb_team_leader_name"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		sessionTeamLeadersName.setAttribute("team_leaders_List", teamLeadersList);
	}
	
	private static void operatrsNameListGenerate(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession sessionOperatorsName = req.getSession();
		ResultSet operatorList           = dbActivities.select("tb_operators");
		operatorList                     = dbActivities.sort("tb_operators", "tb_operators_operator_name");	
		String[] operatorsNameCollection = new String[150];
		int count = 0;
		
		try {
			while (operatorList.next()) {

				if (operatorList.getString("tb_operators_isActive").equals("Да")) {
					count++;
					operatorsNameCollection[count] = operatorList.getString("tb_operators_operator_name");
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sessionOperatorsName.setAttribute("operators_name_collection", operatorsNameCollection);
	}
	
	
	private static void operationsNameListGenerate(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession sessionOperationsName      = req.getSession();
		ResultSet operationList                = dbActivities.select("tb_operations");
		operationList                          = dbActivities.sort("tb_operations", "tb_operations_name");
		ArrayList<String> operationsCollection = new ArrayList<String>();
		
		try {
			while (operationList.next()) {

					operationsCollection.add(operationList.getString("tb_operations_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sessionOperationsName.setAttribute("operations_name_collection", operationsCollection);
	}
	
	private static void productNumberListGenerate(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession sessionProductNumberName = req.getSession();
		ResultSet productNumberList          = dbActivities.selectDistinct("tb_coois_prod_material_number", "tb_coois_prod");
		productNumberList                    = dbActivities.sort("tb_coois_prod", "tb_coois_prod_material_number");		
		ArrayList<String> productNumberCollection = new ArrayList<String>();
		
		if (productNumberList != null) {
			try {
				while (productNumberList.next()) {

						productNumberCollection.add(productNumberList.getString("tb_coois_prod_material_number"));

				}		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			productNumberListGenerate(req, resp);
		}
		
	
		ArrayList<String> newArray = new ArrayList<String>();
		newArray.add(productNumberCollection.get(0));
		for (int i = 1; i < productNumberCollection.size(); i++) {
			if (!newArray.contains(productNumberCollection.get(i))) {
				newArray.add(productNumberCollection.get(i));
			}
			
		}
		
		sessionProductNumberName.setAttribute("product_number_name_collection", newArray);
	}
	
	private static void freeWardrobeListGenerate(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession sessionFreeWardrobe          = req.getSession();
		ArrayList<String> freeWardrobeCollection = SetObjectInfo.getFreeWardrobeInfoFromDatabase();	
		sessionFreeWardrobe.setAttribute("free_wardrobe_collection", freeWardrobeCollection);
		
	}
	
	private static void userListGenerate(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession sessionUserList       = req.getSession();
		ResultSet result                  = dbActivities.select("tb_users");
		result                            = dbActivities.sort("tb_users", "tb_users_full_name");
		ArrayList<String> usersCollection = new ArrayList<String>();
		
		try {
			while (result.next()) {

				usersCollection.add(result.getString("tb_users_full_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sessionUserList.setAttribute("users_name_collection", usersCollection);

	}
	
}
