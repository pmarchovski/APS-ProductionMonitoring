package com.mdrain.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.servlets.ProductionCapacityServlet;

public class Date {

	
	public static ArrayList<LocalDate> publicHolidaysCollection() throws SQLException{
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<LocalDate> publicHolidaysCollection = new ArrayList<LocalDate>();
		LocalDate publicHolidays;
		String table = "tb_public_holidays";
		ResultSet result = null;
		String date = "";
		
		result = dbActivities.select(table);
		
		while (result.next()) {		
			date = result.getString("tb_public_holidays_date");	
            publicHolidaysCollection.add(publicHolidays = date(date));
		}
		
		
		
		return publicHolidaysCollection;
	}
	
	
	
	
	public static ArrayList<String> takeHolidayCollection(String sDate, String eDate) throws SQLException {

		ArrayList<String> holidayCollection = new ArrayList<String>();
		ArrayList<LocalDate> yearHolidaysCollection = new ArrayList<LocalDate>();

		
		LocalDate startDate;
		LocalDate endDate;
		LocalDate nextDay = null;

		yearHolidaysCollection = publicHolidaysCollection();

		String holiday = "";
		String empty = "";

		long daysBetwenDates;

		int increment = 1;

		startDate = date(sDate);
		endDate = date(eDate);
		daysBetwenDates = ChronoUnit.DAYS.between(startDate, endDate) + 1;

		holidayCollection.add(empty);

		for (int i = 0; i < daysBetwenDates; i++) {

			nextDay = startDate.plusDays((increment++) - 1);

			DayOfWeek dayOfWeek = nextDay.getDayOfWeek();
			if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {

				holiday = "п";
				holidayCollection.add(holiday);

			} else {

				boolean isHoliday = false;

				for (int j = 0; j < yearHolidaysCollection.size(); j++) {
					isHoliday = nextDay.equals(yearHolidaysCollection.get(j));
					if (isHoliday) {
						break;
					}
				}
				if (isHoliday) {
					holiday = "п";
				} else {

					holiday = " ";
				}
				holidayCollection.add(holiday);
			}
		}

		
		return holidayCollection;
	}

	public static ArrayList<String> takeWeekDayCollection(String sDate, String eDate) {

		ArrayList<String> weekDayCollection = new ArrayList<String>();
		LocalDate startDate;
		LocalDate endDate;
		LocalDate nextDay;
		String empty = "";

		String takeWeekDay = "";

		long daysBetwenDates;

		int increment = 1;

		startDate = date(sDate);
		endDate = date(eDate);
		daysBetwenDates = ChronoUnit.DAYS.between(startDate, endDate) + 1;

		weekDayCollection.add(empty);

		for (int i = 0; i < daysBetwenDates; i++) {

			nextDay = startDate.plusDays((increment++) - 1);
			DayOfWeek dayOfWeek = nextDay.getDayOfWeek();
			takeWeekDay = dayOfWeek + "";
			weekDayCollection.add(takeWeekDay);

		}
		weekDayCollection.add(empty);
		weekDayCollection = dataCollectionTranslated(weekDayCollection);

		return weekDayCollection;
	}

	public static ArrayList<String> takeDateCollection(String sDate, String eDate) {

		ArrayList<String> dateCollection = new ArrayList<String>();

		LocalDate startDate;
		LocalDate endDate;
		LocalDate nextDay;

		String takeDate = "";
		String empty = "";

		long daysBetwenDates;

		int increment = 1;

		startDate = date(sDate);
		endDate = date(eDate);
		daysBetwenDates = ChronoUnit.DAYS.between(startDate, endDate) + 1;

		String month = String.valueOf(startDate.getMonth());

		dateCollection.add("Присъствена форма за месец " + month);

		for (int i = 0; i < daysBetwenDates; i++) {

			nextDay = startDate.plusDays((increment++) - 1);
			takeDate = (nextDay.getDayOfMonth() + "");
			dateCollection.add(takeDate);

		}

		dateCollection.add("Тийм лидер");

		return dateCollection;
	}

	public static LocalDate date(String dateString) {

		String[] dateContainCollection = null;

		if (dateString.contains(".")) {
			dateString = dateString.replace(".", "/");
		}

		if (dateString.contains("/"))
			dateContainCollection = dateString.split("/");
		if (dateString.contains("."))
			dateContainCollection = dateString.split(".");
		if (dateString.contains(","))
			dateContainCollection = dateString.split(",");
		if (dateString.contains("-"))
			dateContainCollection = dateString.split("-");

		int day = Integer.parseInt(dateContainCollection[2]);
		Month month = month(dateContainCollection[1]);
		int year = Integer.parseInt(dateContainCollection[0]);

		LocalDate date = LocalDate.of(year, month, day);
		return date;
	}

