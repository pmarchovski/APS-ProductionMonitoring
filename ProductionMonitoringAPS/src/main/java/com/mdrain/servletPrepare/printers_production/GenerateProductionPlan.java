package com.mdrain.servletPrepare.printers_production;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mdrain.logic.ExcelTables;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.objects.Orders;

public class GenerateProductionPlan {

	
	public static void bootstrap(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		ArrayList<Orders> orderCollection          = SetObjectInfo.getOrdersInfoFromDataBase();                          
		ArrayList<Object> productionPlanCollection = createProductionPlan(orderCollection, req, resp);              
		
		createProductionPlanExcel(req, resp, productionPlanCollection);
	}
	
	private static ArrayList<Object> createProductionPlan(ArrayList<Orders> orderCollection, HttpServletRequest req,
			HttpServletResponse resp) {
			WeekFields week      = WeekFields.of(Locale.getDefault());
			int year             = Integer.parseInt(req.getParameter("production_capacity_production_plan_week").substring(0, 4));		
			int weekNumber       = Integer.parseInt(req.getParameter("production_capacity_production_plan_week").substring(6, 8));
			String department    = req.getParameter("production_capacity_production_plan_department");
			LocalDate date;
			ArrayList<Object> productionPlanCollection = new ArrayList<Object>();
	
			for (int i = 0; i < orderCollection.size(); i++) {

				date = orderCollection.get(i).getStartDate();

				if (!department.equals("All")) {

					if (orderCollection.get(i).getStartDate().getYear() == year
							&& date.get(week.weekOfWeekBasedYear()) - 1 == weekNumber
							&& orderCollection.get(i).getProductionLine().equals(department)
							&& !orderCollection.get(i).getStatus().equals("TECO")
							&& !orderCollection.get(i).getStatus().equals("DELETED")) {
						
					
						productionPlanCollection.add(orderCollection.get(i).getNumber());
						productionPlanCollection.add(orderCollection.get(i).getMaterialNumber());
						productionPlanCollection.add(orderCollection.get(i).getMaterialDescription());
						productionPlanCollection.add(orderCollection.get(i).getQuantity());
						productionPlanCollection.add(orderCollection.get(i).getStartDate());
						productionPlanCollection.add(orderCollection.get(i).getEndDate());
						productionPlanCollection.add(orderCollection.get(i).getTeamLeader());
						productionPlanCollection.add(orderCollection.get(i).getExportDate());				
						productionPlanCollection.add(orderCollection.get(i).getCustomer());
						
					} 

				} else {
					
					if (orderCollection.get(i).getStartDate().getYear() == year
							&& date.get(week.weekOfWeekBasedYear()) - 1 == weekNumber
							&& !orderCollection.get(i).getStatus().equals("TECO")
							&& !orderCollection.get(i).getStatus().equals("DELETED")) {

						productionPlanCollection.add(orderCollection.get(i).getNumber());
						productionPlanCollection.add(orderCollection.get(i).getMaterialNumber());
						productionPlanCollection.add(orderCollection.get(i).getMaterialDescription());
						productionPlanCollection.add(orderCollection.get(i).getQuantity());
						productionPlanCollection.add(orderCollection.get(i).getStartDate());
						productionPlanCollection.add(orderCollection.get(i).getEndDate());
						productionPlanCollection.add(orderCollection.get(i).getTeamLeader());
						productionPlanCollection.add(orderCollection.get(i).getExportDate());			
						productionPlanCollection.add(orderCollection.get(i).getCustomer());
						
					}
				}
			}

			return productionPlanCollection;
		}

		private static void createProductionPlanExcel(HttpServletRequest req, HttpServletResponse resp, 
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
			headerFieldsCollection.add("Клиент");
			
			
			        int weekNumber = Integer.parseInt(req.getParameter("production_capacity_production_plan_week").substring(6, 8));
			      
			        
			// reads input file from an absolute path
					String relativePath = req.getServletContext().getRealPath("");
					String filePath = relativePath + "\\SAP\\" + "ProductionPlan.xlsx";
					
					File downloadFile = new File(filePath);
					
					downloadFile.delete();
					
					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet worksheet = workbook.createSheet("ProductionPlan");

					ExcelTables.excelTableProductionPlan(workbook, worksheet, headerFieldsCollection, productionPlanCollection, weekNumber);

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
