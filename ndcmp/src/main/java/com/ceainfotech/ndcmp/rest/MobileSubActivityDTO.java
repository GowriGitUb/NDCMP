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
public class MobileSubActivityDTO {


	private Integer id;
	private String sequenceNumber;
	private String subActivity;
	private String status;
	private List<MobileKeyActivityDTO> mobileKeyActivityDTOs = new ArrayList<MobileKeyActivityDTO>();
	private String mov;
	
	public MobileSubActivityDTO() {
	}

	public MobileSubActivityDTO(Integer id, String sequenceNumber,
			String subActivity, String status,
			List<MobileKeyActivityDTO> mobileKeyActivityDTOs, String mov) {
		super();
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.subActivity = subActivity;
		this.status = status;
		this.mobileKeyActivityDTOs = mobileKeyActivityDTOs;
		this.mov = mov;
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
	 * @return the subActivity
	 */
	public String getSubActivity() {
		return subActivity;
	}

	/**
	 * @param subActivity the subActivity to set
	 */
	public void setSubActivity(String subActivity) {
		this.subActivity = subActivity;
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
	 * @return the mobileKeyActivityDTOs
	 */
	public List<MobileKeyActivityDTO> getMobileKeyActivityDTOs() {
		return mobileKeyActivityDTOs;
	}

	/**
	 * @param mobileKeyActivityDTOs the mobileKeyActivityDTOs to set
	 */
	public void setMobileKeyActivityDTOs(
			List<MobileKeyActivityDTO> mobileKeyActivityDTOs) {
		this.mobileKeyActivityDTOs = mobileKeyActivityDTOs;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MobileSubActivityDTO [id=" + id + ", sequenceNumber="
				+ sequenceNumber + ", subActivity=" + subActivity + ", status="
				+ status + ", mobileKeyActivityDTOs=" + mobileKeyActivityDTOs
				+ ", mov=" + mov + "]";
	}
}
