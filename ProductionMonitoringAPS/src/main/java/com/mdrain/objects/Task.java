package com.mdrain.objects;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mdrain.logic.Date;
import com.mdrain.logic.SetObjectInfo;

public class Task {

	private int taskNumber;
	private int numberTaskTargetDateChanges;
	
	private Users creator;
	private Users owner;
	
	private LocalDate createDate;
	private LocalDate targetDate;
	
	private String title;
	private String content;
	private String status;
	
	private ArrayList<Users> members;
	private ArrayList<Comment> comments;
	
	private ArrayList<Users> usersCollection = SetObjectInfo.getUsersFromDataBase();
	
	public Task() {		
	}
	
	public int getNumberTaskTargetDateChanges() {
		return numberTaskTargetDateChanges;
	}
	public void setNumberTaskTargetDateChanges(int numberTaskTargetDateChanges) {
		this.numberTaskTargetDateChanges = numberTaskTargetDateChanges;
	}

	public Users getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		
		for (int i = 0; i < usersCollection.size(); i++) {
			if (creator.equals(usersCollection.get(i).getFullName())) {
				this.creator = usersCollection.get(i);
				return;
			}
		}
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}


	public int getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Users getOwner() {
		return owner;
	}
	public void setOwner(String ownerName) {
		
		for (int i = 0; i < usersCollection.size(); i++) {
			if (ownerName.equals(usersCollection.get(i).getFullName())) {
				this.owner = usersCollection.get(i);
				return;
			}
		}
	}
		
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		
		this.createDate = Date.date(createDate);
	}
	
	
	
	public LocalDate getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = Date.date(targetDate);
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	public ArrayList<Users> getMembers() {
		return members;
	}
	public void setMembers(String members) {
		
		ArrayList<Users> membersArrayCollection = new ArrayList<Users>();
		String[] membersCollection = members.split(", ");
		
		for (int i = 0; i < membersCollection.length; i++) {
			
			for (int j = 0; j < usersCollection.size(); j++) {
				
				if (membersCollection[i].equals(usersCollection.get(j).getFullName())) {
					membersArrayCollection.add(usersCollection.get(j));
					
				}
			}
			
		}
		
		this.members = membersArrayCollection;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
