package com.ceainfotech.ndcmp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_reportingPeriod")
public class ReportingPeriod extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 25)
	private String name;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startdate;

	private transient String sDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REPORTING_PERIOD_STATUS")
	private String reportingStatus;

	/*@ManyToOne
	@JoinColumn(name = "PLANNING_ID",referencedColumnName = "ID")
	private Planning planning;
	*/
	
	private transient String eDate;

	/**
	 * purpose : to load all the list for report printing by Gowri
	 */
	private transient LinkedHashSet<StrategicPillar> strategicPillars;
	private transient LinkedHashSet<Theme> themes;
	private transient LinkedHashSet<Outcome> outcomes;
	private transient LinkedHashSet<Output> outputs;
	private transient LinkedHashSet<KeyActivity> keyActivities ;
	private transient List<SubActivity> subActivities ;
	

	/**
	 * @return the reportingStatus
	 */
	public String getReportingStatus() {
		return reportingStatus;
	}

	/**
	 * @param reportingStatus the reportingStatus to set
	 */
	public void setReportingStatus(String reportingStatus) {
		this.reportingStatus = reportingStatus;
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
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the sDate
	 */
	public String getsDate() {
		return sDate;
	}

	/**
	 * @param sDate the sDate to set
	 */
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	/**
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
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
	 * @return the eDate
	 */
	public String geteDate() {
		return eDate;
	}

	/**
	 * @param eDate the eDate to set
	 */
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * @return the strategicPillars
	 */
	public LinkedHashSet<StrategicPillar> getStrategicPillars() {
		return strategicPillars;
	}

	/**
	 * @param strategicPillars the strategicPillars to set
	 */
	public void setStrategicPillars(LinkedHashSet<StrategicPillar> strategicPillars) {
		this.strategicPillars = strategicPillars;
	}

	/**
	 * @return the themes
	 */
	public LinkedHashSet<Theme> getThemes() {
		return themes;
	}

	/**
	 * @param themes the themes to set
	 */
	public void setThemes(LinkedHashSet<Theme> themes) {
		this.themes = themes;
	}

	/**
	 * @return the outcomes
	 */
	public LinkedHashSet<Outcome> getOutcomes() {
		return outcomes;
	}

	/**
	 * @param outcomes the outcomes to set
	 */
	public void setOutcomes(LinkedHashSet<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	/**
	 * @return the outputs
	 */
	public LinkedHashSet<Output> getOutputs() {
		return outputs;
	}

	/**
	 * @param outputs the outputs to set
	 */
	public void setOutputs(LinkedHashSet<Output> outputs) {
		this.outputs = outputs;
	}

	/**
	 * @return the keyActivities
	 */
	public LinkedHashSet<KeyActivity> getKeyActivities() {
		return keyActivities;
	}

	/**
	 * @param keyActivities the keyActivities to set
	 */
	public void setKeyActivities(LinkedHashSet<KeyActivity> keyActivities) {
		this.keyActivities = keyActivities;
	}

	/**
	 * @return the subActivities
	 */
	public List<SubActivity> getSubActivities() {
		return subActivities;
	}

	/**
	 * @param subActivities the subActivities to set
	 */
	public void setSubActivities(List<SubActivity> subActivities) {
		this.subActivities = subActivities;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReportingPeriod [name=" + name + ", year=" + year
				+ ", startdate=" + startdate + ", enddate=" + enddate
				+ ", status=" + status + ", reportingStatus=" + reportingStatus
				+ "]";
	}
}