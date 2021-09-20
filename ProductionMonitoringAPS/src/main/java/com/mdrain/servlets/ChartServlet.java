package com.mdrain.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.database.DataBaseActivities;
import com.mdrain.objects.Operators;
import com.mdrain.objects.TeamLeaders;

public class ChartServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DataBaseActivities dbActivities = new DataBaseActivities();
		String table = "tb_operators";
		ResultSet result = null;
		ArrayList<String> petarCollection = new ArrayList<String>();
		ArrayList<String> renetaCollection = new ArrayList<String>();
		ArrayList<String> rumianaCollection = new ArrayList<String>();
		ArrayList<String> teamLeadersCollection = new ArrayList<String>();
		ArrayList<Integer> yValueCollection = new ArrayList<Integer>();
		HttpSession session = req.getSession();
		
		teamLeadersCollection.add("Петър Гацовски");
		teamLeadersCollection.add("Ренета Кондарева");
		teamLeadersCollection.add("Румяна Димитрова");
		
	
		
		result = dbActivities.select(table);
		
		try {
			while (result.next()) {
				
				if (result.getString("tb_operators_team_leader").equals(teamLeadersCollection.get(0))) {
					petarCollection.add(result.getString("tb_operators_operator_name"));
					
				}
				
				if (result.getString("tb_operators_team_leader").equals(teamLeadersCollection.get(1))) {
					renetaCollection.add(result.getString("tb_operators_operator_name"));
					
				}
				
				if (result.getString("tb_operators_team_leader").equals(teamLeadersCollection.get(2))) {
					rumianaCollection.add(result.getString("tb_operators_operator_name"));
				
				}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		        int petarNumberOperators = petarCollection.size();
				int renetaNumberOperators = renetaCollection.size();
				int rumianaNumberOperators = rumianaCollection.size();
				
				
				yValueCollection.add(petarNumberOperators);
				yValueCollection.add(renetaNumberOperators);
				yValueCollection.add(rumianaNumberOperators);
		
		
		String xValue = "";
		for (int i = 0; i < teamLeadersCollection.size(); i++) {
			
			xValue += "\"" + teamLeadersCollection.get(i) + "\"" + ", ";
	
		}
		    xValue = xValue.substring(0, xValue.length() - 2);
		    xValue = "[" + xValue + "]";
		    
		String yValue = "";
		
		
	    
		for (int i = 0; i < yValueCollection.size(); i++) {
			
			yValue += yValueCollection.get(i) + ", ";
			
		}
		
		    yValue = yValue.substring(0, yValue.length() - 2);
		    yValue = "[" + yValue + "]";
		    
		session.setAttribute("chartXValue", xValue);
		session.setAttribute("chartYValue", yValue);
		
		resp.sendRedirect("chart.jsp");
	}
}
