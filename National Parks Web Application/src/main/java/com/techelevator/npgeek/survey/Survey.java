package com.techelevator.npgeek.survey;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	
	private long surveyID;
	private String parkCode;
	@NotBlank(message = "Please provide an email address")
	@Email(message = "Please provide a valid email address")
	private String emailAddress;
	private String state;
	private String activityLevel;
	
	
	public long getSurveyID() {
		return surveyID;
	}
	public void setSurveyID(long surveyID) {
		this.surveyID = surveyID;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

}



