package com.mdrain.servletPrepare.finance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.logic.ExcelTables;
import com.mdrain.logic.Tables;
import com.mdrain.objects.FinanceDma;
import com.mdrain.singletons.Singleton;

public class FinanceProtocolDmaDisplayAndUpdate{
	
	static String idForUpdate = "";
	
	public static void prepareProtocolDataForDisplay(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String protocolDate     = req.getParameter("finance_protokol_display_update_dma_date");
		String exploatationDate = req.getParameter("finance_protokol_display_update_dma_exploatation");
		String supplier         = req.getParameter("finance_protokol_display_update_dma_supplie");
		String place            = req.getParameter("finance_protokol_display_update_dma_place");
		String costCenter       = req.getParameter("finance_protokol_display_update_dma_project");
		String invNumber        = req.getParameter("finance_protokol_display_update_dma_invemtory_number");
		String resposiblePerson = req.getParameter("finance_protokol_display_updatel_dma_responsibility_person");

		ArrayList<FinanceDma> dmaCollectioData = new ArrayList<FinanceDma>();
		FinanceDma dmaManipulation             = new FinanceDma();
		HttpSession session                    = req.getSession();
		Tables table                           = new Tables();
		
		String linkDisplay   = "<a href=" + "\"" + "create_dma_protokol_display_explicit_servlet?id=";
		String linkUpdate    = "<a href=" + "\"" + "create_dma_protokol_update_explicit_servlet?id=";
		String linkDownload  = "<a href=" + "\"" + "create_dma_protokol_download_explicit_servlet?id=";
		String linkEnd       = "</a>";

		dmaCollectioData = getDmaProtocolDataFromDataBase();

		ArrayList<String> tableFieldsNameCollection = getMainTableFieldsName();
		ArrayList<Object> tableDataCollection       = new ArrayList<Object>();

		for (int i = 0; i < dmaCollectioData.size(); i++) {
			
			if (protocolDate.equals("") && exploatationDate.equals("") && supplier == null && costCenter.equals("")
					&& invNumber.equals("") && resposiblePerson.equals("") && place.equals("")) {
				
				    dmaManipulation = dmaCollectioData.get(i);

				for (int j = 0; j < 1; j++) {
					tableDataCollection.add(String.valueOf(dmaManipulation.getNumber()));
					tableDataCollection.add(String.valueOf(dmaManipulation.getDmaProtocolDate()));
					tableDataCollection.add(String.valueOf(dmaManipulation.getDmaName()));
					tableDataCollection.add(String.valueOf(dmaManipulation.getDmaInvNumber()));
					tableDataCollection.add(linkDisplay + dmaManipulation.getNumber() + "\"" + ">" + "покажи" + linkEnd);
					tableDataCollection.add(linkUpdate + dmaManipulation.getNumber() + "\"" + ">" + "редактирай" + linkEnd);
					tableDataCollection.add(linkDownload + dmaManipulation.getNumber() + "\"" + ">" + "изтегли" + linkEnd);
				
				}
			} else {
				
				dmaManipulation = dmaCollectioData.get(i);
				LocalDate protocolDateFromObj     = null;
				LocalDate exploatationDateFromObj = null;
				
				if (!protocolDate.equals("")) protocolDateFromObj         = Date.date(protocolDate);
				if (!exploatationDate.equals("")) exploatationDateFromObj = Date.date(exploatationDate);
				
				
				if (dmaManipulation.getDmaProtocolDate().equals(protocolDateFromObj)             
						|| dmaManipulation.getDmaExploatationDate().equals(exploatationDateFromObj) 
						|| dmaManipulation.getDmaSuplier().equals(supplier)
						|| dmaManipulation.getDmaUsePlace().equals(place)
						|| dmaManipulation.getDmaProject().equals(costCenter)
						|| dmaManipulation.getDmaInvNumber().equals(invNumber)
						|| dmaManipulation.getDmaResponsiblePerson().equals(resposiblePerson)) {
					
					for (int j = 0; j < 1; j++) {
						tableDataCollection.add(String.valueOf(dmaManipulation.getNumber()));
						tableDataCollection.add(String.valueOf(dmaManipulation.getDmaProtocolDate()));
						tableDataCollection.add(String.valueOf(dmaManipulation.getDmaName()));
						tableDataCollection.add(String.valueOf(dmaManipulation.getDmaInvNumber()));
						tableDataCollection.add(linkDisplay + dmaManipulation.getNumber() + "\"" + ">" + "покажи" + linkEnd);
						tableDataCollection.add(linkUpdate + dmaManipulation.getNumber() + "\"" + ">" + "редактирай" + linkEnd);
						tableDataCollection.add(linkDownload + dmaManipulation.getNumber() + "\"" + ">" + "изтегли" + linkEnd);
					}	
				}		
			}
		}

		String tableHead = table.createTableHead(tableFieldsNameCollection);
		session.setAttribute("display_prtocol_collection_table_head", tableHead);

		String tableBody = table.createTableBodyStringObject(tableFieldsNameCollection, tableDataCollection);
		session.setAttribute("display_prtocol_collection_table_body", tableBody);

		resp.sendRedirect("finance_protokol_dma_display_update.jsp");
	}
	
	

