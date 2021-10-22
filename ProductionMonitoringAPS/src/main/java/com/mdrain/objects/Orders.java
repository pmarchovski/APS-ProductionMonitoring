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
	double reportedTimeFromProductionCards;
	long daysBetwenDates;
	double orderTimePerDay;
	double timeForOnePc;
	double totalTimeForOrderInHours;
	LocalDate startDate;
	LocalDate endDate;
	LocalDate exportDate;
	LocalDate nextDay;
	LocalDate reportedDate;
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
	String productType;
	String workCenter;
	final String AL31_TEAM_LEADER = "Ренета Кондарева";
	final String AL32_TEAM_LEADER = "Валя Колева";
	final String BR21_TEAM_LEADER = "Румяна Димитрова";
	final String BR22_TEAM_LEADER = "Петър Гацовски";

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

	public LocalDate getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(LocalDate reportedDate) {
		this.reportedDate = reportedDate;
	}

	public double getReportedTimeFromProductionCards() {
		return reportedTimeFromProductionCards;
	}

	public void setReportedTimeFromProductionCards(double reportedTimeFromProductionCards) {
		this.reportedTimeFromProductionCards = reportedTimeFromProductionCards;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(ArrayList<Materials> getMaterialsInfoCollection, Orders order) {

		for (int i = 0; i < getMaterialsInfoCollection.size(); i++) {

			Materials tempMaterialInfo = getMaterialsInfoCollection.get(i);

			if (order.getMaterialNumber().equals(tempMaterialInfo.getMaterialNumber())) {
				this.productType = (tempMaterialInfo.getMaterialGroup());
			}
		}

		if (order.getProductType() == null || order.getProductType().equals("")) {
			
			this.productType = ("No type!!!");
		}

	}

	public String getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(String productionLine) {
		
		if (productionLine.equals("AL31")) this.teamLeader = AL31_TEAM_LEADER;
		if (productionLine.equals("AL32")) this.teamLeader = AL32_TEAM_LEADER;
		if (productionLine.equals("BR21")) this.teamLeader = BR21_TEAM_LEADER;
		if (productionLine.equals("BR22")) this.teamLeader = BR22_TEAM_LEADER;
		
		if (!productionLine.equals("BR22") 
				&& !productionLine.equals("BR21") 
				&& !productionLine.equals("AL32")
				&& !productionLine.equals("AL31")) this.teamLeader = "";
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

			this.productionsWeeksCollection.add(i, weekNumber);
			;
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

		if (status.contains("TECO"))
			this.status = "TECO";
		if (status.contains("DLFL") || status.contains("DELETED"))
			this.status = "DELETED";
		if (status.contains("REL") || status.contains("RELEASED"))
			this.status = "RELEASED";
		if (status.contains("CRTD") || status.contains("CREATED"))
			this.status = "CREATED";

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

	public void setProductionLine(ArrayList<Orders> cooisOperationDataCollection, Orders order) {

		for (int i = 0; i < cooisOperationDataCollection.size(); i++) {

			Orders tempOrder = (Orders) cooisOperationDataCollection.get(i);

			if (order.getNumber() == tempOrder.getNumber()) {

				if (!tempOrder.getWorkCenter().equals("AUD0001") && !tempOrder.getWorkCenter().equals("IND0001")
						&& !tempOrder.getWorkCenter().equals("IND0002") && !tempOrder.getWorkCenter().equals("IND0003")
						&& !tempOrder.getWorkCenter().equals("IND0004") && !tempOrder.getWorkCenter().equals("IND0005")
						&& !tempOrder.getWorkCenter().equals("AUD0001")) {
					this.productionLine = "";
				} else {

					if (tempOrder.getWorkCenter().equals("AUD0001"))
						this.productionLine = "BR21";
					if (tempOrder.getWorkCenter().equals("IND0001"))
						this.productionLine = "AL31";
					if (tempOrder.getWorkCenter().equals("IND0003"))
						this.productionLine = "AL31";
					if (tempOrder.getWorkCenter().equals("IND0004"))
						this.productionLine = "AL32";
					if (tempOrder.getWorkCenter().equals("IND0005"))
						this.productionLine = "AL32";
					if (tempOrder.getWorkCenter().equals("IND0002"))
						this.productionLine = "BR22";
				}

			}
		}

	}

	public String getWorkCenter() {
		return workCenter;
	}

	public void setWorkCenter(String workCenter) {
		this.workCenter = workCenter;
	}

}
