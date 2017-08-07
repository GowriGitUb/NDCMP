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
public class MobileThemeDTO {

	private Integer id;
	private String name;
	private String status;
	private List<MobileStrategicPillarDTO> mobileStrategicPillarDTOs = new ArrayList<MobileStrategicPillarDTO>();
	
	public MobileThemeDTO() {
	}

	public MobileThemeDTO(Integer id, String name, String status,
			List<MobileStrategicPillarDTO> mobileStrategicPillarDTOs) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.mobileStrategicPillarDTOs = mobileStrategicPillarDTOs;
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
	 * @return the mobileStrategicPillarDTOs
	 */
	public List<MobileStrategicPillarDTO> getMobileStrategicPillarDTOs() {
		return mobileStrategicPillarDTOs;
	}

	/**
	 * @param mobileStrategicPillarDTOs the mobileStrategicPillarDTOs to set
	 */
	public void setMobileStrategicPillarDTOs(
			List<MobileStrategicPillarDTO> mobileStrategicPillarDTOs) {
		this.mobileStrategicPillarDTOs = mobileStrategicPillarDTOs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MobileThemeDTO [id=" + id + ", name=" + name + ", status="
				+ status + ", mobileStrategicPillarDTOs="
				+ mobileStrategicPillarDTOs + "]";
	}
}
