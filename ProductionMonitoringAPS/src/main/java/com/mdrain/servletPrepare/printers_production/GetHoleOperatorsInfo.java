package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Operators;
import com.mdrain.singletons.Singleton;

public class GetHoleOperatorsInfo {

	
	public static void bootstrap(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
        HttpSession session = req.getSession();	
		session.setAttribute("hole_operators_info", information(req, resp));
		session.setAttribute("hole_operators_info_none_operator", noneOperatorInfo(req, resp));
		
		resp.sendRedirect("hole_operators_info.jsp");
		
		
	}
	
	private static ArrayList<Object> getInfoFromDataBase() {

		String table                              = "tb_operators";
		DataBaseActivities dbActivities           = Singleton.getInstance();
		ArrayList<Object> operatorsCollectionInfo = new ArrayList<Object>();
		ResultSet result                          = dbActivities.select(table);

		if (result != null) {
			
			try {
			while (result.next()) {
				Operators operators = new Operators();

				operators.setFullName(result.getString("tb_operators_operator_name"));
				operators.setTeamLeader(result.getString("tb_operators_team_leader"));
				operators.setGender(result.getString("tb_operators_gender"));
				operators.setIsActive(result.getString("tb_operators_isActive"));
				operators.setIsMotherhood(result.getString("tb_operators_isMotherhood"));
				operators.setMechAssembly(result.getString("tb_operators_mech_ass"));
				operators.setElAssembly(result.getString("tb_operators_el_ass"));
				operators.setTest(result.getString("tb_operators_test"));
				operators.setPackaging(result.getString("tb_operators_packaging"));

				operatorsCollectionInfo.add(operators);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		} else {
			getInfoFromDataBase();
		}
		
		
		
		return operatorsCollectionInfo;
	}

	private static String information(HttpServletRequest req, HttpServletResponse resp) {

		int totalNumberOperators                  = 0;
		int totalNumberOperatorsWithoutMotherHood = 0;
		int numberOperatorsMale                   = 0;
		int numberOperatorsFamale                 = 0;
		int numberOperatorsMechAss                = 0;
		int numberOperatorsElAss                  = 0;
		int numberOperatorsTest                   = 0;
		int numberOperatorsPackaging              = 0;
		int numberOperatorsMotherhood             = 0;
		int numberTeamLeaders                     = 0;
		int numberSAP                             = 0;
		int numberMagazin                         = 0;
		ArrayList<Object> operatorsCollection = new ArrayList<Object>();
		operatorsCollection                   = getInfoFromDataBase();
		Tables table                          = new Tables();

		Operators operator;
		String teamLeader = req.getParameter("hole_operators_info_team_leader");

		if (teamLeader.equals("all")) {
			for (int i = 0; i < operatorsCollection.size(); i++) {

				operator = (Operators) operatorsCollection.get(i);
				if (!operator.getIsActive().equals("Не")) {
					totalNumberOperators++;
				}
			}

			for (int i = 0; i < operatorsCollection.size(); i++) {

				operator = (Operators) operatorsCollection.get(i);
				if (!operator.getIsActive().equals("Не") && operator.getIsMotherhood().equals("Да")) {
					numberOperatorsMotherhood++;
				}
			}

			for (int i = 0; i < operatorsCollection.size(); i++) {

				operator = (Operators) operatorsCollection.get(i);
				if (!operator.getIsActive().equals("Не") && (!operator.getIsMotherhood().equals("Да"))
						                                 && operator.getElAssembly().equals("Оператор ел. монтаж")) {
					numberOperatorsElAss++;
				}
			}

			for (int i = 0; i < operatorsCollection.size(); i++) {

				operator = (Operators) operatorsCollection.get(i);
				if (!operator.getIsActive().equals("Не") && (!operator.getIsMotherhood().equals("Да"))
						                                 && operator.getTest().equals("Оператор тест")) {
					numberOperatorsTest++;
				}
			}

			for (int i = 0; i < operatorsCollection.size(); i++) {

				operator = (Operators) operatorsCollection.get(i);
				if (!operator.getIsActive().equals("Не") && (!operator.getIsMotherhood().equals("Да"))
						                                 && operator.getPackaging().equals("Оператор опаковка")) {
					numberOperatorsPackaging++;
				}
			}

			for (int i = 0; i < operatorsCollection.size(); i++) {

				operator = (Operators) operatorsCollection.get(i);
				if (!operator.getIsActive().equals("Не") && (!operator.getIsMotherhood().equals("Да"))
						                                 && operator.getMechAssembly().equals("Оператор механичен монтаж")) {
					numberOperatorsMechAss++;
					
				}
			}

			for (int i = 0; i < operatorsCollection.size(); i++) {

				operator = (Operators) operatorsCollection.get(i);
				if (!operator.getIsActive().equals("Не") && !operator.getIsMotherhood().equals("Да")) {
					totalNumberOperatorsWithoutMotherHood++;

				}
			}
		}
			
			
			if (!teamLeader.equals("all")) {
				for (int i = 0; i < operatorsCollection.size(); i++) {

					operator = (Operators) operatorsCollection.get(i);
					if (!operator.getIsActive().equals("Не") && operator.getTeamLeader().equals(teamLeader)) {
						totalNumberOperators++;
					}
				}

				for (int i = 0; i < operatorsCollection.size(); i++) {

					operator = (Operators) operatorsCollection.get(i);
					if (!operator.getIsActive().equals("Не") && operator.getIsMotherhood().equals("Да") 
							                                 && operator.getTeamLeader().equals(teamLeader)) {
						numberOperatorsMotherhood++;
					}
				}

				for (int i = 0; i < operatorsCollection.size(); i++) {

					operator = (Operators) operatorsCollection.get(i);
					if (!operator.getIsActive().equals("Не") && (!operator.getIsMotherhood().equals("Да") 
							                                 && operator.getTeamLeader().equals(teamLeader))
							                                 && operator.getElAssembly().equals("Оператор ел. монтаж")) {
						numberOperatorsElAss++;
					}
				}

				for (int i = 0; i < operatorsCollection.size(); i++) {

					operator = (Operators) operatorsCollection.get(i);
					if (!operator.getIsActive().equals("Не") && (!operator.getIsMotherhood().equals("Да") 
							                                 && operator.getTeamLeader().equals(teamLeader))
							                                 && operator.getTest().equals("Оператор тест")) {
						numberOperatorsTest++;
					}
				}

				for (int i = 0; i < operatorsCollection.size(); i++) {

					operator = (Operators) operatorsCollection.get(i);
					if (!operator.getIsActive().equals("Не") && (!operator.getIsMotherhood().equals("Да") 
							                                 && operator.getTeamLeader().equals(teamLeader))
							                                 && operator.getPackaging().equals("Оператор опаковка")) {
						numberOperatorsPackaging++;
					}
				}

				for (int i = 0; i < operatorsCollection.size(); i++) {

					operator = (Operators) operatorsCollection.get(i);
					if (!operator.getIsActive().equals("Не") && (!operator.getIsMotherhood().equals("Да")
							                                 && operator.getTeamLeader().equals(teamLeader))
							                                 && operator.getMechAssembly().equals("Оператор механичен монтаж")) {
						numberOperatorsMechAss++;
						
					}
				}

				for (int i = 0; i < operatorsCollection.size(); i++) {

					operator = (Operators) operatorsCollection.get(i);
					if (!operator.getIsActive().equals("Не") && !operator.getIsMotherhood().equals("Да") 
							                                 && operator.getTeamLeader().equals(teamLeader)) {
						totalNumberOperatorsWithoutMotherHood++;

					}
				}
			
			}
		

		
		numberOperatorsMechAss = numberOperatorsMechAss - (numberOperatorsTest + numberOperatorsElAss);
		
		ArrayList<Integer> valueOperatorsFileds = new ArrayList<Integer>();
		valueOperatorsFileds.add(totalNumberOperators);
		valueOperatorsFileds.add(numberOperatorsMotherhood);
		valueOperatorsFileds.add(numberOperatorsMechAss + numberOperatorsElAss + numberOperatorsTest);
		valueOperatorsFileds.add(numberOperatorsMechAss);
		valueOperatorsFileds.add(numberOperatorsElAss);
		valueOperatorsFileds.add(numberOperatorsTest);
		valueOperatorsFileds.add(numberOperatorsPackaging);
		

		ArrayList<String> typeOperatorsFileds = new ArrayList<String>();
		typeOperatorsFileds.add("Общ брой ");
		typeOperatorsFileds.add("По майчинство");
		typeOperatorsFileds.add("Оператори");
		typeOperatorsFileds.add("Oператори механичен монтаж");
		typeOperatorsFileds.add("Oператори ел. монтаж");
		typeOperatorsFileds.add("Oператори тест");
		typeOperatorsFileds.add("Oператори опаковка");
		
		String tableString = table.createTableInteger(typeOperatorsFileds, valueOperatorsFileds);
		return tableString;
		
	}
	
	private static String noneOperatorInfo(HttpServletRequest req, HttpServletResponse resp) {
		
		ArrayList<Integer> valueFileds = new ArrayList<Integer>();
		ArrayList<String> typeFileds   = new ArrayList<String>();
		Tables table                   = new Tables();
		
		valueFileds.add(4);
		valueFileds.add(3);
		valueFileds.add(2);
		
		typeFileds.add("Tийм лидери");
		typeFileds.add("Mагазинери");
		typeFileds.add("SAP");
		
		String tableStringNoneOperator = table.createTableInteger(typeFileds, valueFileds);
		return tableStringNoneOperator;
	}
}
