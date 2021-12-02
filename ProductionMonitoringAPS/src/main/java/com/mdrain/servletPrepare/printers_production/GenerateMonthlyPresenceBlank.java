package com.mdrain.servletPrepare.printers_production;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.logic.ExcelTables;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Operators;
import com.mdrain.singletons.Singleton;

public class GenerateMonthlyPresenceBlank {

	

	static ArrayList<String> excelTableFieldCollection;
	static ArrayList<String> excelTableFieldCollectionNext;
	static ArrayList<String> excelHolidayCollection;
	static ArrayList<Operators> excelNamesCollection;
	static String sortField = "tb_operators_operator_name";
	
	public static void generatePresenceBlank(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		ArrayList<String> fieldsCollection       = new ArrayList<String>();
		ArrayList<String> fieldsAllCollection    = new ArrayList<String>();
		ArrayList<String> valueCollection        = new ArrayList<String>();
		ArrayList<String> valueAllCollection     = new ArrayList<String>();
		ArrayList<String> dateCollection         = new ArrayList<String>();
		ArrayList<String> weekDayCollection      = new ArrayList<String>();
		ArrayList<String> holidayCollection      = new ArrayList<String>();
		ArrayList<Operators> operatorsCollection = new ArrayList<Operators>();
		Tables tablesHeadMain                    = new Tables();
		Tables tablesHeadVertical                = new Tables();
		Tables tablesBody                        = new Tables();
		
		DataBaseActivities dbActivities = Singleton.getInstance();
		HttpSession session             = req.getSession();
		
	
		ResultSet result = null;
		
		String table             = "tb_operators";
		String column            = "tb_operators_operator_name";
		String columnTeamLeader  = "tb_operators_team_leader";
		String fieldTeamLeader   = "tb_operators_team_leader";
		String fieldIsActive     = "tb_operators_isActive";
		String fieldIsMotherhood = "tb_operators_isMotherhood";
		String all               = "Всички";
		String isActiveValue     = "Да";
		String isMotherhood      = "Не";
		
		String startDate         = req.getParameter("monthly_presence_blank_start_date");		
		String endDate           = req.getParameter("monthly_presence_blank_end_date");
        String teamLeader        = req.getParameter("monthly_presence_blank_team_leader_name");
        
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
			
		if (result != null) {
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
		}
		
		
		dateCollection        = Date.takeDateCollection(startDate, endDate);
		weekDayCollection     = Date.takeWeekDayCollection(startDate, endDate);
		try {
			holidayCollection = Date.takeHolidayCollection(startDate, endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Generate String for .jsp tables
		String monthlyPresentBlankTableHeadDate      = tablesHeadMain.createTableHead(dateCollection);
		String monthlyPresentBlankTableHeadWeekDay   = tablesHeadVertical.createTableHeadVerticalText(weekDayCollection);
		String monthlyPresentBlankTableBodyData      = tablesBody.createTableBodyNew(operatorsCollection, holidayCollection);
		
		excelTableFieldCollection     = dateCollection;
		excelTableFieldCollectionNext = weekDayCollection;
		excelHolidayCollection        = holidayCollection;
		excelNamesCollection          = operatorsCollection;

		session.setAttribute("monthly_present_blank_table_head_date", monthlyPresentBlankTableHeadDate);
		session.setAttribute("monthly_present_blank_table_head_week_day", monthlyPresentBlankTableHeadWeekDay);
		session.removeAttribute("monthly_presence_blank_empty_date_error_massage");	
		session.setAttribute("monthly_present_blank_table_body_data", monthlyPresentBlankTableBodyData);
		session.setAttribute("monthly_presence_blank_start_date", startDate);
		session.setAttribute("monthly_presence_blank_end_date", endDate);
		
		req.getRequestDispatcher("monthly_presence_blank.jsp").forward(req, resp);
	}
	
	
	public static void generateExcelPresenceBlank(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		session.removeAttribute("monthly_presence_blank_no_generate_table_error_massage");
	
		// reads input file from an absolute path
		String relativePath = req.getServletContext().getRealPath("");
		String filePath     = relativePath + "\\SAP\\" + "PresenceBlank.xlsx";
		
		File downloadFile   = new File(filePath);
		
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
        ServletContext context = req.getServletContext();
         
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
