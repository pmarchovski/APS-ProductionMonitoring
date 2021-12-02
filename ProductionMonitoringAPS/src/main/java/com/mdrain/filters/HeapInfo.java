package com.mdrain.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.servletPrepare.admin.HeapMemoryInfo;

public class HeapInfo implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HeapMemoryInfo.sendHeapFreeInfoMail();
	
		
		chain.doFilter(req, resp);
	}
}
