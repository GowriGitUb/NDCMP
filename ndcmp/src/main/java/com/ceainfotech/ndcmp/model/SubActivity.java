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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Gowri
 *
 */
@Entity
@Table(name = "tbl_sub_activity")


public class SubActivity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name  = "SEQUENCE_NUMBER", length = 25, nullable = false)
	private String sequenceNumber;
	
	@Column(name  = "NAME")
	private String name;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "SUB_ACTIVITY_STATUS")
	private Boolean subActivityStatus;
	
	@Column(name = "SYNC_STATUS" , columnDefinition="BOOLEAN DEFAULT FALSE")
	private boolean syncStatus;
	
	@ManyToOne
	@JoinColumn(name = "PLANNING_ID",referencedColumnName = "ID")
	private Planning planning;
	
	@ManyToOne
	@JoinColumn(name = "KEY_ACTIVITY_ID",referencedColumnName = "ID")
	private KeyActivity keyActivity;
	
	@OneToOne
	@JoinColumn(name = "LEAD_AGENCY")
	private Agency leadAgency;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_partner_agency_mapping", joinColumns = { @JoinColumn(name = "SUB_ACTIVITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "PARTNER_AGENCY_ID") })
	private List<Agency> partnerAgency;
	
	@Column(name = "MOV")
	private String mov;
	
	@ManyToOne
	@JoinColumn(name = "COMPLETED_REPORTING_PERIOD")
	private ReportingPeriod completedReportingPeriod;
	
	@Column(name = "APPROVE_OR_COMPLETE_STATUS" , columnDefinition = "tinyint(1) default 0")
	private boolean approveORCompleteStatus = false;
	
	@Transient
	private  List<Integer> partnerAgencyTypeIds;
	
	@Transient
	private List<Planning> plannings;
	
	@Transient
	private StatusTracking statusTracking;
	
	@Transient
	private String colorCode;
	
	@Transient
	private String error;
	
	private transient boolean carryOverStatus;
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the colorCode
	 */
	public String getColorCode() {
		return colorCode;
	}

	/**
	 * @param colorCode the colorCode to set
	 */
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public SubActivity() {
	}

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
	 * @return the planning
	 */
	public Planning getPlanning() {
		return planning;
	}

	/**
	 * @param planning the planning to set
	 */
	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

	/**
	 * @return the keyActivity
	 */
	public KeyActivity getKeyActivity() {
		return keyActivity;
	}

	/**
	 * @param keyActivity the keyActivity to set
	 */
	public void setKeyActivity(KeyActivity keyActivity) {
		this.keyActivity = keyActivity;
	}

	/**
	 * @return the leadAgency
	 */
	public Agency getLeadAgency() {
		return leadAgency;
	}

	/**
	 * @param leadAgency the leadAgency to set
	 */
	public void setLeadAgency(Agency leadAgency) {
		this.leadAgency = leadAgency;
	}

	/**
	 * @return the partnerAgency
	 */
	public List<Agency> getPartnerAgency() {
		return partnerAgency;
	}

	/**
	 * @param partnerAgency the partnerAgency to set
	 */
	public void setPartnerAgency(List<Agency> partnerAgency) {
		this.partnerAgency = partnerAgency;
	}

	/**
	 * @return the mov
	 */
	public String getMov() {
		return mov;
	}

	/**
	 * @param mov the mov to set
	 */
	public void setMov(String mov) {
		this.mov = mov;
	}

	/**
	 * @return the partnerAgencyTypeIds
	 */
	public List<Integer> getPartnerAgencyTypeIds() {
		return partnerAgencyTypeIds;
	}

	/**
	 * @param partnerAgencyTypeIds the partnerAgencyTypeIds to set
	 */
	public void setPartnerAgencyTypeIds(List<Integer> partnerAgencyTypeIds) {
		this.partnerAgencyTypeIds = partnerAgencyTypeIds;
	}

	/**
	 * @return the regionStatus
	 */
	public Boolean isSubActivityStatus() {
		return subActivityStatus;
	}

	/**
	 * @param regionStatus the regionStatus to set
	 */
	public void setSubActivityStatus(Boolean subActivityStatus) {
		this.subActivityStatus = subActivityStatus;
	}
	
	
	/**
	 * @return the statusTracking
	 */
	public StatusTracking getStatusTracking() {
		return statusTracking;
	}

	/**
	 * @param statusTracking the statusTracking to set
	 */
	public void setStatusTracking(StatusTracking statusTracking) {
		this.statusTracking = statusTracking;
	}
	

	/**
	 * @return the plannings
	 */
	public List<Planning> getPlannings() {
		return plannings;
	}

	/**
	 * @param plannings the plannings to set
	 */
	public void setPlannings(List<Planning> plannings) {
		this.plannings = plannings;
	}
	
	/**
	 * @return the approveORCompleteStatus
	 */
	public boolean isApproveORCompleteStatus() {
		return approveORCompleteStatus;
	}

	/**
	 * @param approveORCompleteStatus the approveORCompleteStatus to set
	 */
	public void setApproveORCompleteStatus(boolean approveORCompleteStatus) {
		this.approveORCompleteStatus = approveORCompleteStatus;
	}
	
	/**
	 * @return the completedReportingPeriod
	 */
	public ReportingPeriod getCompletedReportingPeriod() {
		return completedReportingPeriod;
	}

	/**
	 * @param completedReportingPeriod the completedReportingPeriod to set
	 */
	public void setCompletedReportingPeriod(ReportingPeriod completedReportingPeriod) {
		this.completedReportingPeriod = completedReportingPeriod;
	}

	/**
	 * @return the subActivityStatus
	 */
	public Boolean getSubActivityStatus() {
		return subActivityStatus;
	}
	
	/**
	 * @return the carryOverStatus
	 */
	public boolean isCarryOverStatus() {
		return carryOverStatus;
	}

	/**
	 * @param carryOverStatus the carryOverStatus to set
	 */
	public void setCarryOverStatus(boolean carryOverStatus) {
		this.carryOverStatus = carryOverStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approveORCompleteStatus ? 1231 : 1237);
		result = prime * result
				+ ((colorCode == null) ? 0 : colorCode.hashCode());
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result
				+ ((keyActivity == null) ? 0 : keyActivity.hashCode());
		result = prime * result
				+ ((leadAgency == null) ? 0 : leadAgency.hashCode());
		result = prime * result + ((mov == null) ? 0 : mov.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((partnerAgency == null) ? 0 : partnerAgency.hashCode());
		result = prime
				* result
				+ ((partnerAgencyTypeIds == null) ? 0 : partnerAgencyTypeIds
						.hashCode());
		result = prime * result
				+ ((planning == null) ? 0 : planning.hashCode());
		result = prime * result
				+ ((plannings == null) ? 0 : plannings.hashCode());
		result = prime * result
				+ ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((statusTracking == null) ? 0 : statusTracking.hashCode());
		result = prime
				* result
				+ ((subActivityStatus == null) ? 0 : subActivityStatus
						.hashCode());
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
		SubActivity other = (SubActivity) obj;
		if (approveORCompleteStatus != other.approveORCompleteStatus)
			return false;
		if (colorCode == null) {
			if (other.colorCode != null)
				return false;
		} else if (!colorCode.equals(other.colorCode))
			return false;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		if (keyActivity == null) {
			if (other.keyActivity != null)
				return false;
		} else if (!keyActivity.equals(other.keyActivity))
			return false;
		if (leadAgency == null) {
			if (other.leadAgency != null)
				return false;
		} else if (!leadAgency.equals(other.leadAgency))
			return false;
		if (mov == null) {
			if (other.mov != null)
				return false;
		} else if (!mov.equals(other.mov))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (partnerAgency == null) {
			if (other.partnerAgency != null)
				return false;
		} else if (!partnerAgency.equals(other.partnerAgency))
			return false;
		if (partnerAgencyTypeIds == null) {
			if (other.partnerAgencyTypeIds != null)
				return false;
		} else if (!partnerAgencyTypeIds.equals(other.partnerAgencyTypeIds))
			return false;
		if (planning == null) {
			if (other.planning != null)
				return false;
		} else if (!planning.equals(other.planning))
			return false;
		if (plannings == null) {
			if (other.plannings != null)
				return false;
		} else if (!plannings.equals(other.plannings))
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
		if (statusTracking == null) {
			if (other.statusTracking != null)
				return false;
		} else if (!statusTracking.equals(other.statusTracking))
			return false;
		if (subActivityStatus == null) {
			if (other.subActivityStatus != null)
				return false;
		} else if (!subActivityStatus.equals(other.subActivityStatus))
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
		return "SubActivity [sequenceNumber=" + sequenceNumber + ", name="
				+ name + ", status=" + status + ", subActivityStatus="
				+ subActivityStatus + ", syncStatus=" + syncStatus
				+ ", planning=" + planning + ", keyActivity=" + keyActivity
				+ ", leadAgency=" + leadAgency + ", partnerAgency="
				+ partnerAgency + ", mov=" + mov + ", approveORCompleteStatus="
				+ approveORCompleteStatus + ", partnerAgencyTypeIds="
				+ partnerAgencyTypeIds + ", plannings=" + plannings
				+ ", statusTracking=" + statusTracking + ", colorCode="
				+ colorCode + ", error=" + error + "]";
	}
}
