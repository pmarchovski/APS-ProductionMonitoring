package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Massages;
import com.mdrain.objects.Users;

public class Registration {

	public static void registration(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DataBaseActivities dbActivities = new DataBaseActivities();
		Users user = new Users();
		HttpSession session = req.getSession();
		String table = "tb_users";

		user.setFullName(req.getParameter("user_registration_full_name"));
		user.setUserName(req.getParameter("user_registration_user_name"));
		user.setEmail(req.getParameter("user_registration_email"));
		user.setPassword(req.getParameter("user_registration_password"));
		user.setType(req.getParameter("user_registration_user_type"));

		if ((user.getFullName().equals("")) || (user.getUserName().equals("")) || (user.getEmail().equals(""))
				|| (user.getPassword().equals(""))) {
			resp.sendRedirect("error.jsp");
		} else {
			ResultSet result = dbActivities.select(table);

			try {

				if (result != null) {
					while (result.next()) {

						if (result.getString("tb_users_user_name").equals(user.getUserName())) {
							session.setAttribute("massage_one", Massages.MASSAGE_ONE);
							req.getRequestDispatcher("user_registration.jsp").forward(req, resp);
							return;
						}
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dbActivities.insert(table,
					new String[] { "tb_users_full_name", "tb_users_user_name", "tb_users_email", "tb_users_password",
							"tb_users_type" },
					new String[] { user.getFullName(), user.getUserName(), user.getEmail(), user.getPassword(),
							user.getType() });

			session.setAttribute("user_name", user.getUserName());
			session.setAttribute("massage", Massages.MASSAGE_TWO);
			
			
			String subject = "Нова регистрация";
			String textBody = "Здравейте " + user.getFullName() + "," 
					+ "\r\n"
		   		    + "\r\n"
					+ "Това е служебна регистрация за APS Production Monitoring " + "www.mdrain.eu"
					+ "\r\n"
		   		    + "\r\n"
					+ "Вашия user name е: " + user.getUserName()
					+ "\r\n"
		   		    + "\r\n"
					+ "парола: " + user.getPassword();
			
			String[] recepient = EmailLists.createUserEmailList(user.getEmail());
			
			SendMail.bootstrap(subject, textBody, recepient);
			
			req.getRequestDispatcher("index.jsp").forward(req, resp);

		}
	}
}
