package com.mdrain.servlets.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mdrain.servletPrepare.admin.GenerateList;
import com.mdrain.servletPrepare.admin.JoinAndCreateTableResult;
import com.mdrain.servletPrepare.admin.LogAndLogout;
import com.mdrain.servletPrepare.admin.Registration;
import com.mdrain.servletPrepare.admin.UploadDataIntoCooisTables;
import com.mdrain.servletPrepare.admin.UploadFilesForProductionCapacity;
import com.mdrain.servletPrepare.printers_production.UpdateProductionStaffInfo;


public class AdminServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String path = req.getRequestURI();
		String[] pathArray =  path.split("/");
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_registration")) 
			Registration.registration(req, resp);
		
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
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getRequestURI();
		String[] pathArray =  path.split("/");
		
		if (pathArray[pathArray.length - 1].equals("admin_servlet_upload_files_for_production_capacity")) 
			UploadFilesForProductionCapacity.uploadXmlFilesForProductionCapacity(req, resp);
		
		
	}
	
	
}
