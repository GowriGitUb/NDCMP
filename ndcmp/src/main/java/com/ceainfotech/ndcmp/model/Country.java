/**
 * 
 */
package com.ceainfotech.ndcmp.model;

/**
 * @author Samy
 * 
 */
public enum Country {

	ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted"), LOCKED("Locked");

	private String country;

	private Country(final String country) {
		this.country = country;
	}

	public String getCountry() {
		return this.country;
	}

	@Override
	public String toString() {
		return this.country;
	}

	public String getName() {
		return this.name();
	}
}