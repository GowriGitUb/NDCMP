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
 */
@Entity
@Table(name="tbl_status_tracking")
public class StatusTracking extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "STATUS",referencedColumnName="id")
	private Statuss statuss;
	
	@Column(name = "ACTUAL_STATUS_COLOR")
	private String actualStatusColor;
	
	@Column(name = "ACTUAL_STATUS_PERCENTAGE")
	private String actualStatusPercentage;
	
	@Column(name="KEY_PROGRESS",columnDefinition="TEXT")
	private String keyProgress;
	
	@Column(name="REASON_FOR_GAP",columnDefinition="TEXT")
	private String reasonForGap;
	
	@Column(name="RECTIFY_THE_GAP",columnDefinition="TEXT")
	private String rectifyTheGap;
	
	@ManyToOne
	@JoinColumn(name="SUB_ACTIVITY_PLAN")
	private  SubActivity subActivity;
	
	@ManyToOne
	@JoinColumn(name = "REPORTINGPERIOD_ID", referencedColumnName = "id")
	private ReportingPeriod reportingPeriod;
	
	@Column(name="COMPLETE",columnDefinition="tinyint(1) default 0")
	private boolean complete = false;
	
	@Column(name="SENT_FOR_REVIEW", columnDefinition="tinyint(1) default 0")
	private boolean sentForReview = false;
	
	@Column(name="REWORK_REQUIRED", columnDefinition="tinyint(1) default 0")
	private boolean reworkRequired = false;
	
	@Column(name="REVIEW_STATUS")
	private Integer reviewStatus = 0;
	
	@ManyToOne
	@JoinColumn(name="REVIEWED_BY")
	private User reviewedBy;
	
	@Column(name="REVIEWED_ON")
	private String reviewedOn;
	
	@Column(name="REVIEWER_FEEDBACK",columnDefinition="TEXT")
	private String reviewerFeedback;
	
	@Column(name="USER_LEVEL")
	private Integer userLevel;
	
	/*@Column(name = "APPROVE_OR_COMPLETE_STATUS" , columnDefinition = "tinyint(1) default 0")
	private boolean approveORCompleteStatus = false;*/
	
	@Transient
	private String completeValue;
	
	@Transient
	private boolean approveStatus = false;
	
	@Transient
	private String review;
	
	@ManyToOne
	@JoinColumn(name="USER")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "AGENCY")
	private Agency agency;
	
	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	@Column(name="SYNC_DATETIME")
	private String sync_dateTime;

	@Transient
	private Integer userId;
	
	@Transient
	private Integer subActivityId;
	
	@Transient
	private Integer reportingPeriodId;
	
	@Transient
	private String carriedFrom;
	
	@Transient
	private boolean errorStatus = false;
	
	public Integer getReportingPeriodId() {
		return reportingPeriodId;
	}
	
	/**
	 * @return the approveStatus
	 */
	public boolean isApproveStatus() {
		return approveStatus;
	}



	/**
	 * @param approveStatus the approveStatus to set
	 */
	public void setApproveStatus(boolean approveStatus) {
		this.approveStatus = approveStatus;
	}



	public void setReportingPeriodId(Integer reportingPeriodId) {
		this.reportingPeriodId = reportingPeriodId;
	}

	@Column(name = "REPORTING_PERIOD_STATUS")
	private String reportingPeriodStatus;
	
	@Column(name = "SUBACTIVITY_STATUS")
	private String subActivityStatus;
	
	/**
	 * @return the completeValue
	 */
	public String getCompleteValue() {
		return completeValue;
	}

	/**
	 * @param completeValue the completeValue to set
	 */
	public void setCompleteValue(String completeValue) {
		this.completeValue = completeValue;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the subActivityId
	 */
	public Integer getSubActivityId() {
		return subActivityId;
	}

	/**
	 * @param subActivityId the subActivityId to set
	 */
	public void setSubActivityId(Integer subActivityId) {
		this.subActivityId = subActivityId;
	}

	/**
	 * @return the statuss
	 */
	public Statuss getStatuss() {
		return statuss;
	}

	/**
	 * @param statuss the statuss to set
	 */
	public void setStatuss(Statuss statuss) {
		this.statuss = statuss;
	}

	/**
	 * @return the actualStatusColor
	 */
	public String getActualStatusColor() {
		return actualStatusColor;
	}

	/**
	 * @param actualStatusColor the actualStatusColor to set
	 */
	public void setActualStatusColor(String actualStatusColor) {
		this.actualStatusColor = actualStatusColor;
	}

	/**
	 * @return the actualStatusPercentage
	 */
	public String getActualStatusPercentage() {
		return actualStatusPercentage;
	}

	/**
	 * @param actualStatusPercentage the actualStatusPercentage to set
	 */
	public void setActualStatusPercentage(String actualStatusPercentage) {
		this.actualStatusPercentage = actualStatusPercentage;
	}

	/**
	 * @return the keyProgress
	 */
	public String getKeyProgress() {
		return keyProgress;
	}

	/**
	 * @param keyProgress the keyProgress to set
	 */
	public void setKeyProgress(String keyProgress) {
		this.keyProgress = keyProgress;
	}

	/**
	 * @return the reasonForGap
	 */
	public String getReasonForGap() {
		return reasonForGap;
	}

	/**
	 * @param reasonForGap the reasonForGap to set
	 */
	public void setReasonForGap(String reasonForGap) {
		this.reasonForGap = reasonForGap;
	}

	/**
	 * @return the rectifyTheGap
	 */
	public String getRectifyTheGap() {
		return rectifyTheGap;
	}

	/**
	 * @param rectifyTheGap the rectifyTheGap to set
	 */
	public void setRectifyTheGap(String rectifyTheGap) {
		this.rectifyTheGap = rectifyTheGap;
	}

	/**
	 * @return the subActivity
	 */
	public SubActivity getSubActivity() {
		return subActivity;
	}

	/**
	 * @param subActivity the subActivity to set
	 */
	public void setSubActivity(SubActivity subActivity) {
		this.subActivity = subActivity;
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
	 * @return the sync_dateTime
	 */
	public String getSync_dateTime() {
		return sync_dateTime;
	}

	/**
	 * @param sync_dateTime the sync_dateTime to set
	 */
	public void setSync_dateTime(String sync_dateTime) {
		this.sync_dateTime = sync_dateTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the complete
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * @param complete the complete to set
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/**
	 * @return
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public ReportingPeriod getReportingPeriod() {
		return reportingPeriod;
	}

	public void setReportingPeriod(ReportingPeriod reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	public String getReportingPeriodStatus() {
		return reportingPeriodStatus;
	}

	public void setReportingPeriodStatus(String reportingPeriodStatus) {
		this.reportingPeriodStatus = reportingPeriodStatus;
	}

	public String getSubActivityStatus() {
		return subActivityStatus;
	}

	public void setSubActivityStatus(String subActivityStatus) {
		this.subActivityStatus = subActivityStatus;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	
	/**
	 * @return the reviewStatus
	 */
	public Integer getReviewStatus() {
		return reviewStatus;
	}

	/**
	 * @param reviewStatus the reviewStatus to set
	 */
	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	/**
	 * @return the reviewedBy
	 */
	public User getReviewedBy() {
		return reviewedBy;
	}

	/**
	 * @param reviewedBy the reviewedBy to set
	 */
	public void setReviewedBy(User reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	/**
	 * @return the reviewedOn
	 */
	public String getReviewedOn() {
		return reviewedOn;
	}

	/**
	 * @param reviewedOn the reviewedOn to set
	 */
	public void setReviewedOn(String reviewedOn) {
		this.reviewedOn = reviewedOn;
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
	 * @return the reworkRequired
	 */
	public boolean isReworkRequired() {
		return reworkRequired;
	}

	/**
	 * @param reworkRequired the reworkRequired to set
	 */
	public void setReworkRequired(boolean reworkRequired) {
		this.reworkRequired = reworkRequired;
	}

	/**
	 * @return the reviewerFeedback
	 */
	public String getReviewerFeedback() {
		return reviewerFeedback;
	}

	/**
	 * @param reviewerFeedback the reviewerFeedback to set
	 */
	public void setReviewerFeedback(String reviewerFeedback) {
		this.reviewerFeedback = reviewerFeedback;
	}
	
	public boolean isSentForReview() {
		return sentForReview;
	}

	public void setSentForReview(boolean sentForReview) {
		this.sentForReview = sentForReview;
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
	 * @return the carriedFrom
	 */
	public String getCarriedFrom() {
		return carriedFrom;
	}

	/**
	 * @param carriedFrom the carriedFrom to set
	 */
	public void setCarriedFrom(String carriedFrom) {
		this.carriedFrom = carriedFrom;
	}
	
	/**
	 * @return the errorStatus
	 */
	public boolean isErrorStatus() {
		return errorStatus;
	}

	/**
	 * @param errorStatus the errorStatus to set
	 */
	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatusTracking [statuss=" + statuss + ", actualStatusColor="
				+ actualStatusColor + ", actualStatusPercentage="
				+ actualStatusPercentage + ", keyProgress=" + keyProgress
				+ ", reasonForGap=" + reasonForGap + ", rectifyTheGap="
				+ rectifyTheGap + ", subActivity=" + subActivity
				+ ", reportingPeriod=" + reportingPeriod + ", complete="
				+ complete + ", sentForReview=" + sentForReview
				+ ", reworkRequired=" + reworkRequired + ", reviewStatus="
				+ reviewStatus + ", reviewedBy=" + reviewedBy + ", reviewedOn="
				+ reviewedOn + ", reviewerFeedback=" + reviewerFeedback
				+ ", userLevel=" + userLevel + ", completeValue=" + completeValue
				+ ", approveStatus=" + approveStatus + ", review=" + review
				+ ", user=" + user + ", deviceId=" + deviceId
				+ ", sync_dateTime=" + sync_dateTime + ", userId=" + userId
				+ ", subActivityId=" + subActivityId + ", reportingPeriodId="
				+ reportingPeriodId + ", reportingPeriodStatus="
				+ reportingPeriodStatus + ", subActivityStatus="
				+ subActivityStatus + "]";
	}
}
