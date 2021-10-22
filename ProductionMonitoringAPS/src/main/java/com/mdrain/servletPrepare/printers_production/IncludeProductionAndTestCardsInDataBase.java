package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.DoneStatus;
import com.mdrain.logic.Tables;

public class IncludeProductionAndTestCardsInDataBase {

	public static void includeProductionCardsInDataBase(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		Tables tableDisplay = new Tables();
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<String> orders = new ArrayList<String>();
		ArrayList<String> fieldsInto = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		ArrayList<String> tableFieldsCollection = new ArrayList<String>();
		HttpSession session = req.getSession();
		LocalDate inputDate = LocalDate.now();
		String inputDateDB = inputDate.toString();
		String doneStatus = "";
		String table = "tb_production_card_information";


		fieldsInto.add("production_card_information_date");
		fieldsInto.add("production_card_information_order");
		fieldsInto.add("production_card_information_operator");
		fieldsInto.add("production_card_information_operation");
		fieldsInto.add("production_card_information_quantity");
		fieldsInto.add("production_card_information_time");
		fieldsInto.add("production_card_information_isNewOperator");
		fieldsInto.add("production_card_information_user");
		fieldsInto.add("production_card_information_input_date");

		values.add(req.getParameter("input_production_card_date"));
		values.add(req.getParameter("input_production_card_production_order"));
		values.add(req.getParameter("input_production_card_operators_name"));
		values.add(req.getParameter("input_production_card_operation"));
		values.add(req.getParameter("input_production_card_quantity"));
		values.add(req.getParameter("input_production_card_time"));
		values.add(req.getParameter("input_production_card_isNewOperator"));
		values.add((String) session.getAttribute("user_name"));
		values.add(inputDateDB);

		orders = getOrdersCollection();

		for (int i = 0; i < orders.size(); i++) {
			if (req.getParameter("input_production_card_production_order").equals(orders.get(i))
					|| req.getParameter("input_production_card_production_order").equals("999999")
					|| req.getParameter("input_production_card_production_order").equals("999998")
					|| req.getParameter("input_production_card_production_order").equals("999997")
					|| req.getParameter("input_production_card_production_order").equals("999996")
					|| req.getParameter("input_production_card_production_order").equals("999995")
					|| req.getParameter("input_production_card_production_order").equals("999994")
					|| req.getParameter("input_production_card_production_order").equals("999993")
					|| req.getParameter("input_production_card_production_order").equals("999992")
					|| req.getParameter("input_production_card_production_order").equals("999991")) {

				dbActivities.insert(table, fieldsInto, values);

				doneStatus = "<h3 style=" + "\"font-size: 12pt; color: green\"" + ">"
						+ "Информацията е записана успешно" + "</h3>";

				session.setAttribute("done_status", doneStatus);

				// DoneStatus.doneStatusSuccess(session, "done_status");

				tableFieldsCollection.add("Дата на производство");
				tableFieldsCollection.add("Поръчка");
				tableFieldsCollection.add("Оператор");
				tableFieldsCollection.add("Операция");
				tableFieldsCollection.add("Количество");
				tableFieldsCollection.add("Време");
				tableFieldsCollection.add("Нов оператор ДА/НЕ");
				tableFieldsCollection.add("User name");
				tableFieldsCollection.add("Дата на въвеждане");

				String tableHead = tableDisplay.createTableHead(tableFieldsCollection);
				String tableBody = tableDisplay.createTableString(values);

				session.setAttribute("input_production_card_table_head", tableHead);
				session.setAttribute("input_production_card_table_body", tableBody);
				session.setAttribute("input_production_card_current_date", req.getParameter("input_production_card_date"));

				req.getRequestDispatcher("input_production_card.jsp").forward(req, resp);
				return;
				
			} else {
				doneStatus = "<h3 style=" + "\"font-size: 12pt; color: red\"" + ">"
						+ "Не съществува такава поръчка!!! Информацията не е записана!" + "</h3>";

			}
		}

		session.setAttribute("done_status", doneStatus);
		session.setAttribute("input_production_card_current_date", req.getParameter("input_production_card_date"));
		req.getRequestDispatcher("input_production_card.jsp").forward(req, resp);
	}