	public static void getExpliciteProtocol(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session                    = req.getSession();
		int id                                 = Integer.parseInt(req.getParameter("id"));
		ArrayList<FinanceDma> dmaCollectioData = new ArrayList<FinanceDma>();
		ArrayList<String> protocolDataTable    = new ArrayList<String>();
		ArrayList<String> protocolFieldTable   = new ArrayList<String>();
		Tables table                           = new Tables();
		
		dmaCollectioData = getDmaProtocolDataFromDataBase();
		
		for (int i = 0; i < dmaCollectioData.size(); i++) {
			
		  if (dmaCollectioData.get(i).getNumber() == id) {
			 
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getNumber()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProtocolDate()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaName()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaExploatationDate()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaSuplier()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaUsePlace()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProject()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaModel()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaSerialNumber()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaInvNumber()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaResponsiblePerson()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProducedBy()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaAboutUse()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaNote()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaActiveNumber()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaValueOne()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaValueTwo()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaCategory()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaIncludDate()));
			  
		  }
		}

		protocolFieldTable = getAllTableFieldsName();
		String protocolAllDataTable = table.createTableClassB(protocolFieldTable, protocolDataTable);
		
		session.setAttribute("ptocol_all_data_table", protocolAllDataTable);
		
		resp.sendRedirect("finance_protokol_dma_display_update.jsp");
	}
	
	public static void updateExpliciteProtocol(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session                    = req.getSession();
		int id                                 = Integer.parseInt(req.getParameter("id"));
		idForUpdate                            = req.getParameter("id");
		ArrayList<FinanceDma> dmaCollectioData = new ArrayList<FinanceDma>();
		ArrayList<String> protocolDataTable    = new ArrayList<String>();
		ArrayList<String> protocolFieldTable   = new ArrayList<String>();
		Tables table                           = new Tables();
		
		dmaCollectioData = getDmaProtocolDataFromDataBase();
		
		for (int i = 0; i < dmaCollectioData.size(); i++) {
			
		  if (dmaCollectioData.get(i).getNumber() == id) {
			 
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getNumber()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProtocolDate()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaName()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaExploatationDate()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaSuplier()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaUsePlace()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProject()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaModel()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaSerialNumber()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaInvNumber()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaResponsiblePerson()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProducedBy()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaAboutUse()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaNote()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaActiveNumber()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaValueOne()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaValueTwo()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaCategory()));
			  protocolDataTable.add(String.valueOf(dmaCollectioData.get(i).getDmaIncludDate()));
			  
		  }
		}

		protocolFieldTable = getAllTableFieldsName();
		String protocolAllDataTable = table.createTableClassZ(protocolFieldTable, protocolDataTable);
		
		session.setAttribute("ptocol_all_data_table", protocolAllDataTable);
		
		resp.sendRedirect("finance_protokol_dma_update.jsp");
		
	}
	
	
	
	public static void updateFinalExpliciteProtocol(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session                      = req.getSession();	
		ArrayList<String> dbcolumnNameCollection = new ArrayList<String>();
		ArrayList<String> newDataCollection      = new ArrayList<String>();
		DataBaseActivities dbActivities          = Singleton.getInstance();
		String fieldInto                         = "tb_finance_protokol_dma_number";
		String valueInto                         = idForUpdate;
		String table                             = "tb_finance_protokol_dma";
		
		
		if (!req.getParameter("finance_protokol_dma_update_date").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_date");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_date"));
		}
		
		if (!req.getParameter("finance_protokol_dma_update_active_name").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_active_name");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_active_name"));
		}
		
		if (!req.getParameter("finance_protokol_dma_update_date_exploatation").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_exploatation_date");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_date_exploatation"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_supplier").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_supplier");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_supplier"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_cost_center").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_place");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_cost_center"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_place").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_project");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_place"));
		}
		
		if (!req.getParameter("finance_protokol_dma_update_model").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_model");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_model"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_serial_number").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_serial_number");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_serial_number"));
		}
		
		if (!req.getParameter("finance_protokol_dma_update_inv_number").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_inv_number");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_inv_number"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_responsible_person").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_resp_person");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_responsible_person"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_produced_by").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_produced_by");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_produced_by"));
		}
		
		if (!req.getParameter("finance_protokol_dma_update_used_by").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_about");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_used_by"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_note").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_note");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_note"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_active_number").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_active_number");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_active_number"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_value_one").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_value_one");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_value_one"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_value_two").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_value_two");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_value_two"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_category").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_category");
			newDataCollection.add(req.getParameter("finance_protokol_dma_update_category"));
		}
		
		
		if (!req.getParameter("finance_protokol_dma_update_include_date").equals("")) {
			dbcolumnNameCollection.add("tb_finance_protokol_dma_include_date");
			newDataCollection.add(req.getParameter("tb_finance_protokol_dma_include_date"));
		}
		
		dbcolumnNameCollection.add("tb_finance_protokol_dma_update_user");
		newDataCollection.add(String.valueOf(session.getAttribute("user_name")));
		
		dbActivities.update(table, dbcolumnNameCollection,newDataCollection, fieldInto, valueInto);
	
		resp.sendRedirect("finance_protokol_dma_display_update.jsp");
		
	}
	
	public static void extractFullProtocolToExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		int id                                    = Integer.parseInt(req.getParameter("id"));
		idForUpdate                               = req.getParameter("id");
		ArrayList<FinanceDma> dmaCollectioData    = new ArrayList<FinanceDma>();
		ArrayList<String> protocolDataExcelTable  = new ArrayList<String>();
		ArrayList<String> protocolFieldExcelTable = new ArrayList<String>();
		dmaCollectioData                          = getDmaProtocolDataFromDataBase();	
		protocolFieldExcelTable                   = getAllTableFieldsName();
		
		
		for (int i = 0; i < dmaCollectioData.size(); i++) {
			
			  if (dmaCollectioData.get(i).getNumber() == id) {
				 
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getNumber()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProtocolDate()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaName()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaExploatationDate()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaSuplier()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaUsePlace()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProject()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaModel()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaSerialNumber()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaInvNumber()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaResponsiblePerson()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaProducedBy()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaAboutUse()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaNote()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaActiveNumber()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaValueOne()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaValueTwo()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaCategory()));
				  protocolDataExcelTable.add(String.valueOf(dmaCollectioData.get(i).getDmaIncludDate()));
				  
			  }
			}
		createExcelDmaProtocol(req, resp, protocolDataExcelTable, protocolFieldExcelTable);
	}
	
	

	private static ArrayList<FinanceDma> getDmaProtocolDataFromDataBase() {

		String table                           = "tb_finance_protokol_dma";
		DataBaseActivities dbActivities        = Singleton.getInstance();
		ResultSet result                       = dbActivities.select(table);
		ArrayList<FinanceDma> dmaCollectioData = new ArrayList<FinanceDma>();

		if (result != null) {		
			try {
				while (result.next()) {

					FinanceDma dma = new FinanceDma();

					dma.setNumber(result.getInt("tb_finance_protokol_dma_number"));
					LocalDate protocolDate = Date.date(result.getString("tb_finance_protokol_dma_date"));
					dma.setDmaProtocolDate(protocolDate);
					dma.setDmaName(result.getString("tb_finance_protokol_dma_active_name"));
					LocalDate exloatationDate = Date.date(result.getString("tb_finance_protokol_dma_exploatation_date"));
					dma.setDmaExploatationDate(exloatationDate);
					dma.setDmaSuplier(result.getString("tb_finance_protokol_dma_supplier"));
					dma.setDmaUsePlace(result.getString("tb_finance_protokol_dma_place"));
					dma.setDmaProject(result.getString("tb_finance_protokol_dma_project"));
					dma.setDmaModel(result.getString("tb_finance_protokol_dma_model"));
					dma.setDmaSerialNumber(result.getString("tb_finance_protokol_dma_serial_number"));
					dma.setDmaInvNumber(result.getString("tb_finance_protokol_dma_inv_number"));
					dma.setDmaResponsiblePerson(result.getString("tb_finance_protokol_dma_resp_person"));
					dma.setDmaProducedBy(result.getString("tb_finance_protokol_dma_produced_by"));
					dma.setDmaAboutUse(result.getString("tb_finance_protokol_dma_about"));
					dma.setDmaNote(result.getString("tb_finance_protokol_dma_note"));
					dma.setDmaCreator(result.getString("tb_finance_protokol_dma_creator"));
					dma.setDmaActiveNumber(result.getString("tb_finance_protokol_dma_active_number"));
					dma.setDmaValueOne(result.getDouble("tb_finance_protokol_dma_value_one"));
					dma.setDmaValueTwo(result.getDouble("tb_finance_protokol_dma_value_two"));
					dma.setDmaCategory(result.getString("tb_finance_protokol_dma_category"));
					LocalDate includeDate = Date.date(result.getString("tb_finance_protokol_dma_include_date"));
					dma.setDmaIncludDate(includeDate);
					dma.setDmaReasonForBuy(result.getString("tb_finance_protokol_dma_reason_for_buy"));
					dma.setDmaUpdateUser(result.getString("tb_finance_protokol_dma_update_user"));

					dmaCollectioData.add(dma);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		} else {
			getDmaProtocolDataFromDataBase();
		}
			
		return dmaCollectioData;

	}
	
	private static ArrayList<String> getMainTableFieldsName() {

		ArrayList<String> tableFieldsNameCollection = new ArrayList<String>();
		tableFieldsNameCollection.add("Номер на п-л");
		tableFieldsNameCollection.add("Дата на п-л");
		tableFieldsNameCollection.add("Име на актива");
		tableFieldsNameCollection.add("Инвентарен номер");
		tableFieldsNameCollection.add("Покажи протокола");
		tableFieldsNameCollection.add("Редактирай протокола");
		tableFieldsNameCollection.add("Изтегли в ексел");

		return tableFieldsNameCollection;

	}
	
	private static ArrayList<String> getAllTableFieldsName() {

		ArrayList<String> tableFieldsNameCollection = new ArrayList<String>();
		tableFieldsNameCollection.add("Номер на протокол");
		tableFieldsNameCollection.add("Дата на протокол");
		tableFieldsNameCollection.add("Име на актива");
		tableFieldsNameCollection.add("Дата на въвеждане в експлоатация");
		tableFieldsNameCollection.add("Доставчик");
		tableFieldsNameCollection.add("Разходен център");
		tableFieldsNameCollection.add("Местонамиране");
		tableFieldsNameCollection.add("Марка / модел");
		tableFieldsNameCollection.add("Сериен номер");
		tableFieldsNameCollection.add("Инвентарен номер");
		tableFieldsNameCollection.add("Отговорно лице");
		tableFieldsNameCollection.add("Производител");
		tableFieldsNameCollection.add("Предназначение");
		tableFieldsNameCollection.add("Забележка");
		tableFieldsNameCollection.add("Номер на актива");
		tableFieldsNameCollection.add("Данъчна амортизируема стойност на актива");
		tableFieldsNameCollection.add("Приета данъчна амортизационна норма");
		tableFieldsNameCollection.add("Категория съгласно чл.55 от ЗКПО");
		tableFieldsNameCollection.add("Активът е включен в ДАП, считано от");

		return tableFieldsNameCollection;

	}
	
	
	private static void createExcelDmaProtocol(HttpServletRequest req, HttpServletResponse resp,	
			ArrayList<String> protocolDataCollection, ArrayList<String> protocolFieldsCollection) throws IOException {
		
		
		HttpSession session = req.getSession();
		session.removeAttribute("monthly_presence_blank_no_generate_table_error_massage");
	
		
		String relativePath = req.getServletContext().getRealPath("");
		String filePath     =  relativePath + "\\SAP\\" + "Protocol.xlsx";
		File downloadFile   = new File(filePath);
		
		downloadFile.delete();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet   = workbook.createSheet("protocol");
	
		ExcelTables.extractFullExcelProtocolDma(workbook, worksheet, protocolDataCollection, protocolFieldsCollection);
		
	    FileOutputStream fileOutput = new FileOutputStream(filePath, true);
		workbook.write(fileOutput);

        downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

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
