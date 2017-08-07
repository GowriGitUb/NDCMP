/**
 * 
 */
package com.ceainfotech.ndcmp.util;

/**
 * @author jeeva
 *
 */
public enum Features {

	VIEW_PROJECT("View Project"),
	CONFIGURE_PROJECT("Configure Project"),
	PROJECT_PLANNER("Plan Project"),
	PROJECT_REVIEWER("Review Status"),
	PROJECT_STATUS_UPDATER("Update Status"),
	PROJECT_STATUS_APPROVER("Approve Status"),
	
	ADD_USER("Add User"),
	EDIT_USER("Edit User"),
	ACTIVE_DEACTIVE_USER("Activate or Deactivate User"),
	VIEW_USER("View User"),
	
	ADD_ACCESS_RIGHTS("Add Access Rights"),
	
	ADD_USER_ROLE("Add User Role"),
	EDIT_USER_ROLE("Edit User Role"),
	ACTIVE_DEACTIVE_USER_ROLE("Activate or Deactivate User Role"),
	VIEW_USER_ROLE("View User Role"),
	
	ADD_COUNTRY("Add Country"),
	EDIT_COUNTRY("Edit Country"),
	ACTIVE_DEACTIVE_COUNTRY("Activate or Deactivate Country"),
	VIEW_COUNTRY("View Country"),
	
	ADD_STATE("Add State"),
	EDIT_STATE("Edit State"),
	ACTIVE_DEACTIVE_STATE("Activate or Deactivate State"),
	VIEW_STATE("View State"),
	
	ADD_REGION("Add Region"),
	EDIT_REGION("Edit Region"),
	ACTIVE_DEACTIVE_REGION("Activate or Deactivate Region"),
	VIEW_REGION("View Region"),
	
	ADD_REPORTING_PERIOD("Add Reporting Period"),
	EDIT_REPORTING_PERIOD("Edit Reporting Period"),
	ACTIVE_DEACTIVE_REPORTING_PERIOD("Activate or Deactivate Reporting Period"),
	VIEW_REPORTING_PERIOD("View Reporting Period"),
	
	ADD_AGENCY("Add Agency"),
	EDIT_AGENCY("Edit Agency"),
	ACTIVE_DEACTIVE_AGENCY("Activate or Deactivate Agency"),
	VIEW_AGENCY("View Agency"),
	
	ADD_STATUS("Add Status"),
	EDIT_STATUS("Edit Status"),
	ACTIVE_DEACTIVE_STATUS("Activate or Deactivate Status"),
	VIEW_STATUS("View Status"),
	
	ADD_QUARTER("Add Quarter"),
	EDIT_QUARTER("Edit Quarter"),
	ACTIVE_DEACTIVE_QUARTER("Activate or Deactivate Quarter"),
	VIEW_QUARTER("View Quarter"),
	
	ADD_ALLOWED_DEVICE("Add Allowed Device"),
	
	VIEW_DASHBOARD("View Dashboard"),
	
	VIEW_REPORTS("View Reports");
	
	private String name;
	
	private Features() {
		// TODO Auto-generated constructor stub
	}
	
	

	private Features(String name) {
		this.name = name;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
