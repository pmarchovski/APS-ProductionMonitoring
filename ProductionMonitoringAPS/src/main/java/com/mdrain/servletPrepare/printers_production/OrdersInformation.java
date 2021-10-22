package com.mdrain.servletPrepare.printers_production;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.logic.FormatNumbers;
import com.mdrain.logic.MaterialsProductTypeList;
import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Orders;


public class OrdersInformation {

	public static void productionOrderInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		int year                                = Integer.parseInt(req.getParameter("orders_info_select_year"));
		ArrayList<Orders> ordersCollection      = new ArrayList<Orders>();
		HttpSession session                     = req.getSession();

		ordersCollection                = SetObjectInfo.getOrdersInfoFromDataBase();
		String[] productTypeDescription = MaterialsProductTypeList.getMaterialsGroupList();


		ArrayList<Integer> yearsCollection = new ArrayList<Integer>();

		if (year == 1) {
			yearsCollection.add(2018);
			yearsCollection.add(2019);
			yearsCollection.add(2020);
			yearsCollection.add(2021);

		} else {
			yearsCollection.add(year);
		}

		int[] productTypeQuantity = new int[productTypeDescription.length];

		for (int i = 0; i < productTypeQuantity.length; i++) {
			productTypeQuantity[i] = 0;
		}

		Orders orderInfo;
		int totalQuantity             = 0;
		String[] tableQueryCollection = new String[yearsCollection.size()];
		String tableQuery             = "";
		String tableFieldQuery        = "";
		Tables tableDisplay           = new Tables();
		int k = 0;

		for (k = 0; k < yearsCollection.size(); k++) {

			//Зануляваме колекцията
			for (int i = 0; i < productTypeQuantity.length; i++) {
				productTypeQuantity[i] = 0;
			}
			
			
			for (int i = 0; i < ordersCollection.size(); i++) {

				orderInfo = ordersCollection.get(i);

				// WВъртим цикъла, толкова пъти колкото са годините в колекцията
				if (orderInfo.getStartDate().getYear() == yearsCollection.get(k)) {

					for (int j = 0; j < productTypeDescription.length; j++) {

						if (orderInfo.getProductType().equals(productTypeDescription[j])) {

							totalQuantity = totalQuantity + orderInfo.getQuantity();

							productTypeQuantity[j] = productTypeQuantity[j] + orderInfo.getQuantity();
							
							
						}
					}
				}
			}

			String[] productTypeQtyFormat = new String[productTypeQuantity.length];
			
			for (int i = 0; i < productTypeQtyFormat.length; i++) {
	
				productTypeQtyFormat[i] = FormatNumbers.getFormatedNumbers(productTypeQuantity[i]);
			}
			
			
			tableFieldQuery = tableDisplay.createTableString(productTypeDescription);
			
			tableQuery = tableDisplay.createTableStringWithoutClass(productTypeQtyFormat);
			tableQueryCollection[k] = "<td Class=" + "\"f\""  + ">" +  yearsCollection.get(k) + "</td>" + tableQuery;
			
		}

		String finalQuery = "";
		
		for (int i = 0; i < tableQueryCollection.length; i++) {
			finalQuery += "<div style=" + "\"float: left\"" + ">" + "<table Class=" + "\"a\"" + ">" + tableQueryCollection[i] + "</table>" + "</div>";
		}
		
		String productTypeForTable = "<td Class=" + "\"f\""  + ">" + "Product type" + "</td>";
		finalQuery = "<div style=" +  "\"float: left\"" + ">" + "<table Class=" + "\"a\"" + ">" + productTypeForTable + tableFieldQuery  + "</table>" + "</div>" + finalQuery;
		
		session.setAttribute("production_order_quantity_info", finalQuery);
		
		
		resp.sendRedirect("orders_info.jsp");
	}
}
