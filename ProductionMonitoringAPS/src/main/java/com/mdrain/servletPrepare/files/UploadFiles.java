package com.mdrain.servletPrepare.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFiles {

	public static void uploadFiles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String mainPlace                = req.getServletContext().getRealPath("");
		String subPlace                 = "UploadedFiles";
		
		bootstrap(req, resp, mainPlace, subPlace);
		
		resp.sendRedirect("upload_files.jsp");
		
	}
	
	private static void bootstrap(HttpServletRequest req, HttpServletResponse resp, String mainPlace, String subPlace) {
		
		
		HttpSession session = req.getSession();
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());

		
		
		try {
			ArrayList<FileItem> multifiles = (ArrayList<FileItem>) sf.parseRequest(req);

			for (FileItem item : multifiles) {
				
				
				String fileName = item.getName();
//				String getTypeFile = item.getName().substring(fileName.length() - 4, fileName.length());
//				String renamedFile = session.getAttribute("user_name_avatar") +  "_avatar" + getTypeFile;
				
		        
				item.write(new File(mainPlace + subPlace + "//" + fileName));
//				fileNameAvatar = renamedFile;
//				session.setAttribute("avatar_file_name", fileName);
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
