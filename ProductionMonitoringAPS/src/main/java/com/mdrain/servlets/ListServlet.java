package com.mdrain.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Operators;
import com.mdrain.objects.TeamLeaders;

public class ListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DataBaseActivities dbActivities = new DataBaseActivities();

		ResultSet teamLeaderlist = dbActivities.select("tb_team_leaders");
		ResultSet operatorList = dbActivities.select("tb_operators");
		 operatorList = dbActivities.sort("tb_operators", "tb_operators_operator_name");
		 
		 ResultSet operationList = dbActivities.select("tb_operations");
		 operationList = dbActivities.sort("tb_operations", "tb_operations_name");

		ArrayList<String> teamLeadersList = new ArrayList<String>();
		ArrayList<String> operatorsNameCollection = new ArrayList<String>();
		ArrayList<String> operationsCollection = new ArrayList<String>();
		
		HttpSession sessionTeamLeadersName = req.getSession();
		HttpSession sessionOperatorsName = req.getSession();
		HttpSession sessionOperationsName = req.getSession();

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

				operators.setFullName(operatorList.getString("tb_operators_operator_name"));
				operatorsNameCollection.add(operators.getFullName());

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
		

		sessionTeamLeadersName.setAttribute("team_leaders_List", teamLeadersList);
		sessionOperatorsName.setAttribute("operators_name_collection", operatorsNameCollection);
		sessionOperationsName.setAttribute("operations_name_collection", operationsCollection);
		
		resp.sendRedirect("index.jsp");

	}

}
