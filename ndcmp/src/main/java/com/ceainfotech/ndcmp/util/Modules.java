/**
 * 
 */
package com.ceainfotech.ndcmp.util;

/**
 * @author Samy
 * 
 */
public enum Modules {

	DASHBOARD("Dashboard",1),
	PROJECT("Project",2),
	USER("User",3),
	ACCESS_RIGHTS("Access Rights",4),
	USER_ROLE("User Role",5),
	COUNTRY("Country",6),
	STATE("State",7),
	REGION("Region",8),
	QUARTER("Quarter",9),
	REPORTING_PERIOD("Reporting Period",10),
	AGENCY("Agency",11),
	STATUS("Status",12),
	ALLOWED_DEVICE("Allowed Device", 13),
	REPORTS("Reports",14);
	
	
	private String name;
	private int orderId;
	
	private Modules() {
	}
	
	private Modules(String name,int orderId) {
		this.name = name;
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
