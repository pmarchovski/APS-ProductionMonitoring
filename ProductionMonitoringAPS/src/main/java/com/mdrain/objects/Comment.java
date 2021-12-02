package com.mdrain.objects;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Comment {

	private Users user;
	private int taskNumber;
	private String commentContent;
	private LocalDate commentDate;
	private LocalTime commentTime;
	
	public Comment() {
		
	}
	
	
	public LocalTime getCommentTime() {
		return commentTime;
	}


	public void setCommentTime(Time commentTime) {
		
		String dbTime       = String.valueOf(commentTime);
	    int hours           = Integer.parseInt(dbTime.substring(0, 2));
		int minutes         = Integer.parseInt(dbTime.substring(3, 5));
		int seconds         = Integer.parseInt(dbTime.substring(6, 8));
		LocalTime localTime = LocalTime.of(hours, minutes, seconds);
		localTime           = localTime.plusHours(6);
		
		this.commentTime = localTime;
	}


	public int getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}
	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	public LocalDate getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}
	
	
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
}
