package com.mdrain.objects;

import java.time.LocalDate;

public class ProductionReportTime {

	LocalDate date;
	double prductionTime;
	
	
	public ProductionReportTime(LocalDate date, double prductionTime) {
		this.date = date;
		this.prductionTime = prductionTime;
	}

	
	public ProductionReportTime() {
		
	}

	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public double getPrductionTime() {
		return prductionTime;
	}


	public void setPrductionTime(double prductionTime) {
		this.prductionTime = prductionTime;
	}
	
	
}
