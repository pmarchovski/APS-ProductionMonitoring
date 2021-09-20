package com.mdrain.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import com.mdrain.logic.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Orders;

public class GenerateProductionPlanServlet extends HttpServlet {

	final String AL31_TEAM_LEADER = "Ренета Кондарева";
	final String AL32_TEAM_LEADER = "Валя Колева";
	final String BR21_TEAM_LEADER = "Румяна Димитрова";
	final String BR22_TEAM_LEADER = "Петър Гацовски";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<Object> orderCollection = new ArrayList<Object>();
		ArrayList<Object> orderWorkCenterCollection = new ArrayList<Object>();
		DataBaseActivities dbActivities = new DataBaseActivities();
		ResultSet result = null;
		String table = "tb_coois_prod";
		String tableWorkCenter = "tb_coois_operation";
		LocalDate date;

		// Вземаме информация за работните центрове
		ResultSet resultWorkCenter = null;
		resultWorkCenter = dbActivities.select(tableWorkCenter);

		try {
			while (resultWorkCenter.next()) {

				Orders orderWorkCenter = new Orders();

				orderWorkCenter.setNumber(Integer.parseInt(resultWorkCenter.getString("tb_coois_operation_order")));

				if (resultWorkCenter.getString("tb_coois_operation_work_center").equals("AUD0001"))
					orderWorkCenter.setProductionLine("BR21");
				if (resultWorkCenter.getString("tb_coois_operation_work_center").equals("IND0001"))
					orderWorkCenter.setProductionLine("AL31");
				if (resultWorkCenter.getString("tb_coois_operation_work_center").equals("IND0003"))
					orderWorkCenter.setProductionLine("AL31");
				if (resultWorkCenter.getString("tb_coois_operation_work_center").equals("IND0004"))
					orderWorkCenter.setProductionLine("AL32");
				if (resultWorkCenter.getString("tb_coois_operation_work_center").equals("IND0005"))
					orderWorkCenter.setProductionLine("AL32");
				if (resultWorkCenter.getString("tb_coois_operation_work_center").equals("IND0002"))
					orderWorkCenter.setProductionLine("BR22");

				orderWorkCenterCollection.add(orderWorkCenter);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Вземаме общата информация за поръчките
		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Orders order = new Orders();

				order.setNumber(Integer.parseInt(result.getString("tb_coois_prod_order")));
				order.setMaterialNumber(result.getString("tb_coois_prod_material_number"));
				order.setMaterialDescription(result.getString("tb_coois_prod_material_description"));
				order.setQuantity(Integer.parseInt(result.getString("tb_coois_prod_quantity")));
				order.setStartDate(Date.date(result.getString("tb_coois_prod_start_date")));
				order.setEndDate(Date.date(result.getString("tb_coois_prod_end_date")));
				order.setStatus(result.getString("tb_coois_prod_status"));
				order.setExportDate(Date.date(result.getString("tb_coois_prod_export_date")));

//				if (Date.date(result.getString("tb_coois_prod_export_date")) != null) {
//					order.setExportDate(Date.date(result.getString("tb_coois_prod_export_date")));
//				}

				for (int i = 0; i < orderWorkCenterCollection.size(); i++) {

					Orders tempOrder = (Orders) orderWorkCenterCollection.get(i);

					if (order.getNumber() == tempOrder.getNumber()) {

						order.setProductionLine(tempOrder.getProductionLine());
					}
				}

				orderCollection.add(order);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		ArrayList<Object> productionPlanCollection = new ArrayList<Object>();
		productionPlanCollection = createProductionPlan(orderCollection, req, resp);
		createProductionPlanExcel(req, resp, productionPlanCollection);

		
	}

	private ArrayList<Object> createProductionPlan(ArrayList<Object> orderCollection, HttpServletRequest req,
		HttpServletResponse resp) {
		WeekFields week = WeekFields.of(Locale.getDefault());
		int year = Integer.parseInt(req.getParameter("production_capacity_production_plan_year"));
		int weekNumber = Integer.parseInt(req.getParameter("production_capacity_production_plan_week"));
		String department = req.getParameter("production_capacity_production_plan_department");
		LocalDate date;
		ArrayList<Object> productionPlanCollection = new ArrayList<Object>();
 
		
		
		for (int i = 0; i < orderCollection.size(); i++) {

			Orders orderManipulation = (Orders) orderCollection.get(i);

			date = orderManipulation.getStartDate();

			if (!department.equals("All")) {

				if (orderManipulation.getStartDate().getYear() == year
						&& date.get(week.weekOfWeekBasedYear()) == weekNumber
						&& orderManipulation.getProductionLine().equals(department)
						&& !orderManipulation.getStatus().contains("TECO")
						&& !orderManipulation.getStatus().contains("DLFL")) {

					
					if (orderManipulation.getProductionLine().equals("AL31")) orderManipulation.setTeamLeader(AL31_TEAM_LEADER);
					if (orderManipulation.getProductionLine().equals("AL32")) orderManipulation.setTeamLeader(AL32_TEAM_LEADER);
					if (orderManipulation.getProductionLine().equals("BR21")) orderManipulation.setTeamLeader(BR21_TEAM_LEADER);
					if (orderManipulation.getProductionLine().equals("BR22")) orderManipulation.setTeamLeader(BR22_TEAM_LEADER);
					
					productionPlanCollection.add(orderManipulation.getNumber());
					productionPlanCollection.add(orderManipulation.getMaterialNumber());
					productionPlanCollection.add(orderManipulation.getMaterialDescription());
					productionPlanCollection.add(orderManipulation.getQuantity());
					productionPlanCollection.add(orderManipulation.getStartDate());
					productionPlanCollection.add(orderManipulation.getEndDate());
					productionPlanCollection.add(orderManipulation.getTeamLeader());
					productionPlanCollection.add(orderManipulation.getExportDate());				
					productionPlanCollection.add("  ");
					
				} 

			} else {
				
				if (orderManipulation.getStartDate().getYear() == year
						&& date.get(week.weekOfWeekBasedYear()) == weekNumber
						&& !orderManipulation.getStatus().contains("TECO")
						&& !orderManipulation.getStatus().contains("DLFL")) {
					
					if (orderManipulation.getProductionLine().equals("AL31")) orderManipulation.setTeamLeader(AL31_TEAM_LEADER);
					if (orderManipulation.getProductionLine().equals("AL32")) orderManipulation.setTeamLeader(AL32_TEAM_LEADER);
					if (orderManipulation.getProductionLine().equals("BR21")) orderManipulation.setTeamLeader(BR21_TEAM_LEADER);
					if (orderManipulation.getProductionLine().equals("BR22")) orderManipulation.setTeamLeader(BR22_TEAM_LEADER);

					productionPlanCollection.add(orderManipulation.getNumber());
					productionPlanCollection.add(orderManipulation.getMaterialNumber());
					productionPlanCollection.add(orderManipulation.getMaterialDescription());
					productionPlanCollection.add(orderManipulation.getQuantity());
					productionPlanCollection.add(orderManipulation.getStartDate());
					productionPlanCollection.add(orderManipulation.getEndDate());
					productionPlanCollection.add(orderManipulation.getTeamLeader());
					productionPlanCollection.add(orderManipulation.getExportDate());			
					productionPlanCollection.add("  ");
					
				}
			}
		}

		return productionPlanCollection;
	}

	private void createProductionPlanExcel(HttpServletRequest req, HttpServletResponse resp, 
			                               ArrayList<Object> productionPlanCollection) throws IOException {
		
		ArrayList<String> headerFieldsCollection = new ArrayList<String>();
		headerFieldsCollection.add("Поръчка");
		headerFieldsCollection.add("Изделие");
		headerFieldsCollection.add("Описание");
		headerFieldsCollection.add("Количество");
		headerFieldsCollection.add("Дата старт");
		headerFieldsCollection.add("Дата край");
		headerFieldsCollection.add("Тийм лидер");
		headerFieldsCollection.add("Дата за износ");
		headerFieldsCollection.add("Коментар");
		
		
		        int weekNumber = Integer.parseInt(req.getParameter("production_capacity_production_plan_week"));
		      
		        
		// reads input file from an absolute path
				String relativePath = getServletContext().getRealPath("");
				String filePath = relativePath + "\\SAP\\" + "ProductionPlan.xlsx";
				
				File downloadFile = new File(filePath);
				
				downloadFile.delete();
				
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet worksheet = workbook.createSheet("ProductionPlan");
				
			//	ExcelTables.writeExcelHeaderLineProdPlan(workbook, worksheet, headerFieldsCollection);
			//	ExcelTables.writeExcelDataLineProdPlan(productionPlanCollection, workbook, worksheet, headerFieldsCollection, weekNumber);
				ExcelTables.excelTableProductionPlan(workbook, worksheet, headerFieldsCollection, productionPlanCollection, weekNumber);

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
