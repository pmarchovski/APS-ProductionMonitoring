package com.mdrain.logic;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class FormatNumbers {

	
	public static String getFormatedNumbers(int value) {
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        formatSymbols.setDecimalSeparator('.');
        formatSymbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", formatSymbols);
   
    
        return (formatter.format(value));
		
	}
	
   public static String getFormatedNumbers(double value) {
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        formatSymbols.setDecimalSeparator('.');
        formatSymbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", formatSymbols);
   
    
        return (formatter.format(value));
		
	}
   
   public static String getFormatedNumbers(long value) {
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
       formatSymbols.setDecimalSeparator('.');
       formatSymbols.setGroupingSeparator(' ');
       DecimalFormat formatter = new DecimalFormat("###,###.##", formatSymbols);
  
   
       return (formatter.format(value));
		
	}
   
   public static String getFormatedNumbers(Object value) {
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
       formatSymbols.setDecimalSeparator('.');
       formatSymbols.setGroupingSeparator(' ');
       DecimalFormat formatter = new DecimalFormat("###,###.##", formatSymbols);
  
   
       return (formatter.format(value));
		
	}
   
   public static ArrayList<Object> getFormatedNumbersArrays(ArrayList<Object> valueCollection) {
		
	  ArrayList<Object> newValueCollection = new ArrayList<Object>(); 
	  DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
      formatSymbols.setDecimalSeparator('.');
      formatSymbols.setGroupingSeparator(' ');
      DecimalFormat formatter = new DecimalFormat("###,###.##", formatSymbols);
 
      for(int i = 0; i < valueCollection.size(); i++) {
    	  
    	  if (valueCollection.get(i) instanceof String) {
    		  newValueCollection.add(valueCollection.get(i));
    	  } else {
    		  newValueCollection.add(formatter.format(valueCollection.get(i)));
    	  }
    	  
    	  
      }
      
  
      return newValueCollection;
		
	}
	
}
