package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Chart;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.ChartGenerator;
import com.mdrain.logic.Date;
import com.mdrain.logic.FormatNumbers;
import com.mdrain.logic.NumberOfOperators;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Orders;
import com.mdrain.singletons.Singleton;
import com.mdrain.variables.Variables;

public class PlanedLaborCost {

	
	int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	double[] requirementTimePerMonth = new double[12];

	String table = "tb_result";
	String point = ".";
	String tbCooisProd = "tb_coois_prod";
	String tbCooisOperation = "tb_coois_operation";
	String cooisProdOrder = "tb_coois_prod_order";
	String cooisProdMaterialNumber = "tb_coois_prod_material_number";
	String cooisProdMaterialDescription = "tb_coois_prod_material_description";
	String cooisProdQuantity = "tb_coois_prod_quantity";
	String cooisProdStartDate = "tb_coois_prod_start_date";
	String cooisProdEndDate = "tb_coois_prod_end_date";
	String cooisProdStatus = "tb_coois_prod_status";
	String cooisProdDeliveryQuantity = "tb_coois_prod_del_qty";
	String cooisOperationOrder = "tb_coois_operation_order";
	String cooisOperationWorkCenter = "tb_coois_operation_work_center";
	String cooisOperationProcessingTime = "tb_coois_operation_processing_time";
	String cooisOperationTimePerPc = "tb_coois_operation_time_per_pc";
	String cooisOperationNumberOperators = "tb_coois_operation_number_operators";
	String columnONOne = tbCooisProd + point + cooisProdOrder;
	String columnONTwo = tbCooisOperation + point + cooisOperationOrder;
	WeekFields week = WeekFields.of(Locale.getDefault());
	int productionCapacityYearInt;

