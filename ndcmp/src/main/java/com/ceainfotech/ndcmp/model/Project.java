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
import javax.persistence.Transient;

/**
 * @author bosco
 *
 */

@Entity
@Table(name = "tbl_project")
public class Project extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="PROJECT_NAME",length=25 ,nullable=false)
	private String name;
	
	@Column(name="ADMIN", length=25, nullable=false, unique=true)
	private String admin;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID", referencedColumnName="ID")
	private Countries country;
	
	@ManyToOne
	@JoinColumn(name = "STATE_ID",referencedColumnName = "ID")
	private States states;
	
	@ManyToOne
	@JoinColumn(name = "REGION_ID", referencedColumnName = "ID")
	private Region region;

	@Column(name="DESCRIPTION", length=250, nullable=false, unique=true)
	private String description;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name = "SYNC_STATUS" , columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean syncStatus;
	
	@Transient
	private int projectCount;

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
	 * @return the admin
	 */
	public String getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	/**
	 * @return the country
	 */
	public Countries getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Countries country) {
		this.country = country;
	}

	/**
	 * @return the states
	 */
	public States getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(States states) {
		this.states = states;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the syncStatus
	 */
	public boolean isSyncStatus() {
		return syncStatus;
	}

	/**
	 * @param syncStatus the syncStatus to set
	 */
	public void setSyncStatus(boolean syncStatus) {
		this.syncStatus = syncStatus;
	}

	/**
	 * @return the projectCount
	 */
	public int getProjectCount() {
		return projectCount;
	}

	/**
	 * @param projectCount the projectCount to set
	 */
	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + projectCount;
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((states == null) ? 0 : states.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (syncStatus ? 1231 : 1237);
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
		Project other = (Project) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectCount != other.projectCount)
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (states == null) {
			if (other.states != null)
				return false;
		} else if (!states.equals(other.states))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (syncStatus != other.syncStatus)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Project [name=" + name + ", admin=" + admin + ", country="
				+ country + ", states=" + states + ", region=" + region
				+ ", description=" + description + ", status=" + status
				+ ", syncStatus=" + syncStatus + ", projectCount="
				+ projectCount + "]";
	}
}
