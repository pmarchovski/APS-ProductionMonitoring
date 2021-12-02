package com.mdrain.servlets.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.mdrain.servletPrepare.admin.ForgotPassword;
import com.mdrain.servletPrepare.admin.GenerateList;
import com.mdrain.servletPrepare.admin.HeapMemoryInfo;
import com.mdrain.servletPrepare.admin.JoinAndCreateTableResult;
import com.mdrain.servletPrepare.admin.LogAndLogout;
import com.mdrain.servletPrepare.admin.RestartTomcatServer;
import com.mdrain.servletPrepare.admin.SendSMS;
import com.mdrain.servletPrepare.admin.TaskManager;
import com.mdrain.servletPrepare.admin.Translate;
import com.mdrain.servletPrepare.admin.Registration;
import com.mdrain.servletPrepare.admin.UploadAvatars;
import com.mdrain.servletPrepare.admin.UploadDataIntoCooisTables;
import com.mdrain.servletPrepare.admin.UploadFilesForProductionCapacity;
import com.mdrain.servletPrepare.printers_production.UpdateProductionStaffInfo;
import com.mdrain.servletPrepare.admin.WorkClothesManager;
import com.mdrain.servletPrepare.admin.DisplayJFrame;

public class AdminServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getRequestURI();
		String[] pathArray = path.split("/");

		if (pathArray[pathArray.length - 1].equals("admin_servlet_login"))
			LogAndLogout.logIn(req, resp);

		if (pathArray[pathArray.length - 1].equals("admin_servlet_logout"))
			try {
				LogAndLogout.logOut(req, resp);
				resp.sendRedirect("index.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (pathArray[pathArray.length - 1].equals("admin_servlet_generate_lists"))
			GenerateList.listGenerate(req, resp);

		if (pathArray[pathArray.length - 1].equals("admin_servlet_create_join_table_result"))
			JoinAndCreateTableResult.createAndJoinTableResult(req, resp);

		if (pathArray[pathArray.length - 1].equals("admin_servlet_upload_data_into_coois_prod"))
			UploadDataIntoCooisTables.uploadDataIntoCooisProdTables(req, resp);

		if (pathArray[pathArray.length - 1].equals("admin_servlet_upload_data_into_coois_operation"))
			UploadDataIntoCooisTables.uploadDataIntoCooisOperationTables(req, resp);

		if (pathArray[pathArray.length - 1].equals("admin_servlet_current_operators_data")) {
			UpdateProductionStaffInfo updateProductionInfo = new UpdateProductionStaffInfo();
			updateProductionInfo.getCurrentOperatorsInfo(req, resp);
		}

		if (pathArray[pathArray.length - 1].equals("admin_servlet_forgot_password"))
			ForgotPassword.bootstrap(req, resp);

		if (pathArray[pathArray.length - 1].equals("admin_servlet_registration"))
			try {
				Registration.registration(req, resp);
			} catch (ServletException | IOException | FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (pathArray[pathArray.length - 1].equals("admin_update_servlet_registration_info"))
			UploadAvatars.updateUserInfo(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_restart_tomcat"))
			RestartTomcatServer.bootstrap();
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_create_new_task"))
			TaskManager.createNewTask(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_display_tasks"))
			TaskManager.displayTaskList(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_display_my_tasks"))
			TaskManager.displayMyTaskList(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_display_explicite_tasks"))
			TaskManager.displayExpliciteTask(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_add_comment"))
			TaskManager.addComment(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_change_status"))
			TaskManager.changeTaskStatus(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_change_task_target_date"))
			TaskManager.changeTaskTargetDate(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_work_clothes_for_order"))
			WorkClothesManager.displayWorkClothesForOrder(req, resp);
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_heap_memory_info")) {
			HeapMemoryInfo.heapInfo(req, resp);
		}
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_send_sms")) {
			SendSMS.botExample(req, resp);
		}
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_translator")) {
			Translate.translate(req, resp);
		}
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_display_jframe")) {
			DisplayJFrame.bootstrap(req, resp);
		}
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getRequestURI();
		String[] pathArray = path.split("/");

		if (pathArray[pathArray.length - 1].equals("admin_servlet_upload_files_for_production_capacity"))
			UploadFilesForProductionCapacity.uploadXmlFilesForProductionCapacity(req, resp);

		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_registration_upload_avatar"))
			UploadAvatars.uploadAvatar(req, resp);
		
		
		
	}

}
