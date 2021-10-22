package com.mdrain.servlets.tph;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mdrain.servletPrepare.tph.IncludeDataTph;

public class InputDataTphServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		String getUrl = "";
		
		getUrl = req.getRequestURI();
		
		String url[] = getUrl.split("/");
		
		
		System.out.println(getUrl);
		
		
		if (url[url.length - 1].equals("tph_data_be_servlet")) {
			IncludeDataTph.includeScrapBackEnd(req, resp);
		}
		
		if (url[url.length - 1].equals("tph_data_servlet")) {
			IncludeDataTph.includeScrapAfterEpoxy(req, resp);
		}
		

		if (url[url.length - 1].equals("tph_data_pulse_trimmer_servlet")) {
			IncludeDataTph.includeScrapPulseTrimmer(req, resp);
		}

		
		if (url[url.length - 1].equals("tph_data_final_test_servlet")) {
			IncludeDataTph.includeFinalTestInformation(req, resp);
		}
	}
	
}
