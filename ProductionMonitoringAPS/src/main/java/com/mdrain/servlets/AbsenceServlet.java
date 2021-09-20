package com.mdrain.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Operators;
import com.sun.net.httpserver.Authenticator.Result;

public class AbsenceServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		LocalDate inputDate = LocalDate.now();
		String inputDateDb = inputDate.toString();
		Operators operator = new Operators();
		ArrayList<String> filedsCollection = new ArrayList<String>();
		ArrayList<String> valueCollection = new ArrayList<String>();
		String table = "tb_operators_absence";	
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		
		String operatorName = req.getParameter("abcense_operators_operator_name");
		String date = req.getParameter("abcense_operators_date_absence");
		String absenceHour = req.getParameter("abcense_operators_hours_absence");
		String holidayFromHours = req.getParameter("abcense_operators_holiday_from_hours");
		String user = (String) session.getAttribute("user_name");
		
		
		if(absenceHour.equals("")) absenceHour = "0";
		if(holidayFromHours.equals("")) holidayFromHours = "0";
		
		String[] into = {"tb_operators_absence_operator_name", "tb_operators_absence_add_date", 
				"tb_operators_absence_hour", "tb_operators_absence_holiday_from_hours", "tb_operators_absence_user", "tb_operators_absence_date_input"};
		String[] values = {operatorName, date, absenceHour, holidayFromHours, user, inputDateDb};
		
		dbActivities.insert(table, into, values);
		
		filedsCollection.add("tb_operators_operator_name");
		valueCollection.add(operatorName);
		
		ResultSet result = dbActivities.selectWhere("tb_operators", filedsCollection, valueCollection);
		try {
			while (result.next()) {
			
			operator.setAbsenceHours(result.getDouble("tb_operators_total_absence_hour"));	
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		double absentHourDouble = Double.parseDouble(absenceHour);
		int holidays = Integer.parseInt(holidayFromHours);
		operator.setAbsenceHours((operator.getAbsenceHours() + absentHourDouble) - (holidays * 8));
		
		dbActivities.update("tb_operators", "tb_operators_total_absence_hour", operator.getAbsenceHours(), "tb_operators_operator_name", operatorName);
		
		
		resp.sendRedirect("absents_operators.jsp");
	}
}
