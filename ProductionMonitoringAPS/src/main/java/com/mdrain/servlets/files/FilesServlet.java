package com.mdrain.servlets.files;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mdrain.servletPrepare.files.*;

import com.mdrain.servletPrepare.admin.LogAndLogout;

public class FilesServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getRequestURI();
		String[] pathArray = path.split("/");

		if (pathArray[pathArray.length - 1].equals("admin_servlet_upload_files"))
			UploadFiles.uploadFiles(req, resp);
	}
}
