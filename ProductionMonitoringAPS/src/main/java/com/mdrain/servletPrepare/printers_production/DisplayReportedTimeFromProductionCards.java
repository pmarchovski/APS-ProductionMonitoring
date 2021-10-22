package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Orders;

public class DisplayReportedTimeFromProductionCards {

	public static void bootstrap(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		session.removeAttribute("display_reported_tiem_from_production_cards_table_head");
		session.removeAttribute("display_reported_tiem_from_production_cards_table_body");
		
		
		String materialNumber = "";
		int orderNumber = 0;

		if (req.getParameter("dsplay_reported_production_time_product_number").equals("all")) {
			materialNumber = "all";
		} else {
			materialNumber = req.getParameter("dsplay_reported_production_time_product_number");
		}

		
		if (req.getParameter("dsplay_reported_production_time_orders") == "") {
			orderNumber = 0;
		} else {
			orderNumber = Integer.parseInt(req.getParameter("dsplay_reported_production_time_orders"));
		}
		

		tablePrepare(req, resp, materialNumber, orderNumber);
		
		resp.sendRedirect("display_reported_production_time.jsp");
	}

	private static ArrayList<Orders> getDataFromProductionCards() {

		DataBaseActivities dbActivities = new DataBaseActivities();
		String table = "tb_production_card_information";
		ResultSet result = null;
		ArrayList<Orders> ordersProductionCardCollection = new ArrayList<Orders>();
		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Orders order = new Orders();

				order.setNumber(result.getInt("production_card_information_order"));
				order.setReportedTimeFromProductionCards(result.getInt("production_card_information_time"));
				String dateString = result.getDate("production_card_information_date").toString();
				LocalDate date = Date.date(dateString);
				order.setReportedDate(date);
				

				ordersProductionCardCollection.add(order);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordersProductionCardCollection;

	}

	private static ArrayList<Orders> getDataFromCooisOperation() {

		DataBaseActivities dbActivities = new DataBaseActivities();
		String table = "tb_coois_operation";
		ResultSet result = null;
		ArrayList<Orders> ordersCooisOperatyionCollection = new ArrayList<Orders>();

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Orders ordersCooisOperation = new Orders();

				ordersCooisOperation.setNumber(result.getInt("tb_coois_operation_order"));
				ordersCooisOperation.setQuantity(result.getInt("tb_coois_operation_quantity"));
				ordersCooisOperation.setTimeForOnePc(result.getDouble("tb_coois_operation_time_per_pc"));
				ordersCooisOperation.getTotalTimeForOrderInHours();

				ordersCooisOperatyionCollection.add(ordersCooisOperation);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordersCooisOperatyionCollection;
	}

	private static ArrayList<Orders> getDataFromCooisProd() {

		DataBaseActivities dbActivities = new DataBaseActivities();
		String table = "tb_coois_prod";
		ResultSet result = null;
		ArrayList<Orders> ordersCooisProdCollection = new ArrayList<Orders>();

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				Orders ordersCooisProd = new Orders();

				ordersCooisProd.setNumber(result.getInt("tb_coois_prod_order"));
				ordersCooisProd.setMaterialNumber(result.getString("tb_coois_prod_material_number"));
				ordersCooisProd.setMaterialDescription(result.getString("tb_coois_prod_material_description"));
				ordersCooisProd.setQuantity(result.getInt("tb_coois_prod_quantity"));
				ordersCooisProd.setStatus(result.getString("tb_coois_prod_status"));

				ordersCooisProdCollection.add(ordersCooisProd);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordersCooisProdCollection;
	}

