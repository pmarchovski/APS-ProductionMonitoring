package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.work_clothes.WorkClothes;

public class WorkClothesManager {

	public static void displayWorkClothesForOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		int appronQuantity  = 0;
		int heaterQuantity  = 0;
		int slipperQuantity = 0;

		String[] appronNumberCollection  = { "38", "40", "42", "44", "46", "48", "50", "52", "54", "56", "58", "60","62", "64" };
		String[] heaterNumberCollection  = { "S", "M", "L", "XL", "XXL", "XXXL", "XXXXL" };
		String[] slipperNumberCollection = { "36", "37", "38", "36", "39", "40", "41", "42", "43", "44", "45", "46","47", "48" };
		
		ArrayList<String> columnName = new ArrayList<String>();
		columnName.add("Номер");
		columnName.add("Количество");

		ArrayList<Object> appronCollection  = SetObjectInfo.getAppronFromDataBase();
		ArrayList<Object> heaterCollection  = SetObjectInfo.getHeaterFromDataBase();
		ArrayList<Object> slipperCollection = SetObjectInfo.getSlipperFromDataBase();

		ArrayList<Integer> appronQuantityByNumber  = returnQuntity(appronNumberCollection, appronQuantity, appronCollection);
		ArrayList<Integer> heaterQuantityByNumber  = returnQuntity(heaterNumberCollection, heaterQuantity, heaterCollection);
		ArrayList<Integer> slipperQuantityByNumber = returnQuntity(slipperNumberCollection, slipperQuantity, slipperCollection);

		String workColthesColumnName = createTableHeadWorkClothes(columnName);
		String appronTableQuery = createTableWorkClothes(appronNumberCollection, appronQuantityByNumber);
		String heaterTableQuery = createTableWorkClothes(heaterNumberCollection, heaterQuantityByNumber);
		String slipperTableQuery = createTableWorkClothes(slipperNumberCollection, slipperQuantityByNumber);
		
		session.setAttribute("work_clothe_column_name", workColthesColumnName);
		session.setAttribute("appron_column", "Престилки");
		session.setAttribute("appron_table_for_order", appronTableQuery);
		session.setAttribute("heater_column", "Грейки");
		session.setAttribute("heater_table_for_order", heaterTableQuery);
		session.setAttribute("slipper_column", "Чехли");
		session.setAttribute("slipper_table_for_order", slipperTableQuery);
		
		req.getRequestDispatcher("work_clothes.jsp").forward(req, resp);
		
	}

	private static ArrayList<Integer> returnQuntity(String[] clothesNumberCollection, int clothesQuanity, ArrayList<Object> clothesCollection) {

		ArrayList<Integer> clothesQuantityByNumber = new ArrayList<Integer>();
		
		for (int i = 0; i < clothesNumberCollection.length; i++) {
			clothesQuanity = 0;
			for (int j = 0; j < clothesCollection.size(); j++) {

				if (((WorkClothes) clothesCollection.get(j)).getNumber().equals(clothesNumberCollection[i])) {
					clothesQuanity++;
				} else {
					clothesQuanity = clothesQuanity + 0;
				}
			}
			clothesQuantityByNumber.add(clothesQuanity);
		}		
		return clothesQuantityByNumber;
	}
	
	private static String createTableWorkClothes(String[] workClothesNumberCollection, ArrayList<Integer> workClothesQuantityByNumber) {
		Tables table = new Tables();	
		String workClothesTable = table.createTable(workClothesNumberCollection, workClothesQuantityByNumber);	
		return workClothesTable;
	}
	
	private static String createTableHeadWorkClothes(ArrayList<String> columnName) {
		Tables table = new Tables();
		String workClothesTableColumnName = table.createTableHead(columnName);	
		return workClothesTableColumnName;
	}

}
