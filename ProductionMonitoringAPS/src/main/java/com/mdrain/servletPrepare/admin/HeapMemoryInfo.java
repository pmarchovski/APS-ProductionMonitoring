package com.mdrain.servletPrepare.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HeapMemoryInfo {

	private static int minMemory = 10;
	
	public static void heapInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		System.out.println("Total Memory: " + getTotalMemory() + " MB " + " - " + getPercentTotalMemoryToMaxMemory() + " %");
        System.out.println("Free Memory: " + getFreeMemory() + " MB " + " - " + getPercentFreeMemoryToTotalMemory() + " %");
        System.out.println("Max Memory: " + getMaxMemory());
        System.out.println("");

        session.setAttribute("heap_memory_total", "Total Memory: " + getTotalMemory()  + " MB " + " - " + getPercentTotalMemoryToMaxMemory() + " %");
        session.setAttribute("heap_memory_free", "Free Memory: " + getFreeMemory() + " MB " + " - " + getPercentFreeMemoryToTotalMemory() + " %");
        session.setAttribute("heap_memory_max", "Max Memory: " + getMaxMemory() + " MB");
        session.setAttribute("processor", "Processor: " + getProcessor());
        
        sendHeapFreeInfoMail();
         
        req.getRequestDispatcher("heap_memory_info.jsp").forward(req, resp);
	}
	
	public static long getFreeMemory() {	
		long freeMemory  = Runtime.getRuntime().freeMemory() / 1000000;
		return freeMemory;
	}
	
	public static long getTotalMemory() {
		Long totalMemory = Runtime.getRuntime().totalMemory() / 1000000;
		return totalMemory;
	}
	
	public static long getMaxMemory() {
		long maxMemory   = Runtime.getRuntime().maxMemory() / 1000000;
		return maxMemory;
	}
	
	public static int getProcessor() {
		int processor    = Runtime.getRuntime().availableProcessors();
		return processor;
	}
	
	public static long getPercentFreeMemoryToTotalMemory() {
		long percentFreeMemoryToTotalMemory = (getFreeMemory() * 100) / getTotalMemory();
		return percentFreeMemoryToTotalMemory;
	}
	
	public static long getPercentTotalMemoryToMaxMemory() {
		long percentTotalMemoryToMaxMemory  = (getTotalMemory() * 100) / getMaxMemory();
		return percentTotalMemoryToMaxMemory;
	}
	
	public static void sendHeapFreeInfoMail() throws ServletException, IOException {
		  
		  if (getFreeMemory() < minMemory) {
	        	String massageTitle = "Heap free memory alert!!!";
	        	String massageBody  = "Heap free memory is less than " + minMemory;
	        	String[] recepient  = {"petar.marchovski@gmail.com"};
	        	
	        	
	    		System.gc();
	    	    try {
	    			Thread.sleep(5000);
	    		} catch (InterruptedException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        	
	        	
	        	SendMail.bootstrap(massageTitle, massageBody, recepient);
	        }
	}
}
