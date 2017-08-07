/**
 * 
 */
package com.ceainfotech.ndcmp.util;

/**
 * @author Samy
 * 
 */
public enum Status {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), ACTIVATION_PENDING("ACTIVATION_PENDING");

	private String status;

	private Status() {
	}

	private Status(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
