/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gowri
 *
 */

@Entity
@Table(name = "tbl_allowedDeviceInfo")
public class AllowedDeviceInfo extends BaseEntity implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	@Column(name = "MOBILE_NAME")
	private String mobileName;
	
	@Column(name = "OS_VERSION")
	private String osVersion;
	
	@Column(name = "SDK_VERSION")
	private String sdkVersion;
	
	@Column(name = "AGENCY_IDS")
	private String agencyIds;
	
	@Column(name = "AGENCYCODE") 
	private String agencyCodes;
	
	@Column(name = "STAFF_ID")
	private String staffId;
	
	@Column(name = "REGISTRATION_DATE_TIME")
	private String registerationDateTime;
	
	@Column(name = "LASTSYNCEDTIME")
	private String lastSyncedTime;
	
	@Column(name = "TEMPSYNCREQUESTEDTIME")
	private String tempSyncRequestedTime;
	
	@Column(name = "DEVICE_STATUS", columnDefinition="tinyint(1) default 0")
	private boolean deviceStatus = false;
	
	@Column(name = "DELETE_STATUS", columnDefinition="tinyint(1) default 0")
	private boolean deleteStatus = false;
	
	@Column(name = "APPROVED_BY")
	private String approvedBy;
	
	@Column(name = "APPROVED_ON")
	private String approvedOn;
	
	public AllowedDeviceInfo() {
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the mobileName
	 */
	public String getMobileName() {
		return mobileName;
	}

	/**
	 * @param mobileName the mobileName to set
	 */
	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	/**
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the registerationDateTime
	 */
	public String getRegisterationDateTime() {
		return registerationDateTime;
	}

	/**
	 * @param registerationDateTime the registerationDateTime to set
	 */
	public void setRegisterationDateTime(String registerationDateTime) {
		this.registerationDateTime = registerationDateTime;
	}

	/**
	 * @return the deviceStatus
	 */
	public boolean isDeviceStatus() {
		return deviceStatus;
	}

	/**
	 * @param deviceStatus the deviceStatus to set
	 */
	public void setDeviceStatus(boolean deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	/**
	 * @return the approvedBy
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * @param approvedBy the approvedBy to set
	 */
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @return the approvedOn
	 */
	public String getApprovedOn() {
		return approvedOn;
	}

	/**
	 * @param approvedOn the approvedOn to set
	 */
	public void setApprovedOn(String approvedOn) {
		this.approvedOn = approvedOn;
	}

	/**
	 * @return the sdkVersion
	 */
	public String getSdkVersion() {
		return sdkVersion;
	}

	/**
	 * @param sdkVersion the sdkVersion to set
	 */
	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getAgencyIds() {
		return agencyIds;
	}

	public void setAgencyIds(String agencyIds) {
		this.agencyIds = agencyIds;
	}

	/**
	 * @return the lastSyncedTime
	 */
	public String getLastSyncedTime() {
		return lastSyncedTime;
	}

	/**
	 * @param lastSyncedTime the lastSyncedTime to set
	 */
	public void setLastSyncedTime(String lastSyncedTime) {
		this.lastSyncedTime = lastSyncedTime;
	}

	/**
	 * @return the tempSyncRequestedTime
	 */
	public String getTempSyncRequestedTime() {
		return tempSyncRequestedTime;
	}

	/**
	 * @param tempSyncRequestedTime the tempSyncRequestedTime to set
	 */
	public void setTempSyncRequestedTime(String tempSyncRequestedTime) {
		this.tempSyncRequestedTime = tempSyncRequestedTime;
	}

	/**
	 * @return the agencyCodes
	 */
	public String getAgencyCodes() {
		return agencyCodes;
	}

	/**
	 * @param agencyCodes the agencyCodes to set
	 */
	public void setAgencyCodes(String agencyCodes) {
		this.agencyCodes = agencyCodes;
	}

	/**
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	
	/**
	 * @return the deleteStatus
	 */
	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	/**
	 * @param deleteStatus the deleteStatus to set
	 */
	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AllowedDeviceInfo [deviceId=" + deviceId + ", mobileName="
				+ mobileName + ", osVersion=" + osVersion + ", sdkVersion="
				+ sdkVersion + ", agencyIds=" + agencyIds + ", agencyCodes="
				+ agencyCodes + ", staffId=" + staffId
				+ ", registerationDateTime=" + registerationDateTime
				+ ", lastSyncedTime=" + lastSyncedTime
				+ ", tempSyncRequestedTime=" + tempSyncRequestedTime
				+ ", deviceStatus=" + deviceStatus + ", approvedBy="
				+ approvedBy + ", approvedOn=" + approvedOn + "]";
	}
}
