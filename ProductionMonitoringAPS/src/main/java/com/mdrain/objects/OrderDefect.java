package com.mdrain.objects;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mdrain.variables.Variables;

public class OrderDefect extends Orders{

	ArrayList<Integer> defectsQuantityCollection = new ArrayList<Integer>();
	Variables variables                          = new Variables();
	LocalDate date;
	int number;
	int year;
	int month;
	int orderQuantity;
	String typeProduction;
	String typeProduct;

	public OrderDefect(ArrayList<String> defectsTypeCollection, ArrayList<Integer> defectsQuantityCollection,
			LocalDate date, int number) {
		super();
		this.defectsQuantityCollection = defectsQuantityCollection;
		this.date = date;
		this.number = number;
	}
	
	public OrderDefect() {
		
	}


	public String getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(String typeProduct) {
		
		if (this.getMaterialDescription().contains("ROLLER MODULE") ||
			this.getMaterialDescription().contains("TPH MODUL")	) {
			this.typeProduct = "NO";
		} else {
			this.typeProduct = "YES";
		}
	}

	public String getTypeProduction() {
		return typeProduction;
	}

	public void setTypeProduction() {
		
		if (this.number > 100000 && this.number <= 199999) this.typeProduction = variables.AUTO;
		if (this.number > 200000 && this.number <= 299999) this.typeProduction = variables.INDUSTRIAL;
		if (this.number > 300000 && this.number <= 399999) this.typeProduction = variables.PLASTIC;		
		if (this.number < 100000 || this.number > 400000) this.typeProduction = "";
		
	}

	
	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	
	public int getYear() {
		return year;
	}

	public void setYear() {
		this.year = this.date.getYear();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth() {
		this.month = this.date.getMonthValue();
	}
		
	public ArrayList<Integer> getDefectsQuantityCollection() {
		return defectsQuantityCollection;
	}

	public void setDefectsQuantityCollection(ArrayList<Integer> defectsQuantityCollection) {

		this.defectsQuantityCollection = defectsQuantityCollection;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
}
