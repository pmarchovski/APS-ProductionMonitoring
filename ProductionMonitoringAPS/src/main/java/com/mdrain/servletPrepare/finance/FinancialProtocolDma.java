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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.ExcelTables;
import com.mdrain.servletPrepare.admin.EmailLists;
import com.mdrain.servletPrepare.admin.SendMail;
import com.mdrain.singletons.Singleton;

public class FinancialProtocolDma{
	
	static String filePath = "";

	public static void prepareDmaProtocol(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession session                            = req.getSession();
		ArrayList<Object> protocolDataCollection       = new ArrayList<Object>();
		ArrayList<Object> dataBaseDataCollection       = new ArrayList<Object>();
		ArrayList<String> protocolFieldsCollection     = new ArrayList<String>();
		ArrayList<String> dataBaseColumnNameCollection = new ArrayList<String>();
		
		DataBaseActivities dbActivities                = Singleton.getInstance();
		String table                                   = "tb_finance_protokol_dma";
		int protocolNumber                             = setProtocolNumber();
		
//Excel data prepare
		
		protocolFieldsCollection.add("Наименование на актив:");
		protocolFieldsCollection.add("Дата на въвеждане в експлоатация:");
		protocolFieldsCollection.add("Доставчик:");
		protocolFieldsCollection.add("Местонамиране:");
		protocolFieldsCollection.add("Разходен център / проект:");
		protocolFieldsCollection.add("Марка и модел:");
		protocolFieldsCollection.add("Сериен номер:");
		protocolFieldsCollection.add("Инвентарен номер:");
		protocolFieldsCollection.add("Отговорно лице:");
		protocolFieldsCollection.add("Производител:");
		protocolFieldsCollection.add("Предназанчение (звено / употреба):");
		protocolFieldsCollection.add("Забележки:");
			
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_date"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_active_name"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_date_exploatation"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_supplier"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_place"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_project"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_model"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_serial_number"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_invemtory_number"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_responsibility_person"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_produced"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_about"));
		protocolDataCollection.add(req.getParameter("finance_protokol_dma_note"));
		
				
//Data base include		
		
		LocalDate includDate = LocalDate.now();		
		String creator       = (String) session.getAttribute("user_name");
			
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_date");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_active_name");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_exploatation_date");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_supplier");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_place");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_project");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_model");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_serial_number");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_inv_number");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_resp_person");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_produced_by");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_about");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_note");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_creator");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_include_date");
		dataBaseColumnNameCollection.add("tb_finance_protokol_dma_number");

		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_date"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_active_name"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_date_exploatation"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_supplier"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_place"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_project"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_model"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_serial_number"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_invemtory_number"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_responsibility_person"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_produced"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_about"));
		dataBaseDataCollection.add(req.getParameter("finance_protokol_dma_note"));
		dataBaseDataCollection.add(creator);
		dataBaseDataCollection.add(includDate);
		dataBaseDataCollection.add(protocolNumber);
			
		dbActivities.insertObject(table, dataBaseColumnNameCollection, dataBaseDataCollection);
		
		FinancialProtocolDma createExcelDmaProtocol = new FinancialProtocolDma();
		
		createExcelDmaProtocol.createExcelDmaProtocol(req, resp, protocolDataCollection, protocolFieldsCollection, protocolNumber);
		
		String attachment = filePath;
		String subject = "Нов ДМА протокол " + protocolNumber;
		String massageBody = "ТОВА Е АВТОМАТИЧНО ГЕНЕРИРАНО СЪОБЩЕНИЕ. \r\n" 
		        + "\r\n" 
				+ "Беше създаден нов ДМА протокол с номер: " + protocolNumber
	   		    + "\r\n"
	   		    + "\r\n"
				+ "за актив " + req.getParameter("finance_protokol_dma_active_name")
			    + "\r\n"
			    + "\r\n"
				+ "от " + creator;
		
		
		String[] recepients = EmailLists.dmaProtocolEmailList();
		SendMail.bootstrapAttahment(subject, massageBody, recepients, attachment);
		

	}
	
	public void createExcelDmaProtocol(HttpServletRequest req, HttpServletResponse resp,	
			ArrayList<Object> protocolDataCollection, ArrayList<String> protocolFieldsCollection, int protocolNumber) throws IOException {
		
		
		HttpSession session = req.getSession();
		session.removeAttribute("monthly_presence_blank_no_generate_table_error_massage");
		
		String relativePath = req.getServletContext().getRealPath("");
		String fileName     = "DMA_protocol_" + protocolNumber + ".xlsx";
		filePath            =  relativePath  + fileName;
		
		File downloadFile   = new File(filePath);
		
		downloadFile.delete();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("protocol");
	
		ExcelTables.createExcelProtocolDma(workbook, worksheet, protocolDataCollection, protocolFieldsCollection, protocolNumber);
		
	    FileOutputStream fileOutput = new FileOutputStream(filePath, true);
		workbook.write(fileOutput);

        downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

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
	
	private static int setProtocolNumber() {
		
		//Get id from database
		
		String table                    = "tb_finance_protokol_dma";
		DataBaseActivities dbActivities = Singleton.getInstance();
		ArrayList<Integer> protocolId   = new ArrayList<Integer>();	
		ResultSet result                = dbActivities.select(table);
		
		try {
			while (result.next()) {
				
				protocolId.add(result.getInt("tb_finance_protokol_dma_id"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int protocolNumber = 1000; 
		protocolNumber     = protocolNumber + (protocolId.size() + 1);
		
		return protocolNumber;
	}
	
}
