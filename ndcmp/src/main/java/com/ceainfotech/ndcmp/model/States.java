/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Samy
 * 
 */

@Entity
@Table(name = "tbl_state")
public class States extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "STATE_NAME", length = 25, nullable = false)
	private String name;

	@Column(name = "STATE_CODE", length = 25, nullable = false, unique = true)
	private String code;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID")
	private Countries country;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "STATE_STATUS")
	private boolean stateStatus;
	
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

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the stateStatus
	 */
	public boolean isStateStatus() {
		return stateStatus;
	}

	/**
	 * @param stateStatus the stateStatus to set
	 */
	public void setStateStatus(boolean stateStatus) {
		this.stateStatus = stateStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (stateStatus ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		States other = (States) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stateStatus != other.stateStatus)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "States [name=" + name + ", code=" + code + ", country="
				+ country + ", status=" + status + ", stateStatus="
				+ stateStatus + "]";
	}
}
