package com.mdrain.objects;

public class Wardrobe {

	
	String wardrobeName;
	String wardrobeNumber;
	
	
	public Wardrobe() {
		
	}
	
	public Wardrobe(String wardrobeName, String wardrobeNumber) {
		this.wardrobeName = wardrobeName;
		this.wardrobeNumber = wardrobeNumber;
	}
	
	public String getWardrobeName() {
		return wardrobeName;
	}
	public void setWardrobeName(String wardrobeName) {
		this.wardrobeName = wardrobeName;
	}
	
	
	public String getWardrobeNumber() {
		return wardrobeNumber;
	}
	public void setWardrobeNumber(String wardrobeNumber) {
		this.wardrobeNumber = wardrobeNumber;
	}
	
}
