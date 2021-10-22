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

public class DisplayOperatorsInfo {

	static String all               = "Всички";
	static String table             = "tb_operators";
	static String mechAssOperator   = "Оператор механичен монтаж";
	static String elAssOperator     = "Оператор ел. монтаж";
	static String testOperator      = "Оператор тест";
	static String packagingOperator = "Оператор опаковка";
	static String sortField         = "tb_operators_operator_name";
	static String yes               = "Да";

	static ArrayList<String> excelTableFieldCollection;
	static ArrayList<Object> excelOperatorsInfoCollection;
	static ArrayList<Operators> excelOperatorsCollection;
	
	public static void getOperatorsData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Tables tables                             = new Tables();
		DataBaseActivities dbActivities           = new DataBaseActivities();
		ArrayList<String> fieldsCollection        = new ArrayList<String>();
		ArrayList<String> tableFieldCollection    = new ArrayList<String>();		
		ArrayList<Operators> operatorsCollection  = new ArrayList<Operators>();
		ArrayList<String> valueCollection         = new ArrayList<String>();
		ArrayList<Object> operatorsInfoCollection = new ArrayList<Object>();
		
		HttpSession session = req.getSession();
		ResultSet result    = null;

		// * 1. Take data from list_operator.jsp fields and add info in Lists
		String operatorName              = req.getParameter("display_operator_info_operator_name");
		String teamLeaderName            = req.getParameter("display_operator_info_team_leader_name");
		String[] skills                  = req.getParameterValues("display_operator_info_skills");
		String isActive                  = req.getParameter("display_operator_info_isActive");
		String isMotherhood              = req.getParameter("display_operator_info_isMotherhood");
		String configTableOperatorName   = req.getParameter("table_operators_name");
		String configTableTeamLeader     = req.getParameter("table_team_leader");
		String configTableGender         = req.getParameter("table_gender");
		String configTableSkills         = req.getParameter("table_skills");
		String configTableIsActive       = req.getParameter("table_isActive");
		String configTableIsMotherhood   = req.getParameter("table_isMotherhood");
		String configTablePhone          = req.getParameter("table_phone");
		String configTableAbsenceHours   = req.getParameter("table_absence_hours");
		String configTableNumberAppron   = req.getParameter("table_number_appron");
		String configTableNumberHeater   = req.getParameter("table_number_heater");
		String configTableNumberSlippes  = req.getParameter("table_number_slippers");
		String configTableNumberWardrobe = req.getParameter("table_absence_number_wardrobe");


		// Create Array lists for make a choice SELECT method
		if (!operatorName.equals(all)) {
			fieldsCollection.add("tb_operators_operator_name");
			valueCollection.add(operatorName);
		}

		if (!teamLeaderName.equals(all)) {
			fieldsCollection.add("tb_operators_team_leader");
			valueCollection.add(teamLeaderName);
		}

		if (skills != null) {
			for (int i = 0; i < skills.length; i++) {

				if (skills[i].equals(mechAssOperator)) {
					fieldsCollection.add("tb_operators_mech_ass");
					valueCollection.add(skills[i]);
				}
				if (skills[i].equals(elAssOperator)) {
					fieldsCollection.add("tb_operators_el_ass");
					valueCollection.add(skills[i]);
				}
				if (skills[i].equals(testOperator)) {
					fieldsCollection.add("tb_operators_test");
					valueCollection.add(skills[i]);
				}
				if (skills[i].equals(packagingOperator)) {
					fieldsCollection.add("tb_operators_packaging");
					valueCollection.add(skills[i]);
				}
			}
		}

		if (!isActive.equals(all)) {
			fieldsCollection.add("tb_operators_isActive");
			valueCollection.add(isActive);
		}

		if (!isMotherhood.equals(all)) {
			fieldsCollection.add("tb_operators_isMotherhood");
			valueCollection.add(isMotherhood);
		}
		// **

		// ** 2. Select data form DB
		if (fieldsCollection.size() == 0) {
			result = dbActivities.select(table);
			result = dbActivities.sort(table, sortField);
		}
		if (fieldsCollection.size() == 1)
			result = dbActivities.selectWhereSort(table, fieldsCollection, valueCollection, sortField);
		if (fieldsCollection.size() > 1)
			result = dbActivities.selectWhereAnd(table, fieldsCollection, valueCollection, sortField);
		// **

