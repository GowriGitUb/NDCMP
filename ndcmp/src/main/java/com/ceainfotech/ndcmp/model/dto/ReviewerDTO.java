/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

/**
 * @author jeeva
 *
 */
public class ReviewerDTO {
	
	private Integer reportingperiodId;
	
	private String status;
	
	private String strategicPillar;
	
	private String theme;
	
	private String outcome;
	
	private String output;
	
	public ReviewerDTO() {
		// TODO Auto-generated constructor stub
	}


	public Integer getReportingperiodId() {
		return reportingperiodId;
	}


	public void setReportingperiodId(Integer reportingperiodId) {
		this.reportingperiodId = reportingperiodId;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStrategicPillar() {
		return strategicPillar;
	}

	public void setStrategicPillar(String strategicPillar) {
		this.strategicPillar = strategicPillar;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
}
