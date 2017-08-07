/**
 * 
 */
package com.ceainfotech.ndcmp.rest;


/**
 * @author Gowri
 *
 */
public class ThemeJSON {

	private Integer themeId;
	
	private String themeName;
	
	private Integer spId;
	
	private String themeStatus;
	
	private Boolean syncStatus;
	
	private Boolean wholeSyncStatus;

	private String wholeSyncStatusMessage;
	
	public ThemeJSON() {
	}

	public ThemeJSON(Integer themeId, String themeName, Integer spId,
			String themeStatus, Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.themeId = themeId;
		this.themeName = themeName;
		this.spId = spId;
		this.themeStatus = themeStatus;
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	public ThemeJSON(Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	/**
	 * @return the themeId
	 */
	public Integer getThemeId() {
		return themeId;
	}

	/**
	 * @param themeId the themeId to set
	 */
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	/**
	 * @return the themeName
	 */
	public String getThemeName() {
		return themeName;
	}

	/**
	 * @param themeName the themeName to set
	 */
	public void setThemeName(String themeName) {
		this.themeName = themeName;
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
	 * @return the themeStatus
	 */
	public String getThemeStatus() {
		return themeStatus;
	}

	/**
	 * @param themeStatus the themeStatus to set
	 */
	public void setThemeStatus(String themeStatus) {
		this.themeStatus = themeStatus;
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
		return "ThemeJSON [themeId=" + themeId + ", themeName=" + themeName
				+ ", spId=" + spId + ", themeStatus=" + themeStatus
				+ ", syncStatus=" + syncStatus + ", wholeSyncStatus="
				+ wholeSyncStatus + ", wholeSyncStatusMessage="
				+ wholeSyncStatusMessage + "]";
	}
}
