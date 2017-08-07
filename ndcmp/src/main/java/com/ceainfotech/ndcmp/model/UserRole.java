package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Gowri
 * 
 */

@Entity
@Table(name = "tbl_user_group")
public class UserRole extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 25, unique = true, nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", length = 250)
	private String description;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "USER_ROLE_STATUS")
	private boolean userRoleStatus;
	/**
	 * 
	 */
	public UserRole() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param description
	 * @param status
	 */
	public UserRole(Integer id) {
		super.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the userRoleStatus
	 */
	public boolean isUserRoleStatus() {
		return userRoleStatus;
	}

	/**
	 * @param userRoleStatus the userRoleStatus to set
	 */
	public void setUserRoleStatus(boolean userRoleStatus) {
		this.userRoleStatus = userRoleStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (userRoleStatus ? 1231 : 1237);
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
		UserRole other = (UserRole) obj;
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userRoleStatus != other.userRoleStatus)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRole [name=" + name + ", description=" + description
				+ ", status=" + status + ", userRoleStatus=" + userRoleStatus
				+ "]";
	}
}
