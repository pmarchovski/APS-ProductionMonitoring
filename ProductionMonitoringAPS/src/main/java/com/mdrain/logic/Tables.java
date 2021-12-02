package com.mdrain.logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Operators;

public class Tables {

	private  String tableHead = "";
	private  String tableData = "";
	private  String tr = "<tr>";
	private  String trClasswhite_row = "<tr Class=\"white_row\">";
	private  String trEnd = "</tr>";
	private  String td = "<td>";
	private  String tdClassA = "<td Class=\"a\">";
	private  String tdClassAA = "<td Class=\"aa\">";
	private  String tdClassB = "<td Class=\"b\">";
	private  String tdClassD = "<td Class=\"d\">";
	private  String tdClassE = "<td Class=\"e\">";
	private  String tdClassF = "<td Class=\"f\">";
	private  String tdClassJ = "<td Class=\"j\">";
	private  String tdClassZ = "<td Class=\"z\">";
	private  String tdClassP = "<td Class=\"p\">";
	private  String tdClassQ = "<td Class=\"q\">";
	private  String tdClassHead = "<td Class=\"head\">";
	private  String tableB = "<table Class=\"b\">";
	private  String tableEnd = "</table>";
	private  String tdEnd = "</td>";
	private  String th = "<th>";
	private  String thClassB = "<th Class=\"b\">";
	private  String thClassX = "<th Class=\"x\">";
	private  String thClassF = "<th Class=\"f\">";
	private  String thEnd = "</th>";
	private  String finalTableBody = "";
	private  String holidayData = "";
	private  String finLast = "";
	private  String operatorName = "";
	private  String teamLeaderName = "";
	private  String span = "<span>";
	private  String spanEnd = "</span>";
	private  String div = "<div class=\"handtekening borderLabel\">";
	private  String divEnd = "</div>";

	private  int i = 0;
	private  int next = 0;
	private  int j = 0;

	public  void createTable() {

	}

	public  String createTableHeadVerticalText(ArrayList<String> tableFiledCollection) {

		for (String element : tableFiledCollection) {
			tableHead += thClassB + element + thEnd;
		}

		String finalTableHead = tableHead;
		return finalTableHead;
	}
	
	
	public  String createTableHeadVerticalTextMatrix(ArrayList<String> tableFiledCollection) {

		for (String element : tableFiledCollection) {
			tableHead += thClassB + element + thEnd;
		}

		String firstCell = th + "Трите имена на оператор" + thEnd;
		String finalTableHead = firstCell + tableHead;
		return finalTableHead;
	}
	

	public  String createTableHead(ArrayList<String> tableFiledCollection) {

		for (String element : tableFiledCollection) {
			tableHead += tdClassHead + element + tdEnd;
		}

		String finalTableHead = tableHead;
		return finalTableHead;
	}
	

	public  String createTableHead(String[] tableFiledCollection) {

		for (String element : tableFiledCollection) {
			tableHead += tdClassHead + element + tdEnd;
		}

		String finalTableHead = tableHead;
		return finalTableHead;
	}
	
	public  String createTableHeadFreezHead(String[] tableFiledCollection) {

		for (String element : tableFiledCollection) {
			tableHead += th + element + thEnd;
		}

		String finalTableHead = tableHead;
		return finalTableHead;
	}

	public  String createTableBody(ArrayList<Object> dataCollection) {

		for (Object element : dataCollection) {
			tableData += td + element + tdEnd;
		}

		String finalTableData = tr + tableData + trEnd;
		return finalTableData;

	}

	public  String createTableBody(ArrayList<Operators> numberRowsCollection, ArrayList<Object> dataCollection) {

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

	public  String createTableBodyStringObject(ArrayList<String> numberColumnCollection,
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

					firstRow += tdClassAA + dataCollection.get(j) + tdEnd;
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
	
	
	public  String createTableBodyStringObject(String[] numberColumnCollection,
			ArrayList<Object> dataCollection) {

		String firstRow = "";
		String lastRow = "";
		int next = 0;

		if (dataCollection.size() == numberColumnCollection.length) {

			for (i = 0; i < 1; i++) {

				if (!firstRow.equals("")) {
					lastRow += tr + firstRow + trEnd;
					firstRow = "";
				}

				int until = numberColumnCollection.length + next;

				for (j = next; j < until; j++) {

					firstRow += tdClassAA + dataCollection.get(j) + tdEnd;
					lastRow = tr + firstRow + trEnd;

					next++;
				}

			}

		} else {

			for (i = 0; i < dataCollection.size() / numberColumnCollection.length + 1; i++) {

				if (!firstRow.equals("")) {
					lastRow += tr + firstRow + trEnd;
					firstRow = "";
				}

				if (i < dataCollection.size() / numberColumnCollection.length) {

					int until = numberColumnCollection.length + next;

					for (j = next; j < until; j++) {

						firstRow += tdClassAA + dataCollection.get(j) + tdEnd;
						next++;
					}
				}
			}
		}

		return lastRow;
	}

	public  String createTableBodyNew(ArrayList<Operators> numberRowsCollection, ArrayList<String> datacollection) {

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

	public  String createTable(ArrayList<String> fieldsCollection, ArrayList<String> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.size(); i++) {
			tableQuery += tr + tdClassF + fieldsCollection.get(i) + tdEnd + td + dataCollection.get(i) + tdEnd + trEnd;
		}
		return tableQuery;
	}
	
	public  String createTable(String[] fieldsCollection, ArrayList<Integer> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.length; i++) {
			tableQuery += tr + tdClassF + fieldsCollection[i] + tdEnd + td + dataCollection.get(i) + tdEnd + trEnd;
		}
		return tableQuery;
	}
	
	public  String createTable(String[] fieldsCollection, String[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.length; i++) {
			tableQuery += tr + tdClassF + fieldsCollection[i] + tdEnd + td + dataCollection[i] + tdEnd + trEnd;
		}
		return tableQuery;
	}

	public  String createTableClassB(ArrayList<String> fieldsCollection, ArrayList<String> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.size(); i++) {
			tableQuery += tr + tdClassB + fieldsCollection.get(i) + tdEnd + tdClassB + dataCollection.get(i) + tdEnd
					+ trEnd;
		}
		return tableQuery;
	}

