package com.ceainfotech.ndcmp.model;

/**
 * 
 * @author Samy
 * 
 */

public enum UserProfileType {
	USER("USER"), DBA("DBA"), ADMIN("ADMIN"), SUPER_ADMIN("SUPER_ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}
