package com.mdrain.servletPrepare.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.logic.PassEncrypt;
import com.mdrain.logic.PictureGenerate;
import com.mdrain.singletons.Singleton;

public class ForgotPassword {

	  static boolean isAvailableUserMail = false;
	  static boolean isPasswordsEquals;
	  static boolean isPasswordLength;
	  static public String puctureOK        = PictureGenerate.returnPictureOK();
	  static public String puctureNOK       = PictureGenerate.returnPictureNOK();
	  static public String puctureAttention = PictureGenerate.returnPictureAttention();
	
	public static void bootstrap(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		ArrayList<String> userMailCollection = new ArrayList<String>();
		HttpSession session                  = req.getSession();
		String email                         = req.getParameter("forgot_password_email");
		String newPassword                   = PassEncrypt.encryptPassword(req.getParameter("forgot_password_new_password"));
		String repeatPassword       		 = PassEncrypt.encryptPassword(req.getParameter("forgot_password_repeat_new_password"));
		String table               			 = "tb_users";
		String userMail           			 = "";
		int setPasswordLength                = 6;
	
		
		DataBaseActivities dbActivities = Singleton.getInstance();
		ResultSet result = dbActivities.select(table);
		
		
		// Check whether the new password is equals with repeat password
		if (!newPassword.equals(repeatPassword)) {
			isPasswordsEquals = false;
			session.setAttribute("change_password_message", returnMessageComparePass(isPasswordsEquals));	
			req.getRequestDispatcher("forgot_password.jsp").forward(req, resp);
			return;
		}
		
		// Check password length
		if (newPassword.length() < setPasswordLength) {
			isPasswordLength = false;
			session.setAttribute("change_password_message", returnMessageLength(isPasswordLength));	
			req.getRequestDispatcher("forgot_password.jsp").forward(req, resp);
			return;
		}
		
		
		// Check for available user with such e-mail and return user mail
		
		if (result != null) {
			try {
			while (result.next()) {
				
				userMailCollection.add(result.getString("tb_users_email"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			bootstrap(req, resp);
		}

		for (int i = 0; i < userMailCollection.size(); i++) {
			
			if (userMailCollection.get(i).equals(email)) {			
				userMail = email;
				isAvailableUserMail = true;
				break;
			} else {
				isAvailableUserMail = false;	
			
			}
		}
		
		if (isAvailableUserMail == false) {
			session.setAttribute("change_password_message", returnMessage(isAvailableUserMail));
			req.getRequestDispatcher("forgot_password.jsp").forward(req, resp);
		} else {
			
			dbActivities.update(table, "tb_users_password", newPassword, "tb_users_email", userMail);
			isAvailableUserMail = true;
			
			session.removeAttribute("login_inform_massage");
		    session.setAttribute("change_password_message", returnMessage(isAvailableUserMail));
		    String[] recepient = EmailLists.changeUserPassword(userMail);
		    SendMail.bootstrap("Променена парола", "Паролата на потребител с email: " + userMail + " е променена "
		    		+ "успешно.", recepient);
		    req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
	private static String returnMessage(boolean isAvailableUserMail) {
	String message = "";
		
	if (isAvailableUserMail == false) message = puctureNOK + " Не съществува потребител с този e-mail!";
	if (isAvailableUserMail == true) message  = puctureOK + " Паролата е променена успешно! Моля, проверете пощата си.";
	
	
	return message;
	}
	
	private static String returnMessageComparePass(boolean isPasswordsEquals) {
	
	String message = "";
	if (isPasswordsEquals == false) {
		message = puctureAttention + " Няма съвпадение в паролите!!!";
	} else {
		message = "";
	}
	return message;
	}
	
	private static String returnMessageLength(boolean isPasswordLength) {
		
		String message = "";
		if (isPasswordLength == false) {
			message = puctureAttention + " Паролата трябва да бъде минимум 6 символа!!!";
		} else {
			message = "";
		}
		return message;
		}
}
