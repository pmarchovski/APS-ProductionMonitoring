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

public class HoleOperatorsInfo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		HttpSession session = req.getSession();
		
		session.setAttribute("hole_operators_info", information());
		
		resp.sendRedirect("hole_operators_info.jsp");

	}

	private ArrayList<Object> getInfoFromDataBase() {

		String table = "tb_operators";
		DataBaseActivities dbActivities = new DataBaseActivities();
		ResultSet result = null;
		ArrayList<Object> operatorsCollectionInfo = new ArrayList<Object>();

		result = dbActivities.select(table);

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
		return operatorsCollectionInfo;
	}

	private String information() {

		int totalNumberOperators = 0;
		int totalNumberOperatorsWithoutMotherHood = 0;
		int numberOperatorsMale = 0;
		int numberOperatorsFamale = 0;
		int numberOperatorsMechAss = 0;
		int numberOperatorsElAss = 0;
		int numberOperatorsTest = 0;
		int numberOperatorsPackaging = 0;
		int numberOperatorsMotherhood = 0;
		ArrayList<Object> operatorsCollection = new ArrayList<Object>();
		operatorsCollection = getInfoFromDataBase();

		Operators operator;
		String teamLeader = "Ренета Кондарева";

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
		
		numberOperatorsMechAss = numberOperatorsMechAss - (numberOperatorsTest + numberOperatorsElAss);

		System.out.println("Общ брой служители в производството - " + totalNumberOperators);
		System.out.println("По майчинство - " + numberOperatorsMotherhood);
		System.out.println("Общ брой служители без майчинство - " + totalNumberOperatorsWithoutMotherHood);
		System.out.println("");
		System.out.println("Оператори " + (numberOperatorsMechAss + numberOperatorsElAss + numberOperatorsTest));
		System.out.println("Общ брой оператори механичен монтаж - " + numberOperatorsMechAss);
		System.out.println("Общ брой оператори ел. монтаж - " + numberOperatorsElAss);
		System.out.println("Общ брой оператори тест - " + numberOperatorsTest);
		
		System.out.println("");
		System.out.println("Опаковка");
		System.out.println("Общ брой оператори опаковка - " + numberOperatorsPackaging);
		System.out.println("");
		System.out.println("Тийм лидери");
		System.out.println("Общ брой тийм лидери - " + 4);
		System.out.println("");
		System.out.println("Магазин");
		System.out.println("Общ брой магазинери - " + 3);
		System.out.println("");
		System.out.println("САП");
		System.out.println("Общ брой САП - " + 2);
		
		ArrayList<Integer> valueFileds = new ArrayList<Integer>();
		valueFileds.add(totalNumberOperators);
		valueFileds.add(numberOperatorsMotherhood);
		valueFileds.add(numberOperatorsMechAss + numberOperatorsElAss + numberOperatorsTest);
		valueFileds.add(numberOperatorsMechAss);
		valueFileds.add(numberOperatorsElAss);
		valueFileds.add(numberOperatorsTest);
		valueFileds.add(numberOperatorsPackaging);
		valueFileds.add(4);
		valueFileds.add(3);
		valueFileds.add(2);

		ArrayList<String> typeFileds = new ArrayList<String>();
		typeFileds.add("Общ брой служители в производството");
		typeFileds.add("По майчинство");
		typeFileds.add("Оператори");
		typeFileds.add("Oператори механичен монтаж");
		typeFileds.add("Oператори ел. монтаж");
		typeFileds.add("Oператори тест");
		typeFileds.add("Oператори опаковка");
		typeFileds.add("Tийм лидери");
		typeFileds.add("Mагазинери");
		typeFileds.add("SAP");
		
		Tables table = new Tables();
		
		String tableString = table.createTableInteger(typeFileds, valueFileds);
		return tableString;
		
	}
}
