package com.mdrain.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.ChartGenerator;
import com.mdrain.logic.Date;
import com.mdrain.logic.GetDataFromTestCard;
import com.mdrain.objects.OrderDefect;
import com.mdrain.objects.Orders;
import org.apache.commons.math3.util.Precision;

public class TestCardDispalyInfoServlet extends HttpServlet {

	int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	int defectCollectionCount = 0;
	OrderDefect orders = new OrderDefect();
	ArrayList<Integer> ordersCollectioFromDefefectList = new ArrayList<Integer>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<Integer> deffectCollectionValue = new ArrayList<Integer>();
		ArrayList<Double> deffectCollectionValueDouble = new ArrayList<Double>();
		ArrayList<String> deffectTypeCollection = new ArrayList<String>();

		HttpSession session = req.getSession();
		ChartGenerator chart = new ChartGenerator();
		String testCartDisplayYvalue = "";
		String testCartDisplayXvalue = "";
		String testCardDisplayTarget = "";

		int year = Integer.parseInt(req.getParameter("input_test_card_year"));
		int month = Integer.parseInt(req.getParameter("input_test_card_month"));
		String department = req.getParameter("input_test_card_department");
		String typeChart = req.getParameter("input_test_card_chart_type");
		ArrayList<Object> orderDefectCollection = getDataFromDataBase(typeChart);
		int ordersQuantity = getProductionOrderQuantity(year, month, department, orderDefectCollection);

		deffectTypeCollection = getDefectTypeCollection();

		if (typeChart.equals("PARETO")) {

			if (month == 0) {

				deffectCollectionValue = GetDataFromTestCard.getDeffectCollectionValuePerYear(defectCollectionCount,
						orderDefectCollection, orders, year, department);

				sortCollection(deffectCollectionValue, deffectTypeCollection);

				testCartDisplayYvalue = chart.yValueGenerate(deffectCollectionValue);

			}
			if (month != 0) {
				deffectCollectionValue = GetDataFromTestCard.getDeffectCollectionValuePerMonth(defectCollectionCount,
						orderDefectCollection, orders, year, month, department);

				sortCollection(deffectCollectionValue, deffectTypeCollection);

				testCartDisplayYvalue = chart.yValueGenerate(deffectCollectionValue);
			}

			paretoChart(testCartDisplayYvalue, testCartDisplayXvalue, testCardDisplayTarget, chart, session,
					deffectTypeCollection, typeChart, deffectCollectionValueDouble);

		}

		if (typeChart.equals("MAIN")) {

			if (month == 0) {

				deffectCollectionValueDouble = GetDataFromTestCard.getDeffectCollectionValuePerYearMainChart(
						defectCollectionCount, orderDefectCollection, orders, year, department, ordersQuantity);

				testCartDisplayYvalue = chart.yValueGenerateDouble(deffectCollectionValueDouble);

			}

			if (month != 0) {
				deffectCollectionValueDouble = GetDataFromTestCard.getDeffectCollectionValuePerMonthMainChart(
						defectCollectionCount, orderDefectCollection, orders, year, month, department, ordersQuantity);

				testCartDisplayYvalue = chart.yValueGenerateDouble(deffectCollectionValueDouble);
			}

			mainChart(testCartDisplayYvalue, testCartDisplayXvalue, testCardDisplayTarget, chart, session,
					deffectTypeCollection, typeChart, year, department, deffectCollectionValueDouble);
		}

