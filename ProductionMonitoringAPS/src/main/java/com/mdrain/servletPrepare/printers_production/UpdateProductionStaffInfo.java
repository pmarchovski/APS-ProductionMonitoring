package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Operators;
import com.mdrain.singletons.Singleton;

public class UpdateProductionStaffInfo {

	static String table             = "tb_operators";
	static String mechAssOperator   = "Оператор механичен монтаж";
	static String elAssOperator     = "Оператор ел. монтаж";
	static String testOperator      = "Оператор тест";
	static String packagingOperator = "Оператор опаковка";
	
	public static void updateProductionStaffInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		DataBaseActivities dbActivities = Singleton.getInstance();
		Operators operator              = new Operators();
		HttpSession session             = req.getSession();

		String skillsList   = "";
		String operatorName = req.getParameter("edit_operators_name");
		String teamLeader   = req.getParameter("edit_operators_team_leader");
		String gender       = req.getParameter("edit_operators_gender");
		String[] skills     = req.getParameterValues("edit_operators_skills");

		if (skills != null) {
			for (String element : skills) {
				skillsList += (element + ", ");
			}

			skillsList = skillsList.substring(0, skillsList.length() - 2);
		}

		String isActive           = req.getParameter("edit_operators_isActive");
		String isMotherhood       = req.getParameter("edit_operators_isMotherhood");
		String phone              = req.getParameter("edit_operators_phone");
		String numberApron        = req.getParameter("edit_operators_apron");
		String numberHeater       = req.getParameter("edit_operators_heater");
		String numberSlippers     = req.getParameter("edit_operators_slippers");
		String numberWardrobe     = req.getParameter("edit_operators_wardrobe");
		String dateChangeAppron   = req.getParameter("edit_operators_date_change_appron");
		String dateChangeHeater   = req.getParameter("edit_operators_date_change_heater");
		String dateChangeSlippers = req.getParameter("edit_operators_date_change_slippers");

		operator.setFullName(operatorName);
		operator.setTeamLeader(teamLeader);
		operator.setGender(gender);
		operator.setSkills(skills);
		operator.setIsActive(isActive);
		operator.setIsMotherhood(isMotherhood);
		operator.setPhone(phone);
		
		
		if (numberApron.equals("")) {
			operator.setNumberApron(0);
		} else {
			operator.setNumberApron(Integer.parseInt(numberApron));
		}
		
		
		operator.setNumberHeater(numberHeater);
		
		if (numberSlippers.equals("")) {
			operator.setNumberSlippers(0);
		} else {
			operator.setNumberSlippers(Integer.parseInt(numberSlippers));
		}

		operator.setNumberWardrobe(numberWardrobe);
		
		if (dateChangeAppron.equals("")) {
			operator.setChangeDateAppron(LocalDate.now());
		} else {
			operator.setChangeDateAppron(Date.date(dateChangeAppron));
		}
		
		
		if (dateChangeHeater.equals("")) {
			operator.setChangeDateHeater(LocalDate.now());
		} else {
			operator.setChangeDateHeater(Date.date(dateChangeHeater));
		}
		
		if (dateChangeSlippers.equals("")) {
			operator.setChangeDateSlippers(LocalDate.now());
		} else {
			operator.setChangeDateSlippers(Date.date(dateChangeSlippers));
		}
		

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
		
		
		if (!numberApron.equals("")) {
			fieldsCollection.add("tb_operators_number_apron");
			valueCollection.add(String.valueOf(operator.getNumberApron()));
		}
				

		if (!numberHeater.equals("")) {
			fieldsCollection.add("tb_operators_number_heater");
			valueCollection.add(operator.getNumberHeater());
		}
		
		
		if (!numberSlippers.equals("")) {
			fieldsCollection.add("tb_operators_number_slippers");
			valueCollection.add(String.valueOf(operator.getNumberSlippers()));
		}
		
		
		if (!numberWardrobe.equals("")) {
			fieldsCollection.add("tb_operators_wardrobe");
			valueCollection.add(operator.getNumberWardrobe());
		}
		
		
		if (operator.getIsActive().equals("Да")) {
			fieldsCollection.add("tb_operators_wardrobe");
			valueCollection.add("");
		}
		
		
		if (!dateChangeAppron.equals("")) {
			fieldsCollection.add("tb_operators_date_change_appron");
			valueCollection.add(String.valueOf(operator.getChangeDateAppron()));
		}
		
		
		if (!dateChangeHeater.equals("")) {
			fieldsCollection.add("tb_operators_date_change_heater");
			valueCollection.add(String.valueOf(operator.getChangeDateHeater()));
		}
		
