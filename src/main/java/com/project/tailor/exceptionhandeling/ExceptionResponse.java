package com.project.tailor.exceptionhandeling;

import java.util.ArrayList;

public class ExceptionResponse {

	private int status;
	private ArrayList<String> messages=new ArrayList<>();
	private long timeStamp;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<String> getMessage() {
		return messages;
	}
	public void setMessage(ArrayList<String> messages) {
		this.messages = messages;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public void add (String message) {
		messages.add(message);
	}
	
	
}
