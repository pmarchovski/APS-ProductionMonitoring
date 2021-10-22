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
	String tdClassD = "<td Class=\"d\">";
	String tdClassE = "<td Class=\"e\">";
	String tdClassF = "<td Class=\"f\">";
	String tdClassJ = "<td Class=\"j\">";
	String tdClassZ = "<td Class=\"z\">";
	String tdClassP = "<td Class=\"p\">";
	String tdEnd = "</td>";
	String th = "<th>";
	String thClassB = "<th Class=\"b\">";
	String thClassF = "<th Class=\"f\">";
	String thEnd = "</th>";
	String finalTableBody = "";
	String holidayData = "";
	String finLast = "";
	String operatorName = "";
	String teamLeaderName = "";
	String span = "<span>";
	String spanEnd = "</span>";

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
	
	
	public String createTableHeadVerticalTextMatrix(ArrayList<String> tableFiledCollection) {

		for (String element : tableFiledCollection) {
			tableHead += thClassB + element + thEnd;
		}

		String firstCell = th + "Трите имена на оператор" + thEnd;
		String finalTableHead = firstCell + tableHead;
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

	public String createTableBodyStringObject(ArrayList<String> numberColumnCollection,
			ArrayList<Object> dataCollection) {

		String firstRow = "";
		String lastRow = "";
		int next = 0;

		if (dataCollection.size() == numberColumnCollection.size()) {

			for (i = 0; i < 1; i++) {

				if (!firstRow.equals("")) {
					lastRow += tr + firstRow + trEnd;
					firstRow = "";
				}

				int until = numberColumnCollection.size() + next;

				for (j = next; j < until; j++) {

					firstRow += td + dataCollection.get(j) + tdEnd;
					lastRow = tr + firstRow + trEnd;

					next++;
				}

			}

		} else {

			for (i = 0; i < dataCollection.size() / numberColumnCollection.size() + 1; i++) {

				if (!firstRow.equals("")) {
					lastRow += tr + firstRow + trEnd;
					firstRow = "";
				}

				if (i < dataCollection.size() / numberColumnCollection.size()) {

					int until = numberColumnCollection.size() + next;

					for (j = next; j < until; j++) {

						firstRow += td + dataCollection.get(j) + tdEnd;
						next++;
					}
				}
			}
		}

		return lastRow;
	}

	public String createTableBodyNew(ArrayList<Operators> numberRowsCollection, ArrayList<String> datacollection) {

		for (j = 0; j < numberRowsCollection.size() + 1; j++) {

			finLast = tr + operatorName + holidayData + teamLeaderName + trEnd;
			finalTableBody += finLast;

			if (j < numberRowsCollection.size()) {

				operatorName = tdClassB + numberRowsCollection.get(j).getFullName() + tdEnd;
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

	public String createTableClassB(ArrayList<String> fieldsCollection, ArrayList<String> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.size(); i++) {
			tableQuery += tr + tdClassB + fieldsCollection.get(i) + tdEnd + tdClassB + dataCollection.get(i) + tdEnd
					+ trEnd;
		}
		return tableQuery;
	}

	public String createTableClassZ(ArrayList<String> fieldsCollection, ArrayList<String> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.size(); i++) {
			tableQuery += tr + tdClassZ + fieldsCollection.get(i) + tdEnd + tdClassZ + dataCollection.get(i) + tdEnd
					+ trEnd;
		}
		return tableQuery;
	}

	public String createTableInteger(ArrayList<String> fieldsCollection, ArrayList<Integer> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.size(); i++) {
			tableQuery += tr + tdClassE + fieldsCollection.get(i) + tdEnd + tdClassE + dataCollection.get(i) + tdEnd
					+ trEnd;
		}
		return tableQuery;
	}

	public String createTableInteger(int[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.length; i++) {
			tableQuery += tr + td + dataCollection[i] + tdEnd + trEnd;
		}
		return tableQuery;
	}

	public String createTableString(String[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.length; i++) {
			tableQuery += tr + tdClassF + dataCollection[i] + tdEnd + trEnd;
		}
		return tableQuery;
	}

	public String createTableString(ArrayList<String> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.size(); i++) {
			tableQuery += tdClassJ + dataCollection.get(i) + tdEnd;
		}

		tableQuery = tr + tableQuery + trEnd;

		return tableQuery;
	}

	public String createTableObject(ArrayList<Object> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.size(); i++) {
			tableQuery += tdClassJ + dataCollection.get(i) + tdEnd;
		}

		tableQuery = tr + tableQuery + trEnd;

		return tableQuery;
	}

	public String createTableStringWithoutClass(String[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.length; i++) {
			tableQuery += tr + td + dataCollection[i] + tdEnd + trEnd;
		}
		return tableQuery;
	}

	public String createTablesSkillsMatrix(ArrayList<String> firstColumn, ArrayList<String> nextColumns, int typeSize) {

		String queryOperatorName = "";
		String queryNextColumn = "";
		String queryNextColumnNewRow = "";
		int next = 0;
		int nextOperator = 0;
		int j = 0;

		

			for (int i = 0; i < firstColumn.size(); i++) {

				
				for (int h = nextOperator; h <= nextOperator; h++) {
			    queryOperatorName = td + firstColumn.get(h) + tdEnd;
				}
				
				queryNextColumn = "";

				for (j = next; j < (typeSize + next); j++) {

					queryNextColumn += td + nextColumns.get(j) + tdEnd;
				}

				next = j;
				nextOperator++;
				queryNextColumnNewRow += tr + queryOperatorName + queryNextColumn + trEnd;

				
		}
		return queryNextColumnNewRow;
	}

}
