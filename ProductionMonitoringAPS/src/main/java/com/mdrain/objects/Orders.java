package com.mdrain.objects;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import com.mdrain.logic.Date;

public class Orders {

	int number;
	int quantity;
	int delQuantity;
	int numberOfDaysForProduction;
	int week;
	int month;
	int dayOfYear;
	int increment = 1;
	int numberOfOperatorsForOrder;
    long daysBetwenDates;	
	double orderTimePerDay;
	double timeForOnePc;
	double totalTimeForOrderInHours;
	LocalDate startDate;
	LocalDate endDate;
	LocalDate exportDate;
	LocalDate nextDay;
	ArrayList<LocalDate> productionsDatesCollection = new ArrayList<LocalDate>();
	ArrayList<Integer> productionsWeeksCollection = new ArrayList<Integer>();
	ArrayList<Integer> productionsMonthsCollection = new ArrayList<Integer>();
	ArrayList<Integer> productionsYearsCollection = new ArrayList<Integer>();
	String status;
	String materialNumber;
	String materialDescription;
	String customer;
	String productionLine;
	String teamLeader;
	
	DayOfWeek dayOfWeek;
	
	
	public Orders(int number, LocalDate startDate, LocalDate endDate, String status, int quantity,
			String materialNumber, String materialDescription, LocalDate exportDate, String customer,
			String productionLine) {
		super();
		this.number = number;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.quantity = quantity;
		this.materialNumber = materialNumber;
		this.materialDescription = materialDescription;
		this.exportDate = exportDate;
		this.customer = customer;
		this.productionLine = productionLine;
	}
	public Orders() {
		
	}
	

	
	
	public String getTeamLeader() {
		return teamLeader;
	}
	
	
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
		
		
	}
	public int getDelQuantity() {
		return delQuantity;
	}
	public void setDelQuantity(int delQuantity) {
		this.delQuantity = delQuantity;
	}
	public int getNumberOfOperatorsForOrder() {
		return numberOfOperatorsForOrder;
	}
	public void setNumberOfOperatorsForOrder(int numberOfOperatorsForOrder) {
		this.numberOfOperatorsForOrder = numberOfOperatorsForOrder;
	}
	
	
	public ArrayList<Integer> getProductionsMonthsCollection() {
		return productionsMonthsCollection;
	}
	public void setProductionsMonthsCollection() {
		
          for (int i = 0; i < this.getProductionsDatesCollection().size(); i++) {
			
			int month = this.getProductionsDatesCollection().get(i).getMonthValue();
			this.productionsMonthsCollection.add(i, month);
		}
		
	}
	
	
	
	public ArrayList<Integer> getProductionsYearsCollection() {
		return productionsYearsCollection;
	}
	public void setProductionsYearsCollection() {
		
		for (int i = 0; i < this.getProductionsDatesCollection().size(); i++) {
			
			int year = this.getProductionsDatesCollection().get(i).getYear();
			this.productionsYearsCollection.add(i, year);
		}
		
	}
	
	
	
	public ArrayList<Integer> getProductionsWeeksCollection() {
		return productionsWeeksCollection;
	}
	public void setProductionsWeeksCollection() {
		
		WeekFields week = WeekFields.of(Locale.getDefault());

		for (int i = 0; i < this.getProductionsDatesCollection().size(); i++) {
			
			LocalDate date = this.getProductionsDatesCollection().get(i);
			int weekNumber = date.get(week.weekOfWeekBasedYear());
			
			this.productionsWeeksCollection.add(i, weekNumber);;
		}
		
	}
	
	
	public ArrayList<LocalDate> getProductionsDatesCollection() {
		
		return productionsDatesCollection;
	}
	
	public void setProductionsDatesCollection(ArrayList<LocalDate> yearHolidaysCollection) throws SQLException {
		
        this.setDaysBetwenDates(this.startDate, this.endDate);
		ArrayList<String> holidays = Date.takeHolidayCollection(this.startDate, this.endDate, yearHolidaysCollection);
  	
        int k = 1;
		for (int i = 0; i < this.getDaysBetwenDates(); i++) {		
			nextDay = startDate.plusDays((increment++) - 1);
	
			
			for (int j = k - 1; j < holidays.size(); j++) {
		
				if (!holidays.get(j).equals("п")) {			
					this.productionsDatesCollection.add(nextDay);
					k++;
					break;
				} else {
					k++;
					break;
				}
			}		
			
		}	
	}
	
	public long getDaysBetwenDates() {

		return daysBetwenDates;
	}
	
	public void setDaysBetwenDates(LocalDate startDate, LocalDate endDate) {
		
		this.daysBetwenDates = ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}
	
	
	public int getNumberOfDaysForProduction() {
		return numberOfDaysForProduction;
	}
	public void setNumberOfDaysForProduction() {

		this.numberOfDaysForProduction = this.getProductionsDatesCollection().size();
	
		if (this.startDate.equals(this.endDate)) {
			this.numberOfDaysForProduction = 1;
		}	
	}
	
	
	public double getTotalTimeForOrderInHours() {
		
		this.totalTimeForOrderInHours = (this.getTimeForOnePc() * this.getQuantity()) / 60;
		return this.totalTimeForOrderInHours;
	}

	
	public double getTimeForOnePc() {
		return timeForOnePc;
	}
	public void setTimeForOnePc(double timeForOnePc) {
		this.timeForOnePc = timeForOnePc;
	}

	
	public double getOrderTimePerDay() {
		return orderTimePerDay;
	}
	public void setOrderTimePerDay() {

		this.orderTimePerDay = this.getTotalTimeForOrderInHours() / this.getNumberOfDaysForProduction();
	}
	
	
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	
	
	public int getDayOfYear() {
		return dayOfYear;
	}
	public void setDayOfYear(int dayOfYear) {
		this.dayOfYear = dayOfYear;
	}
	
	
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	
	
	public String getMaterialDescription() {
		return materialDescription;
	}
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	
	
	public LocalDate getExportDate() {
		return exportDate;
	}
	public void setExportDate(LocalDate exportDate) {
		this.exportDate = exportDate;
	}
	
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
	public String getProductionLine() {
		return productionLine;
	}
	public void setProductionLine(String productionLine) {
		
	    this.productionLine = productionLine;
		
	}
	

	
}
