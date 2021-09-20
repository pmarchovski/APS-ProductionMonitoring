package com.mdrain.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.ChartGenerator;
import com.mdrain.logic.Date;
import com.mdrain.logic.NumberOfOperators;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Orders;


public class ProductionCapacityServlet extends HttpServlet {

	int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	double[] requirementTimePerMonth      = new double[12];
	double[] requirementOperatorsPerMonth = new double[12];
	int[] weeks = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
			28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
	double[] requirementTimePerWeeks      = new double[52];
	double[] availableTimePerWeeks        = new double[52];
	double[] availableTimePerMonths       = new double[12];

	String table                         = "tb_result";
	String point                         = ".";
	String tbCooisProd                   = "tb_coois_prod";
	String tbCooisOperation              = "tb_coois_operation";
	String cooisProdOrder                = "tb_coois_prod_order";
	String cooisProdMaterialNumber       = "tb_coois_prod_material_number";
	String cooisProdMaterialDescription  = "tb_coois_prod_material_description";
	String cooisProdQuantity             = "tb_coois_prod_quantity";
	String cooisProdStartDate            = "tb_coois_prod_start_date";
	String cooisProdEndDate              = "tb_coois_prod_end_date";
	String cooisProdStatus               = "tb_coois_prod_status";
	String cooisProdDeliveryQuantity     = "tb_coois_prod_del_qty";
	String cooisOperationOrder           = "tb_coois_operation_order";
	String cooisOperationWorkCenter      = "tb_coois_operation_work_center";
	String cooisOperationProcessingTime  = "tb_coois_operation_processing_time";
	String cooisOperationTimePerPc       = "tb_coois_operation_time_per_pc";
	String cooisOperationNumberOperators = "tb_coois_operation_number_operators";
	String columnONOne                   = tbCooisProd + point + cooisProdOrder;
	String columnONTwo                   = tbCooisOperation + point + cooisOperationOrder;
	WeekFields week                      = WeekFields.of(Locale.getDefault());
		

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		HttpSession session = req.getSession();
		session.removeAttribute("production_capacity_by_weeks_xValue");
		session.removeAttribute("production_capacity_by_weeks_yValue");
		session.removeAttribute("production_capacity_by_weeks_bar_color");
		session.removeAttribute("production_capacity_by_weeks_year");
		
