package com.project.tailor.exceptionhandeling;

import com.project.tailor.entity.Brand;

public class SuccessResponse {
	
	

	private int status=200;
	private Object message;
	private long timeStamp;
	

	public SuccessResponse(Object message, long timeStamp) {
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}
	
	public void setSatus(int status) {
		this.status=status;
	}
	
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