	public void boodstrap(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session                         = req.getSession();
		HttpSession sessionChart                    = req.getSession();	
		ArrayList<LocalDate> yearHolidaysCollection = new ArrayList<LocalDate>();
		int numberOperators                         = NumberOfOperators.numberOfOperators();
		ArrayList<Object> ordersCollection          = new ArrayList<Object>();
		DataBaseActivities dbActivities             = Singleton.getInstance();
		Orders ordersManipulation                   = new Orders();
		Tables tables                               = new Tables();

		// Clear current data


		try {
			yearHolidaysCollection = Date.publicHolidaysCollection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Get info from database

		ResultSet result = dbActivities.select(table);

		String productionCapacityYear  = req.getParameter("production_capacity_year");
		String productionCapacityMonth = req.getParameter("production_capacity_month");
		String productionCapacityWeek  = "";

		productionCapacityYearInt = 2021;

		try {
			while (result.next()) {

				Orders orders = new Orders();
				orders.setNumber(Integer.parseInt(result.getString(cooisProdOrder)));
				orders.setMaterialNumber(result.getString(cooisProdMaterialNumber));
				orders.setMaterialDescription(result.getString(cooisProdMaterialDescription));
				orders.setQuantity(Integer.parseInt(result.getString(cooisProdQuantity)));

				String startProdDate = result.getString(cooisProdStartDate);
				String endProdDate   = result.getString(cooisProdEndDate);

				LocalDate startProductionDate = Date.date(startProdDate);
				LocalDate endProductionDate   = Date.date(endProdDate);

				orders.setStartDate(startProductionDate);
				orders.setEndDate(endProductionDate);

				orders.setStatus(result.getString(cooisProdStatus));
				orders.setNumberOfOperatorsForOrder(Integer.parseInt(result.getString(cooisOperationNumberOperators)));
				orders.setTimeForOnePc(Double.parseDouble(result.getString(cooisOperationTimePerPc)));

				orders.setProductionsDatesCollection(yearHolidaysCollection);
				orders.setNumberOfDaysForProduction();
				orders.setOrderTimePerDay();


				ordersCollection.add(orders);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Display information for requirement time for production orders //
		for (int i = 0; i < ordersCollection.size(); i++) {

			ordersManipulation = (Orders) ordersCollection.get(i);

			int size = ordersManipulation.getNumberOfDaysForProduction();

			for (int j = 0; j < size; j++) {

				if (ordersManipulation.getProductionsDatesCollection().get(j).getYear() == productionCapacityYearInt) {

					for (int k = 0; k < size; k++) {

						for (int y = 0; y <= months.length; y++) {

							if (ordersManipulation.getProductionsDatesCollection().get(k).getMonthValue() == y) {

								requirementTimePerMonth[y - 1] += ordersManipulation.getOrderTimePerDay();

							}
						}
					}

					size = 0;
				}
			}
		}

		ArrayList<Object> totalPlanedCostPerMonth = new ArrayList<Object>();
		ArrayList<Object> totalRealCostPerMonth = getRealLaborCost();
		
		double totalYearCost = 0.00;
		
		totalPlanedCostPerMonth.add("Планирани разходи за труд");
		
		for (int i = 0; i < requirementTimePerMonth.length; i++) {

			double costPerMonth = (requirementTimePerMonth[i] * 60) * Variables.getLabourCostPerMinute();
			
			totalPlanedCostPerMonth.add(costPerMonth);
			
			totalYearCost += costPerMonth;
		}
		
		totalPlanedCostPerMonth.add(totalYearCost);
		
		totalRealCostPerMonth = getRealLaborCost();
		
		
		String tableHead      = tables.createTableHead(getMonthsName());
		String tableBody      = tables.createTableBody(FormatNumbers.getFormatedNumbersArrays(totalPlanedCostPerMonth));
		
		String tableSecondRow = tables.createTableBody(FormatNumbers.getFormatedNumbersArrays(totalRealCostPerMonth));
		
		ChartGenerator chart = new ChartGenerator();
		
		session.setAttribute("planet_production_cost_head", tableHead);
		session.setAttribute("planet_production_cost_body", tableBody);
		session.setAttribute("real_production_cost_body_second_row", tableSecondRow);
		
		String chartPlanedProductionCostXValue = chart.xValueGenerate(getMonthsName());
		String chartPlanedProductionCostYValue = chart.yValueGenerateObject(getPlanedLaborCostForChart());
		String chartRealProductionCostYValue = chart.yValueGenerateObject(getRealLaborCostForChart());
		
		sessionChart.setAttribute("chart_planed_production_cost_xValue", chartPlanedProductionCostXValue);
		sessionChart.setAttribute("chart_planed_production_cost_yValue", chartPlanedProductionCostYValue);
		sessionChart.setAttribute("chart_real_production_cost_yValue", chartRealProductionCostYValue);
		
		req.getRequestDispatcher("planed_labour_cost.jsp").forward(req, resp);
	}
	
	private ArrayList<String> getMonthsName(){
		
		ArrayList<String> monthsName = new ArrayList<String>();
		
		monthsName.add("");
		monthsName.add("JAN");
		monthsName.add("FEB");
		monthsName.add("MAR");
		monthsName.add("APR");
		monthsName.add("MAY");
		monthsName.add("JUN");
		monthsName.add("JUL");
		monthsName.add("AUG");
		monthsName.add("SEP");
		monthsName.add("OCT");
		monthsName.add("NOV");
		monthsName.add("DEC");
		monthsName.add("YEAR TOTAL");
		
		return monthsName;
	}
	
	private ArrayList<Object> getRealLaborCost(){
		
		ArrayList<Object> realLaborCost = new ArrayList<Object>();
		
		realLaborCost.add("Реални разходи за производство");
		realLaborCost.add(135795.55);
		realLaborCost.add(149777.64);
		realLaborCost.add(171259.53);
		realLaborCost.add(165176.85);
		realLaborCost.add(175081.01);
		realLaborCost.add(244199.10);
		realLaborCost.add(170129.71);
		realLaborCost.add(170126.71);
		realLaborCost.add(174614.02);
		realLaborCost.add(249069.22);
		realLaborCost.add(0.00);
		realLaborCost.add(0.00);
		
		double yearRealLaborCost = 0.00;
		
		for (int i = 1; i < realLaborCost.size(); i++) {
			
			yearRealLaborCost = yearRealLaborCost + (Double) realLaborCost.get(i);
		}
		
		
		realLaborCost.add(yearRealLaborCost);
		
		
		return realLaborCost;
	}
	
	
	private ArrayList<Object> getRealLaborCostForChart(){
		
		ArrayList<Object> realLaborCost = new ArrayList<Object>();
		
		realLaborCost.add(0.00);
		realLaborCost.add(135795.55);
		realLaborCost.add(149777.64);
		realLaborCost.add(171259.53);
		realLaborCost.add(165176.85);
		realLaborCost.add(175081.01);
		realLaborCost.add(244199.10);
		realLaborCost.add(170129.71);
		realLaborCost.add(170126.71);
		realLaborCost.add(174614.02);
		realLaborCost.add(249069.22);
		realLaborCost.add(0.00);
		realLaborCost.add(0.00);
		
		double yearRealLaborCost = 0.00;
		
		for (int i = 1; i < realLaborCost.size(); i++) {
			
			yearRealLaborCost = yearRealLaborCost + (Double) realLaborCost.get(i);
		}
		
		
//		realLaborCost.add(yearRealLaborCost);
		
		
		return realLaborCost;
	}
	
	
	private ArrayList<Object> getPlanedLaborCostForChart(){
		
		ArrayList<Object> totalPlanedCostPerMonth = new ArrayList<Object>();
		
		double totalYearCost = 0.00;
		
		totalPlanedCostPerMonth.add(0.00);
		
		for (int i = 0; i < requirementTimePerMonth.length; i++) {

			double costPerMonth = (requirementTimePerMonth[i] * 60) * Variables.getLabourCostPerMinute();
			
			totalPlanedCostPerMonth.add((double) costPerMonth);
			
			totalYearCost += (double) costPerMonth;
		}
		
//		totalPlanedCostPerMonth.add(totalYearCost);
		return totalPlanedCostPerMonth;
	}
}
