/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

/**
 * @author Samy
 *
 */
public class FilterHierarchy {
	
	private String quater;
	
	private String year;
	
	private String status;
	
	private String strategicPillar;
	
	private String theme;
	
	private String outcome;
	
	private String output;
	
	private String responseEntity;
	
	private String partnerAgency;
	
	/**
	 * 
	 */
	public FilterHierarchy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the quater
	 */
	public String getQuater() {
		return quater;
	}

	/**
	 * @param quater the quater to set
	 */
	public void setQuater(String quater) {
		this.quater = quater;
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
	 * @return the strategicPillar
	 */
	public String getStrategicPillar() {
		return strategicPillar;
	}

	/**
	 * @param strategicPillar the strategicPillar to set
	 */
	public void setStrategicPillar(String strategicPillar) {
		this.strategicPillar = strategicPillar;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * @return the responseEntity
	 */
	public String getResponseEntity() {
		return responseEntity;
	}

	/**
	 * @param responseEntity the responseEntity to set
	 */
	public void setResponseEntity(String responseEntity) {
		this.responseEntity = responseEntity;
	}

	/**
	 * @return the partnerAgency
	 */
	public String getPartnerAgency() {
		return partnerAgency;
	}

	/**
	 * @param partnerAgency the partnerAgency to set
	 */
	public void setPartnerAgency(String partnerAgency) {
		this.partnerAgency = partnerAgency;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FilterHierarchy [quater=" + quater + ", year=" + year + ", status=" + status + ", strategicPillar=" + strategicPillar + ", theme="
				+ theme + ", outcome=" + outcome + ", output=" + output + ", responseEntity=" + responseEntity + ", partnerAgency=" + partnerAgency
				+ "]";
	}
	
}
