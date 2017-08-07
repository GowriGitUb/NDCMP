/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

/**
 * @author pushpa
 *
 */
public class ActivityDashboardDTO {
	
	private String reportingPeriod;
	
	private Integer currentTotal;
	
	private Integer toBeCompleted;

	private Integer completed;
	
	private Integer notCompleted;
	
	private Integer outstandingTotal;
	
	private Integer outstandingCompleted;
	
	private Integer outstandingNotCompleted;

	public ActivityDashboardDTO(){
		currentTotal = toBeCompleted = completed = notCompleted = outstandingTotal = outstandingCompleted = outstandingNotCompleted = 0;
	}
	
	/**
	 * @return the reportingPeriod
	 */
	public String getReportingPeriod() {
		return reportingPeriod;
	}

	/**
	 * @param reportingPeriod the reportingPeriod to set
	 */
	public void setReportingPeriod(String reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	/**
	 * @return the toBeCompleted
	 */
	public Integer getToBeCompleted() {
		return toBeCompleted;
	}

	/**
	 * @param toBeCompleted the toBeCompleted to set
	 */
	public void setToBeCompleted(Integer toBeCompleted) {
		this.toBeCompleted = toBeCompleted;
	}

	/**
	 * @return the completed
	 */
	public Integer getCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(Integer completed) {
		this.completed = completed;
	}

	/**
	 * @return the notCompleted
	 */
	public Integer getNotCompleted() {
		return notCompleted;
	}

	/**
	 * @param notCompleted the notCompleted to set
	 */
	public void setNotCompleted(Integer notCompleted) {
		this.notCompleted = notCompleted;
	}

	/**
	 * @return the outstandingTotal
	 */
	public Integer getOutstandingTotal() {
		return outstandingTotal;
	}

	/**
	 * @param outstandingTotal the outstandingTotal to set
	 */
	public void setOutstandingTotal(Integer outstandingTotal) {
		this.outstandingTotal = outstandingTotal;
	}

	/**
	 * @return the outstandingCompleted
	 */
	public Integer getOutstandingCompleted() {
		return outstandingCompleted;
	}

	/**
	 * @param outstandingCompleted the outstandingCompleted to set
	 */
	public void setOutstandingCompleted(Integer outstandingCompleted) {
		this.outstandingCompleted = outstandingCompleted;
	}

	/**
	 * @return the outstandingNotCompleted
	 */
	public Integer getOutstandingNotCompleted() {
		return outstandingNotCompleted;
	}

	/**
	 * @param outstandingNotCompleted the outstandingNotCompleted to set
	 */
	public void setOutstandingNotCompleted(Integer outstandingNotCompleted) {
		this.outstandingNotCompleted = outstandingNotCompleted;
	}

	/**
	 * @return the currentTotal
	 */
	public Integer getCurrentTotal() {
		return currentTotal;
	}

	/**
	 * @param currentTotal the currentTotal to set
	 */
	public void setCurrentTotal(Integer currentTotal) {
		this.currentTotal = currentTotal;
	}
}
