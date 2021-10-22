package com.mdrain.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mdrain.servletPrepare.admin.LogAndLogout;

public class DataBaseActivities {

	DataBaseConnect dbConnect = new DataBaseConnect();	
	Connection connectionMdrain = dbConnect.connection;
	Statement statement;

	public void deleteTable(String table) {

		String query = "DROP TABLE " + table;
	
			this.execute(query);
	}

	public void truncateTable(String table) {

		String query = "TRUNCATE TABLE " + table;
	
			this.execute(query);
	}

	// CREATE TABLE result SELECT tb_coois_prod.tb_coois_prod_order,
	// tb_coois_prod.tb_coois_prod_material_number,
	// tb_coois_prod.tb_coois_prod_quantity,
	// tb_coois_operation.tb_coois_operation_processing_time
	// FROM tb_coois_prod
	// INNER JOIN tb_coois_operation
	// ON tb_coois_prod.tb_coois_prod_order =
	// tb_coois_operation.tb_coois_operation_order

	public void createTable(String table1, String table2, ArrayList<String> column, String columnON1,
			String columnON2) {

		String query = "";
		String newTableName = "tb_result";
		String columnQuery = "";

		for (String element : column) {
			columnQuery += element + ", ";
		}

		columnQuery = columnQuery.substring(0, columnQuery.length() - 2);

		query = "CREATE TABLE " + newTableName + " SELECT " + columnQuery + " FROM " + table1 + " INNER JOIN " + table2
				+ " ON " + columnON1 + " = " + columnON2;

			this.execute(query);

	}

	public void delete(String table) {

		String query = "DELETE FROM " + table;
	
			this.execute(query);
	}

//	DELETE FROM tb_coois_prod WHERE tb_coois_prod_status LIKE '%TECO%' AND tb_coois_prod_del_qty = 0

	public void deleteWhereLikeAnd(String table, ArrayList<String> column, ArrayList<Object> value) {

		String query = "DELETE FROM " + table + " WHERE " + column.get(0) + " LIKE " + "'%" + value.get(0) + "%'"
				+ " AND " + column.get(1) + " = " + value.get(1);
		if (DataBaseConnect.getInstance() != null)
			this.execute(query);
	}

	public void insert(String table, String into, double value) {

		String query = "INSERT INTO " + table + "(" + into + ")" + "VALUES " + "(" + "'" + value + "'" + ")";
		if (DataBaseConnect.getInstance() != null)
			this.execute(query);
	}

	public void insert(String table, String into, String value) {

		String query = "INSERT INTO " + table + "(" + into + ")" + "VALUES " + "(" + "'" + value + "'" + ")";
		
			this.execute(query);
	}

	public void insert(String table, String[] into, String[] value) {

		// INSERT INTO table (fields, fields1, fields2) VALUES ('value', 'value1',
		// 'value2');

		String intoString = "";

		for (String element : into) {
			intoString += (element + ",");
		}
		intoString = "(" + intoString.substring(0, intoString.length() - 1) + ")";

		String valueString = "";

		for (String element : value) {
			valueString += ("'" + element + "'" + ",");
		}

		valueString = "(" + valueString.substring(0, valueString.length() - 1) + ")";

		String query = "INSERT INTO " + table + intoString + "VALUES " + valueString;

		
			this.execute(query);

	}

	public void insert(String table, ArrayList<String> into, ArrayList<String> values) {

		String intoString = "";
		for (String element : into) {
			intoString += (element + ",");
		}
		intoString = "(" + intoString.substring(0, intoString.length() - 1) + ")";

		String valueString = "";

		for (String element : values) {
			valueString += ("'" + element + "'" + ",");
		}

		valueString = "(" + valueString.substring(0, valueString.length() - 1) + ")";
		String query = "INSERT INTO " + table + intoString + "VALUES " + valueString;
		
			this.execute(query);
	}

	public void insertObject(String table, ArrayList<String> into, ArrayList<Object> values) {

		String intoString = "";
		for (String element : into) {
			intoString += (element + ",");
		}
		intoString = "(" + intoString.substring(0, intoString.length() - 1) + ")";

		String valueString = "";

		for (Object element : values) {
			valueString += ("'" + element + "'" + ",");
		}

		valueString = "(" + valueString.substring(0, valueString.length() - 1) + ")";
		String query = "INSERT INTO " + table + intoString + "VALUES " + valueString;
		
			this.execute(query);
	}

	public ResultSet select(String table, String column) {

		// SELECT column FROM table

		String query = "SELECT " + column + " FROM " + table;

		return this.executeQuery(query);

	}

	public ResultSet select(String table) {

		// SELECT * FROM table

		String query = "SELECT * FROM " + table;

		return this.executeQuery(query);

	}

	public ResultSet selectDistinct(String column, String table) {

		// SELECT * FROM table

		String query = "SELECT DISTINCT " + "(" + column + ")" + " FROM " + table;

		return this.executeQuery(query);

	}

	public ResultSet select(String table, String fields, String value, String sortByField) {

		// SELECT * FROM table WHERE

		String query = "SELECT * FROM " + table + " WHERE " + fields + " = " + "'" + value + "'" + " ORDER by "
				+ sortByField;

		return this.executeQuery(query);

	}

	public ResultSet select(String table, String fields, String value) {

		// SELECT * FROM table WHERE

		String query = "SELECT * FROM " + table + " WHERE " + fields + " = " + "'" + value + "'";

		return this.executeQuery(query);

	}