		ArrayList<LocalDate> yearHolidaysCollection = new ArrayList<LocalDate>();
		int numberOperators                         = NumberOfOperators.numberOfOperators();
		ArrayList<Object> ordersCollection          = new ArrayList<Object>();	
		DataBaseActivities dbActivities             = new DataBaseActivities();	
		Orders ordersManipulation                   = new Orders();
		ResultSet result                            = null;
		Tables displayTable                         = new Tables();
		
		
		// Clear current data 
		cleareCurrentData();
		
		
		try {
			yearHolidaysCollection = Date.publicHolidaysCollection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		result = dbActivities.select(table);

		String productionCapacityYear  = req.getParameter("production_capacity_year");
		String productionCapacityMonth = req.getParameter("production_capacity_month");
		String productionCapacityWeek  = "";

		int productionCapacityYearInt  = Integer.parseInt(productionCapacityYear);

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
	
				orders.setProductionsWeeksCollection();
				orders.setProductionsMonthsCollection();
				orders.setProductionsYearsCollection();

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

				int weekNumber;
				LocalDate date = ordersManipulation.getProductionsDatesCollection().get(j);
				weekNumber = date.get(week.weekOfWeekBasedYear());
	
				if (ordersManipulation.getProductionsDatesCollection().get(j).getYear() == productionCapacityYearInt) {        

					for (int k = 0; k < size; k++) {

						for (int y = 0; y <= months.length; y++) {

							if (ordersManipulation.getProductionsDatesCollection().get(k).getMonthValue() == y) {

								requirementTimePerMonth[y - 1] += ordersManipulation.getOrderTimePerDay();
								
							}
						}
					}

					for (int k = 0; k < size; k++) {

						for (int y = 0; y < weeks.length; y++) {

							date = ordersManipulation.getProductionsDatesCollection().get(k);
							weekNumber = date.get(week.weekOfWeekBasedYear());
							if (weekNumber == y) {

								requirementTimePerWeeks[y - 1] += ordersManipulation.getOrderTimePerDay();
								
							}
						}				
					}                 
					size = 0;
				}
			}
		}		
		//------------------------------------------------------------------------------------------------------------//
		
		//------------------------------------------------------------------------------------------------------------//
		
		// Available operators time per week //
		int year = Integer.parseInt(productionCapacityYear);
		ArrayList<LocalDate> workDaysCollection = new ArrayList<LocalDate>();
			
		try {
			workDaysCollection = Date.takeWorkDaysCollection(year, yearHolidaysCollection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		String columnName = displayTable.createTableHeadVerticalText(getMonthArrayCollection());
		String columnValue = displayTable.createTableBody(getRequirementNumberOperatorsPerMonth(requirementTimePerMonth, workDaysCollection));
		
		
		availableTimePerMonths = getAvailableTimePerMonth(workDaysCollection, ordersManipulation, numberOperators);
		availableTimePerWeeks = getAvailableTimePerWeeks(workDaysCollection, ordersManipulation, numberOperators);
		
		//------------------------------------------------------------------------------------------------------------// 
	
		ChartGenerator productionCpacityChart = new ChartGenerator();

		String productionCapacityXValue = "";
		String productionCapacityYValue = "";
		String productionCapacityBarColor = "";
		String productionCapacityAvailableTime = "";
		
		
		if (productionCapacityMonth.equals("by_weeks")) {
			
			productionCapacityXValue = productionCpacityChart.xValueGenerate(weeks);
			productionCapacityYValue = productionCpacityChart.yValueGenerate(requirementTimePerWeeks);
			productionCapacityBarColor = productionCpacityChart.barColors(weeks);
			productionCapacityAvailableTime = productionCpacityChart.yValueGenerate(availableTimePerWeeks);
		} 
		
		if (productionCapacityMonth.equals("by_months")) {
			
			productionCapacityXValue = productionCpacityChart.xValueGenerate(months);
			productionCapacityYValue = productionCpacityChart.yValueGenerate(requirementTimePerMonth);
			productionCapacityBarColor = productionCpacityChart.barColors(months);
			productionCapacityAvailableTime = productionCpacityChart.yValueGenerate(availableTimePerMonths);
			
			
		}
		

		session.setAttribute("production_capacity_by_weeks_xValue", productionCapacityXValue);
		session.setAttribute("production_capacity_by_weeks_yValue", productionCapacityYValue);
		session.setAttribute("production_capacity_by_weeks_bar_color", productionCapacityBarColor);
		session.setAttribute("production_capacity_by_weeks_year", "Производствен капацитет " + productionCapacityYear);
		session.setAttribute("production_available_time_by_week", productionCapacityAvailableTime);
		session.setAttribute("production_capacity_requirement_operators_per_month_head", columnName);
		session.setAttribute("production_capacity_requirement_operators_per_month_data", columnValue);
		session.setAttribute("year", productionCapacityYear);

		resp.sendRedirect("production_capacity.jsp");
	}
	
	
	private ArrayList<String> getMonthArrayCollection(){
		
		ArrayList<String> monthCollection = new ArrayList<String>();
		
		for (int i = 1; i <= 12; i++) {
			String month = String.valueOf(LocalDate.of(2021, i, 01).getMonth());		
			monthCollection.add(month);			
		}
		
		return monthCollection;
	}
	
	private ArrayList<Object> getRequirementNumberOperatorsPerMonth(double[] requirementTimePerMonth, ArrayList<LocalDate> workDaysCollection) {
		
		
		
		for (int i = 1; i <= months.length; i++) {
			int numbersOfDays = 0;
			
			for (int j = 0; j < workDaysCollection.size(); j++) {
				if (workDaysCollection.get(j).getMonthValue() == i) {
					numbersOfDays++;
				}
			}
			requirementOperatorsPerMonth[i - 1] = (requirementTimePerMonth[i - 1] / numbersOfDays) / 7.5;	
		}
		
		ArrayList<Object> requirementOperatorsPerMonthArray = new ArrayList<Object>();
		
		for (int i = 0; i < requirementOperatorsPerMonth.length; i++) {
			
			requirementOperatorsPerMonthArray.add((int) requirementOperatorsPerMonth[i]);
			
		}	
		return requirementOperatorsPerMonthArray;
	}
	

	private double[] getAvailableTimePerMonth(ArrayList<LocalDate> workDaysCollection, Orders ordersManipulation, int numberOperators) {
		
		for (int i = 0; i < workDaysCollection.size(); i++) {
			
			for (int j = 1; j < months.length; j++) {
				
				int monthNumber;
				monthNumber = workDaysCollection.get(i).getMonthValue();
				
				if (monthNumber == j) {
					availableTimePerMonths[j - 1] += numberOperators * 7.5;
				}
			}
			
		}
		return availableTimePerMonths;
	}
	
	private double[] getAvailableTimePerWeeks(ArrayList<LocalDate> workDaysCollection, Orders ordersManipulation, int numberOperators) {
		for (int i = 0; i < workDaysCollection.size(); i++) {
			
			for (int j = 0; j < weeks.length; j++) {
				
				int weekNumber;
				LocalDate date = workDaysCollection.get(i);
				weekNumber = date.get(week.weekOfWeekBasedYear());
				
				if (weekNumber == j) {
					availableTimePerWeeks[j - 1] += numberOperators * 7.5;
				}
			}
		}
		return availableTimePerWeeks;
	}
	
	
	
	private void productionOrdersInformation() {
		
		
//		for (int j = 0; j < size; j++) {
//		
//		
//		if (ordersManipulation.getProductionsDatesCollection().get(j).get(week.weekOfWeekBasedYear()) == 39 
//			&&  ordersManipulation.getProductionsDatesCollection().get(j).get(week.weekBasedYear()) == 2021){
//			
//			System.out.println(ordersManipulation.getNumber() 
//					+ "; " + ordersManipulation.getOrderTimePerDay() 
//					+ "; " + ordersManipulation.getQuantity()
//					+ "; " + ordersManipulation.getProductionsDatesCollection()
//					+ "; " + ordersManipulation.getNumberOfDaysForProduction());
//		}
//	}
	}
	
	private void cleareCurrentData() {
		
		for (int i = 0; i < requirementTimePerWeeks.length; i++) {
			requirementTimePerWeeks[i] = 0;
		}

		for (int i = 0; i < requirementTimePerMonth.length; i++) {
			requirementTimePerMonth[i] = 0;
		}
		
		for (int i = 0; i < availableTimePerWeeks.length; i++) {
			availableTimePerWeeks[i] = 0;
		}
		for (int i = 0; i < availableTimePerMonths.length; i++) {
			availableTimePerMonths[i] = 0;
		}
		for (int i = 0; i < requirementOperatorsPerMonth.length; i++) {
			requirementOperatorsPerMonth[i] = 0;
		}
		
	}
}
