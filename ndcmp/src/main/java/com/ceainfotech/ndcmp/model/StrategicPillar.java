/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author bosco
 * 
 */

@Entity
@Table(name = "tbl_strategic_pillar")
public class StrategicPillar extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "SEQUENCE_NUMBER", length = 25, nullable = false, unique = true)
	private String sequenceNumber;

	@Column(name = "STRATEGIC_PILLAR", length = 250, nullable = false, unique = true)
	private String name;

	@Column(name = "STATUS", length = 30, nullable = false)
	private String status;

	@Column(name = "SYNC_STATUS" , columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean syncStatus;
	
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID")
	private Project project;
	
	
	
	private transient List<Theme> themes;

	
	
	
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
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the themes
	 */
	public List<Theme> getThemes() {
		return themes;
	}

	/**
	 * @param themes the themes to set
	 */
	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result
				+ ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
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
		StrategicPillar other = (StrategicPillar) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (sequenceNumber == null) {
			if (other.sequenceNumber != null)
				return false;
		} else if (!sequenceNumber.equals(other.sequenceNumber))
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
		return "StrategicPillar [sequenceNumber=" + sequenceNumber + ", name=" + name + ", status=" + status + ", syncStatus=" + syncStatus
				+ ", project=" + project + "]";
	}

}
