package com.mdrain.objects;

import java.util.ArrayList;

public class TeamLeaders extends Users{

	private String department;
	private ArrayList<Operators> operators;

	public TeamLeaders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeamLeaders(String fullName) {
		super(fullName);
		// TODO Auto-generated constructor stub
	}
	
	

	public ArrayList<Operators> getOperators() {
		return operators;
	}

	public void setOperators(ArrayList<Operators> operators) {
		this.operators = operators;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	
}
