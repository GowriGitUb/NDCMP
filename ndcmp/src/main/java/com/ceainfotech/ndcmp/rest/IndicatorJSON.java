/**
 * 
 */
package com.ceainfotech.ndcmp.rest;


/**
 * @author Gowri
 *
 */
public class IndicatorJSON {

	private Integer indicatorId;
	
	private String message;
	
	private Integer outputId;
	
	private Boolean syncStatus;
	
	private Boolean wholeSyncStatus;

	private String wholeSyncStatusMessage;
	
	public IndicatorJSON() {
	}

	public IndicatorJSON(Integer indicatorId, String message, Integer outputId,
			Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.indicatorId = indicatorId;
		this.message = message;
		this.outputId = outputId;
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	public IndicatorJSON(Boolean syncStatus, Boolean wholeSyncStatus,
			String wholeSyncStatusMessage) {
		super();
		this.syncStatus = syncStatus;
		this.wholeSyncStatus = wholeSyncStatus;
		this.wholeSyncStatusMessage = wholeSyncStatusMessage;
	}

	/**
	 * @return the indicatorId
	 */
	public Integer getIndicatorId() {
		return indicatorId;
	}

	/**
	 * @param indicatorId the indicatorId to set
	 */
	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
		return "IndicatorJSON [indicatorId=" + indicatorId + ", message="
				+ message + ", outputId=" + outputId + ", syncStatus="
				+ syncStatus + ", wholeSyncStatus=" + wholeSyncStatus
				+ ", wholeSyncStatusMessage=" + wholeSyncStatusMessage + "]";
	}
}
