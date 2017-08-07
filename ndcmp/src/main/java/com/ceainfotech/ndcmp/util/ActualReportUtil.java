/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.List;

import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;

/**
 * @author Gowri
 *
 */
public class ActualReportUtil {

	private List<KeyActivity> keyActivities;
	
	private List<StatusTracking> statusTrackings;
	
	private List<Planning> plannings;
	
	private List<ReportingPeriod> reportingPeriods;
	
	private List<String> years;
	
	public ActualReportUtil() {
	}

	/**
	 * @return the keyActivities
	 */
	public List<KeyActivity> getKeyActivities() {
		return keyActivities;
	}

	/**
	 * @param keyActivities the keyActivities to set
	 */
	public void setKeyActivities(List<KeyActivity> keyActivities) {
		this.keyActivities = keyActivities;
	}

	/**
	 * @return the statusTrackings
	 */
	public List<StatusTracking> getStatusTrackings() {
		return statusTrackings;
	}

	/**
	 * @param statusTrackings the statusTrackings to set
	 */
	public void setStatusTrackings(List<StatusTracking> statusTrackings) {
		this.statusTrackings = statusTrackings;
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
	 * @return the reportingPeriods
	 */
	public List<ReportingPeriod> getReportingPeriods() {
		return reportingPeriods;
	}

	/**
	 * @param reportingPeriods the reportingPeriods to set
	 */
	public void setReportingPeriods(List<ReportingPeriod> reportingPeriods) {
		this.reportingPeriods = reportingPeriods;
	}
	
	/**
	 * @return the years
	 */
	public List<String> getYears() {
		return years;
	}

	/**
	 * @param years the years to set
	 */
	public void setYears(List<String> years) {
		this.years = years;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActualReportUtil [keyActivities=" + keyActivities
				+ ", statusTrackings=" + statusTrackings + ", plannings="
				+ plannings + ", reportingPeriods=" + reportingPeriods
				+ ", years=" + years + "]";
	}
}
