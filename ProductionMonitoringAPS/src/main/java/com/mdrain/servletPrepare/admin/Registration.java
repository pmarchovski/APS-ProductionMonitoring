package com.mdrain.servletPrepare.admin;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;


import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.Massages;
import com.mdrain.logic.PassEncrypt;
import com.mdrain.logic.UploadFilesOnServer;
import com.mdrain.objects.Users;
import com.mdrain.singletons.Singleton;

public class Registration {

	public static void registration(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, FileUploadException {

		DataBaseActivities dbActivities = Singleton.getInstance();
		Users user                      = new Users();
		HttpSession session             = req.getSession();
		String table                    = "tb_users";
		String password                 = "";

		user.setFullName(req.getParameter("user_registration_full_name"));
		user.setUserName(req.getParameter("user_registration_user_name"));
		user.setEmail(req.getParameter("user_registration_email"));		
		password = PassEncrypt.encryptPassword(req.getParameter("user_registration_password"));		
		user.setPassword(password);
		user.setType(req.getParameter("user_registration_user_type"));
		user.setAvatar("avatar.png");

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
							"tb_users_type", "tb_users_avatar" },
					new String[] { user.getFullName(), user.getUserName(), user.getEmail(), user.getPassword(),
							user.getType(), user.getAvatar() });
			
					
			session.setAttribute("user_name", user.getUserName());
			session.setAttribute("massage", Massages.MASSAGE_TWO);
			
			
			String subject = "Нова регистрация";
			String textBody = "Здравейте " + user.getFullName() + "," 
					+ "\r\n"
		   		    + "\r\n"
					+ "Това е служебна регистрация за APS Production Monitoring " + "www.aps-monitoring.eu"
					+ "\r\n"
		   		    + "\r\n"
					+ "Вашият user name е: " + user.getUserName();
			
			String[] recepient = EmailLists.createUserEmailList(user.getEmail());
			
			SendMail.bootstrap(subject, textBody, recepient);

			
			req.getRequestDispatcher("user_registration.jsp").forward(req, resp);

		}
	}
}
