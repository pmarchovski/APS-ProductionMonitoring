package com.mdrain.objects;

public class Materials extends Orders{

	String materialNumber;
	String materialDescription;
	String materialGroup;

	public Materials(String materialNumber, String materialDescription, String materialGroup) {

		this.materialNumber      = materialNumber;
		this.materialDescription = materialDescription;
		this.materialGroup       = materialGroup;
	}

	public Materials(String materialNumber, String materialGroup) {

		this.materialNumber = materialNumber;
		this.materialGroup  = materialGroup;
	}

	public Materials() {

	}

	
	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	
	

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	
	

	public String getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(String materialGroup) {
		this.materialGroup = materialGroup;
	}
}
