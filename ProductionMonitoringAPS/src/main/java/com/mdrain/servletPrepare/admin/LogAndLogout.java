package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;


public class LogAndLogout {

	static public String userNameForDataBase = "";
	
	public static void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		HttpSession session = req.getSession();

		String userName = req.getParameter("login_user_name");
		String password = req.getParameter("login_user_password");
		userNameForDataBase = userName;

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
						resp.sendRedirect("admin_servlet_generate_lists");

						
						return;
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	public static void logOut(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("login_inform_massage");
		session.removeAttribute("user");
		session.invalidate();
        
	}
}