	public ResultSet selectWhereSort(String table, ArrayList<String> fieldsCollection,
			ArrayList<String> valueCollection, String sortByField) {

		// SELECT * FROM table WHERE fields = 'value'

		String fields = "";

		for (int i = 0; i < fieldsCollection.size(); i++) {
			fields += fieldsCollection.get(i) + " = ";

			for (int j = 0; j <= i; j++) {
				fields += "'" + valueCollection.get(i) + "'";
			}

		}

		String query = "SELECT * FROM " + table + " WHERE " + fields + "ORDER by " + sortByField;

		return this.executeQuery(query);
	}

	public ResultSet selectWhere(String table, ArrayList<String> fieldsCollection, ArrayList<String> valueCollection) {

		// SELECT * FROM table WHERE fields = 'value'

		String fields = "";

		for (int i = 0; i < fieldsCollection.size(); i++) {
			fields += fieldsCollection.get(i) + " = ";

			for (int j = 0; j <= i; j++) {
				fields += "'" + valueCollection.get(i) + "'";
			}

		}

		String query = "SELECT * FROM " + table + " WHERE " + fields;

		return this.executeQuery(query);

	}
	
	
	public ResultSet selectWhere(String table, String field, String value) {

		// SELECT * FROM table WHERE fields = 'value'


		String query = "SELECT " + "tb_wardrobe_number" + " FROM " + table + " WHERE " + field + " = " + "'" + value + "'";

		return this.executeQuery(query);

	}
	
	

	public ResultSet selectWhere(String table, String column, ArrayList<String> fieldsCollection,
			ArrayList<String> valueCollection) {

		// SELECT * FROM table WHERE fields = 'value'

		String fields = "";

		for (int i = 0; i < fieldsCollection.size(); i++) {
			fields += fieldsCollection.get(i) + " = ";

			for (int j = 0; j <= i; j++) {
				fields += "'" + valueCollection.get(i) + "'";
			}

		}

		String query = "SELECT " + column + " FROM " + table + " WHERE " + fields;

		return this.executeQuery(query);

	}

	public ResultSet selectWhereAnd(String table, ArrayList<String> fieldsCollection, ArrayList<String> valueCollection,
			String sortByField) {

		// SELECT * FROM table WHERE fields = 'value' AND fields1 = 'value1' AND fields2
		// = 'value2'

		String fields = "";

		for (int i = 0; i < fieldsCollection.size(); i++) {
			fields += fieldsCollection.get(i) + " = ";

			for (int j = i; j <= i; j++) {
				fields += "'" + valueCollection.get(i) + "'" + " AND ";
			}

		}

		fields = fields.substring(0, fields.length() - 4);

		String query = "SELECT * FROM " + table + " WHERE " + fields + "ORDER by " + sortByField;

		return this.executeQuery(query);
	}

	public void update(String table, String field, String value, String fieldInto, String valueInto) {
//		UPDATE Customers SET ContactName = 'Alfred Schmidt', City = 'Frankfurt' WHERE CustomerID = 1;

		String fields = field + " = " + "'" + value + "'";
		String fieldsInto = fieldInto + " = " + "'" + valueInto + "'";

		String query = "UPDATE " + table + " SET " + fields + " WHERE " + fieldsInto;

		this.execute(query);
	}

	public void update(String table, String field, double value, String fieldInto, String valueInto) {
//		UPDATE Customers SET ContactName = 'Alfred Schmidt', City = 'Frankfurt' WHERE CustomerID = 1;

		String fields = field + " = " + "'" + value + "'";
		String fieldsInto = fieldInto + " = " + "'" + valueInto + "'";

		String query = "UPDATE " + table + " SET " + fields + " WHERE " + fieldsInto;

		this.execute(query);
	}

	public void update(String table, ArrayList<String> fieldsCollection, ArrayList<String> valueCollection,
			String fieldInto, String valueInto) {

		// UPDATE Customers SET ContactName = 'Alfred Schmidt', City = 'Frankfurt' WHERE
		// CustomerID = 1;

		String fields = "";
		String fieldsInto = "";

		for (int i = 0; i < fieldsCollection.size(); i++) {
			fields += fieldsCollection.get(i) + " = ";
			for (int j = i; j <= i; j++) {
				fields += "'" + valueCollection.get(i) + "'" + ", ";
			}
		}
		fields = fields.substring(0, fields.length() - 2);
		fieldsInto = fieldInto + " = " + "'" + valueInto + "'";

		String query = "UPDATE " + table + " SET " + fields + " WHERE " + fieldsInto;

		this.execute(query);
	}

	public ResultSet sort(String table, String field) {

		String query = "SELECT * FROM " + table + " ORDER by " + field;

		return this.executeQuery(query);
	}

	public Statement statement() {

		
		String user = LogAndLogout.userNameForDataBase;
		
		try {
			
			if(connectionMdrain == null) {
				connectionMdrain = dbConnect.connect();
				System.out.println(user);
				return statement = connectionMdrain.createStatement();
				
			} else {
				System.out.println(user + " 1");
				return statement = connectionMdrain.createStatement();
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return null;
	}

	private ResultSet executeQuery(String query) {

		try {
		
				return statement().executeQuery(query);
		
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;

	}

	private void execute(String query) {
		
		try {
				this.statement().execute(query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	// Обяснение за мен

//	private ResultSet selectNew() {
//		
//		Connection connection = (Connection) DataBaseConnect.connect();
//		try {
//			Statement quaryManager = connection.createStatement();
//			return quaryManager.executeQuery("тук се изписва SQL кода");
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
}