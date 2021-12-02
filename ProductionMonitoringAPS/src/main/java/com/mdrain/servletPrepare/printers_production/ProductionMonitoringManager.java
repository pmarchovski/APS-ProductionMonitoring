package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.logic.Date;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Orders;

public class ProductionMonitoringManager {

   static String longTimeNoProductionHtmlStringFormatStart = "<p style=\"color:red; font-weight: bold;\">";
   static String activeOrderHtmlFormatString               = "<p style=\"background-color:#ffff80; font-weight: bold;\">";
   static String finishOrderHtmlFormatString               = "<p style=\"background-color:#00ce67; font-weight: bold;\">";
   static String delayOrderHtmlFormatString                = "<p style=\"background-color:#fb4646; font-weight: bold;\">";
   static String currentWeekHtmlFormatString               = "<p style=\"background-color:#00ce67; font-weight: bold;\">";
   static String htmlStringFormatEnd                       = "</p>";
   static String blinkStart                                = "<blink>";
   static String blinkEnd                                  = "</blink>";
   static LocalDate today                                  = LocalDate.now();
   static LocalDate firstDay                               = LocalDate.of(1900, 1, 1);
   static int maxExecute                                   = 100;
	
	public static void displayProductionDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session                  = req.getSession();
        ArrayList<Object> dataInfoCollection = new ArrayList<Object>(); 
		String[] productionLineCollection    = req.getParameterValues("production_orders_dashboard_check_box");
		String weekFromWebApp                = req.getParameter("production_orders_dashboard_week");
		String monthFromWebApp               = req.getParameter("production_orders_dashboard_month");
		int yearAndWeek[]                    = Date.convertNumberWeek(weekFromWebApp);
		int yearAndMonth[]                   = Date.convertNumberMonth(monthFromWebApp);
		int year                             = yearAndWeek[0];
		int week                             = yearAndWeek[1];
		int yearMonth                        = yearAndMonth[0];
		int month                            = yearAndMonth[1];
		Tables tables                        = new Tables();

		String[] columnNameCollection = {
				"Линия",
				"Поръчка",
				"Изделие",
				"Описание",
				"Старт дата",
				"Край дата",
				"Седмица",
				"Количество",
				"Произведено",
				"Дата на износ",
				"Клиент",
				"Изпълнение"
		};
		

		if (year == 9999 && week == 99 && yearMonth == 9999 && month == 99) {
			dataInfoCollection = getDataInfoCollectionAll(productionLineCollection);
		} else if (year != 9999 && week != 99){
			dataInfoCollection = getDataInfoCollectionByWeek(year, week, productionLineCollection);
		} else if (yearMonth != 9999 && month != 99 && year == 9999 && week == 99) {
			dataInfoCollection = getDataInfoCollectionByMonth(yearMonth, month, productionLineCollection);
		}
		
		String tableHead = tables.createTableHeadFreezHead(columnNameCollection);
		String tableBody = tables.createTableBodyStringObject(columnNameCollection, dataInfoCollection);
		
