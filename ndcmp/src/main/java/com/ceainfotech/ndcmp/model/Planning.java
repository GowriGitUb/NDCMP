/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author bosco
 *
 */

@Entity
@Table(name="tbl_planning")
public class Planning extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SUB_ACTIVITY_ID",referencedColumnName="ID")
	private SubActivity subActivity;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="REPORTING_PERIOD_ID",referencedColumnName="ID")
	private ReportingPeriod reportingPeriod;
	
	@Column(name = "STATUS_OF_PROGRESS",columnDefinition="BOOLEAN DEFAULT FALSE")
	private Boolean statusOfProgress;

	@Transient
	private List<Integer> subActivityIds;
	
	@Transient
	private List<Integer> reportingPeriodIds;

	

	public SubActivity getSubActivity() {
		return subActivity;
	}

	public void setSubActivity(SubActivity subActivity) {
		this.subActivity = subActivity;
	}

	public ReportingPeriod getReportingPeriod() {
		return reportingPeriod;
	}

	public void setReportingPeriod(ReportingPeriod reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	

	/**
	 * @return the statusOfProgress
	 */
	public Boolean getStatusOfProgress() {
		return statusOfProgress;
	}

	/**
	 * @param statusOfProgress the statusOfProgress to set
	 */
	public void setStatusOfProgress(Boolean statusOfProgress) {
		this.statusOfProgress = statusOfProgress;
	}

	public List<Integer> getSubActivityIds() {
		return subActivityIds;
	}

	public void setSubActivityIds(List<Integer> subActivityIds) {
		this.subActivityIds = subActivityIds;
	}

	public List<Integer> getReportingPeriodIds() {
		return reportingPeriodIds;
	}

	public void setReportingPeriodIds(List<Integer> reportingPeriodIds) {
		this.reportingPeriodIds = reportingPeriodIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Planning [subActivity=" + subActivity + ", reportingPeriod="
				+ reportingPeriod + ", statusOfProgress=" + statusOfProgress
				+ ", subActivityIds=" + subActivityIds
				+ ", reportingPeriodIds=" + reportingPeriodIds + "]";
	}


	
}
