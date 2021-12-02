package com.mdrain.servletPrepare.admin;

import java.io.File;
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
import com.mdrain.logic.UploadFilesOnServer;
import com.mdrain.singletons.Singleton;

public class UploadAvatars {

	   
	public static void uploadAvatar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session    = req.getSession();
		session.removeAttribute("change_user_data_massage");
		session.removeAttribute("change_password_message");
		String table                    = "tb_users";
		DataBaseActivities dbActivities = Singleton.getInstance();
        String uploadSubPlace           = "pictures//avatars//";
		String uploadPlace              = req.getServletContext().getRealPath("");
		String fileName                 = "";
		String user                     = (String) session.getAttribute("user_name_avatar");
		
		
		UploadFilesOnServer uploadFile = new UploadFilesOnServer();
		
		File file = new File(uploadPlace + uploadSubPlace + "\\" + user + "_avatar.png");
		file.deleteOnExit();
		uploadFile.uploadFile(req, resp, uploadPlace, uploadSubPlace);
		fileName = uploadFile.fileNameAvatar;

		dbActivities.update(table, "tb_users_avatar", fileName, "tb_users_user_name", user);
		
		session.setAttribute("change_user_data_massage", "Снимката е променена успешно. Моля, влезте отново в профила си!");

		req.getRequestDispatcher("display_and_update_user_info.jsp").forward(req, resp);
		
	}
	
	public static void updateUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session                = req.getSession();
		session.removeAttribute("change_user_data_massage");
		session.removeAttribute("change_password_message");
		DataBaseActivities dbActivities    = Singleton.getInstance();
		ArrayList<String> fieldsCollection = new ArrayList<String>();
		ArrayList<String> valueCollection  = new ArrayList<String>();
		
		String table = "tb_users";
		
		String fullName        = req.getParameter("display_and_update_user_full_name");
		String userName        = (String) session.getAttribute("user_name_avatar");
		String userNameFromApp = req.getParameter("display_and_update_user_name");
		String userMail        = req.getParameter("display_and_update_user_email");
		String newPassword     = PassEncrypt.encryptPassword(req.getParameter("display_and_update_user_password"));
		String repeatPassword  = PassEncrypt.encryptPassword(req.getParameter("display_and_update_user_repeat_password"));
		
		
		if (!fullName.equals("")) {
			fieldsCollection.add("tb_users_full_name");
			valueCollection.add(fullName);
		}
		
		if (!userMail.equals("")) {
			fieldsCollection.add("tb_users_email");
			valueCollection.add(userMail);
		}
		
		if (!userNameFromApp.equals("")) {
			fieldsCollection.add("tb_users_user_name");
			valueCollection.add(userNameFromApp);
		}
		
		if (!newPassword.equals("") && newPassword.equals(repeatPassword)) {
			fieldsCollection.add("tb_users_password");
			valueCollection.add(newPassword);
		} 
		
		if (!newPassword.equals(repeatPassword)){
			session.setAttribute("change_password_message", "Няма съвпадение на паролите");
			req.getRequestDispatcher("display_and_update_user_info.jsp").forward(req, resp);
			return;
		}
		
		dbActivities.update(table, fieldsCollection, valueCollection, "tb_users_user_name", userName);
		
		session.setAttribute("change_user_data_massage", "Данните са променени успешно. Моля, влезте отново в профила си!");
		
		req.getRequestDispatcher("display_and_update_user_info.jsp").forward(req, resp);
	}
	
}
