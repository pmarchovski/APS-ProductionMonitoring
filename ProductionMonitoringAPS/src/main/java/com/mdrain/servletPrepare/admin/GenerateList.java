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
import com.mdrain.objects.Operators;
import com.mdrain.objects.TeamLeaders;

public class GenerateList {

	public static void listGenerate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	
		HttpSession sessionTeamLeadersName = req.getSession();
		HttpSession sessionOperatorsName = req.getSession();
		HttpSession sessionOperationsName = req.getSession();
		HttpSession sessionProductNumberName = req.getSession();
		HttpSession sessionFreeWardrobe = req.getSession();
		
		DataBaseActivities dbActivities = new DataBaseActivities();

		ResultSet teamLeaderlist = dbActivities.select("tb_team_leaders");
		
		ResultSet operatorList = dbActivities.select("tb_operators");
		operatorList = dbActivities.sort("tb_operators", "tb_operators_operator_name");

		ResultSet operationList = dbActivities.select("tb_operations");
		operationList = dbActivities.sort("tb_operations", "tb_operations_name");

		ResultSet productNumberList = dbActivities.selectDistinct("tb_coois_prod_material_number", "tb_coois_prod");
		productNumberList = dbActivities.sort("tb_coois_prod", "tb_coois_prod_material_number");
		

		ArrayList<String> teamLeadersList = new ArrayList<String>();
		ArrayList<String> operatorsNameCollection = new ArrayList<String>();
		ArrayList<String> operationsCollection = new ArrayList<String>();
		ArrayList<String> productNumberCollection = new ArrayList<String>();
		ArrayList<String> freeWardrobeCollection = new ArrayList<String>();
		
		freeWardrobeCollection = SetObjectInfo.getFreeWardrobeInfoFromDatabase();
		
		try {
			while (teamLeaderlist.next()) {

				TeamLeaders teamLeader = new TeamLeaders();

				teamLeader.setFullName(teamLeaderlist.getString("tb_team_leader_name"));
				teamLeadersList.add(teamLeader.getFullName());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		try {
			while (operatorList.next()) {
				Operators operators = new Operators();

				operators.setIsActive(operatorList.getString("tb_operators_isActive"));
				operators.setFullName(operatorList.getString("tb_operators_operator_name"));
					
				if (operators.getIsActive().equals("Да")) operatorsNameCollection.add(operators.getFullName());
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (operationList.next()) {

					operationsCollection.add(operationList.getString("tb_operations_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (productNumberList.next()) {

					productNumberCollection.add(productNumberList.getString("tb_coois_prod_material_number"));

			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		ArrayList<String> newArray = new ArrayList<String>();
		newArray.add(productNumberCollection.get(0));
		for (int i = 0; i < productNumberCollection.size(); i++) {
			if (!newArray.contains(productNumberCollection.get(i))) {
				newArray.add(productNumberCollection.get(i));
			}
			
		}

		sessionTeamLeadersName.setAttribute("team_leaders_List", teamLeadersList);
		sessionOperatorsName.setAttribute("operators_name_collection", operatorsNameCollection);
		sessionOperationsName.setAttribute("operations_name_collection", operationsCollection);
		sessionProductNumberName.setAttribute("product_number_name_collection", newArray);
		sessionFreeWardrobe.setAttribute("free_wardrobe_collection", freeWardrobeCollection);
	
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	
	}
}
