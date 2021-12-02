package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.logic.Date;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Absence;

public class AbsenceReport {

	static ArrayList<Absence> absenceCollection = SetObjectInfo.getAbsenceInfoCollection();

	public static void bootstrap(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session                = req.getSession();
		ArrayList<Object> dataCollection   = new ArrayList<Object>();
		ArrayList<String> fieldsCollection = new ArrayList<String>();
		Tables table                       = new Tables();
		fieldsCollection.add("Име на оператор");
		fieldsCollection.add("Дата на отсъствието");
		fieldsCollection.add("Часове отсъствие");
		fieldsCollection.add("Отпуски от часове");
		fieldsCollection.add("Въвел информацията");
		fieldsCollection.add("Дата на въвеждане");

		String operatorName = req.getParameter("absence_report_operators_name");
		String date         = req.getParameter("absence_report_date");

		for (int i = 0; i < absenceCollection.size(); i++) {

			if (absenceCollection.get(i).getOperatorName().equals(operatorName)) {
				
				dataCollection.add(absenceCollection.get(i).getOperatorName());
				
				String absenceDate = Date.convertDate(String.valueOf(absenceCollection.get(i).getAbsenceDate()));
				dataCollection.add(absenceDate);
				dataCollection.add(String.valueOf(absenceCollection.get(i).getAbsenceHours()));
				dataCollection.add(String.valueOf(absenceCollection.get(i).getHolidayFromHours()));
				dataCollection.add(absenceCollection.get(i).getUser());
				
				String inputDate = Date.convertDate(String.valueOf(absenceCollection.get(i).getInputDate()));
				dataCollection.add(inputDate);
			}
			
            if (operatorName.equals("Всички")) {
				
            	dataCollection.add(absenceCollection.get(i).getOperatorName());
            	String absenceDate = Date.convertDate(String.valueOf(absenceCollection.get(i).getAbsenceDate()));
				dataCollection.add(absenceDate);
				dataCollection.add(String.valueOf(absenceCollection.get(i).getAbsenceHours()));
				dataCollection.add(String.valueOf(absenceCollection.get(i).getHolidayFromHours()));
				dataCollection.add(absenceCollection.get(i).getUser());
				
				String inputDate = Date.convertDate(String.valueOf(absenceCollection.get(i).getInputDate()));
				dataCollection.add(inputDate);
			}
		}
		
		String tableHead = table.createTableHead(fieldsCollection);
		String tableData = table.createTableBodyStringObject(fieldsCollection, dataCollection);
		
		session.setAttribute("absence_table_head", tableHead);
		session.setAttribute("absence_table_body", tableData);
		session.setAttribute("avsence_current_operator", operatorName);
		
		req.getRequestDispatcher("absence_report.jsp").forward(req, resp);
		
	}

}
