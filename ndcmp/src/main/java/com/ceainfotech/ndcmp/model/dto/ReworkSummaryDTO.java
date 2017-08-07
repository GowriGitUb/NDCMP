package com.ceainfotech.ndcmp.model.dto;

public class ReworkSummaryDTO {

	int userId;
	
	String userName;
	
	String agencyName;
	
	int totalRework;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public int getTotalRework() {
		return totalRework;
	}

	public void setTotalRework(int totalRework) {
		this.totalRework = totalRework;
	}

	
}
