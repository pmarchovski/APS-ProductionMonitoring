package com.mdrain.servletPrepare.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFilesForProductionCapacity {

	
	public static void uploadXmlFilesForProductionCapacity(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String uploadPlace = req.getServletContext().getRealPath("");

		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());

		HttpSession session = req.getSession();

		try {
			ArrayList<FileItem> multifiles = (ArrayList<FileItem>) sf.parseRequest(req);

			for (FileItem item : multifiles) {
				item.write(new File(uploadPlace + item.getName()));
			}

			session.setAttribute("before_path", "Файловете са качени успешно на: ");
			session.setAttribute("path", uploadPlace);

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String buttonPath = "<form id=\"upload_coois_prod_form\" method=\"get\" action=\"admin_servlet_upload_data_into_coois_prod\">\r\n"
				+ "\r\n"
				+ "<input type=\"submit\" value=\"REFRESH COOIS_PROD\" Class=\"butt\" id=\"click_coois_prod\">\r\n"
				+ "\r\n"
				+ "</form>";
		
		session.setAttribute("admin_servlet_upload_data_into_coois", buttonPath);
		resp.sendRedirect("production_capacity.jsp");
		
		
	}
	
}
