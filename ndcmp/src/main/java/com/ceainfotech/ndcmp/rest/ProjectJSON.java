/**
 * 
 */
package com.ceainfotech.ndcmp.rest;


/**
 * @author Gowri
 *
 */
public class ProjectJSON {

	private Integer projectId;
	
	private String projectName;
	
	private String country;
	
	private String states;
	
	private String region;
	
	private String projectDescription;
	
	private String projectStatus;
	
	private Boolean syncStatus;
	
	private Boolean wholeSyncStatus;

	private String wholeSyncStatusMessage;
	
	public ProjectJSON() {
	}

	public ProjectJSON(Integer projectId, String projectName, String country,
			String states, String region, String projectDescription,
			String projectStatus, Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.country = country;
		this.states = states;
		this.region = region;
		this.projectDescription = projectDescription;
		this.projectStatus = projectStatus;
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	public ProjectJSON(Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the states
	 */
	public String getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(String states) {
		this.states = states;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the projectDescription
	 */
	public String getProjectDescription() {
		return projectDescription;
	}

	/**
	 * @param projectDescription the projectDescription to set
	 */
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	/**
	 * @return the projectStatus
	 */
	public String getProjectStatus() {
		return projectStatus;
	}

	/**
	 * @param projectStatus the projectStatus to set
	 */
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	/**
	 * @return the syncStatus
	 */
	public Boolean getSyncStatus() {
		return syncStatus;
	}

	/**
	 * @param syncStatus the syncStatus to set
	 */
	public void setSyncStatus(Boolean syncStatus) {
		this.syncStatus = syncStatus;
	}

	/**
	 * @return the wholeSyncStatus
	 */
	public Boolean getWholeSyncStatus() {
		return wholeSyncStatus;
	}

	/**
	 * @param wholeSyncStatus the wholeSyncStatus to set
	 */
	public void setWholeSyncStatus(Boolean wholeSyncStatus) {
		this.wholeSyncStatus = wholeSyncStatus;
	}

	/**
	 * @return the wholeSyncStatusMessage
	 */
	public String getWholeSyncStatusMessage() {
		return wholeSyncStatusMessage;
	}

	/**
	 * @param wholeSyncStatusMessage the wholeSyncStatusMessage to set
	 */
	public void setWholeSyncStatusMessage(String wholeSyncStatusMessage) {
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectJSON [projectId=" + projectId + ", projectName="
				+ projectName + ", country=" + country + ", states=" + states
				+ ", region=" + region + ", projectDescription="
				+ projectDescription + ", projectStatus=" + projectStatus
				+ ", syncStatus=" + syncStatus + ", wholeSyncStatus="
				+ wholeSyncStatus + ", wholeSyncStatusMessage="
				+ wholeSyncStatusMessage + "]";
	}
}
