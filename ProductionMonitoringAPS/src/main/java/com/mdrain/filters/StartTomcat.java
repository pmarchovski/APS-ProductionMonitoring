package com.mdrain.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.mdrain.servletPrepare.admin.RestartTomcatServer;

public class StartTomcat implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
	//	RestartTomcatServer.bootstrap();
		
		chain.doFilter(req, resp);
	}

}