		resp.sendRedirect("input_test_card.jsp");
	}

	private Integer getProductionOrderQuantity(int year, int month, String department,
			ArrayList<Object> defectsCollection) {

		String table = "tb_coois_prod";
		ResultSet result = null;
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<Orders> ordersCollection = new ArrayList<Orders>();
		OrderDefect orderManipulation = new OrderDefect();

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				OrderDefect orders = new OrderDefect();
				orders.setQuantity(result.getInt("tb_coois_prod_quantity"));
				String date = result.getString("tb_coois_prod_start_date");
				orders.setStartDate(Date.date(date));
				orders.setNumber(result.getInt("tb_coois_prod_order"));
				orders.setMaterialDescription(result.getString("tb_coois_prod_material_description"));
				orders.setTypeProduction();
				orders.setTypeProduct(orders.getMaterialDescription());

				ordersCollection.add(orders);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int tempQuantity = 0;
		int totalQuantity = 0;

		for (int i = 0; i < ordersCollection.size(); i++) {

			orderManipulation = (OrderDefect) ordersCollection.get(i);
			totalQuantity = totalQuantity + tempQuantity;
			tempQuantity = 0;

			if (orderManipulation.getStartDate() == null) {
				orderManipulation.setStartDate(LocalDate.of(2018, 1, 1));
			}

			if (orderManipulation.getTypeProduct() == null) {
				orderManipulation.setTypeProduct("YES");
			}
			// TO DO ....... да оправя условието за месеца, защото се на може да се извади
			// количестовто произведено за година

			if (month != 0 && orderManipulation.getTypeProduction() != null) {

				if (orderManipulation.getStartDate().getYear() == year
						&& orderManipulation.getStartDate().getMonthValue() == month
						&& orderManipulation.getTypeProduction().equals(department)
						&& !orderManipulation.getTypeProduct().equals("NO")) {
					tempQuantity = orderManipulation.getQuantity();
				}

			} else {
				if (orderManipulation.getTypeProduction() != null) {
					if (orderManipulation.getStartDate().getYear() == year
							&& orderManipulation.getTypeProduction().equals(department)
							&& !orderManipulation.getTypeProduct().equals("NO")) {
						tempQuantity = orderManipulation.getQuantity();

					}

				}
			}
		}

		return totalQuantity;

	}

	private ArrayList<Object> getDataFromDataBase(String typeChart) {

		String tableDefects = "tb_test_cards";
		DataBaseActivities dbActivities = new DataBaseActivities();
		ResultSet result = null;
		ArrayList<Object> orderDefectCollection = new ArrayList<Object>();

		result = dbActivities.select(tableDefects);

		try {
			while (result.next()) {

				String dataString = result.getString("tb_test_cards_date");

				OrderDefect orderDefect = new OrderDefect();
				orderDefect.setDate(Date.date(dataString));
				ArrayList<Integer> defectsCollection = new ArrayList<Integer>();

				orderDefect.setNumber(result.getInt("tb_test_cards_order"));

				defectsCollection.add(result.getInt("tb_test_cards_pale_print"));
				defectsCollection.add(result.getInt("tb_test_cards_thermistor"));
				defectsCollection.add(result.getInt("tb_test_cards_concise_print"));
				defectsCollection.add(result.getInt("tb_test_cards_main_board"));
				defectsCollection.add(result.getInt("tb_test_cards_opto_sensor"));
				defectsCollection.add(result.getInt("tb_test_cards_second_board"));
				defectsCollection.add(result.getInt("tb_test_cards_motor"));
				defectsCollection.add(result.getInt("tb_test_cards_broken_components"));
				defectsCollection.add(result.getInt("tb_test_cards_tph"));
				defectsCollection.add(result.getInt("tb_test_cards_mech_deffect"));
				defectsCollection.add(result.getInt("tb_test_cards_noise"));
				defectsCollection.add(result.getInt("tb_test_cards_other"));

				int totalDefects = 0;
				;
				for (int i = 0; i < defectsCollection.size(); i++) {
					totalDefects = totalDefects + defectsCollection.get(i);
				}

				if (typeChart.equals("MAIN")) {
					defectsCollection.add(totalDefects);
				} else {
					defectsCollection.add(0);
				}

				orderDefect.setDefectsQuantityCollection(defectsCollection);
				orderDefect.setTypeProduction();

				orderDefectCollection.add(orderDefect);
				defectCollectionCount = defectsCollection.size();
				ordersCollectioFromDefefectList.add(orderDefect.getNumber());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderDefectCollection;
	}

	private ArrayList<String> getDefectTypeCollection() {
		ArrayList<String> defectsTypeCollection = new ArrayList<String>();

		defectsTypeCollection.add("Pale print");
		defectsTypeCollection.add("Thermistor");
		defectsTypeCollection.add("Concise print");
		defectsTypeCollection.add("Main board");
		defectsTypeCollection.add("Opto sensor");
		defectsTypeCollection.add("Second board");
		defectsTypeCollection.add("Motor");
		defectsTypeCollection.add("Broken component");
		defectsTypeCollection.add("TPH");
		defectsTypeCollection.add("Mech. defect");
		defectsTypeCollection.add("Noise");
		defectsTypeCollection.add("Others");
		defectsTypeCollection.add("Total");

		return defectsTypeCollection;
	}

	private void sortCollection(ArrayList<Integer> deffectCollectionValue, ArrayList<String> deffectTypeCollection) {

		Object[] arrayDeffectsValue = deffectCollectionValue.toArray();
		Object[] arrayDeffectsType = deffectTypeCollection.toArray();

		int firstElement = (int) arrayDeffectsValue[0];
		int secondElement;
		String firstElementType = (String) arrayDeffectsType[0];
		String secondElementType;

		for (int i = 0; i < arrayDeffectsValue.length; i++) {

			for (int j = 0; j < arrayDeffectsValue.length - 1; j++) {

				secondElement = (int) arrayDeffectsValue[j + 1];
				firstElement = (int) arrayDeffectsValue[j];

				secondElementType = (String) arrayDeffectsType[j + 1];
				firstElementType = (String) arrayDeffectsType[j];

				if ((int) arrayDeffectsValue[j] < (int) arrayDeffectsValue[j + 1]) {

					arrayDeffectsValue[j + 1] = firstElement;
					arrayDeffectsValue[j] = secondElement;

					arrayDeffectsType[j + 1] = firstElementType;
					arrayDeffectsType[j] = secondElementType;

				}
			}
		}

		for (int i = 0; i < arrayDeffectsType.length; i++) {
			deffectCollectionValue.set(i, (Integer) arrayDeffectsValue[i]);
			deffectTypeCollection.set(i, (String) arrayDeffectsType[i]);
		}
	}

	private void paretoChart(String testCartDisplayYvalue, String testCartDisplayXvalue, String testCardDisplayTarget,
			ChartGenerator chart, HttpSession session, ArrayList<String> deffectTypeCollection, String typeChart,
			ArrayList<Double> deffectCollectionValueDouble) {

		String testCardDisplayChartColor = "";
		double target = 0.00;
		testCartDisplayXvalue = chart.xValueGenerate(deffectTypeCollection);

		ArrayList<Integer> targetCollection = new ArrayList<Integer>();
		for (int i = 0; i < 12; i++) {
			targetCollection.add(0);
		}

		testCardDisplayTarget = chart.yValueGenerate(targetCollection);
		testCardDisplayChartColor = chart.barColors(deffectTypeCollection);

		session.setAttribute("test_card_display_xValue", testCartDisplayXvalue);
		session.setAttribute("test_card_display_yValue", testCartDisplayYvalue);
		session.setAttribute("test_card_display_yValueTarget", testCardDisplayTarget);
		session.setAttribute("test_card_display_color", testCardDisplayChartColor);
		session.setAttribute("precentage", typeChartWithPercentage(typeChart, deffectCollectionValueDouble, target));

	}

	private void mainChart(String testCartDisplayYvalue, String testCartDisplayXvalue, String testCardDisplayTarget,
			ChartGenerator chart, HttpSession session, ArrayList<String> deffectTypeCollection, String typeChart,
			int year, String department, ArrayList<Double> deffectCollectionValueDouble) {

		String testCardDisplayChartColor = "";

		testCartDisplayXvalue = chart.xValueGenerate(deffectTypeCollection);

		ArrayList<Double> targetCollection = new ArrayList<Double>();
		double target = 0.00;
		if (department.equals("INDUSTRIAL")) {

			for (int i = 0; i < deffectTypeCollection.size(); i++) {

				target = 0.00;

				if (year == 2018)
					target = 3.00;
				if (year == 2019)
					target = 2.00;
				if (year == 2020)
					target = 1.00;
				if (year == 2021)
					target = 0.40;

				targetCollection.add(target);
			}
		}

		if (department.equals("AUTO")) {

			for (int i = 0; i < deffectTypeCollection.size(); i++) {

				target = 0.00;

				if (year == 2018)
					target = 1.00;
				if (year == 2019)
					target = 0.50;
				if (year == 2020)
					target = 0.20;
				if (year == 2021)
					target = 0.20;

				targetCollection.add(target);
			}

		}

		testCardDisplayTarget = chart.yValueGenerateDouble(targetCollection);
		testCardDisplayChartColor = chart.barMultiColors(deffectTypeCollection);

		session.setAttribute("test_card_display_xValue", testCartDisplayXvalue);
		session.setAttribute("test_card_display_yValue", testCartDisplayYvalue);
		session.setAttribute("test_card_display_yValueTarget", testCardDisplayTarget);
		session.setAttribute("test_card_display_color", testCardDisplayChartColor);
		session.setAttribute("precentage", typeChartWithPercentage(typeChart, deffectCollectionValueDouble, target));

	}

	private String typeChartWithPercentage(String typeChart, ArrayList<Double> deffectCollectionValueDouble,
			double target) {

		String percentage = "";
		double stepSize = 0.00;

		if (typeChart.equals("PARETO")) {
			percentage = "";
		} else {

			double max = getMaxValueFromdeffectCollectionValueDouble(deffectCollectionValueDouble);

			if (max < target) {
				max = target + 0.20;
				max = Precision.round(max, 2);
				if (max <= 0.5)
					stepSize = 0.20;
				if (max > 0.5 && max <= 1.00)
					stepSize = 0.30;
				if (max > 1.00 && max <= 1.50)
					stepSize = 0.40;
				if (max > 1.50 && max <= 2.50)
					stepSize = 0.50;
				if (max > 2.50 && max <= 3.00)
					stepSize = 0.80;
				if (max > 3.00 && max <= 4.00)
					stepSize = 1.00;
				if (max > 4.00)
					stepSize = 1.50;

				if (typeChart.equals("MAIN")) {
					percentage = "scales: {yAxes: [{ticks: {min: 0, max:" + max + ", stepSize:" + stepSize
							+ ", callback: function(value){return value+" + "\"%\""
							+ "}}, scaleLabel: {display: true, labelString:" + "\"Percentage\"" + "}}]},";
				}
			}
		}

		return percentage;
	}

	private double getMaxValueFromdeffectCollectionValueDouble(ArrayList<Double> deffectCollectionValueDouble) {

		// double roundedDbl = Precision.round(input,2)

		double maxValue = 0.00;
		double nextElement;

		maxValue = Precision.round(deffectCollectionValueDouble.get(0), 4);
		for (int i = 0; i < deffectCollectionValueDouble.size(); i++) {

			nextElement = Precision.round(deffectCollectionValueDouble.get(i), 4);

			if (maxValue < nextElement) {
				maxValue = nextElement;
			}
		}

		return maxValue;
	}

}