	public  String createTableClassZ(ArrayList<String> fieldsCollection, ArrayList<String> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.size(); i++) {
			tableQuery += tr + tdClassZ + fieldsCollection.get(i) + tdEnd + tdClassZ + dataCollection.get(i) + tdEnd
					+ trEnd;
		}
		return tableQuery;
	}

	public  String createTableInteger(ArrayList<String> fieldsCollection, ArrayList<Integer> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < fieldsCollection.size(); i++) {
			tableQuery += tr + tdClassE + fieldsCollection.get(i) + tdEnd + tdClassE + dataCollection.get(i) + tdEnd
					+ trEnd;
		}
		return tableQuery;
	}

	public  String createTableInteger(int[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.length; i++) {
			tableQuery += tr + td + dataCollection[i] + tdEnd + trEnd;
		}
		return tableQuery;
	}

	public  String createTableString(String[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.length; i++) {
			tableQuery += tr + tdClassF + dataCollection[i] + tdEnd + trEnd;
		}
		return tableQuery;
	}

	public  String createTableString(ArrayList<String> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.size(); i++) {
			tableQuery += tdClassJ + dataCollection.get(i) + tdEnd;
		}

		tableQuery = tr + tableQuery + trEnd;

		return tableQuery;
	}
	
	public  String createTable(String[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.length; i++) {
			tableQuery += tdClassJ + dataCollection[i] + tdEnd;
		}

		tableQuery = tr + tableQuery + trEnd;

		return tableQuery;
	}

	public  String createTableObject(ArrayList<Object> dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.size(); i++) {
			tableQuery += tdClassJ + dataCollection.get(i) + tdEnd;
		}

		tableQuery = tr + tableQuery + trEnd;

		return tableQuery;
	}
	
	public  String createTableObject(Object[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.length; i++) {
			tableQuery += tdClassJ + dataCollection[i] + tdEnd;
		}

		tableQuery = tr + tableQuery + trEnd;

		return tableQuery;
	}

	public  String createTableStringWithoutClass(String[] dataCollection) {

		String tableQuery = "";
		for (int i = 0; i < dataCollection.length; i++) {
			tableQuery += tr + td + dataCollection[i] + tdEnd + trEnd;
		}
		return tableQuery;
	}

	public  String createTablesSkillsMatrix(ArrayList<String> firstColumn, ArrayList<String> nextColumns, int typeSize) {

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

	public  String createTableForTask(ArrayList<Object> dataInfo) {
		
		
		String selectQuery = "<select name=\"change_task_status\" class=\"select\" form=\"change_status_form\" onchange=\"document.status_change_form.submit();\">"
		+ "<option value=\"\">Промени статуса" + "</option>"
		+ "<option value=\"Closed\">Затвори задачата" + "</option>"
		+ "<option value=\"Active\">Отвори задачата" + "</option>"
		+ "</select>";
		
		String targetDateQuery = "<input type=\"date\""
				                  + "name=\"change_target_date\""
				                  + "class=\"select\""
				                  + "form=\"change_target_task_date_form\""
				                  + "onchange=\"document.change_target_date.submit();\">";
				
		
		ArrayList<String> labels = new ArrayList<String>();
		labels.add("Задача");
		labels.add("Пълно описание");
		labels.add("Отговорник");
		labels.add("Дата на създаване");
		labels.add("Краен срок за изпъление" 
		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
		+ "Change" + "&nbsp;&nbsp;&nbsp;" + targetDateQuery);
		labels.add("Екип");
		labels.add("Статус" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + selectQuery);
		labels.add("Коментари към задачата");
		
		
		
		String query = "";
		String labelQuery = "";
		String empty = "<br>";
		
		for (int i = 0;  i < dataInfo.size(); i++) {
			
			labelQuery = "<label>" + labels.get(i) + "</label>";
			
			query = query + tr + tdClassQ + div + labelQuery + empty + dataInfo.get(i) + divEnd + tdEnd + trEnd;
			
			
		}
		
		String commentField = "<label Class=\"label_form_comment\">Добавяне на коментар</label><br>"
				+ "<textarea rows = \"20\""
				+ "cols=\"60\""
				+ "name=\"add_comment\""
				+ "class=\"input_big\""
				+ "form=\"add_comment_form\">"
				+ "</textarea><br>";
		
		String submit = "<input type=\"submit\"\r\n"
				+ "           form=\"add_comment_form\"\r\n"
				+ "           value=\"Добави\"\r\n"
				+ "           class=\"button\">";
		
		query = tableB + query + tr + tdClassQ + commentField + tdEnd + trEnd + tr + tdClassQ + submit + tdEnd + trEnd + tableEnd;
		
		return query;
	}
	
}
