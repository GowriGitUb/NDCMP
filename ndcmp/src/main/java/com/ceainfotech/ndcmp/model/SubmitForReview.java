/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author bosco
 *
 */
@Entity
@Table(name="tbl_submit_review")
public class SubmitForReview extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="KEY_LEARNING",columnDefinition="TEXT")
	private String keyLearning;
	
	@Column(name="KEY_CHALLENGE",columnDefinition="TEXT")
	private String keyChallenge;
	
	@Column(name="BEST_PRACTICE",columnDefinition="TEXT")
	private String bestPractice;
	
	@Column(name="SUPPORT_NEEDS",columnDefinition="TEXT")
	private String supportNeeds;
	
	@Column(name = "DEVICEID")
	private String deviceId;
	
	@Column(name="SUBMIT_DATETIME")
	private Date submit_dateTime;
	
	@Column(name="USER_LEVEL")
	private Integer userLevel;
	
	@Column(name="LEAD_REWORK_STATUS", columnDefinition="tinyint(1) default 0")
	private boolean leadReworkStatus = false;
	
	@Column(name="PARTNER_REWORK_STATUS", columnDefinition="tinyint(1) default 0")
	private boolean partnerReworkStatus = false;
	
	@Column(name="REWORK_COUNT")
	private Integer reworkCount = 0;
	
	@OneToOne
	@JoinColumn(name="USER")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "REPORTING_PERIOD")
	private ReportingPeriod reportingPeriod;
	
	@ManyToOne
	@JoinColumn(name = "AGENCY")
	private Agency agency;
	
	@Transient
	private String total;
	
	@Transient
	private String remaining;
	
	@Transient
	private String completed;
	
	@Transient
	private String reportPeriodId;
	
	/**
	 * @return the reworkCount
	 */
	public Integer getReworkCount() {
		return reworkCount;
	}

	/**
	 * @param reworkCount the reworkCount to set
	 */
	public void setReworkCount(Integer reworkCount) {
		this.reworkCount = reworkCount;
	}

	/**
	 * @return the reportPeriodId
	 */
	public String getReportPeriodId() {
		return reportPeriodId;
	}

	/**
	 * @param reportPeriodId the reportPeriodId to set
	 */
	public void setReportPeriodId(String reportPeriodId) {
		this.reportPeriodId = reportPeriodId;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the remaining
	 */
	public String getRemaining() {
		return remaining;
	}

	/**
	 * @param remaining the remaining to set
	 */
	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}

	
	/**
	 * @return the completed
	 */
	public String getCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(String completed) {
		this.completed = completed;
	}

	/**
	 * @return the keyLearning
	 */
	public String getKeyLearning() {
		return keyLearning;
	}

	/**
	 * @param keyLearning the keyLearning to set
	 */
	public void setKeyLearning(String keyLearning) {
		this.keyLearning = keyLearning;
	}

	/**
	 * @return the keyChallenge
	 */
	public String getKeyChallenge() {
		return keyChallenge;
	}

	/**
	 * @param keyChallenge the keyChallenge to set
	 */
	public void setKeyChallenge(String keyChallenge) {
		this.keyChallenge = keyChallenge;
	}

	/**
	 * @return the bestPractice
	 */
	public String getBestPractice() {
		return bestPractice;
	}

	/**
	 * @param bestPractice the bestPractice to set
	 */
	public void setBestPractice(String bestPractice) {
		this.bestPractice = bestPractice;
	}

	/**
	 * @return the supportNeeds
	 */
	public String getSupportNeeds() {
		return supportNeeds;
	}

	/**
	 * @param supportNeeds the supportNeeds to set
	 */
	public void setSupportNeeds(String supportNeeds) {
		this.supportNeeds = supportNeeds;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the submit_dateTime
	 */
	public Date getSubmit_dateTime() {
		return submit_dateTime;
	}

	/**
	 * @param submit_dateTime the submit_dateTime to set
	 */
	public void setSubmit_dateTime(Date submit_dateTime) {
		this.submit_dateTime = submit_dateTime;
	}

	public ReportingPeriod getReportingPeriod() {
		return reportingPeriod;
	}

	public void setReportingPeriod(ReportingPeriod reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the agency
	 */
	public Agency getAgency() {
		return agency;
	}

	/**
	 * @param agency the agency to set
	 */
	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	/**
	 * @return the userLevel
	 */
	public Integer getUserLevel() {
		return userLevel;
	}

	/**
	 * @param userLevel the userLevel to set
	 */
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * @return the leadReworkStatus
	 */
	public boolean isLeadReworkStatus() {
		return leadReworkStatus;
	}

	/**
	 * @param leadReworkStatus the leadReworkStatus to set
	 */
	public void setLeadReworkStatus(boolean leadReworkStatus) {
		this.leadReworkStatus = leadReworkStatus;
	}

	/**
	 * @return the partnerReworkStatus
	 */
	public boolean isPartnerReworkStatus() {
		return partnerReworkStatus;
	}

	/**
	 * @param partnerReworkStatus the partnerReworkStatus to set
	 */
	public void setPartnerReworkStatus(boolean partnerReworkStatus) {
		this.partnerReworkStatus = partnerReworkStatus;
	}
}