	private static Month month(String month) {

		int monthInt = Integer.parseInt(month);

		Month[] month1 = Month.values();

		for (int i = 0; i < month1.length; i++) {
			if (monthInt == i + 1) {
				return month1[i];
			}
		}
		return null;
	}

	private static ArrayList<String> dataCollectionTranslated(ArrayList<String> dateCollection) {

		for (int i = 0; i < dateCollection.size(); i++) {

			if (dateCollection.get(i).contains("MONDAY")) {
				dateCollection.set(i, "Понеделник");
			}
			if (dateCollection.get(i).contains("TUESDAY")) {
				dateCollection.set(i, "Вторник");
			}
			if (dateCollection.get(i).contains("WEDNESDAY")) {
				dateCollection.set(i, "Сряда");
			}
			if (dateCollection.get(i).contains("THURSDAY")) {
				dateCollection.set(i, "Четвъртък");
			}
			if (dateCollection.get(i).contains("FRIDAY")) {
				dateCollection.set(i, "Петък");
			}
			if (dateCollection.get(i).contains("SATURDAY")) {
				dateCollection.set(i, "Събота");
			}
			if (dateCollection.get(i).contains("SUNDAY")) {
				dateCollection.set(i, "Неделя");
			}

		}

		ArrayList<String> dataCollectionTranslated = dateCollection;

		return dataCollectionTranslated;
	}
	
	
	public static ArrayList<String> takeHolidayCollection(LocalDate sDate, LocalDate eDate,  ArrayList<LocalDate> yearHolidaysCollection) throws SQLException {

		ArrayList<String> holidayCollection = new ArrayList<String>();
//		ArrayList<LocalDate> yearHolidaysCollection = new ArrayList<LocalDate>();

		
		LocalDate startDate = sDate;
		LocalDate endDate = eDate;
		LocalDate nextDay = null;

//		yearHolidaysCollection = publicHolidaysCollection();
		

		String holiday = "";
		String empty = "";

		long daysBetwenDates;

		int increment = 1;

		daysBetwenDates = ChronoUnit.DAYS.between(startDate, endDate) + 1;

//		holidayCollection.add(empty);

		for (int i = 0; i < daysBetwenDates; i++) {

			nextDay = startDate.plusDays((increment++) - 1);
			
			DayOfWeek dayOfWeek = nextDay.getDayOfWeek();
			if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {

				holiday = "п";
				holidayCollection.add(holiday);

			} else {

				boolean isHoliday = false;

				for (int j = 0; j < yearHolidaysCollection.size(); j++) {
					isHoliday = nextDay.equals(yearHolidaysCollection.get(j));
					if (isHoliday) {
						break;
					}
				}
				if (isHoliday) {
					holiday = "п";
				} else {

					holiday = " ";
				}
				holidayCollection.add(holiday);
			}
		}

		
		return holidayCollection;
	}


	public static ArrayList<LocalDate> takeWorkDaysCollection(int year, ArrayList<LocalDate> yearHolidaysCollection) throws SQLException {

		ArrayList<LocalDate> workDaysCollection = new ArrayList<LocalDate>();
//		ArrayList<LocalDate> yearHolidaysCollection = new ArrayList<LocalDate>();

		
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year, 12, 31);;
		LocalDate nextDay = null;

//		yearHolidaysCollection = publicHolidaysCollection();
		

		LocalDate workDay = null;
		String empty = "";

		long daysBetwenDates;

		
		int increment = 1;

		daysBetwenDates = ChronoUnit.DAYS.between(startDate, endDate) + 1;

	//	workDaysCollection.add(LocalDate.of(0000, 00, 00));

		for (int i = 0; i < daysBetwenDates; i++) {

			int check = 1;
			nextDay = startDate.plusDays((increment++) - 1);

			DayOfWeek dayOfWeek = nextDay.getDayOfWeek();
			
			if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {

				check = 0;

			} 

			if (check == 1) {
				
				for (int j = 0; j < yearHolidaysCollection.size(); j++) {
					
					if (nextDay.equals(yearHolidaysCollection.get(j))) {
						check = 0;	
						break;
				} 
			}
				
			if (check == 1) {
				
				workDaysCollection.add(nextDay);	
			}

			}
		}
		return workDaysCollection;
		
	}
	
	public static void availableTimePerDate() {
	
		
		
	}
}
