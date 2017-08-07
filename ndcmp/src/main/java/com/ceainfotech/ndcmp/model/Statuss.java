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
@Table(name = "tbl_status")
public class Statuss extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8807051728987118103L;

	

	@Column(name = "NAME", length = 50, nullable = false, unique = true)
	private String name;

	@Column(name = "COLOR", length = 100)
	private String statusColor;

	@Column(name = "DESCRIPTION", length = 250)
	private String description;

	@Column(name = "START_RANGE")
	private Integer startRange;

	@Column(name = "END_RANGE")
	private Integer endRange;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "STATUS_STATUS")
	private Boolean statusStatus;
	
	@Column(name="COLOR_PERCENT")
	private Integer colorPercent;
	

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the colorPercent
	 */
	public Integer getColorPercent() {
		return colorPercent;
	}

	/**
	 * @param colorPercent the colorPercent to set
	 */
	public void setColorPercent(Integer colorPercent) {
		this.colorPercent = colorPercent;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusStatus
	 */
	public Boolean getStatusStatus() {
		return statusStatus;
	}

	/**
	 * @param statusStatus the statusStatus to set
	 */
	public void setStatusStatus(Boolean statusStatus) {
		this.statusStatus = statusStatus;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatusColor() {
		return statusColor;
	}

	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStartRange() {
		return startRange;
	}

	public void setStartRange(Integer startRange) {
		this.startRange = startRange;
	}

	public Integer getEndRange() {
		return endRange;
	}

	public void setEndRange(Integer endRange) {
		this.endRange = endRange;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Statuss [name=" + name + ", statusColor=" + statusColor
				+ ", description=" + description + ", startRange=" + startRange
				+ ", endRange=" + endRange + ", status=" + status
				+ ", statusStatus=" + statusStatus + ", colorPercent="
				+ colorPercent + "]";
	}


}
