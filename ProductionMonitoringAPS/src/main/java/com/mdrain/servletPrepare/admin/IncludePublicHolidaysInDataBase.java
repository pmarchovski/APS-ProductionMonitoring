package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;

public class IncludePublicHolidaysInDataBase {

	
	public static void includePublicHolidays(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession session = req.getSession();

		session.removeAttribute("public_holidays_error_massage");
		session.removeAttribute("public_holidays_access_massage");

		String table                    = "tb_public_holidays";
		String field                    = "tb_public_holidays_date";
		String date                     = req.getParameter("public_holidays_date");
		DataBaseActivities dbActivities = new DataBaseActivities();
		ResultSet result                = null;

		result                          = dbActivities.select(table);

		try {
			while (result.next()) {

				if (date.equals(result.getString("tb_public_holidays_date"))) {

					session.setAttribute("public_holidays_error_massage", "Избраната дата съществува в базата данни");
					resp.sendRedirect("public_holidays.jsp");
					return;
				}

			}

			dbActivities.insert(table, field, date);
			session.setAttribute("public_holidays_access_massage", "Почивният ден е добавен успешно в базата");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		req.getRequestDispatcher("public_holidays.jsp").forward(req, resp);
	}
	
}
