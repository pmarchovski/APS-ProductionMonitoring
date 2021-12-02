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
import com.mdrain.logic.Tables;
import com.mdrain.objects.Operators;
import com.mdrain.servletPrepare.admin.EmailLists;
import com.mdrain.servletPrepare.admin.SendMail;
import com.mdrain.singletons.Singleton;

public class IncludeAndManageAbsence {

	
	public static void includeAndManageAbsence(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession session                 = req.getSession();
		LocalDate inputDate                 = LocalDate.now();
		Operators operator                  = new Operators();
		DataBaseActivities dbActivities     = Singleton.getInstance();
		String table                        = "tb_operators_absence";	
		String inputDateDb                  = inputDate.toString();
		String operatorName                 = req.getParameter("abcense_operators_operator_name");
		String date                         = req.getParameter("abcense_operators_date_absence");
		String absenceHour                  = req.getParameter("abcense_operators_hours_absence");
		String holidayFromHours             = req.getParameter("abcense_operators_holiday_from_hours");
		String user                         = (String) session.getAttribute("user_name");
		Tables tables                       = new Tables();
		
		
		session.removeAttribute("absence_operators_table_head");
		session.removeAttribute("absence_operators_table_body");
		
		if(absenceHour.equals("")) absenceHour = "0";
		if(holidayFromHours.equals("")) holidayFromHours = "0";
		
		String[] into   = {"tb_operators_absence_operator_name", "tb_operators_absence_add_date", 
				           "tb_operators_absence_hour", "tb_operators_absence_holiday_from_hours", 
				           "tb_operators_absence_user", "tb_operators_absence_date_input"};
		
		String[] values = {operatorName, date, absenceHour, holidayFromHours, user, inputDateDb};
		
		dbActivities.insert(table, into, values);
		
		String[] filedsCollection = {"tb_operators_operator_name"};	
		String[] valueCollection  = {operatorName};
		
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
		int holidays            = Integer.parseInt(holidayFromHours);
		operator.setAbsenceHours((operator.getAbsenceHours() + absentHourDouble) - (holidays * 8));
		
		dbActivities.update("tb_operators", "tb_operators_total_absence_hour", operator.getAbsenceHours(), "tb_operators_operator_name", operatorName);
		
		
		String[] tableFieldsCollsetion = {
				"Дата - отсъствие",
				"Оператор",
				"Въведени часове отсъствие",
				"Въведени дни отпуск",
				"User name",
				"Дата на операцията"
		};
			
		Object[] tableDataCollsetion = {
				date,
				operatorName,
				absenceHour,
				holidayFromHours,
				user,
				inputDate
		};
		
		String tableHead = tables.createTableHead(tableFieldsCollsetion);
		String tableBody = tables.createTableObject(tableDataCollsetion);
		
		
		session.setAttribute("absence_operators_table_head", tableHead);
		session.setAttribute("absence_operators_table_body", tableBody);
		
		String massage = "";
		String hours = "";
		String days = "";
		
		
		if (holidayFromHours.equals("1")) {
			days = "ден";
		} else {
			days = "дни";
		}
		
		
		if (!absenceHour.equals("1")) {
			hours = "часа";
		} else {
			hours = "час";
		}
		
		
		if (absenceHour.equals("0") && !holidayFromHours.equals("")) {
			massage = "Служителят " + operatorName + " е пуснал " + holidayFromHours + " " + days + " отпуска от часове";
		} else {
			massage = "Служителят " + operatorName + " ще бъде отпуск за " + absenceHour + " " + hours + " " + "на " + date;
		}
		
		
		String subject     = "Отъстиве на " + operatorName;	
		String[] recepient = EmailLists.absenceEmailList();	
		SendMail.bootstrap(subject, massage, recepient);
	
		resp.sendRedirect("absents_operators.jsp");
	}
	
}
