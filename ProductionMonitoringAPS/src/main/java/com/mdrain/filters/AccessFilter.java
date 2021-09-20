package com.mdrain.filters;

import java.io.IOException;
import javax.servlet.ServletRequest;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.logic.PageAccessCollection;

public class AccessFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		
		String page = "";
		String pageTwo = "";
		
		HttpServletRequest sessionReq = (HttpServletRequest) req;
		HttpServletResponse sessionResp = (HttpServletResponse) resp;

		
		String url = sessionReq.getRequestURI();
		String[] collectionUrlSplit = url.split("/");
		
		
		if (collectionUrlSplit.length <= 0) {		
			page = "/index.jsp";
			
		} else {
			page = collectionUrlSplit[collectionUrlSplit.length - 1];
		}

		if (collectionUrlSplit.length <= 0) {
			pageTwo = "/index.jsp";
			
		} else {
			pageTwo = collectionUrlSplit[collectionUrlSplit.length - 1];
		}
		
		
		
		ArrayList<String> pageCollection = new ArrayList<String>();
		ArrayList<String> pageCollectionAfterLogin = new ArrayList<String>();
		
		
		if (role(req).equals("with_out_login")) {
			pageCollectionAfterLogin = PageAccessCollection.roleWithOutLoginCollection();
		}
			
		if (role(req).equals("user")) {
			pageCollectionAfterLogin = PageAccessCollection.roleUserCollection();
		}
		
		if (role(req).equals("guest")) {
			pageCollectionAfterLogin = PageAccessCollection.roleGuestCollection();
		}
		
		if (role(req).equals("admin")) {
			pageCollectionAfterLogin = PageAccessCollection.roleAdminCollection();
			sessionReq.setAttribute("registration", "<li><a href=\"user_registration.jsp\">Регистрация</a></li>");
		}
				

		pageCollection = PageAccessCollection.roleWithOutLoginCollection();

		for(int i = 0; i < pageCollectionAfterLogin.size(); i++) {
			if(pageTwo.equals(pageCollectionAfterLogin.get(i)))
				if(this.isNoAccess(req)) {
					sessionResp.sendRedirect("index.jsp");
					return;
				}
		}
		
		for(int i = 0; i < pageCollection.size(); i++) {
			
			if(page.equals(pageCollection.get(i))) {
				if(!this.isAccess(req)) {
					sessionResp.sendRedirect("login.jsp");
					return;
				}
			}	
		}
		
	    chain.doFilter(req, resp);
	}
	
	private boolean isAccess(ServletRequest req) {
		
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpSession httpSession = httpReq.getSession();
		
		if(httpSession.getAttribute("isAccess") != null) {
			return (boolean) httpSession.getAttribute("isAccess");
		}
		
		
		return false;
	}
	
	private boolean isNoAccess(ServletRequest req) {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpSession httpSession = httpReq.getSession();
		
		if(httpSession.getAttribute("isNoAccess") != null) {
			return (boolean) httpSession.getAttribute("isNoAccess");
		}
		
		return false;
	}
	
	private String role(ServletRequest req) {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpSession httpSession = httpReq.getSession();
			
		if (httpSession.getAttribute("role") == null) return "with_out_login";
		if (httpSession.getAttribute("role").equals("user")) return "user";
		if (httpSession.getAttribute("role").equals("guest")) return "guest";
		if (httpSession.getAttribute("role").equals("admin")) return "admin";
		
	    return "admin";
	}

}