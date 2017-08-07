/**
 * 
 */
package com.ceainfotech.ndcmp.rest;


/**
 * @author Gowri
 *
 */
public class OutcomeJSON {

	private Integer outcomeId;
	
	private String outcomeSeqNumber;
	
	private String outputName;
	
	private String outputStatus;
	
	private Integer themeId;
	
	private Boolean syncStatus;
	
	private Boolean wholeSyncStatus;

	private String wholeSyncStatusMessage;
	
	public OutcomeJSON() {
	}

	public OutcomeJSON(Integer outcomeId, String outcomeSeqNumber,
			String outputName, String outputStatus, Integer themeId,
			Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.outcomeId = outcomeId;
		this.outcomeSeqNumber = outcomeSeqNumber;
		this.outputName = outputName;
		this.outputStatus = outputStatus;
		this.themeId = themeId;
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	public OutcomeJSON(Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	/**
	 * @return the outcomeId
	 */
	public Integer getOutcomeId() {
		return outcomeId;
	}

	/**
	 * @param outcomeId the outcomeId to set
	 */
	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
	}

	/**
	 * @return the outcomeSeqNumber
	 */
	public String getOutcomeSeqNumber() {
		return outcomeSeqNumber;
	}

	/**
	 * @param outcomeSeqNumber the outcomeSeqNumber to set
	 */
	public void setOutcomeSeqNumber(String outcomeSeqNumber) {
		this.outcomeSeqNumber = outcomeSeqNumber;
	}

	/**
	 * @return the outputName
	 */
	public String getOutputName() {
		return outputName;
	}

	/**
	 * @param outputName the outputName to set
	 */
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	/**
	 * @return the outputStatus
	 */
	public String getOutputStatus() {
		return outputStatus;
	}

	/**
	 * @param outputStatus the outputStatus to set
	 */
	public void setOutputStatus(String outputStatus) {
		this.outputStatus = outputStatus;
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
		return "OutcomeJSON [outcomeId=" + outcomeId + ", outcomeSeqNumber="
				+ outcomeSeqNumber + ", outputName=" + outputName
				+ ", outputStatus=" + outputStatus + ", themeId=" + themeId
				+ ", syncStatus=" + syncStatus + ", wholeSyncStatus="
				+ wholeSyncStatus + ", wholeSyncStatusMessage="
				+ wholeSyncStatusMessage + "]";
	}
}
