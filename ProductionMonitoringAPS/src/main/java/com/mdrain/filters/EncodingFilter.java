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


public class EncodingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		System.out.println(resp.getCharacterEncoding());
		
	    chain.doFilter(req, resp);
	}
	
}
	
