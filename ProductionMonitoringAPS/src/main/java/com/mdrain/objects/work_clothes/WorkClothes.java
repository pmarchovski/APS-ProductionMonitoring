package com.mdrain.objects.work_clothes;

import java.time.LocalDate;

public class WorkClothes {


	private String number;
	private int quantity;
	private LocalDate clothesDate;

	public WorkClothes() {
		
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getClothesDate() {
		return clothesDate;
	}

	public void setClothesDate(LocalDate clothesDate) {
		this.clothesDate = clothesDate;
	}
}
