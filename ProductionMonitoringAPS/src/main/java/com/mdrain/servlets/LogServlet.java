package com.mdrain.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Date;
import com.mdrain.logic.Massages;
import com.mdrain.objects.Users;

public class LogServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		DataBaseActivities dbActivities = new DataBaseActivities();
		HttpSession session = req.getSession();

		String userName = req.getParameter("login_user_name");
		String password = req.getParameter("login_user_password");

		if (userName.equals("") || password.equals("")) {
			session.setAttribute("massage_log", "Моля попълнете всички полета");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}

		ResultSet result = dbActivities.select("tb_users");

		try {
			if (result != null) {
				while (result.next()) {
					String tempUserName = result.getString("tb_users_user_name");
					String tempPassword = result.getString("tb_users_password");
					String tempUserType = result.getString("tb_users_type");

					if (userName.equals(tempUserName) && password.equals(tempPassword)) {
						session.setAttribute("isAccess", true);
						session.setAttribute("isNoAccess", true);
						session.setAttribute("user_name", userName);
						session.setAttribute("role", tempUserType);

						session.setAttribute("login_inform_massage", "влязoхте успешно в системата");
						resp.sendRedirect("lists");

						return;
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		session.removeAttribute("login_inform_massage");
		session.removeAttribute("user");
		session.invalidate();

		resp.sendRedirect("index.jsp");

	}

}