	public static void includeTestCardsInDataBase(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String table = "tb_test_cards";
		DataBaseActivities dbActivies = new DataBaseActivities();
		ArrayList<String> orders = new ArrayList<String>();
		ArrayList<String> fieldsInto = new ArrayList<String>();
		ArrayList<Object> value = new ArrayList<Object>();
		HttpSession session = req.getSession();
		LocalDate date = LocalDate.now();
		String dateString = "";
		String doneStatus = "";

		session.removeAttribute("done_status");

		fieldsInto.add("tb_test_cards_date");
		fieldsInto.add("tb_test_cards_order");
		fieldsInto.add("tb_test_cards_pale_print");
		fieldsInto.add("tb_test_cards_thermistor");
		fieldsInto.add("tb_test_cards_concise_print");
		fieldsInto.add("tb_test_cards_main_board");
		fieldsInto.add("tb_test_cards_opto_sensor");
		fieldsInto.add("tb_test_cards_second_board");
		fieldsInto.add("tb_test_cards_motor");
		fieldsInto.add("tb_test_cards_broken_components	");
		fieldsInto.add("tb_test_cards_tph");
		fieldsInto.add("tb_test_cards_mech_deffect");
		fieldsInto.add("tb_test_cards_noise");
		fieldsInto.add("tb_test_cards_other");
		fieldsInto.add("tb_test_cards_user");
		fieldsInto.add("tb_test_cards_date_input");

		value.add(req.getParameter("input_test_card_date"));
		value.add(req.getParameter("input_test_card_production_order"));
		value.add(req.getParameter("input_test_card_pale_print"));
		value.add(req.getParameter("input_test_card_thermistor"));
		value.add(req.getParameter("input_test_card_concise_print"));
		value.add(req.getParameter("input_test_card_main_board"));
		value.add(req.getParameter("input_test_card_opto_sensor"));
		value.add(req.getParameter("input_test_card_second_board"));
		value.add(req.getParameter("input_test_card_motor"));
		value.add(req.getParameter("input_test_card_broken_component"));
		value.add(req.getParameter("input_test_card_tph"));
		value.add(req.getParameter("input_test_card_mechanical_deffect"));
		value.add(req.getParameter("input_test_card_noise"));
		value.add(req.getParameter("input_test_card_others"));

		value.add((String) session.getAttribute("user_name"));

		dateString = date.toString();
		value.add(dateString);

		orders = getOrdersCollection();

		for (int i = 0; i < orders.size(); i++) {
			if (req.getParameter("input_test_card_production_order").equals(orders.get(i))) {
				dbActivies.insertObject(table, fieldsInto, value);
				doneStatus = "<h3 style=" + "\"font-size: 12pt; color: green\"" + ">"
						+ "Информацията е записана успешно" + "</h3>";

				session.setAttribute("done_status", doneStatus);
				resp.sendRedirect("input_test_card.jsp");

				return;

			} else {

				doneStatus = "<h3 style=" + "\"font-size: 12pt; color: red\"" + ">"
						+ "Не съществува такава поръчка!!! Информацията не е записана!" + "</h3>";

			}
		}

		session.setAttribute("done_status", doneStatus);
		req.getRequestDispatcher("input_test_card.jsp").forward(req, resp);

	}

	private static ArrayList<String> getOrdersCollection() {

		String table = "tb_coois_prod";
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<String> order = new ArrayList<String>();
		ResultSet result = null;

		result = dbActivities.select(table);

		try {
			while (result.next()) {

				order.add(result.getString("tb_coois_prod_order"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;
	}

}
