package com.mdrain.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.singletons.Singleton;

public class NumberOfOperators {

	
	public static int numberOfOperators () {
		
		String table                    = "tb_operators";
		String column                   = "tb_operators_operator_name";
		DataBaseActivities dbActivities = Singleton.getInstance();
		ArrayList<String> operators     = new ArrayList<String>();
		ResultSet result = dbActivities.select(table);
		
		if (result != null) {		
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
		} else{
			numberOfOperators();
		}	
		return operators.size();
	}
}
