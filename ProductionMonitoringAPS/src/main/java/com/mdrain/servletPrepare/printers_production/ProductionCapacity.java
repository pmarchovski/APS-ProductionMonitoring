package com.mdrain.servletPrepare.printers_production;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Orders;

public class ProductionCapacity {

	
	public static void cleareCurrentData(double[] requirementTimePerMonth, double[] requirementOperatorsPerMonth, double[] requirementTimePerWeeks,
			double[] availableTimePerWeeks, double[] availableTimePerMonths, double[] realAvailableTimePerWeek, double[] realAvailableTimePerMonth) {
		
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
		
		for (int i = 0; i < realAvailableTimePerMonth.length; i++) {
			realAvailableTimePerMonth[i] = 0;
		}
		
		for (int i = 0; i < realAvailableTimePerWeek.length; i++) {
			realAvailableTimePerWeek[i] = 0;
		}
		
	}

}
