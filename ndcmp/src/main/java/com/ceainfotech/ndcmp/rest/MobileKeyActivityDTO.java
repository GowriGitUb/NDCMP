/**
 * 
 */
package com.ceainfotech.ndcmp.rest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gowri
 *
 */
public class MobileKeyActivityDTO {

	private Integer id;
	private String sequenceNumber;
	private String keyActivity;
	private String status;
	private List<MobileOutputDTO> mobileOutputDTOs = new ArrayList<MobileOutputDTO>();
	
	public MobileKeyActivityDTO() {
	}

	public MobileKeyActivityDTO(Integer id, String sequenceNumber,
			String keyActivity, String status,
			List<MobileOutputDTO> mobileOutputDTOs) {
		super();
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.keyActivity = keyActivity;
		this.status = status;
		this.mobileOutputDTOs = mobileOutputDTOs;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param sequenceNumber the sequenceNumber to set
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
	 * @param keyActivity the keyActivity to set
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
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the mobileOutputDTOs
	 */
	public List<MobileOutputDTO> getMobileOutputDTOs() {
		return mobileOutputDTOs;
	}

	/**
	 * @param mobileOutputDTOs the mobileOutputDTOs to set
	 */
	public void setMobileOutputDTOs(List<MobileOutputDTO> mobileOutputDTOs) {
		this.mobileOutputDTOs = mobileOutputDTOs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MobileKeyActivityDTO [id=" + id + ", sequenceNumber="
				+ sequenceNumber + ", keyActivity=" + keyActivity + ", status="
				+ status + ", mobileOutputDTOs=" + mobileOutputDTOs + "]";
	}
}
