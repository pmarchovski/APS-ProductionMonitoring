package com.mdrain.servletPrepare.printers_production;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mdrain.logic.ExcelTables;
import com.mdrain.logic.MaterialsProductTypeList;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Operators;
import com.mdrain.objects.ProductionCards;

public class GenerateSkillsMatrix {

	static ArrayList<Operators> operatorsCollection = SetObjectInfo.getOperatorsDataFromDataBase();
	static String[] productTypeFieldCollection = MaterialsProductTypeList.getMaterialTypeFields();
	static int productTypeSize = MaterialsProductTypeList.getMaterialTypeFields().length;

	public static void getOperatorsData(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		HttpSession session = req.getSession();

		session.removeAttribute("skills_matrix_table_head");
		session.removeAttribute("skills_matrix_table_data");

		ArrayList<String> productTypeFields                   = new ArrayList<String>();
		ArrayList<String> skillsCollectionFromProductionCards = new ArrayList<String>();
		ArrayList<String> operatorsName                       = new ArrayList<String>();
		Tables table                                          = new Tables();

		for (int i = 0; i < productTypeFieldCollection.length; i++) {
			productTypeFields.add(productTypeFieldCollection[i]);
		}

		for (int i = 0; i < operatorsCollection.size(); i++) {

			Operators operator = new Operators();

			operator = operatorsCollection.get(i);

			
			if (operator.getIsActive().equals("Да")) operatorsName.add(operator.getFullName());

			for (int j = 0; j < operator.getSkillsCollectionFromProductionCards().length; j++) {

				if (operator.getIsActive().equals("Да") && operator.getSkillsCollectionFromProductionCards()[j] != null) {
					skillsCollectionFromProductionCards.add("ДА");
				} else {

					skillsCollectionFromProductionCards.add("");
				}
			}

		}

		
		String skillsMatrixTableHead = table.createTableHeadVerticalTextMatrix(productTypeFields);
		String skillsMatrixTableData = table.createTablesSkillsMatrix(operatorsName,
				skillsCollectionFromProductionCards, productTypeSize);

		session.setAttribute("skills_matrix_table_head", skillsMatrixTableHead);
		session.setAttribute("skills_matrix_table_data", skillsMatrixTableData);

		req.getRequestDispatcher("skills_matrix.jsp").forward(req, resp);
		// createExcelSkillsMatrix(req, resp, productTypeFields, operatorsName,
		// skillsCollectionFromProductionCards);

	}

	public static void createExcelSkillsMatrix(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ArrayList<String> productTypeFields                   = new ArrayList<String>();
		ArrayList<String> skillsCollectionFromProductionCards = new ArrayList<String>();
		ArrayList<String> operatorsName                       = new ArrayList<String>();

		for (int i = 0; i < productTypeFieldCollection.length; i++) {
			productTypeFields.add(productTypeFieldCollection[i]);
		}

		for (int i = 0; i < operatorsCollection.size(); i++) {

			Operators operator = new Operators();

			operator = operatorsCollection.get(i);

			operatorsName.add(operator.getFullName());

			for (int j = 0; j < operator.getSkillsCollectionFromProductionCards().length; j++) {

				if (operator.getSkillsCollectionFromProductionCards()[j] != null) {
					skillsCollectionFromProductionCards.add("ДА");
				} else {

					skillsCollectionFromProductionCards.add("");
				}
			}
		}

		String relativePath = req.getServletContext().getRealPath("");
		String filePath = relativePath + "\\SAP\\" + "SkillsMatrix.xlsx";

		File downloadFile = new File(filePath);

		downloadFile.delete();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("skills_matrix");

		ExcelTables.createExcelSkillsMatrix(workbook, worksheet, productTypeFields, operatorsName,
				skillsCollectionFromProductionCards, productTypeSize);

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

}
