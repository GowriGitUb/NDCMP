/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

/**
 * @author pushpa
 *
 */
public class DashboardDTO {
	
	private String reportingPeriod;
	
	private Integer expected;
	
	private Integer updated;
	
	private Integer notUpdated;
	
	private Integer outstandingExpected;
	
	private Integer outstandingUpdated;
	
	private Integer outstandingNotUpdated;
	
	
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
	 * @return the expected
	 */
	public Integer getExpected() {
		return expected;
	}

	/**
	 * @param expected the expected to set
	 */
	public void setExpected(Integer expected) {
		this.expected = expected;
	}

	/**
	 * @return the updated
	 */
	public Integer getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Integer updated) {
		this.updated = updated;
	}

	/**
	 * @return the notUpdated
	 */
	public Integer getNotUpdated() {
		return notUpdated;
	}

	/**
	 * @param notUpdated the notUpdated to set
	 */
	public void setNotUpdated(Integer notUpdated) {
		this.notUpdated = notUpdated;
	}

	/**
	 * @return the outstandingExpected
	 */
	public Integer getOutstandingExpected() {
		return outstandingExpected;
	}

	/**
	 * @param outstandingExpected the outstandingExpected to set
	 */
	public void setOutstandingExpected(Integer outstandingExpected) {
		this.outstandingExpected = outstandingExpected;
	}

	/**
	 * @return the outstandingUpdated
	 */
	public Integer getOutstandingUpdated() {
		return outstandingUpdated;
	}

	/**
	 * @param outstandingUpdated the outstandingUpdated to set
	 */
	public void setOutstandingUpdated(Integer outstandingUpdated) {
		this.outstandingUpdated = outstandingUpdated;
	}

	/**
	 * @return the outstandingNotUpdated
	 */
	public Integer getOutstandingNotUpdated() {
		return outstandingNotUpdated;
	}

	/**
	 * @param outstandingNotUpdated the outstandingNotUpdated to set
	 */
	public void setOutstandingNotUpdated(Integer outstandingNotUpdated) {
		this.outstandingNotUpdated = outstandingNotUpdated;
	}
	
}
