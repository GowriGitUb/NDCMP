/**
 * 
 */
package com.ceainfotech.ndcmp.rest;


/**
 * @author Gowri
 *
 */
public class OutputJSON {

	private Integer outputId;
	
	private String outputSeqNumber;
	
	private String outputName;
	
	private String ouputStatus;
	
	private Integer outcomeId;
	
	private Boolean syncStatus;
	
	private Boolean wholeSyncStatus;

	private String wholeSyncStatusMessage;
	
	public OutputJSON() {
	}

	public OutputJSON(Integer outputId, String outputSeqNumber,
			String outputName, String ouputStatus, Integer outcomeId,
			Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.outputId = outputId;
		this.outputSeqNumber = outputSeqNumber;
		this.outputName = outputName;
		this.ouputStatus = ouputStatus;
		this.outcomeId = outcomeId;
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	public OutputJSON(Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
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
	 * @return the outputSeqNumber
	 */
	public String getOutputSeqNumber() {
		return outputSeqNumber;
	}

	/**
	 * @param outputSeqNumber the outputSeqNumber to set
	 */
	public void setOutputSeqNumber(String outputSeqNumber) {
		this.outputSeqNumber = outputSeqNumber;
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
	 * @return the ouputStatus
	 */
	public String getOuputStatus() {
		return ouputStatus;
	}

	/**
	 * @param ouputStatus the ouputStatus to set
	 */
	public void setOuputStatus(String ouputStatus) {
		this.ouputStatus = ouputStatus;
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
		return "OutputJSON [outputId=" + outputId + ", outputSeqNumber="
				+ outputSeqNumber + ", outputName=" + outputName
				+ ", ouputStatus=" + ouputStatus + ", outcomeId=" + outcomeId
				+ ", syncStatus=" + syncStatus + ", wholeSyncStatus="
				+ wholeSyncStatus + ", wholeSyncStatusMessage="
				+ wholeSyncStatusMessage + "]";
	}
}
