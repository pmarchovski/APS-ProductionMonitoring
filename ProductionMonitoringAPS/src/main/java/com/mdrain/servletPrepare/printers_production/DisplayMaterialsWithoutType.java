package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.MaterialsProductTypeList;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Materials;
import com.mdrain.objects.Orders;
import com.mdrain.singletons.Singleton;

public class DisplayMaterialsWithoutType {

	
	static ArrayList<String> productType = MaterialsProductTypeList.getArrayListType();
	static ArrayList<Materials> infoCollection = new ArrayList<Materials>();
	
	
	public static void updateMaterial(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String table                    = "tb_product_type";
		DataBaseActivities dbActivities = Singleton.getInstance();
		
		for (int i = 0; i < infoCollection.size(); i++) {
			
	
			String typeFromWebApp = req.getParameter("materials_without_type_list " + (i + 1));
			
			ArrayList<String> into  = new ArrayList<String>();
		    ArrayList<String> value = new ArrayList<String>();
			
			into.add("tb_product_type_number");
		    into.add("tb_product_type_description");
		    into.add("tb_product_type_type");
		    
		    value.add(infoCollection.get(i).getMaterialNumber());
		    value.add(infoCollection.get(i).getMaterialDescription());
		    value.add(String.valueOf(typeFromWebApp));
	
		    dbActivities.insert(table, into, value);
		    
		}

		req.getRequestDispatcher("display_materials_type.jsp").forward(req, resp);
		
	}
	
	public static void getMaterialsWithoutType(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ArrayList<Orders> ordersInfoCollection = SetObjectInfo.getOrdersInfoFromDataBase();
		
		int j = 0;

		for (int i = 0; i < ordersInfoCollection.size(); i++) {

			if (ordersInfoCollection.get(i).getProductType().equals("No type!!!") 
					&& isAvailable(infoCollection, ordersInfoCollection.get(i).getMaterialNumber()) == false) {
	
				
				Materials material = new Materials();
				material.setMaterialNumber(ordersInfoCollection.get(i).getMaterialNumber());
				material.setMaterialDescription(ordersInfoCollection.get(i).getMaterialDescription());
				material.setMaterialGroup(ordersInfoCollection.get(i).getProductType());
				
				infoCollection.add(material);
			}
		}
		
		
		getFieldList(req, resp);
		createTable(infoCollection, req, resp);
		
		req.getRequestDispatcher("display_materials_type.jsp").forward(req, resp);
		
		}
	
	private static boolean isAvailable (ArrayList<Materials> infoCollection, String order){
		
		boolean isAvailableNumber = false;
		
		for (int i = 0; i < infoCollection.size(); i++) {
			
			if (infoCollection.get(i).getMaterialNumber().equals(order)) {
				return isAvailableNumber = true;
				
			} else {
				isAvailableNumber = false;
			}
		}
		
		return isAvailableNumber;
	}
	
	
	private static void createTable(ArrayList<Materials> infoCollection, HttpServletRequest req, HttpServletResponse resp) {
		
		ArrayList<String> tableFields = new ArrayList<String>();
		ArrayList<Object> tableData   = new ArrayList<Object>();	
		HttpSession session           = req.getSession();
		Tables table                  = new Tables();
			
		session.removeAttribute("materials_without_type_table_head");
		session.removeAttribute("materials_without_type_table_body");
		
		
		int count = 0;
		tableFields.add("Номер на материал");
		tableFields.add("Описание на метериал");
		tableFields.add("Група на материал");
		tableFields.add("Нова група на материал");
		
		String listType = "";
		for (int i = 0; i < productType.size(); i++) {
	
			listType += "<option value=" + "\"" + productType.get(i) 
			         + "\"" + ">" + productType.get(i) 
			         + "</option>";
		}
	
		
		for (int i = 0; i < infoCollection.size(); i++) {
			
			count++;
			
			tableData.add(infoCollection.get(i).getMaterialNumber());
			tableData.add(infoCollection.get(i).getMaterialDescription());
			tableData.add(infoCollection.get(i).getMaterialGroup());
			
			String listField = "<select name=" 
		        + "\"materials_without_type_list " + count + "\"" 
				+ " Class=" + "\"input\"" + ">"     
				+ listType
		        + "</select>";
			
			tableData.add(listField);
			
		}
	
		String tableHead = table.createTableHead(tableFields);
		String tableBody = table.createTableBodyStringObject(tableFields, tableData);
		
		session.setAttribute("materials_without_type_table_head", tableHead);
		session.setAttribute("materials_without_type_table_body", tableBody);
		
	}	
	
	private static void getFieldList(HttpServletRequest req, HttpServletResponse resp) {
				
		HttpSession session = req.getSession();
		
		session.setAttribute("product_type_collection", productType);
	}
}
