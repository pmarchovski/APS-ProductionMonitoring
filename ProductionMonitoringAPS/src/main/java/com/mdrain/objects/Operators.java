package com.mdrain.objects;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mdrain.logic.Date;
import com.mdrain.logic.MaterialsProductTypeList;
import com.mdrain.variables.Variables;

public class Operators extends Users {
	
    boolean isAvailable;
	private String teamLeader;
	private String gender;
	private String[] skills;
	private String isActive;
	private String isMotherhood;
	private String phone;
	private String skillList = "";
	private String mechAssembly;
	private String elAssembly;
	private String test;
	private String packaging;
	private String numberHeater;
	private String numberWardrobe;	
	private Double absenceHours;
	
	private int index;
	private int numberApron;
	private int numberSlippers;
	
	private LocalDate changeDateAppron;
	private LocalDate changeDateHeater;
	private LocalDate changeDateSlippers;
	private LocalDate nextChangeDateAppron;
	private LocalDate nextChangeDateHeater;
	private LocalDate nextChangeDateSlippers;
	
	private long numberYearDays                          = Variables.getNumberYersDays();
	private long numberYearDaysForHeater                 = Variables.getNumberYersDays() * 2;
	private int typeFieldsLength                         = MaterialsProductTypeList.getMaterialTypeFields().length;
	
	private String[] skillsCollectionFromProductionCards = new String[typeFieldsLength];

	public Operators() {
	}

	public Operators(String fullName) {
		super(fullName);

	}
	
	public Operators(String fullName, String teamLeader, String gender, String[] skills, String isActive,
			String isMotherhood, String numberWardrobe) {
		super(fullName);
		this.teamLeader     = teamLeader;
		this.gender         = gender;
		this.skills         = skills;
		this.isActive       = isActive;
		this.isMotherhood   = isMotherhood;
		this.numberWardrobe = numberWardrobe;
		
	}

	public LocalDate getNextChangeDateAppron() {
		return nextChangeDateAppron;
	}

	public void setNextChangeDateAppron(LocalDate changeDateAppron) {
		
		this.nextChangeDateAppron = changeDateAppron.plusDays(numberYearDays);
	}
	

	public LocalDate getNextChangeDateHeater() {
		return nextChangeDateHeater;
	}

	
	public void setNextChangeDateHeater(LocalDate changeDateHeater) {
		
		
		this.nextChangeDateHeater = changeDateHeater.plusDays(numberYearDaysForHeater);
	}

	public LocalDate getNextChangeDateSlippers() {
		return nextChangeDateSlippers;
	}

	
	public void setNextChangeDateSlippers(LocalDate changeDateSlippers) {
		
		this.nextChangeDateSlippers = changeDateSlippers.plusDays(numberYearDays);
	}

	
	public LocalDate getChangeDateAppron() {
		return changeDateAppron;
	}

	public void setChangeDateAppron(LocalDate changeDateAppron) {
		this.changeDateAppron = changeDateAppron;
	}

	public LocalDate getChangeDateHeater() {
		return changeDateHeater;
	}

	public void setChangeDateHeater(LocalDate changeDateHeater) {
		this.changeDateHeater = changeDateHeater;
	}

	public LocalDate getChangeDateSlippers() {
		return changeDateSlippers;
	}

	public void setChangeDateSlippers(LocalDate changeDateSlippers) {
		this.changeDateSlippers = changeDateSlippers;
	}

	public String getNumberHeater() {
		return numberHeater;
	}

	public void setNumberHeater(String numberHeater) {
		this.numberHeater = numberHeater;
	}

	public String getNumberWardrobe() {
		return numberWardrobe;
	}

	public void setNumberWardrobe(String numberWardrobe) {


		this.numberWardrobe = numberWardrobe;
	}

	public int getNumberApron() {
		return numberApron;
	}

	public void setNumberApron(int numberApron) {
		this.numberApron = numberApron;
	}

	public int getNumberSlippers() {
		return numberSlippers;
	}

	public void setNumberSlippers(int numberSlippers) {
		this.numberSlippers = numberSlippers;
	}

	public String[] getSkillsCollectionFromProductionCards() {
		return skillsCollectionFromProductionCards;
	}

	public void setSkillsCollectionFromProductionCards(ArrayList<Object> productionCardsInfoCollection) {

		String[] productTyeFields = MaterialsProductTypeList.getMaterialTypeFields();

		for (int i = 0; i < productionCardsInfoCollection.size(); i++) {

			isAvailable = false;
			ProductionCards productionCard = new ProductionCards();

			productionCard = (ProductionCards) productionCardsInfoCollection.get(i);

			if (productionCard.getProductionCardOperator().equals(this.getFullName())) {

				int j = 0;

				for (j = 0; j < skillsCollectionFromProductionCards.length; j++) {

					if (productionCard.getUnionProdTypeAndOperationForMatrix()
							.equals(skillsCollectionFromProductionCards[j])) {

						isAvailable = true;
						j = typeFieldsLength;
					}
				}
				int k = 0;

				for (k = 0; k < skillsCollectionFromProductionCards.length; k++) {

					if (skillsCollectionFromProductionCards[k] == null && isAvailable != true) {

						for (int u = 0; u < productTyeFields.length; u++) {

							if (productionCard.getUnionProdTypeAndOperationForMatrix().equals(productTyeFields[u])) {

								index = u;
							}

						}

						skillsCollectionFromProductionCards[index] = productionCard
								.getUnionProdTypeAndOperationForMatrix();

						k = typeFieldsLength;

					}
				}
			}
		}
	}

	public Double getAbsenceHours() {
		return absenceHours;
	}

	public void setAbsenceHours(Double absenceHours) {
		this.absenceHours = absenceHours;
	}

	public String getMechAssembly() {
		return mechAssembly;
	}

	public void setMechAssembly(String mechAssembly) {
		this.mechAssembly = mechAssembly;
	}

	public String getElAssembly() {
		return elAssembly;
	}

	public void setElAssembly(String elAssembly) {
		this.elAssembly = elAssembly;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getSkillList() {
		return skillList;
	}

	public void setSkillList(String[] skills) {

		for (int i = 0; i < skills.length; i++) {
			skillList += (i + 1) + "." + " " + skills[i] + ";" + "   ";

		}
		
		if (skills[0].equals("")) {
			
			skillList = skillList.substring(0, skillList.length() - 7);
		} else {
			skillList = skillList.substring(0, skillList.length() - 4);
		}
		
	}

	public String getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String[] getSkills() {

		return skills;
	}

	public void setSkills(String[] skills) {

		this.skills = skills;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsMotherhood() {
		return isMotherhood;
	}

	public void setIsMotherhood(String isMotherhood) {
		this.isMotherhood = isMotherhood;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
