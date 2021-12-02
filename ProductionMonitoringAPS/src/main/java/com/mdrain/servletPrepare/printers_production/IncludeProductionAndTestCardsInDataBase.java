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
import com.mdrain.logic.Tables;
import com.mdrain.singletons.Singleton;

public class IncludeProductionAndTestCardsInDataBase {
	
	private static ArrayList<String> orders     = new ArrayList<String>();

	public static void includeProductionCardsInDataBase(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
        HttpSession session                     = req.getSession();
		DataBaseActivities dbActivities         = Singleton.getInstance();
		LocalDate inputDate                     = LocalDate.now();
		String inputDateDB                      = inputDate.toString();
		String doneStatus                       = "";
		String table                            = "tb_production_card_information";
		Tables tables                           = new Tables();
		
		String[] fieldsInto = {
				"production_card_information_date",
				"production_card_information_order",
				"production_card_information_operator",
				"production_card_information_operation",
				"production_card_information_quantity",
				"production_card_information_time",
				"production_card_information_isNewOperator",
				"production_card_information_user",
				"production_card_information_input_date"};


		String[] values = {
				req.getParameter("input_production_card_date"),
				req.getParameter("input_production_card_production_order"),
				req.getParameter("input_production_card_operators_name"),
				req.getParameter("input_production_card_operation"),
				req.getParameter("input_production_card_quantity"),
				req.getParameter("input_production_card_time"),
				req.getParameter("input_production_card_isNewOperator"),
				(String) session.getAttribute("user_name_avatar"),
				inputDateDB};

		if (orders.size() == 0) {
			orders = getOrdersCollection();
		}
		

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

				
				String[] tableFieldsCollection = {
						"Дата на производство",
						"Поръчка",
						"Оператор",
						"Операция",
						"Количество",
						"Време",
						"Нов оператор ДА/НЕ",
						"User name",
						"Дата на въвеждане"};

				String tableHead = tables.createTableHead(tableFieldsCollection);
				String tableBody = tables.createTable(values);

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

		HttpSession session           = req.getSession();
		DataBaseActivities dbActivies = Singleton.getInstance();	
		LocalDate date                = LocalDate.now();
		String table                  = "tb_test_cards";
		String dateString             = date.toString();
		String doneStatus             = "";

		session.removeAttribute("done_status");
		
		String[] fieldsInto = {
				"tb_test_cards_date",
				"tb_test_cards_order",
				"tb_test_cards_pale_print",
				"tb_test_cards_thermistor",
				"tb_test_cards_concise_print",
				"tb_test_cards_main_board",
				"tb_test_cards_opto_sensor",
				"tb_test_cards_second_board",
				"tb_test_cards_motor",
				"tb_test_cards_broken_components",
				"tb_test_cards_tph",
				"tb_test_cards_mech_deffect",
				"tb_test_cards_noise",
				"tb_test_cards_other",
				"tb_test_cards_user",
				"tb_test_cards_date_input"
		};
		
		Object[] value = {
				req.getParameter("input_test_card_date"),
				req.getParameter("input_test_card_production_order"),
				req.getParameter("input_test_card_pale_print"),
				req.getParameter("input_test_card_thermistor"),
				req.getParameter("input_test_card_concise_print"),
				req.getParameter("input_test_card_main_board"),
				req.getParameter("input_test_card_opto_sensor"),
				req.getParameter("input_test_card_second_board"),
				req.getParameter("input_test_card_motor"),
				req.getParameter("input_test_card_broken_component"),
				req.getParameter("input_test_card_tph"),
				req.getParameter("input_test_card_mechanical_deffect"),
				req.getParameter("input_test_card_noise"),
				req.getParameter("input_test_card_others"),
				(String) session.getAttribute("user_name_avatar"),
				dateString
		};
		

		if (orders.size() == 0) {
			orders = getOrdersCollection();
		}
		
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

		String table                    = "tb_coois_prod";
		DataBaseActivities dbActivities = Singleton.getInstance();
		ResultSet result                = dbActivities.select(table);
   
		if (result != null) {
			try {
				while (result.next()) {
					orders.add(result.getString("tb_coois_prod_order"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			getOrdersCollection();
		}
		return orders;
	}

}
