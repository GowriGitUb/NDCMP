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
public class MobileOutputDTO {

	private Integer id;
	private String sequenceNumber;
	private String name;
	private String status;
	private List<MobileOutcomeDTO> mobileOutcomeDTOs = new ArrayList<MobileOutcomeDTO>();
	
	
	public MobileOutputDTO() {
	}
	
	public MobileOutputDTO(Integer id, String sequenceNumber, String name,
			String status, List<MobileOutcomeDTO> mobileOutcomeDTOs) {
		super();
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.name = name;
		this.status = status;
		this.mobileOutcomeDTOs = mobileOutcomeDTOs;
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
	 * @return the mobileOutcomeDTOs
	 */
	public List<MobileOutcomeDTO> getMobileOutcomeDTOs() {
		return mobileOutcomeDTOs;
	}

	/**
	 * @param mobileOutcomeDTOs the mobileOutcomeDTOs to set
	 */
	public void setMobileOutcomeDTOs(List<MobileOutcomeDTO> mobileOutcomeDTOs) {
		this.mobileOutcomeDTOs = mobileOutcomeDTOs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MobileOutputDTO [id=" + id + ", sequenceNumber="
				+ sequenceNumber + ", name=" + name + ", status=" + status
				+ ", mobileOutcomeDTOs=" + mobileOutcomeDTOs + "]";
	}
}
