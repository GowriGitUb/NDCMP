/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

import java.util.Date;

/**
 * @author Samy
 * 
 */
public class ReportingPeriodDTO {

	private Integer id;
	private String name;
	private String year;
	private Date startDate;
	private Date endDate;
	private String status;

	/**
	 * 
	 */
	public ReportingPeriodDTO() {
	}

	/**
	 * @param id
	 * @param name
	 * @param year
	 * @param startDate
	 * @param endDate
	 * @param status
	 */
	public ReportingPeriodDTO(Integer id, String name, String year, Date startDate, Date endDate, String status) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReportingPeriodDTO [id=" + id + ", name=" + name + ", year=" + year + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + "]";
	}

}
