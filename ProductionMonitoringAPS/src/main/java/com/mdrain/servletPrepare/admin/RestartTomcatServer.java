package com.mdrain.servletPrepare.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.mdrain.servlets.admin.AdminServlet;


public class RestartTomcatServer {

	
	public static void bootstrap() throws IOException {
			
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8005);
		tomcat.setHostname("localhost");
		String appBase = ".";
		tomcat.getHost().setAppBase(appBase);
		
		File docBase = new File(System.getProperty("java.io.tmpdir"));
		Context context = tomcat.addContext("", docBase.getAbsolutePath());
		
		Class servletClass = AdminServlet.class;
		Tomcat.addServlet(
		  context, servletClass.getSimpleName(), servletClass.getName());
		context.addServletMappingDecoded(
		  "/admin_servlet_restart_tomcat/*", servletClass.getSimpleName());
		
		
		try {
			tomcat.start();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
