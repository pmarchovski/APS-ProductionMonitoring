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
import com.mdrain.logic.Tables;
import com.mdrain.objects.Operators;

public class UpdateOperatorsServlet extends HttpServlet {

	String table = "tb_operators";
	String mechAssOperator = "Оператор механичен монтаж";
	String elAssOperator = "Оператор ел. монтаж";
	String testOperator = "Оператор тест";
	String packagingOperator = "Оператор опаковка";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DataBaseActivities dbActivities = new DataBaseActivities();
		Operators operator = new Operators();
		HttpSession session = req.getSession();

		String skillsList = "";
		String operatorName = req.getParameter("edit_operators_name");
		String teamLeader = req.getParameter("edit_operators_team_leader");
		String gender = req.getParameter("edit_operators_gender");
		String[] skills = req.getParameterValues("edit_operators_skills");

		if (skills != null) {
			for (String element : skills) {
				skillsList += (element + ", ");
			}

			skillsList = skillsList.substring(0, skillsList.length() - 2);
		}

		String isActive = req.getParameter("edit_operators_isActive");
		String isMotherhood = req.getParameter("edit_operators_isMotherhood");
		String phone = req.getParameter("edit_operators_phone");

		operator.setFullName(operatorName);
		operator.setTeamLeader(teamLeader);
		operator.setGender(gender);
		operator.setSkills(skills);
		operator.setIsActive(isActive);
		operator.setIsMotherhood(isMotherhood);
		operator.setPhone(phone);

		if (skills != null) {
			for (int i = 0; i < skills.length; i++) {
				if (skills[i].equals(mechAssOperator))
					operator.setMechAssembly(skills[i]);
				if (skills[i].equals(elAssOperator))
					operator.setElAssembly(skills[i]);
				if (skills[i].equals(testOperator))
					operator.setTest(skills[i]);
				if (skills[i].equals(packagingOperator))
					operator.setPackaging(skills[i]);
			}
		}

		ArrayList<String> fieldsCollection = new ArrayList<String>();
		ArrayList<String> valueCollection = new ArrayList<String>();

		if (!teamLeader.equals("")) {
			fieldsCollection.add("tb_operators_team_leader");
			valueCollection.add(operator.getTeamLeader());
		}

		if (!gender.equals("")) {
			fieldsCollection.add("tb_operators_gender");
			valueCollection.add(operator.getGender());
		}

		if (!isActive.equals("")) {
			fieldsCollection.add("tb_operators_isActive");
			valueCollection.add(operator.getIsActive());
		}

		if (!isMotherhood.equals("")) {
			fieldsCollection.add("tb_operators_isMotherhood");
			valueCollection.add(operator.getIsMotherhood());
		}

		if (!phone.equals("")) {
			fieldsCollection.add("tb_operators_phone");
			valueCollection.add(operator.getPhone());
		}

		if (skills != null) {
			fieldsCollection.add("tb_operators_skills");
			valueCollection.add(skillsList);
		}

		if (skills != null) {
			
			
			for (int i = 0; i < skills.length; i++) {
				
				fieldsCollection.add("tb_operators_mech_ass");
				valueCollection.add("");
				
				fieldsCollection.add("tb_operators_el_ass");
			    valueCollection.add("");
			    
				fieldsCollection.add("tb_operators_test");
			    valueCollection.add("");
			    
				fieldsCollection.add("tb_operators_packaging");
			    valueCollection.add("");
				
			}
			
			String fieldInto = "tb_operators_operator_name";
			String valueInto = operator.getFullName();
			
			dbActivities.update(table, fieldsCollection, valueCollection, fieldInto, valueInto);
			
			
			for (int i = 0; i < skills.length; i++) {

				System.out.println(skills.length);

				if (skills[i].equals(mechAssOperator)) {
					fieldsCollection.add("tb_operators_mech_ass");
					valueCollection.add(skills[i]);
				}	

				if (skills[i].equals(elAssOperator)) {
					fieldsCollection.add("tb_operators_el_ass");
					valueCollection.add(skills[i]);
				}	

				if (skills[i].equals(testOperator)) {
					fieldsCollection.add("tb_operators_test");
					valueCollection.add(skills[i]);
				}	


				if (skills[i].equals(packagingOperator)) {
					fieldsCollection.add("tb_operators_packaging");
					valueCollection.add(skills[i]);
				}	
			
			}
		}

		String fieldInto = "tb_operators_operator_name";
		String valueInto = operator.getFullName();

		dbActivities.update(table, fieldsCollection, valueCollection, fieldInto, valueInto);

		session.setAttribute("massage_edit", "Операторът е променен успешно");
		req.getRequestDispatcher("edit_operators.jsp").forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DataBaseActivities dbActivities = new DataBaseActivities();
		ResultSet result = null;
		String fields = "tb_operators_operator_name";
		ArrayList<String> fieldsCollection = new ArrayList<String>();
		ArrayList<String> dataCollection = new ArrayList<String>();
        Operators operator = new Operators();
		Tables displayTable = new Tables();
		HttpSession session = req.getSession();

		String operatorName = req.getParameter("edit_operators_name_two");

		result = dbActivities.select(table, fields, operatorName);

		try {
			
			while (result.next()) {
			operator.setFullName(result.getString("tb_operators_operator_name"));
			operator.setTeamLeader(result.getString("tb_operators_team_leader"));
			operator.setGender(result.getString("tb_operators_gender"));
			operator.setSkills(result.getString("tb_operators_skills").split(", "));
			operator.setSkillList(operator.getSkills());
			operator.setIsActive(result.getString("tb_operators_isActive"));
			operator.setIsMotherhood(result.getString("tb_operators_isMotherhood"));
			operator.setPhone(result.getString("tb_operators_phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataCollection.add(operator.getFullName());
		dataCollection.add(operator.getTeamLeader());
		dataCollection.add(operator.getGender());
		dataCollection.add(operator.getSkillList());
		dataCollection.add(operator.getIsActive());
		dataCollection.add(operator.getIsMotherhood());
		dataCollection.add(operator.getPhone());

		fieldsCollection.add("Трите имена");
		fieldsCollection.add("Тийм лидер");
		fieldsCollection.add("Пол");
		fieldsCollection.add("Тип оператор");
		fieldsCollection.add("Активен да/не");
		fieldsCollection.add("Майчинство да/не");
		fieldsCollection.add("Телефон");

		String tableFields = displayTable.createTable(fieldsCollection, dataCollection);
		session.setAttribute("update_operators_display_info", tableFields);

		req.getRequestDispatcher("edit_operators.jsp").forward(req, resp);
	}
}
