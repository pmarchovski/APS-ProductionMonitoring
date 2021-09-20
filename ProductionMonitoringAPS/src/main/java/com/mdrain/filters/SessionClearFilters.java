package com.mdrain.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionClearFilters implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
	
	
		String page = "";
		
		HttpServletRequest sessionReq = (HttpServletRequest) req;
		HttpServletResponse sessionResp = (HttpServletResponse) resp;
		
		HttpSession session = ((HttpServletRequest) req).getSession();
		
		String url = sessionReq.getRequestURI();
		String[] collectionUrlSplit = url.split("/");
	
		if (collectionUrlSplit.length <= 0) {		
			page = "/index.jsp";
			
		} else {
			page = collectionUrlSplit[collectionUrlSplit.length - 1];
			
			session.removeAttribute("update_operators_display_info");
			session.removeAttribute("table_head");
			session.removeAttribute("operators_info_table_body");
			
			session.removeAttribute("monthly_present_blank_table_head_date");
			session.removeAttribute("monthly_present_blank_table_head_week_day");
			session.removeAttribute("monthly_present_blank_table_body_data");
			session.removeAttribute("include_operators_inform_massage");
			session.removeAttribute("massage_edit");
			
			
		}
		
		
		
	
		 chain.doFilter(req, resp);
		
	}
}