		try {
			while (result.next()) {

				Operators operators = new Operators();

				operators.setFullName(result.getString("tb_operators_operator_name"));
				operators.setTeamLeader(result.getNString("tb_operators_team_leader"));
				operators.setGender(result.getString("tb_operators_gender"));
				operators.setSkills(result.getString("tb_operators_skills").split(", "));
				operators.setSkillList(operators.getSkills());
				operators.setIsActive(result.getString("tb_operators_isActive"));
				operators.setIsMotherhood(result.getNString("tb_operators_isMotherhood"));
				operators.setPhone(result.getString("tb_operators_phone"));
				operators.setAbsenceHours(result.getDouble("tb_operators_total_absence_hour"));
				operators.setNumberApron(result.getInt("tb_operators_number_apron"));
				operators.setNumberHeater(result.getString("tb_operators_number_heater"));
				operators.setNumberSlippers(result.getInt("tb_operators_number_slippers"));
				operators.setNumberWardrobe(result.getString("tb_operators_wardrobe"));
				operators.setChangeDateAppron(Date.date(result.getString("tb_operators_date_change_appron")));
				operators.setChangeDateHeater(Date.date(result.getString("tb_operators_date_change_heater")));
				operators.setChangeDateSlippers(Date.date(result.getString("tb_operators_date_change_slippers")));

				operatorsCollection.add(operators);

				if (configTableOperatorName.equals(yes)) operatorsInfoCollection.add(operators.getFullName());
				if (configTableTeamLeader.equals(yes)) operatorsInfoCollection.add(operators.getTeamLeader());
				if (configTableGender.equals(yes)) operatorsInfoCollection.add(operators.getGender());
				if (configTableSkills.equals(yes)) operatorsInfoCollection.add(operators.getSkillList());
				if (configTableIsActive.equals(yes)) operatorsInfoCollection.add(operators.getIsActive());
				if (configTableIsMotherhood.equals(yes)) operatorsInfoCollection.add(operators.getIsMotherhood());
				if (configTablePhone.equals(yes)) operatorsInfoCollection.add(operators.getPhone());
				if (configTableAbsenceHours.equals(yes)) operatorsInfoCollection.add(operators.getAbsenceHours());
				
				if (configTableNumberAppron.equals(yes)) operatorsInfoCollection.add(operators.getNumberApron());
				if (configTableNumberAppron.equals(yes)) operatorsInfoCollection.add(operators.getChangeDateAppron());
				
				if (configTableNumberHeater.equals(yes)) operatorsInfoCollection.add(operators.getNumberHeater());
				if (configTableNumberHeater.equals(yes)) operatorsInfoCollection.add(operators.getChangeDateHeater());
				
				if (configTableNumberSlippes.equals(yes)) operatorsInfoCollection.add(operators.getNumberSlippers());
				if (configTableNumberSlippes.equals(yes)) operatorsInfoCollection.add(operators.getChangeDateSlippers());
				
				if (configTableNumberWardrobe.equals(yes)) operatorsInfoCollection.add(operators.getNumberWardrobe());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (configTableOperatorName.equals(yes)) tableFieldCollection.add("Трите имена");
		if (configTableTeamLeader.equals(yes)) tableFieldCollection.add("Тийм лидер");
		if (configTableGender.equals(yes)) tableFieldCollection.add("Пол");
		if (configTableSkills.equals(yes)) tableFieldCollection.add("Тип оператор");
		if (configTableIsActive.equals(yes)) tableFieldCollection.add("Активен да/не");
		if (configTableIsMotherhood.equals(yes)) tableFieldCollection.add("Майчинство да/не");
		if (configTablePhone.equals(yes)) tableFieldCollection.add("Телефон");
		if (configTableAbsenceHours.equals(yes)) tableFieldCollection.add("Натрупани часове");
		
		if (configTableNumberAppron.equals(yes)) tableFieldCollection.add("Номер престилка");
		if (configTableNumberAppron.equals(yes)) tableFieldCollection.add("Дата престилка");
		
		if (configTableNumberHeater.equals(yes)) tableFieldCollection.add("Номер грейка");
		if (configTableNumberHeater.equals(yes)) tableFieldCollection.add("Дата грейка");
		
		if (configTableNumberSlippes.equals(yes)) tableFieldCollection.add("Номер чехли");
		if (configTableNumberSlippes.equals(yes)) tableFieldCollection.add("Дата чехли");
		
		if (configTableNumberWardrobe.equals(yes)) tableFieldCollection.add("Номер гардеробче");

		// Display table
		String finalTableHead = tables.createTableHead(tableFieldCollection);
		String finalTableBody = tables.createTableBody(operatorsCollection, operatorsInfoCollection);
		// **

		excelTableFieldCollection = tableFieldCollection;
		excelOperatorsInfoCollection = operatorsInfoCollection;
		excelOperatorsCollection = operatorsCollection;
		
		session.setAttribute("table_head", finalTableHead);
		session.setAttribute("operators_info_table_body", finalTableBody);
		
		req.getRequestDispatcher("display_operator_info.jsp").forward(req, resp);
		
		
		
	}
	
	
	
	public static void getOperatorsDataExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// reads input file from an absolute path
				String relativePath = req.getServletContext().getRealPath("");
				String filePath     = relativePath + "\\SAP\\" + "PresenceBlank.xlsx";
			
				
				File downloadFile   = new File(filePath);
				
				downloadFile.delete();
				
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet worksheet   = workbook.createSheet("presence_blank");
					
				ExcelTables.excelTableAbsence(workbook, worksheet, excelTableFieldCollection, excelOperatorsInfoCollection, excelOperatorsCollection);

				 FileOutputStream fileOutput = new FileOutputStream(filePath, true);
					workbook.write(fileOutput);

			        downloadFile             = new File(filePath);
			        FileInputStream inStream = new FileInputStream(downloadFile);
			         
			        // obtains ServletContext
			        ServletContext context   = req.getServletContext();
			         
			        // gets MIME type of the file
			        String mimeType          = context.getMimeType(filePath);
			        if (mimeType == null) {        
			            // set to binary type if MIME mapping not found
			            mimeType = "application/octet-stream";
			        }
			        System.out.println("MIME type: " + mimeType);
			         
			        // modifies response
			        resp.setContentType(mimeType);
			        resp.setContentLength((int) downloadFile.length());
			         
			        // forces download
			        String headerKey   = "Content-Disposition";
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
