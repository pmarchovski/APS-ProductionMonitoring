package com.mdrain.servlets.finance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mdrain.servletPrepare.finance.FinanceProtocolDmaDisplayAndUpdate;
import com.mdrain.servletPrepare.finance.FinancialProtocolDma;

public class CreateDmaProtokolServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		
		String path = req.getRequestURI();
		String[] pathArray =  path.split("/");
		
		if (pathArray[pathArray.length - 1].equals("create_dma_protokol_servlet")) FinancialProtocolDma.prepareDmaProtocol(req, resp);
		if (pathArray[pathArray.length - 1].equals("create_dma_protokol_display_servlet")) FinanceProtocolDmaDisplayAndUpdate.prepareProtocolDataForDisplay(req, resp);
		if (pathArray[pathArray.length - 1].equals("create_dma_protokol_display_explicit_servlet")) FinanceProtocolDmaDisplayAndUpdate.getExpliciteProtocol(req, resp);		
		if (pathArray[pathArray.length - 1].equals("create_dma_protokol_update_explicit_servlet")) FinanceProtocolDmaDisplayAndUpdate.updateExpliciteProtocol(req, resp);
		if (pathArray[pathArray.length - 1].equals("create_dma_protokol_update_final_explicit_servlet")) FinanceProtocolDmaDisplayAndUpdate.updateFinalExpliciteProtocol(req, resp);	
		if (pathArray[pathArray.length - 1].equals("create_dma_protokol_download_explicit_servlet")) FinanceProtocolDmaDisplayAndUpdate.extractFullProtocolToExcel(req, resp);
	}
}