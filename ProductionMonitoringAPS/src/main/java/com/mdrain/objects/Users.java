package com.mdrain.objects;

public class Users {

	private String fullName;
	private String userName;
	private String email;
	private String password;
	private String type;
	
	public Users(String fullName, String userName, String email, String password) {
		
		this.fullName = fullName;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Users(String email, String password) {
		
		this.email = email;
		this.password = password;
	}
	
	public Users(String fullName) {
		this.fullName = fullName;
	}

	public Users() {
		
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
