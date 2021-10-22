package com.mdrain.servletPrepare.printers_production;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mdrain.logic.ExcelTables;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.objects.Orders;
import com.mdrain.objects.SerialNumber;

public class GenerateSerialNumber {
	
	public static void bootstrap(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String orderFromWebApp = req.getParameter("printers_production_generate_serial_number_order");
		ArrayList<Orders> ordersInfoCollection = SetObjectInfo.getOrdersInfoFromDataBaseOne();
		SerialNumber serialNumberMainInfo = new SerialNumber();
		ArrayList<String> serialNumberAllInfoCollection = new ArrayList<String>();
		for (int i = 0; i < ordersInfoCollection.size(); i++) {

			if (String.valueOf(ordersInfoCollection.get(i).getNumber()).equals(orderFromWebApp)) {

				serialNumberMainInfo.setOrderNumber(String.valueOf(ordersInfoCollection.get(i).getNumber()));
				serialNumberMainInfo.setMaterialNumber(ordersInfoCollection.get(i).getMaterialNumber());
				serialNumberMainInfo.setMatetialDescription(ordersInfoCollection.get(i).getMaterialDescription());
				serialNumberMainInfo.setSerialNumberDate(ordersInfoCollection.get(i).getStartDate());
				serialNumberMainInfo.setOrderQuantity(ordersInfoCollection.get(i).getQuantity());
				serialNumberMainInfo.setSerialNumber();
			}
		}

		ArrayList<String> serialNumberCollection = serialNumberMainInfo.getSerialNumber();

		for (int i = 0; i < serialNumberCollection.size(); i++) {
			serialNumberAllInfoCollection.add(serialNumberMainInfo.getOrderNumber());
			serialNumberAllInfoCollection.add(serialNumberMainInfo.getMaterialNumber());
			serialNumberAllInfoCollection.add(serialNumberMainInfo.getMatetialDescription());
			serialNumberAllInfoCollection.add(serialNumberCollection.get(i));

		}
		
		String orderName = serialNumberMainInfo.getOrderNumber();
		
		String[] serialNumberArrayCollection = new String[serialNumberAllInfoCollection.size()];
		
		for (int i = 0; i < serialNumberArrayCollection.length; i++) {
			serialNumberArrayCollection[i] = serialNumberAllInfoCollection.get(i);
		}

		serialNumberAllInfoCollection.clear();
		createProductionPlanExcel(req, resp, serialNumberArrayCollection, orderName);
	}
	
	

	private static void createProductionPlanExcel(HttpServletRequest req, HttpServletResponse resp,
			String[] serialNumberAllInfoCollection, String orderName) throws IOException {

		String[] headerFieldsCollection = {"Order", "Material", "Material Description", "Serial Number"};
        
		String fileName = orderName;

// reads input file from an absolute path
		String relativePath = req.getServletContext().getRealPath("");
		String filePath = relativePath + "\\SAP\\" + fileName + ".xlsx";

		File downloadFile = new File(filePath);

		downloadFile.delete();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("Sheet1");
        
        ExcelTables generateSerialNumberExcel = new ExcelTables();
        generateSerialNumberExcel.generateSerialNumberExcel(workbook, worksheet, headerFieldsCollection, serialNumberAllInfoCollection);
        
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
