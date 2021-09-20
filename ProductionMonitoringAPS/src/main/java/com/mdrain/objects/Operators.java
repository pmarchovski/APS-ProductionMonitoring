package com.mdrain.objects;

public class Operators extends Users{

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
	private Double absenceHours;
	
	public Operators() {
	}

	public Operators(String fullName) {
		super(fullName);
		
	}

	public Operators(String fullName, String teamLeader, String gender,
			String[] skills, String isActive, String isMotherhood, String phone) {
		super(fullName);
		this.teamLeader = teamLeader;
		this.gender = gender;
		this.skills = skills;
		this.isActive = isActive;
		this.isMotherhood = isMotherhood;
		this.phone = phone;
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
