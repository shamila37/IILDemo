package com.example.pojo;

public class EligibilityProcessRequest {

	private String RequestedUser;
	private String courseNames;

	public String getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(String courseNames) {
		this.courseNames = courseNames;
	}

	public String getRequestedUser() {
		return RequestedUser;
	}

	public void setRequestedUser(String requestedUser) {
		RequestedUser = requestedUser;
	}
}
