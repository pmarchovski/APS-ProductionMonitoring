package com.mdrain.objects;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mdrain.logic.Date;

public class SerialNumber {

	
	String orderNumber;
	int orderQuantity;
	String materialNumber;
	String matetialDescription;
	String serialNumberDate;
	ArrayList<String> serialNumber;

	public SerialNumber(String orderNumber, String materialNumber, String matetialDescription, ArrayList<String> serialNumber) {

		this.orderNumber = orderNumber;
		this.materialNumber = materialNumber;
		this.matetialDescription = matetialDescription;
		this.serialNumber = serialNumber;
	}
	
	public SerialNumber() {

	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	

	public String getSerialNumberDate() {
		return serialNumberDate;
	}

	public void setSerialNumberDate(LocalDate startDate) {
		
		String dateString    = Date.convertDateNew(startDate);
	    String newDateString = "";		
	    String[] dateArray   =  dateString.split("-");
	    dateArray[0]         = dateArray[0].substring(2, 4);
	      
	      for (int i = dateArray.length - 1; i >= 0; i--) {
	    	  
	    	  newDateString += dateArray[i]; 	  
	      }
		this.serialNumberDate = newDateString;
	}
	

	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getMatetialDescription() {
		return matetialDescription;
	}
	public void setMatetialDescription(String matetialDescription) {
		
		String shortDescription = matetialDescription.substring(0, 13);
		
		this.matetialDescription = shortDescription;
	}
	
	
	public ArrayList<String> getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber() {
		
		  ArrayList<String> serialNumberCollection = new ArrayList<String>();
		  int count = this.orderQuantity;
	      int serialNumber = 0;
	      String padded = "";
	  
	      for (int i = 0; i < count; i++) {
	    	  
	    	  serialNumber = i + 1;
	    	  
	    	  padded = String.format("%05d" , serialNumber);
	    	  
	    	  serialNumberCollection.add(this.serialNumberDate + this.orderNumber + padded);
	    	  
	      }  

		this.serialNumber = serialNumberCollection;
	}
	
	
}
