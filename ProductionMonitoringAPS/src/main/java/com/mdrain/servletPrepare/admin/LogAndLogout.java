package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.PassEncrypt;
import com.mdrain.logic.PictureGenerate;
import com.mdrain.singletons.Singleton;

public class LogAndLogout {

	static public String userNameForDataBase = "";
	static public String puctureOK           = PictureGenerate.returnPictureOK();
	static public String puctureNOK          = PictureGenerate.returnPictureNOK();
	static public String puctureAttention    = PictureGenerate.returnPictureAttention();
	
	public static void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		DataBaseActivities dbActivities = Singleton.getInstance();
		HttpSession session = req.getSession();

		String userName = req.getParameter("login_user_name");
		String password = PassEncrypt.encryptPassword(req.getParameter("login_user_password"));
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
					String tempAvatar   = result.getString("tb_users_avatar");
					String userFullName = result.getString("tb_users_full_name");
					String userMail     = result.getString("tb_users_email");
                    String avatar       = PictureGenerate.returnAvatar(tempAvatar);

					if (userName.equals(tempUserName) && password.equals(tempPassword)) {
						
						String userLinkQuery = "<a href=" + "\"" + "display_and_update_user_info.jsp" + "\"" + ">" + userName + "</a";
						session.setAttribute("isAccess", true);
						session.setAttribute("isNoAccess", true);
						session.setAttribute("user_name", userLinkQuery);
						session.setAttribute("user_name_avatar", userName);
						session.setAttribute("user_full_name", userFullName);
						session.setAttribute("user_mail", userMail);
						session.setAttribute("role", tempUserType);
						session.setAttribute("avatar", avatar);
						session.setAttribute("login_inform_massage", userName);
						session.setAttribute("current_month", returnCurrentMonth());
						resp.sendRedirect("admin_servlet_generate_lists");
						return;
					} 
				}

				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		    session.removeAttribute("change_password_message");
			session.setAttribute("login_inform_massage", puctureNOK + " Грешно потребителско име или парола");
			
			resp.sendRedirect("login.jsp");

	}
		
	public static void logOut(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("login_inform_massage");
		session.removeAttribute("user");
		session.invalidate();
        
	}
	
	private static String returnCurrentMonth() {
		
		LocalDate now = LocalDate.now();
		
		int currentYear = now.getYear();
		int currenMonth = now.getMonthValue();
		String currentYearAndMonth = currentYear + "-" + currenMonth;
		
		return currentYearAndMonth;
	}

}
