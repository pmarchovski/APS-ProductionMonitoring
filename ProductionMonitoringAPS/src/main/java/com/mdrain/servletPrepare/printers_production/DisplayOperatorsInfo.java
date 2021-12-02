package com.mdrain.servletPrepare.printers_production;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import com.mdrain.variables.Variables;


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
		
		DataBaseActivities dbActivities                = Singleton.getInstance();
		ArrayList<String> fieldsCollection             = new ArrayList<String>();
		ArrayList<String> tableFieldCollection         = new ArrayList<String>();		
		ArrayList<Operators> operatorsCollection       = new ArrayList<Operators>();
		ArrayList<String> valueCollection              = new ArrayList<String>();
		ArrayList<Object> operatorsInfoCollection      = new ArrayList<Object>();
		ArrayList<Object> operatorsInfoCollectionExcel = new ArrayList<Object>();
		Tables tables                                  = new Tables();
		
		HttpSession session = req.getSession();
		ResultSet result    = null;

		// * 1. Take data from list_operator.jsp fields and add info in Lists
		String operatorName              = req.getParameter("display_operator_info_operator_name");
		String teamLeaderName            = req.getParameter("display_operator_info_team_leader_name");
		String[] skills                  = req.getParameterValues("display_operator_info_skills");
		String isActive                  = req.getParameter("display_operator_info_isActive");
		String isMotherhood              = req.getParameter("display_operator_info_isMotherhood");
		String[] tableConfiguration      = req.getParameterValues("operators_info_config_table");


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

		if (result != null) {
			
			try {
				while (result.next()) {

					Operators operators = new Operators();

					operators.setFullName(result.getString("tb_operators_operator_name"));
					operators.setTeamLeader(result.getNString("tb_operators_team_leader"));
					operators.setGender(result.getString("tb_operators_gender"));
					
					if (result.getString("tb_operators_skills").equals("") || result.getString("tb_operators_skills") == null) {
						String[] skillCollect = {result.getString("tb_operators_other")};
						operators.setSkills(skillCollect);
					} else {
						operators.setSkills(result.getString("tb_operators_skills").split(", "));
					}			
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
					operators.setNextChangeDateAppron(operators.getChangeDateAppron());
					operators.setChangeDateHeater(Date.date(result.getString("tb_operators_date_change_heater")));
					operators.setNextChangeDateHeater(operators.getChangeDateHeater());
					operators.setChangeDateSlippers(Date.date(result.getString("tb_operators_date_change_slippers")));
					operators.setNextChangeDateSlippers(operators.getChangeDateSlippers());

					operatorsCollection.add(operators);

					
					for (int i = 0; i < tableConfiguration.length; i++) {
						
						//**** for html table
						if (tableConfiguration[i].equals("full_name")) operatorsInfoCollection.add(operators.getFullName());
						if (tableConfiguration[i].equals("team_leader")) operatorsInfoCollection.add(operators.getTeamLeader());
						if (tableConfiguration[i].equals("jender")) operatorsInfoCollection.add(operators.getGender());
						if (tableConfiguration[i].equals("operator_type")) operatorsInfoCollection.add(operators.getSkillList());
						if (tableConfiguration[i].equals("is_active")) operatorsInfoCollection.add(operators.getIsActive());
						if (tableConfiguration[i].equals("is_motherhood")) operatorsInfoCollection.add(operators.getIsMotherhood());
						if (tableConfiguration[i].equals("phone")) operatorsInfoCollection.add(operators.getPhone());
						if (tableConfiguration[i].equals("absence_hours")) operatorsInfoCollection.add(operators.getAbsenceHours());
						
						if (tableConfiguration[i].equals("number_apron")) operatorsInfoCollection.add(operators.getNumberApron());
						if (tableConfiguration[i].equals("number_apron")) operatorsInfoCollection.add(Date.convertDate(operators.getChangeDateAppron()));				
						if (tableConfiguration[i].equals("number_apron")) operatorsInfoCollection.add(getNextChanageDateCondition(operators.getNextChangeDateAppron()));
						
						if (tableConfiguration[i].equals("number_heater")) operatorsInfoCollection.add(operators.getNumberHeater());
						if (tableConfiguration[i].equals("number_heater")) operatorsInfoCollection.add(Date.convertDate(operators.getChangeDateHeater()));			
						if (tableConfiguration[i].equals("number_heater")) operatorsInfoCollection.add(getNextChanageDateCondition(operators.getNextChangeDateHeater()));
							
						if (tableConfiguration[i].equals("number_slippers")) operatorsInfoCollection.add(operators.getNumberSlippers());
						if (tableConfiguration[i].equals("number_slippers")) operatorsInfoCollection.add(Date.convertDate(operators.getChangeDateSlippers()));		
						if (tableConfiguration[i].equals("number_slippers")) operatorsInfoCollection.add(getNextChanageDateCondition(operators.getNextChangeDateSlippers()));
						
						
						
						if (tableConfiguration[i].equals("number_wardrob")) operatorsInfoCollection.add(operators.getNumberWardrobe());
						 
						//**** for excel table
						if (tableConfiguration[i].equals("full_name")) operatorsInfoCollectionExcel.add(operators.getFullName());
						if (tableConfiguration[i].equals("team_leader")) operatorsInfoCollectionExcel.add(operators.getTeamLeader());
						if (tableConfiguration[i].equals("jender")) operatorsInfoCollectionExcel.add(operators.getGender());
						if (tableConfiguration[i].equals("operator_type")) operatorsInfoCollectionExcel.add(operators.getSkillList());
						if (tableConfiguration[i].equals("is_active")) operatorsInfoCollectionExcel.add(operators.getIsActive());
						if (tableConfiguration[i].equals("is_motherhood")) operatorsInfoCollectionExcel.add(operators.getIsMotherhood());
						if (tableConfiguration[i].equals("phone")) operatorsInfoCollectionExcel.add(operators.getPhone());
						if (tableConfiguration[i].equals("absence_hours")) operatorsInfoCollectionExcel.add(operators.getAbsenceHours());
						
						if (tableConfiguration[i].equals("number_apron")) operatorsInfoCollectionExcel.add(operators.getNumberApron());
						if (tableConfiguration[i].equals("number_apron")) operatorsInfoCollectionExcel.add(Date.convertDate(operators.getChangeDateAppron()));
						if (tableConfiguration[i].equals("number_apron")) operatorsInfoCollectionExcel.add(Date.convertDate(operators.getNextChangeDateAppron()));
						
						if (tableConfiguration[i].equals("number_heater")) operatorsInfoCollectionExcel.add(operators.getNumberHeater());
						if (tableConfiguration[i].equals("number_heater")) operatorsInfoCollectionExcel.add(Date.convertDate(operators.getChangeDateHeater()));
						if (tableConfiguration[i].equals("number_heater")) operatorsInfoCollectionExcel.add(Date.convertDate(operators.getNextChangeDateHeater()));
						
						if (tableConfiguration[i].equals("number_slippers")) operatorsInfoCollectionExcel.add(operators.getNumberSlippers());
						if (tableConfiguration[i].equals("number_slippers")) operatorsInfoCollectionExcel.add(Date.convertDate(operators.getChangeDateSlippers()));
						if (tableConfiguration[i].equals("number_slippers")) operatorsInfoCollectionExcel.add(Date.convertDate(operators.getNextChangeDateSlippers()));
						
						if (tableConfiguration[i].equals("number_wardrob")) operatorsInfoCollectionExcel.add(operators.getNumberWardrobe());
						
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			getOperatorsData(req, resp);
		}
		

		for (int i = 0; i < tableConfiguration.length; i++) {
			
		
		if (tableConfiguration[i].equals("full_name")) tableFieldCollection.add("Трите имена");
		if (tableConfiguration[i].equals("team_leader")) tableFieldCollection.add("Тийм лидер");
		if (tableConfiguration[i].equals("jender")) tableFieldCollection.add("Пол");
		if (tableConfiguration[i].equals("operator_type")) tableFieldCollection.add("Тип служител");
		if (tableConfiguration[i].equals("is_active")) tableFieldCollection.add("Активен да/не");
		if (tableConfiguration[i].equals("is_motherhood")) tableFieldCollection.add("Майчинство да/не");
		if (tableConfiguration[i].equals("phone")) tableFieldCollection.add("Телефон");
		if (tableConfiguration[i].equals("absence_hours")) tableFieldCollection.add("Натрупани часове");
		
		if (tableConfiguration[i].equals("number_apron")) tableFieldCollection.add("Номер престилка");
		if (tableConfiguration[i].equals("number_apron")) tableFieldCollection.add("Последна смяна на престилка");
		if (tableConfiguration[i].equals("number_apron")) tableFieldCollection.add("Следваща смяна на престилка");
		
		if (tableConfiguration[i].equals("number_heater")) tableFieldCollection.add("Номер грейка");
		if (tableConfiguration[i].equals("number_heater")) tableFieldCollection.add("Последна смяна на грейка");
		if (tableConfiguration[i].equals("number_heater")) tableFieldCollection.add("Следваща смяна на грейка");
		
		if (tableConfiguration[i].equals("number_slippers")) tableFieldCollection.add("Номер чехли");
		if (tableConfiguration[i].equals("number_slippers")) tableFieldCollection.add("Последна смяна на чехли");
		if (tableConfiguration[i].equals("number_slippers")) tableFieldCollection.add("Следваща смяна на чехли");
		
		if (tableConfiguration[i].equals("number_wardrob")) tableFieldCollection.add("Номер гардеробче");
		
	    }

		// Display table
		String finalTableHead = tables.createTableHead(tableFieldCollection);
		String finalTableBody = tables.createTableBody(operatorsCollection, operatorsInfoCollection);
		// **

		excelTableFieldCollection = tableFieldCollection;
		excelOperatorsInfoCollection = operatorsInfoCollectionExcel;
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
	
	public static String getNextChanageDateCondition(LocalDate nextChangeDate) {
		
		LocalDate firstDate = LocalDate.of(1900, 1, 1);
		LocalDate currentDate = LocalDate.now().plusDays(Variables.getWorkClothesLeatTyme());
		long daysBetweenCurrent = Date.getDaysBetweenTwoDates(firstDate, currentDate);
		long daysBetweenNextChange = Date.getDaysBetweenTwoDates(firstDate, nextChangeDate);
		String dateNextChange = Date.convertDate(nextChangeDate);
		
		
		if (daysBetweenCurrent >= daysBetweenNextChange) {
			dateNextChange = "<span style=\"color:red;font-weight:bold\">" + dateNextChange + "</span>";
		} else {
			dateNextChange = "<span style=\"color:black\">" + dateNextChange + "</span>";
		}
		
		return dateNextChange;
	}
}
