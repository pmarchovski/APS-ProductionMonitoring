package com.mdrain.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.logic.ExcelTables;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Operators;



public class MonthlyPresentsBlankServlet extends HttpServlet{
	
	
	ArrayList<String> excelTableFieldCollection;
	ArrayList<String> excelTableFieldCollectionNext;
	ArrayList<String> excelHolidayCollection;
	ArrayList<Operators> excelNamesCollection;
	String sortField = "tb_operators_operator_name";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		Tables tableHeadDate = new Tables();
		Tables tableHeadWeekDay = new Tables();
		Tables tableHoliday = new Tables();
		Tables tableBody = new Tables();
			
		ArrayList<String> fieldsCollection = new ArrayList<String>();
		ArrayList<String> fieldsAllCollection = new ArrayList<String>();
		ArrayList<String> valueCollection = new ArrayList<String>();
		ArrayList<String> valueAllCollection = new ArrayList<String>();
		ArrayList<Operators> operatorsCollection = new ArrayList<Operators>();
		ArrayList<String> dateCollection = new ArrayList<String>();
		ArrayList<String> weekDayCollection = new ArrayList<String>();
		ArrayList<String> holidayCollection = new ArrayList<String>();
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		HttpSession session = req.getSession();
		
		session.removeAttribute("monthly_present_blank_table_body_data");
		session.removeAttribute("monthly_present_blank_table_head_date");
		session.removeAttribute("monthly_present_blank_table_head_week_day");
		session.removeAttribute("monthly_present_blank_table_body_data");
		
	
		ResultSet result = null;
		
		String table = "tb_operators";
		String column = "tb_operators_operator_name";
		String columnTeamLeader = "tb_operators_team_leader";
		String fieldTeamLeader = "tb_operators_team_leader";
		String fieldIsActive = "tb_operators_isActive";
		String fieldIsMotherhood = "tb_operators_isMotherhood";
		String all = "Всички";
		String isActiveValue = "Да";
		String isMotherhood = "Не";
		
		String startDate = req.getParameter("monthly_presence_blank_start_date");		
		String endDate = req.getParameter("monthly_presence_blank_end_date");
        String teamLeader = req.getParameter("monthly_presence_blank_team_leader_name");
        
        if (startDate.equals("") || endDate.equals("")) {
        	
        	resp.sendRedirect("monthly_presence_blank.jsp");
        	session.setAttribute("monthly_presence_blank_empty_date_error_massage", "Моля попълнете полетата дата");
        	return;
        }
        
		
		fieldsCollection.add(fieldTeamLeader);
		fieldsCollection.add(fieldIsActive);
		fieldsCollection.add(fieldIsMotherhood);
		valueCollection.add(teamLeader);
		valueCollection.add(isActiveValue);
		valueCollection.add(isMotherhood);
		fieldsAllCollection.add(fieldIsActive);
		fieldsAllCollection.add(fieldIsMotherhood);
		valueAllCollection.add(isActiveValue);
		valueAllCollection.add(isMotherhood);
		
		if(teamLeader.equals(all)) {
			result = dbActivities.selectWhereAnd(table, fieldsAllCollection, valueAllCollection, sortField);
			
			
		} else {
			result = dbActivities.selectWhereAnd(table, fieldsCollection, valueCollection, sortField);
		}
			
		
		try {
			while(result.next()) {
				
				Operators operator = new Operators();
		
				operator.setFullName(result.getString(column));
			    operator.setTeamLeader(result.getString(columnTeamLeader));
				
				operatorsCollection.add(operator);
				
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
		dateCollection = Date.takeDateCollection(startDate, endDate);
		weekDayCollection = Date.takeWeekDayCollection(startDate, endDate);
		try {
			holidayCollection = Date.takeHolidayCollection(startDate, endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Generate String for .jsp tables
		String monthlyPresentBlankTableHeadDate = tableHeadDate.createTableHead(dateCollection);
		String monthlyPresentBlankTableHeadWeekDay = tableHeadWeekDay.createTableHeadVerticalText(weekDayCollection);
		//String monthlyPresentBlankTableHeadHoliday = tableHoliday.createTableHead(holidayCollection);	
		String monthlyPresentBlankTableBodyData = tableBody.createTableBodyNew(operatorsCollection, holidayCollection);
		
		excelTableFieldCollection = dateCollection;
		excelTableFieldCollectionNext = weekDayCollection;
		excelHolidayCollection = holidayCollection;
		excelNamesCollection = operatorsCollection;

		session.setAttribute("monthly_present_blank_table_head_date", monthlyPresentBlankTableHeadDate);
		session.setAttribute("monthly_present_blank_table_head_week_day", monthlyPresentBlankTableHeadWeekDay);
		//session.setAttribute("monthly_present_blank_table_head_holiday", monthlyPresentBlankTableHeadHoliday);
		session.removeAttribute("monthly_presence_blank_empty_date_error_massage");
		
		session.setAttribute("monthly_present_blank_table_body_data", monthlyPresentBlankTableBodyData);
		
		req.getRequestDispatcher("monthly_presence_blank.jsp").forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.removeAttribute("monthly_presence_blank_no_generate_table_error_massage");
	
		// reads input file from an absolute path
		String relativePath = getServletContext().getRealPath("");
		String filePath = relativePath + "\\SAP\\" + "PresenceBlank.xlsx";
		
		File downloadFile = new File(filePath);
		
		downloadFile.delete();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("presence_blank");
		
	//	ExcelTables.writeExcelHeaderTwoLine(workbook, worksheet, excelTableFieldCollection, excelTableFieldCollectionNext);
	//	ExcelTables.writeExcelDataLineNew(excelNamesCollection, workbook, worksheet, excelHolidayCollection);
	
		ExcelTables.excelTableMonthlyPresentBlank(workbook, worksheet, excelTableFieldCollection, 
				                                  excelTableFieldCollectionNext, excelNamesCollection, excelHolidayCollection);
		
	    FileOutputStream fileOutput = new FileOutputStream(filePath, true);
		workbook.write(fileOutput);
		
		
		
        downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
         
        // obtains ServletContext
        ServletContext context = getServletContext();
         
        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
         
        // modifies response
        resp.setContentType(mimeType);
        resp.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        resp.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        OutputStream outStream = resp.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        
        
        inStream.close();
        outStream.close();     
		
	}
}
