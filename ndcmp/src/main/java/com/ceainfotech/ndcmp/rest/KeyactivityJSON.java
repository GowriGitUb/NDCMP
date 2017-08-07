/**
 * 
 */
package com.ceainfotech.ndcmp.rest;


/**
 * @author Gowri
 *
 */
public class KeyactivityJSON {

	private Integer keyId;
	
	private String keySeqNumber;
	
	private String keyName;
	
	private Integer outputId;
	
	private String keyStatus;
	
	private Boolean syncStatus;
	
	private Boolean wholeSyncStatus;

	private String wholeSyncStatusMessage;
	
	public KeyactivityJSON() {
	}

	public KeyactivityJSON(Integer keyId, String keySeqNumber, String keyName,
			Integer outputId, String keyStatus, Boolean syncStatus,
			Boolean wholeSyncStatus, String wholeSyncStatusMessage) {
		super();
		this.keyId = keyId;
		this.keySeqNumber = keySeqNumber;
		this.keyName = keyName;
		this.outputId = outputId;
		this.keyStatus = keyStatus;
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	public KeyactivityJSON(Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	/**
	 * @return the keyId
	 */
	public Integer getKeyId() {
		return keyId;
	}

	/**
	 * @param keyId the keyId to set
	 */
	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}

	/**
	 * @return the keySeqNumber
	 */
	public String getKeySeqNumber() {
		return keySeqNumber;
	}

	/**
	 * @param keySeqNumber the keySeqNumber to set
	 */
	public void setKeySeqNumber(String keySeqNumber) {
		this.keySeqNumber = keySeqNumber;
	}

	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * @return the outputId
	 */
	public Integer getOutputId() {
		return outputId;
	}

	/**
	 * @param outputId the outputId to set
	 */
	public void setOutputId(Integer outputId) {
		this.outputId = outputId;
	}

	/**
	 * @return the keyStatus
	 */
	public String getKeyStatus() {
		return keyStatus;
	}

	/**
	 * @param keyStatus the keyStatus to set
	 */
	public void setKeyStatus(String keyStatus) {
		this.keyStatus = keyStatus;
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
		return "KeyactivityJSON [keyId=" + keyId + ", keySeqNumber="
				+ keySeqNumber + ", keyName=" + keyName + ", outputId="
				+ outputId + ", keyStatus=" + keyStatus + ", syncStatus="
				+ syncStatus + ", wholeSyncStatus=" + wholeSyncStatus
				+ ", wholeSyncStatusMessage=" + wholeSyncStatusMessage + "]";
	}
}