	private static ArrayList<Orders> unionOrdersInfo() {

		ArrayList<Orders> ordersCooisOperatyionCollection = new ArrayList<Orders>();
		ArrayList<Orders> ordersProductionCardCollection = new ArrayList<Orders>();
		ArrayList<Orders> ordersCooisProdCollection = new ArrayList<Orders>();

		ordersCooisOperatyionCollection = getDataFromCooisOperation();
		ordersProductionCardCollection = getDataFromProductionCards();
		ordersCooisProdCollection = getDataFromCooisProd();

		Orders unionOrder = new Orders();

		for (int i = 0; i < ordersCooisOperatyionCollection.size(); i++) {

			for (int j = 0; j < ordersProductionCardCollection.size(); j++) {

				if (ordersCooisOperatyionCollection.get(i).getNumber() == ordersProductionCardCollection.get(j)
						.getNumber()) {

					double reportedTimeFromProductionCards = ordersProductionCardCollection.get(j)
							.getReportedTimeFromProductionCards();
					double reportedTimeFromSetCoois = ordersCooisOperatyionCollection.get(i)
							.getReportedTimeFromProductionCards();

					ordersCooisOperatyionCollection.get(i).setReportedTimeFromProductionCards(
							(reportedTimeFromSetCoois + reportedTimeFromProductionCards));

					ordersCooisOperatyionCollection.get(i).setReportedTimeFromProductionCards(
							ordersCooisOperatyionCollection.get(i).getReportedTimeFromProductionCards());
				}

			}

		}

		for (int i = 0; i < ordersCooisOperatyionCollection.size(); i++) {

			for (int j = 0; j < ordersCooisProdCollection.size(); j++) {

				if (ordersCooisOperatyionCollection.get(i).getNumber() == ordersCooisProdCollection.get(j)
						.getNumber()) {
					ordersCooisOperatyionCollection.get(i)
							.setMaterialNumber(ordersCooisProdCollection.get(j).getMaterialNumber());
					ordersCooisOperatyionCollection.get(i)
							.setMaterialDescription(ordersCooisProdCollection.get(j).getMaterialDescription());
					ordersCooisOperatyionCollection.get(i).setQuantity(ordersCooisProdCollection.get(j).getQuantity());
					ordersCooisOperatyionCollection.get(i).setStatus(ordersCooisProdCollection.get(j).getStatus());
					
				}

			}
		}


		return ordersCooisOperatyionCollection;
	}

	private static void tablePrepare(HttpServletRequest req, HttpServletResponse resp, String materialNumber,
			int orderNumber) {

		HttpSession session = req.getSession();
		ArrayList<Orders> ordersCooisOperatyionCollection = unionOrdersInfo();
		ArrayList<String> fieldsNameCollection = new ArrayList<String>();
		ArrayList<Object> orderDataCollection = new ArrayList<Object>();
		Tables table = new Tables();

		fieldsNameCollection.add("Поръчка");
		fieldsNameCollection.add("Изделие номер");
		fieldsNameCollection.add("Описание");
		fieldsNameCollection.add("Количество");
		fieldsNameCollection.add("Необходимо време ч/ч");
		fieldsNameCollection.add("Отчетено време ч/ч");

		for (int i = 0; i < ordersCooisOperatyionCollection.size(); i++) {

			if (ordersCooisOperatyionCollection.get(i).getMaterialNumber() != null) {

				if (materialNumber.equals("all") && ordersCooisOperatyionCollection.get(i).getStatus().equals("TECO")) {

					if (ordersCooisOperatyionCollection.get(i).getNumber() == orderNumber) {

						orderDataCollection.add(ordersCooisOperatyionCollection.get(i).getNumber());
						orderDataCollection.add(ordersCooisOperatyionCollection.get(i).getMaterialNumber());
						orderDataCollection.add(ordersCooisOperatyionCollection.get(i).getMaterialDescription());
						orderDataCollection.add(ordersCooisOperatyionCollection.get(i).getQuantity());

						String.format("$%,.2f", ordersCooisOperatyionCollection.get(i).getTotalTimeForOrderInHours());

						orderDataCollection.add(String.format("%,.2f",
								ordersCooisOperatyionCollection.get(i).getTotalTimeForOrderInHours()));

						orderDataCollection.add(String.format("%,.2f",
								(ordersCooisOperatyionCollection.get(i).getReportedTimeFromProductionCards()) / 60));
					}

				} else {

					if (ordersCooisOperatyionCollection.get(i).getMaterialNumber().equals(materialNumber) && ordersCooisOperatyionCollection.get(i).getStatus().equals("TECO")) {

						orderDataCollection.add(ordersCooisOperatyionCollection.get(i).getNumber());
						orderDataCollection.add(ordersCooisOperatyionCollection.get(i).getMaterialNumber());
						orderDataCollection.add(ordersCooisOperatyionCollection.get(i).getMaterialDescription());
						orderDataCollection.add(ordersCooisOperatyionCollection.get(i).getQuantity());

						String.format("$%,.2f", ordersCooisOperatyionCollection.get(i).getTotalTimeForOrderInHours());

						orderDataCollection.add(String.format("%,.2f",
								ordersCooisOperatyionCollection.get(i).getTotalTimeForOrderInHours()));

						orderDataCollection.add(String.format("%,.2f",
								(ordersCooisOperatyionCollection.get(i).getReportedTimeFromProductionCards()) / 60));
					}
				}
			}
		}

		String tableHead = table.createTableHead(fieldsNameCollection);
		String tableBody = table.createTableBodyStringObject(fieldsNameCollection, orderDataCollection);

		System.out.println(tableBody);
		
		session.setAttribute("display_reported_tiem_from_production_cards_table_head", tableHead);
		session.setAttribute("display_reported_tiem_from_production_cards_table_body", tableBody);
	}
}
