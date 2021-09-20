package com.mdrain.servlets;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.User;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.database.DataBaseConnect;
import com.mdrain.logic.Massages;
import com.mdrain.objects.Users;

public class RegServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
						dbActivities.insert(table, new String[] { "tb_users_full_name", "tb_users_user_name", "tb_users_email", "tb_users_password", "tb_user_type" },
								new String[] { user.getFullName(), user.getUserName(), user.getEmail(),
										user.getPassword(), user.getType() });

						session.setAttribute("user_name", user.getUserName());
						session.setAttribute("massage", Massages.MASSAGE_TWO);
						req.getRequestDispatcher("index.jsp").forward(req, resp);
				
		}		
	}
}
