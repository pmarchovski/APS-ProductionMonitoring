package com.mdrain.logic;

import java.util.ArrayList;

public class ChartGenerator {

	
	public String xValueGenerate(int[] xValue) {
		
		
		String xValueQuery = "";
		for (int i = 0; i < xValue.length; i++) {
			
			xValueQuery += "\"" + xValue[i] + "\"" + ", ";
	
		}
		xValueQuery = xValueQuery.substring(0, xValueQuery.length() - 2);
		xValueQuery = "[" + xValueQuery + "]";
		
		
		return xValueQuery;
		
	}	
	
	
	public String xValueGenerate(ArrayList<String> xValue) {
		
		
		String xValueQuery = "";
		for (int i = 0; i < xValue.size(); i++) {
			
			xValueQuery += "\"" + xValue.get(i) + "\"" + ", ";
	
		}
		xValueQuery = xValueQuery.substring(0, xValueQuery.length() - 2);
		xValueQuery = "[" + xValueQuery + "]";
		
		
		return xValueQuery;
		
	}	
	
	
	
    public String yValueGenerate(double[] yValue) {
		
	String yValueQuery = "";
		
		
	    
		for (int i = 0; i < yValue.length; i++) {
			
			yValueQuery += yValue[i] + ", ";
			
		}
		
		yValueQuery = yValueQuery.substring(0, yValueQuery.length() - 2);
		yValueQuery = "[" + yValueQuery + "]";
		
		
		return yValueQuery;
		
	}	
    
    
    public String yValueGenerateDouble(ArrayList<Double> yValue) {
		
    	String yValueQuery = "";
    		
    		
    	    
    		for (int i = 0; i < yValue.size(); i++) {
    			
    			yValueQuery += yValue.get(i) + ", ";
    			
    		}
    		
    		yValueQuery = yValueQuery.substring(0, yValueQuery.length() - 2);
    		yValueQuery = "[" + yValueQuery + "]";
    		
    		
    		return yValueQuery;
    		
    	}	
    
    
    public String yValueGenerate(ArrayList<Integer> yValue) {
		
    	String yValueQuery = "";
    		
    		
    	    
    		for (int i = 0; i < yValue.size(); i++) {
    			
    			yValueQuery += yValue.get(i) + ", ";
    			
    		}
    		
    		yValueQuery = yValueQuery.substring(0, yValueQuery.length() - 2);
    		yValueQuery = "[" + yValueQuery + "]";
    		
    		
    		return yValueQuery;
    		
    	}	
    
    public String barColors(int[] xValue) {
    	
    	String color = "blue";
    	String query = "";
		for (int i = 0; i < xValue.length; i++) {
			
			query += "\"" + color + "\"" + ", ";
	
		}
		query = query.substring(0, query.length() - 2);
		query = "[" + query + "]";
    	
    	return query;
    }
    
    public String barColors(ArrayList<String> xValue) {
    	
    	String color = "blue";
    	String query = "";
		for (int i = 0; i < xValue.size(); i++) {
			
			query += "\"" + color + "\"" + ", ";
	
		}
		query = query.substring(0, query.length() - 2);
		query = "[" + query + "]";
    	
    	return query;
    }
    
    
   public String barMultiColors(ArrayList<String> xValue) {
    	
    	String[] color = {"blue", "orange", "pink", "broun", "red", "green", "grey", "yellow", "black", "purple", "violet", "gold", "silver"};
    	String query = "";
		for (int i = 0; i < xValue.size(); i++) {
			
			query += "\"" + color[i] + "\"" + ", ";
	
		}
		query = query.substring(0, query.length() - 2);
		query = "[" + query + "]";
    	
    	return query;
    }
}
