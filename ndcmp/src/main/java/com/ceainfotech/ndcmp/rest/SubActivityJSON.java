/**
 * 
 */
package com.ceainfotech.ndcmp.rest;

import java.util.Set;

/**
 * @author Gowri
 *
 */
public class SubActivityJSON {
	
	private Integer subId;
	
	private String subSeqNumber;
	
	private String subName;
	
	private String subStatus;
	
	/*private Planning planning;*/
	
	private Integer keyActivityId;
	
	private Integer leadAgencyId;
	
	private Set<Integer> partnerAgency;
	
	private String mov;
	
	private Boolean syncStatus;
	
	private Boolean wholeSyncStatus;

	private String wholeSyncStatusMessage;

	public SubActivityJSON() {
	}

	public SubActivityJSON(Integer subId, String subSeqNumber, String subName,
			String subStatus, Integer keyActivityId, Integer leadAgencyId,
			Set<Integer> partnerAgency, String mov, Boolean syncStatus,
			Boolean wholeSyncStatus, String wholeSyncStatusMessage) {
		super();
		this.subId = subId;
		this.subSeqNumber = subSeqNumber;
		this.subName = subName;
		this.subStatus = subStatus;
		this.keyActivityId = keyActivityId;
		this.leadAgencyId = leadAgencyId;
		this.partnerAgency = partnerAgency;
		this.mov = mov;
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	public SubActivityJSON(Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	/**
	 * @return the subId
	 */
	public Integer getSubId() {
		return subId;
	}

	/**
	 * @param subId the subId to set
	 */
	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	/**
	 * @return the subSeqNumber
	 */
	public String getSubSeqNumber() {
		return subSeqNumber;
	}

	/**
	 * @param subSeqNumber the subSeqNumber to set
	 */
	public void setSubSeqNumber(String subSeqNumber) {
		this.subSeqNumber = subSeqNumber;
	}

	/**
	 * @return the subName
	 */
	public String getSubName() {
		return subName;
	}

	/**
	 * @param subName the subName to set
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}

	/**
	 * @return the subStatus
	 */
	public String getSubStatus() {
		return subStatus;
	}

	/**
	 * @param subStatus the subStatus to set
	 */
	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	/**
	 * @return the keyActivityId
	 */
	public Integer getKeyActivityId() {
		return keyActivityId;
	}

	/**
	 * @param keyActivityId the keyActivityId to set
	 */
	public void setKeyActivityId(Integer keyActivityId) {
		this.keyActivityId = keyActivityId;
	}

	/**
	 * @return the leadAgencyId
	 */
	public Integer getLeadAgencyId() {
		return leadAgencyId;
	}

	/**
	 * @param leadAgencyId the leadAgencyId to set
	 */
	public void setLeadAgencyId(Integer leadAgencyId) {
		this.leadAgencyId = leadAgencyId;
	}

	/**
	 * @return the partnerAgency
	 */
	public Set<Integer> getPartnerAgency() {
		return partnerAgency;
	}

	/**
	 * @param partnerAgency the partnerAgency to set
	 */
	public void setPartnerAgency(Set<Integer> partnerAgency) {
		this.partnerAgency = partnerAgency;
	}

	/**
	 * @return the mov
	 */
	public String getMov() {
		return mov;
	}

	/**
	 * @param mov the mov to set
	 */
	public void setMov(String mov) {
		this.mov = mov;
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
		return "SubActivityJSON [subId=" + subId + ", subSeqNumber="
				+ subSeqNumber + ", subName=" + subName + ", subStatus="
				+ subStatus + ", keyActivityId=" + keyActivityId
				+ ", leadAgencyId=" + leadAgencyId + ", partnerAgency="
				+ partnerAgency + ", mov=" + mov + ", syncStatus=" + syncStatus
				+ ", wholeSyncStatus=" + wholeSyncStatus
				+ ", wholeSyncStatusMessage=" + wholeSyncStatusMessage + "]";
	}
}
