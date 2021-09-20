package com.mdrain.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Operators;

public class IncludeOperatorsServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DataBaseActivities dbActivities = new DataBaseActivities();
		Operators operator = new Operators();
		HttpSession session = req.getSession();
		String table = "tb_operators";
		
		
		String skillsList = "";
		
		String operatorName = req.getParameter("include_operators_operator_name");
		String teamLeader = req.getParameter("include_operators_team_leader");
		String gender = req.getParameter("include_operators_gender");
		String[] skills = req.getParameterValues("include_operators_skills");
		
		for(String element : skills) {
		skillsList += (element + ", ");	
		}	
		skillsList = skillsList.substring(0, skillsList.length() - 2);

		String phone = req.getParameter("include_operators_phone");
		
		operator.setFullName(operatorName);
		operator.setTeamLeader(teamLeader);
		operator.setGender(gender);
		operator.setSkills(skills);
		operator.setPhone(phone);
		
		for (int i = 0; i < skills.length; i++) {
			if (skills[i].equals("Оператор механичен монтаж")) operator.setMechAssembly(skills[i]);
			if (skills[i].equals("Оператор ел. монтаж")) operator.setElAssembly(skills[i]);;
			if (skills[i].equals("Оператор тест")) operator.setTest(skills[i]);
			if (skills[i].equals("Оператор опаковка")) operator.setPackaging(skills[i]);			
		}

		
		String[] into = {"tb_operators_operator_name", "tb_operators_team_leader", "tb_operators_gender", "tb_operators_skills", 
				 "tb_operators_phone", "tb_operators_mech_ass", "tb_operators_el_ass", "tb_operators_test", "tb_operators_packaging"};
		String[] values = {operator.getFullName(), operator.getTeamLeader(), operator.getGender(),
				skillsList, operator.getPhone(), operator.getMechAssembly(), operator.getElAssembly(),
				operator.getTest(), operator.getPackaging()};
		

		dbActivities.insert(table, into, values);
		
		session.setAttribute("include_operators_inform_massage", "Операторът е въведен успешно");
		
		req.getRequestDispatcher("include_operators.jsp").forward(req, resp);
		
		
	}
}
