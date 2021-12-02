package com.mdrain.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.singletons.Singleton;

public class Date {

	
	public static ArrayList<LocalDate> publicHolidaysCollection() throws SQLException{
		
		DataBaseActivities dbActivities               = Singleton.getInstance();
		ArrayList<LocalDate> publicHolidaysCollection = new ArrayList<LocalDate>();
		LocalDate publicHolidays;
		String table                                  = "tb_public_holidays";
		ResultSet result                              = null;
		String date                                   = "";
		
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
		String empty   = "";

		long daysBetwenDates;

		int increment = 1;

		startDate       = date(sDate);
		endDate         = date(eDate);
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
		String empty    = "";

		long daysBetwenDates;

		int increment = 1;

		startDate       = date(sDate);
		endDate         = date(eDate);
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
	

		if (dateString != null) {
			
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
		} else {
			return LocalDate.of(2021, 1, 1);
		}
		
		
		
	}

	private static Month month(String month) {

		int monthInt   = Integer.parseInt(month);

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
	
	public static String convertDate(Object dateWebApp) {
	
		
		String newDate = "";
    	String date = String.valueOf(dateWebApp);
    	String[] elementCollection = date.split("-");
    	String[] newElementCollection = new String[elementCollection.length];
    	for (int i = 1; i <= elementCollection.length; i++) {
    		newElementCollection[i - 1] = elementCollection[elementCollection.length - i];
    		newDate = newDate + newElementCollection[i - 1] + ".";
    	}
    	
    	newDate = newDate.substring(0, date.length());
		
		return newDate;
	}
	
	public static String convertDateNew(Object dateWebApp) {
	
		
		String newDate = "";
    	String date = String.valueOf(dateWebApp);
    	String[] elementCollection = date.split("-");
    	String[] newElementCollection = new String[elementCollection.length];
    	for (int i = 1; i <= elementCollection.length; i++) {
    		newElementCollection[i - 1] = elementCollection[i - 1];
    		newDate = newDate + newElementCollection[i - 1] + "-";
    	}
    	
    	newDate = newDate.substring(0, date.length());
		
		return newDate;
	}
	
	public static long getDaysBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
		
		long daysBetwenDates;
		daysBetwenDates = ChronoUnit.DAYS.between(startDate, endDate) + 1;
		
		return daysBetwenDates;
	}
	
	public static String convertTime(String time) {
		
		String localTime = String.valueOf(time);
		String localTimeWoHour = localTime.substring(2, 7);
		int hour = Integer.parseInt(localTime.substring(0, 1));
//		hour = hour + 6;
		String localFinaTime = String.valueOf(hour) + localTimeWoHour;
		
		
		return localFinaTime;
	}
	
	public static int[] convertNumberWeek(String weekFromWebApp) {
		
		int year = 0;
		int week = 0;
		
		if (weekFromWebApp.length() == 0) {
			year = 9999;
			week = 99;
		} else {		
			year = Integer.parseInt(weekFromWebApp.substring(0, 4));
		    week = Integer.parseInt(weekFromWebApp.substring(6, 8));
		}

		int numberWeek[] = {year, week};
		
		return numberWeek;
	}
	
	public static int[] convertNumberMonth(String monthFromWebApp) {
		
		int year = 0;
		int month = 0;
		
		if (monthFromWebApp.length() == 0) {
			year  = 9999;
			month = 99;
		} else {		
			year  = Integer.parseInt(monthFromWebApp.substring(0, 4));
			month = Integer.parseInt(monthFromWebApp.substring(5, 7));
		}

		int numberMonth[] = {year, month};
		
		return numberMonth;
	}
	
	public static int getWeekNumberFromCurrentDate(LocalDate date) {
		WeekFields week = WeekFields.of(Locale.getDefault());		
		int weekNumber  = date.get(week.weekOfWeekBasedYear());
		
		return weekNumber;
	}

}
