/**
 * 
 */
package com.ceainfotech.ndcmp.rest;

/**
 * @author Gowri
 *
 */
public class MobileStrategicPillarDTO {

	private Integer id;
	private String name;
	private String status;
	
	public MobileStrategicPillarDTO() {
	}

	public MobileStrategicPillarDTO(Integer id, String name, String status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MobileStrategicPillarDTO [id=" + id + ", name=" + name
				+ ", status=" + status + "]";
	}
}
