package com.mdrain.objects;

import java.time.LocalDate;

public class Absence {

	
	String operatorName;
	LocalDate absenceDate;
	double absenceHours;
	int holidayFromHours;
	String user;
	LocalDate inputDate;
	
	public Absence(String operatorName, LocalDate absenceDate, double absenceHours, int holidayFromHours, String user,
			LocalDate inputDate) {
		this.operatorName = operatorName;
		this.absenceDate = absenceDate;
		this.absenceHours = absenceHours;
		this.holidayFromHours = holidayFromHours;
		this.user = user;
		this.inputDate = inputDate;
	}
	
	public Absence() {
		
	}
	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	
	public LocalDate getAbsenceDate() {
		return absenceDate;
	}
	public void setAbsenceDate(LocalDate absenceDate) {
		this.absenceDate = absenceDate;
	}
	
	
	public double getAbsenceHours() {
		return absenceHours;
	}
	public void setAbsenceHours(double absenceHours) {
		this.absenceHours = absenceHours;
	}
	
	
	public int getHolidayFromHours() {
		return holidayFromHours;
	}
	public void setHolidayFromHours(int holidayFromHours) {
		this.holidayFromHours = holidayFromHours;
	}
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	public LocalDate getInputDate() {
		return inputDate;
	}
	public void setInputDate(LocalDate inputDate) {
		this.inputDate = inputDate;
	}
	
}
