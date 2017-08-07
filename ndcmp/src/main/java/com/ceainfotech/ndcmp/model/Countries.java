/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Samy
 * 
 */

@Entity
@Table(name = "tbl_country")
public class Countries extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "COUNTRY_NAME", length = 25, nullable = false)
	private String name;

	@Column(name = "COUNTRY_CODE", length = 25, nullable = false, unique = true)
	private String code;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "COUNTRY_STATUS")
	private boolean countryStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	 * @return the countryStatus
	 */
	public boolean isCountryStatus() {
		return countryStatus;
	}

	/**
	 * @param countryStatus the countryStatus to set
	 */
	public void setCountryStatus(boolean countryStatus) {
		this.countryStatus = countryStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Countries [name=" + name + ", code=" + code + ", status="
				+ status + ", countryStatus=" + countryStatus + "]";
	}
}
