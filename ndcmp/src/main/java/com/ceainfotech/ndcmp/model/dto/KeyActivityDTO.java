/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samy
 * 
 */
public class KeyActivityDTO {

	private Integer id;
	private String sequenceNumber;
	private String keyActivity;
	private String status;
	private List<SubActivityDTO> subActivities = new ArrayList<SubActivityDTO>();

	/**
	 * 
	 */
	public KeyActivityDTO() {
	}

	/**
	 * @param id
	 * @param sequenceNumber
	 * @param keyActivity
	 * @param status
	 * @param subActivities
	 */
	public KeyActivityDTO(Integer id, String sequenceNumber, String keyActivity, String status, List<SubActivityDTO> subActivities) {
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.keyActivity = keyActivity;
		this.status = status;
		this.subActivities = subActivities;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the sequenceNumber
	 */
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber
	 *            the sequenceNumber to set
	 */
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return the keyActivity
	 */
	public String getKeyActivity() {
		return keyActivity;
	}

	/**
	 * @param keyActivity
	 *            the keyActivity to set
	 */
	public void setKeyActivity(String keyActivity) {
		this.keyActivity = keyActivity;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the subActivities
	 */
	public List<SubActivityDTO> getSubActivities() {
		return subActivities;
	}

	/**
	 * @param subActivities
	 *            the subActivities to set
	 */
	public void setSubActivities(List<SubActivityDTO> subActivities) {
		this.subActivities = subActivities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeyActivityDTO [id=" + id + ", sequenceNumber=" + sequenceNumber + ", keyActivity=" + keyActivity + ", status=" + status + "]";
	}

}
