package com.mdrain.servlets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class UploadFileServlet extends HttpServlet {

	boolean hasOrder = false;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uploadPlace = getServletContext().getRealPath("");

		System.out.println(uploadPlace);

		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());

		HttpSession session = req.getSession();

		try {
			ArrayList<FileItem> multifiles = (ArrayList<FileItem>) sf.parseRequest(req);

			for (FileItem item : multifiles) {
				item.write(new File(uploadPlace + item.getName()));
			}

			System.out.println(uploadPlace);
			session.setAttribute("before_path", "Файловете са качени успешно на: ");
			session.setAttribute("path", uploadPlace);

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("/upload_xml_data_into_coois_prod_servlet");
	}

}
