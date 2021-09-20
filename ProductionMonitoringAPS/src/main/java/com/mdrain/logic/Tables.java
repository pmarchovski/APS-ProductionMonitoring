package com.mdrain.logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Operators;

public class Tables {
	
	String tableHead = "";
	String tableData = "";
	String tr = "<tr>";
	String trEnd = "</tr>";
	String td = "<td>";
	String tdClassA = "<td Class=\"a\">";
	String tdClassB = "<td Class=\"b\">";
	String tdClassE = "<td Class=\"e\">";
	String tdEnd = "</td>";
	String th = "<th>";
	String thClassB = "<th Class=\"b\">";
	String thEnd = "</th>";
	String finalTableBody = "";
	String holidayData = "";
	String finLast = "";
	String operatorName = "";
	String teamLeaderName = "";
	
    int i = 0;
		int next = 0;
		int j = 0;

	public static void createTable() {
		
	
	}
	
	
	public String createTableHeadVerticalText(ArrayList<String> tableFiledCollection) {
		
		for (String element : tableFiledCollection) {	
			tableHead += thClassB + element + thEnd;
		}
		
		String finalTableHead = tableHead;
		return finalTableHead;
	}
	
	
	public String createTableHead(ArrayList<String> tableFiledCollection) {
		
		for (String element : tableFiledCollection) {	
			tableHead += th + element + thEnd;
		}
		
		String finalTableHead = tableHead;
		return finalTableHead;
	}
	
	
	public String createTableBody(ArrayList<Object> dataCollection) {
		
		for (Object element : dataCollection) {	
			tableData += td + element + tdEnd;
		}
		
		String finalTableData = tr + tableData + trEnd;
		return finalTableData;

	}
	
	
	
	public String createTableBody(ArrayList<Operators> numberRowsCollection, ArrayList<Object> dataCollection) {
			
		for (j = 0; j < numberRowsCollection.size() + 1; j++) {

			finLast = tr + holidayData + trEnd;

			if (!holidayData.equals("")) {

				next = i;
				finalTableBody += finLast;
				holidayData = "";
			}

			if (j < numberRowsCollection.size()) {

				for (i = next; i < (dataCollection.size() / numberRowsCollection.size()) + next; i++) {

					holidayData += td + dataCollection.get(i) + tdEnd;

				}
			}
		}	
		
		return finalTableBody;
	}
	
	public String createTableBodyNew(ArrayList<Operators> numberRowsCollection, ArrayList<String> datacollection) {
	
		
		for (j = 0; j < numberRowsCollection.size() + 1; j++) {

						    
				finLast = tr + operatorName + holidayData + teamLeaderName + trEnd;
			    finalTableBody += finLast;
			
			if (j < numberRowsCollection.size()) {
				
				operatorName =  tdClassB + numberRowsCollection.get(j).getFullName() + tdEnd;	
				teamLeaderName = tdClassB + numberRowsCollection.get(j).getTeamLeader() + tdEnd;
				holidayData = "";
			}
				
				for (i = 1; i < datacollection.size(); i++) {
					holidayData += td + datacollection.get(i) + tdEnd;
			}
			
	
		}	
		
		return finalTableBody;
	}
	
	public String createTable(ArrayList<String> fieldsCollection, ArrayList<String> dataCollection) {
		
		String tableQuery = "";	
		for (int i = 0; i < fieldsCollection.size(); i++) {		
			tableQuery += tr + td + fieldsCollection.get(i) + tdEnd + td + dataCollection.get(i) + tdEnd + trEnd;		
		}
		return tableQuery;
	}
	
	
	
	public String createTableInteger(ArrayList<String> fieldsCollection, ArrayList<Integer> dataCollection) {
		
		String tableQuery = "";	
		for (int i = 0; i < fieldsCollection.size(); i++) {		
			tableQuery += tr + tdClassB + fieldsCollection.get(i) + tdEnd + tdClassB + dataCollection.get(i) + tdEnd + trEnd;		
		}
		return tableQuery;
	}
}
