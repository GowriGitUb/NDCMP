/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author pushpa
 *
 */
@Entity
@Table(name = "tbl_status_tracking_log")
public class StatusTrackingLog extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "SUBACTIVITY", referencedColumnName = "id")
	private SubActivity subActivity;
	
	@ManyToOne
	@JoinColumn(name = "REPORTING_PERIOD", referencedColumnName="id")
	private ReportingPeriod reportingPeriod;
	
	@ManyToOne
	@JoinColumn(name="USER")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "STATUS",referencedColumnName="id")
	private Statuss statuss;
	
	@ManyToOne
	@JoinColumn(name="REVIEWED_BY")
	private User reviewedBy;
	
	@ManyToOne
	@JoinColumn(name = "STATUS_TRACKING_ID")
	private StatusTracking statusTracking;
	
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
	
	@Column(name="COMPLETE",columnDefinition="tinyint(1) default 0")
	private boolean complete = false;
	
	@Column(name="SENT_FOR_REVIEW", columnDefinition="tinyint(1) default 0")
	private boolean sentForReview = false;
	
	@Column(name="REWORK_REQUIRED", columnDefinition="tinyint(1) default 0")
	private boolean reworkRequired = false;
	
	@Column(name="REVIEW_STATUS")
	private Integer reviewStatus = 0;
	
	@Column(name="REVIEWED_ON")
	private String reviewedOn;
	
	@Column(name="REVIEWER_FEEDBACK",columnDefinition="TEXT")
	private String reviewerFeedback;
	
	@Column(name="USER_LEVEL")
	private Integer userLevel;
	
	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	@Column(name="SYNC_DATETIME")
	private String sync_dateTime;
	
	@Column(name="SYNC_STATUS")
	private boolean syncStatus;
	
	@Column(name="SYNC_FAILURE_MESSAGE")
	private String syncFailureMessage;
	
	@Column(name = "ACTION_TYPE")
	private String actionType;

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
	 * @return the reportingPeriod
	 */
	public ReportingPeriod getReportingPeriod() {
		return reportingPeriod;
	}

	/**
	 * @param reportingPeriod the reportingPeriod to set
	 */
	public void setReportingPeriod(ReportingPeriod reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
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
	 * @return the syncFailureMessage
	 */
	public String getSyncFailureMessage() {
		return syncFailureMessage;
	}

	/**
	 * @param syncFailureMessage the syncFailureMessage to set
	 */
	public void setSyncFailureMessage(String syncFailureMessage) {
		this.syncFailureMessage = syncFailureMessage;
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
	 * @return the sentForReview
	 */
	public boolean isSentForReview() {
		return sentForReview;
	}

	/**
	 * @param sentForReview the sentForReview to set
	 */
	public void setSentForReview(boolean sentForReview) {
		this.sentForReview = sentForReview;
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
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