		if (!dateChangeSlippers.equals("")) {
			fieldsCollection.add("tb_operators_date_change_slippers");
			valueCollection.add(String.valueOf(operator.getChangeDateSlippers()));
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
		
		if (isActive.equals("Не")) {
	
			String table                = "tb_wardrobe";
			String columnNameForUpdate  = "tb_wardrobe_name";
			String columnValueForUpdate = "";
			String columneNameInto      = "tb_wardrobe_number";
			String WardrobeNumber       = SetObjectInfo.getOperatorInfoWhere(table, valueInto);
			
			
			dbActivities.update(table, columnNameForUpdate, columnValueForUpdate, columneNameInto, WardrobeNumber);
		}

		session.setAttribute("massage_edit", "Операторът е променен успешно");
		
		req.getRequestDispatcher("admin_servlet_current_operators_data").forward(req, resp);
		
	}
	
	public void getCurrentOperatorsInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session                = req.getSession();
		DataBaseActivities dbActivities    = Singleton.getInstance();
        Operators operator                 = new Operators();
        String fields                      = "tb_operators_operator_name";
		String operatorName                = req.getParameter("edit_operators_name");
		Tables tables                      = new Tables();

		ResultSet result = dbActivities.select(table, fields, operatorName);

		if (result != null) {
			
				try {
			
			while (result.next()) {
			operator.setFullName(result.getString("tb_operators_operator_name"));
			operator.setTeamLeader(result.getString("tb_operators_team_leader"));
			operator.setGender(result.getString("tb_operators_gender"));
			
			if (!result.getString("tb_operators_skills").equals("")) {
				
				operator.setSkills(result.getString("tb_operators_skills").split(", "));
			} else {
				operator.setSkills(result.getString("tb_operators_other").split(", "));
			}
			operator.setSkillList(operator.getSkills());
			operator.setIsActive(result.getString("tb_operators_isActive"));
			operator.setIsMotherhood(result.getString("tb_operators_isMotherhood"));
			operator.setPhone(result.getString("tb_operators_phone"));
			operator.setNumberApron(result.getInt("tb_operators_number_apron"));
			operator.setNumberHeater(result.getString("tb_operators_number_heater"));
			operator.setNumberSlippers(result.getInt("tb_operators_number_slippers"));
			operator.setNumberWardrobe(result.getString("tb_operators_wardrobe"));
			operator.setChangeDateAppron(Date.date(result.getString("tb_operators_date_change_appron")));
			operator.setNextChangeDateAppron(operator.getChangeDateAppron());
			
			operator.setChangeDateHeater(Date.date(result.getString("tb_operators_date_change_heater")));
			operator.setNextChangeDateHeater(operator.getChangeDateHeater());
			
			operator.setChangeDateSlippers(Date.date(result.getString("tb_operators_date_change_slippers")));
			operator.setNextChangeDateSlippers(operator.getChangeDateSlippers());
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			getCurrentOperatorsInfo(req, resp);
		}
		
		String[] dataCollection = {
				operator.getFullName(),
				operator.getTeamLeader(),
				operator.getGender(),
				operator.getSkillList(),
				operator.getIsActive(),
				operator.getIsMotherhood(),
				operator.getPhone(),
				String.valueOf(operator.getNumberApron()),
				Date.convertDate(operator.getChangeDateAppron()),
				DisplayOperatorsInfo.getNextChanageDateCondition(operator.getNextChangeDateAppron()),
				operator.getNumberHeater(),
				Date.convertDate(operator.getChangeDateHeater()),
				DisplayOperatorsInfo.getNextChanageDateCondition(operator.getNextChangeDateHeater()),
				String.valueOf(operator.getNumberSlippers()),
				Date.convertDate(operator.getChangeDateSlippers()),
				DisplayOperatorsInfo.getNextChanageDateCondition(operator.getNextChangeDateSlippers()),
				operator.getNumberWardrobe()
				
		};
		
		String[] fieldsCollection = {
				"Трите имена",
				"Тийм лидер",
				"Пол",
				"Тип служител",
				"Активен да/не",
				"Майчинство да/не",
				"Телефон",
				"Номер на престилка",
				"Последна смяна на престилка",
				"Следваща смяна на престилка",
				"Номер на грейка",
				"Последна смяна на грейка",
				"Следваща смяна на грейка",
				"Номер на чехли",
				"Последна смяна на чехли",
				"Следваща смяна на чехли",
				"Номер на гардеробче"
		};
		

		String tableFields = tables.createTable(fieldsCollection, dataCollection);
		session.setAttribute("update_operators_display_info", tableFields);
		session.setAttribute("update_operators_display_info_selected_operator", operator.getFullName());
		session.setAttribute("display_operators_info", "Информация за служителя");
		
		req.getRequestDispatcher("edit_operators.jsp").forward(req, resp);
	}
	
}
