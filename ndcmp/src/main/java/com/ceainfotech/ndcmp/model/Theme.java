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
@Table(name = "tbl_theme")
public class Theme extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "THEME", length = 250, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "STRATEGIC_PILLAR_ID", referencedColumnName = "ID")
	private StrategicPillar strategicPillar;

	@Column(name = "STATUS", length = 20)
	private String status;
	
	@Column(name = "SYNC_STATUS" , columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean syncStatus;

	private transient List<Outcome> outcomes;
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
	 * @return the strategicPillar
	 */
	public StrategicPillar getStrategicPillar() {
		return strategicPillar;
	}

	/**
	 * @param strategicPillar the strategicPillar to set
	 */
	public void setStrategicPillar(StrategicPillar strategicPillar) {
		this.strategicPillar = strategicPillar;
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
	 * @return the outcomes
	 */
	public List<Outcome> getOutcomes() {
		return outcomes;
	}

	/**
	 * @param outcomes the outcomes to set
	 */
	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((strategicPillar == null) ? 0 : strategicPillar.hashCode());
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
		Theme other = (Theme) obj;
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
		if (strategicPillar == null) {
			if (other.strategicPillar != null)
				return false;
		} else if (!strategicPillar.equals(other.strategicPillar))
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
		return "Theme [name=" + name + ", strategicPillar=" + strategicPillar + ", status=" + status + ", syncStatus=" + syncStatus + "]";
	}
	
}
