/**
 * 
 */
package com.ceainfotech.ndcmp.util;

/**
 * @author jeeva
 *
 */
public enum ModuleFeatures {
	
	VIEW_PROJECT("Project","View Project"),
	CONFIGURE_PROJECT("Project","Configure Project"),
	PROJECT_PLANNER("Project","Plan Project"),
	PROJECT_REVIEWER("Project","Review Status"),
	PROJECT_STATUS_UPDATER("Project","Update Status"),
	PROJECT_STATUS_APPROVER("Project","Approve Status"),
	
	ADD_USER("User","Add User"),
	EDIT_USER("User","Edit User"),
	ACTIVE_DEACTIVE_USER("User","Activate or Deactivate User"),
	VIEW_USER("User","View User"),
	
	ADD_ACCESS_RIGHTS("Access Rights","Add Access Rights"),
	
	ADD_USER_ROLE("User Role","Add User Role"),
	EDIT_USER_ROLE("User Role","Edit User Role"),
	ACTIVE_DEACTIVE_USER_ROLE("User Role","Activate or Deactivate User Role"),
	VIEW_USER_ROLE("User Role","View User Role"),
	
	ADD_COUNTRY("Country","Add Country"),
	EDIT_COUNTRY("Country","Edit Country"),
	ACTIVE_DEACTIVE_COUNTRY("Country","Activate or Deactivate Country"),
	VIEW_COUNTRY("Country","View Country"),
	
	ADD_STATE("State","Add State"),
	EDIT_STATE("State","Edit State"),
	ACTIVE_DEACTIVE_STATE("State","Activate or Deactivate State"),
	VIEW_STATE("State","View State"),
	
	ADD_REGION("Region","Add Region"),
	EDIT_REGION("Region","Edit Region"),
	ACTIVE_DEACTIVE_REGION("Region","Activate or Deactivate Region"),
	VIEW_REGION("Region","View Region"),
	
	ADD_REPORTING_PERIOD("Reporting Period","Add Reporting Period"),
	EDIT_REPORTING_PERIOD("Reporting Period","Edit Reporting Period"),
	ACTIVE_DEACTIVE_REPORTING_PERIOD("Reporting Period","Activate or Deactivate Reporting Period"),
	VIEW_REPORTING_PERIOD("Reporting Period","View Reporting Period"),
	
	ADD_AGENCY("Agency","Add Agency"),
	EDIT_AGENCY("Agency","Edit Agency"),
	ACTIVE_DEACTIVE_AGENCY("Agency","Activate or Deactivate Agency"),
	VIEW_AGENCY("Agency","View Agency"),
	
	ADD_STATUS("Status","Add Status"),
	EDIT_STATUS("Status","Edit Status"),
	ACTIVE_DEACTIVE_STATUS("Status","Activate or Deactivate Status"),
	VIEW_STATUS("Status","View Status"),
	
	ADD_QUARTER("Quarter","Add Quarter"),
	EDIT_QUARTER("Quarter","Edit Quarter"),
	ACTIVE_DEACTIVE_QUARTER("Quarter","Activate or Deactivate Quarter"),
	VIEW_QUARTER("Quarter","View Quarter"),
	
	ADD_ALLOWED_DEVICE("Allowed Device","Add Allowed Device"),
	
	VIEW_DASHBOARD("Dashboard","View Dashboard"),
	
	VIEW_REPORTS("Reports","View Reports");
	
	private String moduleName;
	
	private String featureName;
	
	private ModuleFeatures() {
		// TODO Auto-generated constructor stub
	}
	
	private ModuleFeatures(String moduleName, String featureName) {
		this.moduleName = moduleName;
		this.featureName = featureName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

}
