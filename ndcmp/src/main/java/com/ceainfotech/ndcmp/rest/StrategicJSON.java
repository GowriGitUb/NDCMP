/**
 * 
 */
package com.ceainfotech.ndcmp.rest;

/**
 * @author Gowri
 *
 */
public class StrategicJSON {

	private Integer spId;
	
	private String spName;
	
	private String spStatus;
	
	private Boolean syncStatus;
	
	private Boolean wholeSyncStatus;

	private String wholeSyncStatusMessage;
	
	private Integer projectId;
	
	public StrategicJSON() {
	}

	public StrategicJSON(Integer spId, String spName, String spStatus,
			Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage, Integer projectId) {
		super();
		this.spId = spId;
		this.spName = spName;
		this.spStatus = spStatus;
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
		this.projectId = projectId;
	}

	public StrategicJSON(Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	/**
	 * @return the spId
	 */
	public Integer getSpId() {
		return spId;
	}

	/**
	 * @param spId the spId to set
	 */
	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	/**
	 * @return the spName
	 */
	public String getSpName() {
		return spName;
	}

	/**
	 * @param spName the spName to set
	 */
	public void setSpName(String spName) {
		this.spName = spName;
	}

	/**
	 * @return the spStatus
	 */
	public String getSpStatus() {
		return spStatus;
	}

	/**
	 * @param spStatus the spStatus to set
	 */
	public void setSpStatus(String spStatus) {
		this.spStatus = spStatus;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StrategicJSON [spId=" + spId + ", spName=" + spName
				+ ", spStatus=" + spStatus + ", syncStatus=" + syncStatus
				+ ", wholeSyncStatus=" + wholeSyncStatus
				+ ", wholeSyncStatusMessage=" + wholeSyncStatusMessage
				+ ", projectId=" + projectId + "]";
	}
}
