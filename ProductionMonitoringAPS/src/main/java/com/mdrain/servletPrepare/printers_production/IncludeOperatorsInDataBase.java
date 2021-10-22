package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.objects.Operators;
import com.mdrain.servletPrepare.admin.EmailLists;
import com.mdrain.servletPrepare.admin.SendMail;

public class IncludeOperatorsInDataBase {

	
	public static void includeOperatorsInDataBase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		Operators operator              = new Operators();
		HttpSession session             = req.getSession();
		LocalDate currentDate           = LocalDate.now();
		String table                    = "tb_operators";
		String skillsList               = "";
		
		String operatorName             = req.getParameter("include_operators_operator_name");
		String teamLeader               = req.getParameter("include_operators_team_leader");
		String gender                   = req.getParameter("include_operators_gender");
		String[] skills                 = req.getParameterValues("include_operators_skills");
		String numberApron              = req.getParameter("include_operators_apron");
		String numberHeater             = req.getParameter("include_operators_heater");
		String numberSlippers           = req.getParameter("include_operators_slippers");
		String numberWardrobe           = req.getParameter("include_operators_wardrobe");
		
		for(String element : skills) {
		skillsList += (element + ", ");	
		}	
		skillsList                      = skillsList.substring(0, skillsList.length() - 2);

		String phone                    = req.getParameter("include_operators_phone");
		
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

		operator.setNumberApron(Integer.parseInt(numberApron));
		operator.setNumberHeater(numberHeater);
		operator.setNumberSlippers(Integer.parseInt(numberSlippers));
		operator.setNumberWardrobe(numberWardrobe);
		operator.setChangeDateAppron(currentDate);
		operator.setChangeDateHeater(currentDate);
		operator.setChangeDateSlippers(currentDate);
		
		
		String[] into = {"tb_operators_operator_name", "tb_operators_team_leader", "tb_operators_gender", "tb_operators_skills", 
				 "tb_operators_phone", "tb_operators_mech_ass", "tb_operators_el_ass", "tb_operators_test", "tb_operators_packaging",
				 "tb_operators_number_apron", "tb_operators_number_heater", "tb_operators_number_slippers", "tb_operators_wardrobe",
				 "tb_operators_date_change_appron", "tb_operators_date_change_heater", "tb_operators_date_change_slippers"};
		
		String[] values = {operator.getFullName(), operator.getTeamLeader(), operator.getGender(),
				skillsList, operator.getPhone(), operator.getMechAssembly(), operator.getElAssembly(),
				operator.getTest(), operator.getPackaging(), String.valueOf(operator.getNumberApron()), operator.getNumberHeater(),
				String.valueOf(operator.getNumberSlippers()), operator.getNumberWardrobe(), Date.convertDateNew(operator.getChangeDateAppron()),
				Date.convertDateNew(operator.getChangeDateHeater()), Date.convertDateNew(operator.getChangeDateSlippers())};
		

		dbActivities.insert(table, into, values);
		dbActivities.update("tb_wardrobe", "tb_wardrobe_name", operator.getFullName(), "tb_wardrobe_number", operator.getNumberWardrobe());
		
		session.setAttribute("include_operators_inform_massage", "Операторът е въведен успешно");
		
		String subject = "Въведен е нов оператор " + operator.getFullName();
		String massage = "Операторът " + operator.getFullName() + " е въведен успешно в системата";
		String[] recepient = EmailLists.newOperatorEmailList();
		
		SendMail.bootstrap(subject, massage, recepient);
		
		req.getRequestDispatcher("include_operators.jsp").forward(req, resp);
	}
}
