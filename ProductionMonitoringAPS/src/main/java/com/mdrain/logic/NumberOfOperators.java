package com.mdrain.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mdrain.database.DataBaseActivities;

public class NumberOfOperators {

	
	public static int numberOfOperators () {
		
		String table                    = "tb_operators";
		String column                   = "tb_operators_operator_name";
		DataBaseActivities dbActivities = new DataBaseActivities();
		ArrayList<String> operators     = new ArrayList<String>();
		ResultSet result                = null;
		
		result = dbActivities.select(table);
		
		try {
			while (result.next()) {
				
				if (result.getString("tb_operators_isActive").equals("Да") && 
						result.getString("tb_operators_mech_ass").equals("Оператор механичен монтаж")) {
					operators.add(result.getString(column));
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return operators.size();
	}
	
}
