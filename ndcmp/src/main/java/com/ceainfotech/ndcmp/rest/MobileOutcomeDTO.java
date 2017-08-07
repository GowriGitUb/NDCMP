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
public class MobileOutcomeDTO {

	private Integer id;
	private String sequenceNumber;
	private String name;
	private String status;
	private List<MobileThemeDTO> mobileThemeDTOs = new ArrayList<MobileThemeDTO>();
	
	public MobileOutcomeDTO() {
	}

	public MobileOutcomeDTO(Integer id, String sequenceNumber, String name,
			String status, List<MobileThemeDTO> mobileThemeDTOs) {
		super();
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.name = name;
		this.status = status;
		this.mobileThemeDTOs = mobileThemeDTOs;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the mobileThemeDTOs
	 */
	public List<MobileThemeDTO> getMobileThemeDTOs() {
		return mobileThemeDTOs;
	}

	/**
	 * @param mobileThemeDTOs the mobileThemeDTOs to set
	 */
	public void setMobileThemeDTOs(List<MobileThemeDTO> mobileThemeDTOs) {
		this.mobileThemeDTOs = mobileThemeDTOs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MobileOutcomeDTO [id=" + id + ", sequenceNumber="
				+ sequenceNumber + ", name=" + name + ", status=" + status
				+ ", mobileThemeDTOs=" + mobileThemeDTOs + "]";
	}
}