		session.setAttribute("production_orders_dashboard_table_head", tableHead);
		session.setAttribute("production_orders_dashboard_table_body", tableBody);
		
	
		req.getRequestDispatcher("production_orders_dashboard.jsp").forward(req, resp);
	}
	
	private static int returnExecuteOrderPercent(int planedQuantity, int producedQuantity) {
		
		int percentExecute = (producedQuantity * 100) / planedQuantity;			
		if (producedQuantity == 0) percentExecute = 0;
		
		return percentExecute;
	}
	
	private static String doneCheck(int progress, int maxExecute) {
		
		String done = "";
		
		if (progress == maxExecute) {
			done = "green";
		} else if (progress > 0 && progress < maxExecute){
			done = "red";
		} else {
			done = "";
		}
		
		return done;
	}
	
	private static String returnProgressExecuteBarHtmlString(String done, int progress) {
		
		String progressExecuteBarHtmlString = "";
		
		if (done == "") {
			progressExecuteBarHtmlString = "";
		} else {		
			progressExecuteBarHtmlString = "<div class=\"w3-light-grey\">\r\n"
				+ "<div class=\"w3-container w3-" + done + " w3-center\" style=\"width:" + progress + "%\">" + progress + "%</div>\r\n"
				+ "</div>";
		}
			
		return progressExecuteBarHtmlString;
	}
		
    private static ArrayList<Object> getDataInfoCollectionAll(String[] productionLineCollection ){
	    
	    ArrayList<Orders> ordersCollection   = SetObjectInfo.getFullProductionOrdersInfo();
        ArrayList<Object> dataInfoCollection = new ArrayList<Object>(ordersCollection.size());
		
		for (int i = 0; i < ordersCollection.size(); i++) {	
			dataInfoCollection = getData(i, productionLineCollection, ordersCollection, dataInfoCollection);	
			}	
		return dataInfoCollection;
	}
	
    
	private static ArrayList<Object> getDataInfoCollectionByWeek(int year, int week, String[] productionLineCollection ){
		
		ArrayList<Orders> ordersCollection   = SetObjectInfo.getFullProductionOrdersInfo();
        ArrayList<Object> dataInfoCollection = new ArrayList<Object>(ordersCollection.size());
        
		for (int i = 0; i < ordersCollection.size(); i++) {
			if (year == ordersCollection.get(i).getStartDate().getYear() 
					&& week == ordersCollection.get(i).getWeek()) dataInfoCollection 
			                                                      = getData(i, productionLineCollection, ordersCollection, dataInfoCollection);			
		}	
		return dataInfoCollection;
	}
	

     private static ArrayList<Object> getDataInfoCollectionByMonth(int yearMonth, int month, String[] productionLineCollection ){

        ArrayList<Orders> ordersCollection   = SetObjectInfo.getFullProductionOrdersInfo();
        ArrayList<Object> dataInfoCollection = new ArrayList<Object>(ordersCollection.size());

        for (int i = 0; i < ordersCollection.size(); i++) {
	       if (yearMonth == ordersCollection.get(i).getStartDate().getYear() 
	    		   && month == ordersCollection.get(i).getStartDate().getMonthValue()) dataInfoCollection 
	                                                             = getData(i, productionLineCollection, ordersCollection, dataInfoCollection);
      }
        return dataInfoCollection;
    }


    private static ArrayList<Object> getData(int i, String[] productionLineCollection, ArrayList<Orders> ordersCollection, ArrayList<Object> dataInfoCollection) {
	
    	for (int j = 0; j < productionLineCollection.length; j++) {
			
			if (ordersCollection.get(i).getProductionLine().equals(productionLineCollection[j])) {
					
				int delQuantity              = ordersCollection.get(i).getDelQuantity();
				int quantity                 = ordersCollection.get(i).getQuantity();
				int weekProd                 = ordersCollection.get(i).getWeek();
				String orderNumber           = String.valueOf(ordersCollection.get(i).getNumber());
				LocalDate exportOrderDate    = ordersCollection.get(i).getExportDate();
				long daysToday               = Date.getDaysBetweenTwoDates(firstDay, today);
				long daysExportDate          = Date.getDaysBetweenTwoDates(firstDay, exportOrderDate);
				int currentWeek              = Date.getWeekNumberFromCurrentDate(today) - 1;
				
				dataInfoCollection.add(ordersCollection.get(i).getProductionLine());
				
				
				if (delQuantity < quantity && daysExportDate <= daysToday) {
					orderNumber = blinkStart + delayOrderHtmlFormatString + orderNumber + htmlStringFormatEnd + blinkEnd;
				//	orderNumber = getScript(orderNumber);
					
				} else if (delQuantity < quantity && weekProd <=  currentWeek){
					orderNumber = activeOrderHtmlFormatString + orderNumber + htmlStringFormatEnd;
				}
				
				if (delQuantity < quantity && daysExportDate - 1 <= daysToday) {
					orderNumber = blinkStart + activeOrderHtmlFormatString + orderNumber + htmlStringFormatEnd + blinkEnd;
				}
				
				if (delQuantity == quantity) {
					orderNumber = finishOrderHtmlFormatString + orderNumber + htmlStringFormatEnd;
				}
				
	
		        dataInfoCollection.add(orderNumber);
		
		        if (ordersCollection.get(i).isMaterialProducedLong() == true) {
			       dataInfoCollection.add(longTimeNoProductionHtmlStringFormatStart + ordersCollection.get(i).getMaterialNumber() + htmlStringFormatEnd);
			       dataInfoCollection.add(longTimeNoProductionHtmlStringFormatStart + ordersCollection.get(i).getMaterialDescription() + htmlStringFormatEnd);
		        } else {
			       dataInfoCollection.add(ordersCollection.get(i).getMaterialNumber());
			       dataInfoCollection.add(ordersCollection.get(i).getMaterialDescription());
		        }
		       dataInfoCollection.add(Date.convertDate(ordersCollection.get(i).getStartDate()));
		       dataInfoCollection.add(Date.convertDate(ordersCollection.get(i).getEndDate()));
		       if (currentWeek == weekProd) {
		    	   dataInfoCollection.add(currentWeekHtmlFormatString + weekProd + htmlStringFormatEnd);
		       } else {
		    	  dataInfoCollection.add(weekProd); 
		       }
		       dataInfoCollection.add(quantity);
		       dataInfoCollection.add(delQuantity);
		       dataInfoCollection.add(Date.convertDate(ordersCollection.get(i).getExportDate()));
		       dataInfoCollection.add(ordersCollection.get(i).getCustomer());
		
		       int progress = returnExecuteOrderPercent(ordersCollection.get(i).getQuantity(), ordersCollection.get(i).getDelQuantity());
		       String done  = doneCheck(progress, maxExecute);
		       String progressExecuteBar = returnProgressExecuteBarHtmlString(done, progress);
		       
		       dataInfoCollection.add(progressExecuteBar);
				
			}		
		}
    	
    	return dataInfoCollection;
    }
    
    private static String getScript(String order) {
    	
    	String query = "";
    	
    	query = "<script>\r\n"
    			+ "$(function () {\r\n"
    			+ "	$('#basicBoard').flightboard({messages: ['DELAY'," + "'" + order + "'" + "],\r\n"
    			+ "		lettersImage: 'flightBoardLarge.png',\r\n"
    			+ "		shadingImages: ['flightBoardHigh.png', 'flightBoardShad.png']});\r\n"
    			+ "});\r\n"
    			+ "</script>";
    	
    	return query;
    }
}
