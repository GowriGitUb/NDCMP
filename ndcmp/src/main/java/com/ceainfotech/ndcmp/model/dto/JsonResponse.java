/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

import java.util.List;

import com.ceainfotech.ndcmp.model.ReportingPeriod;

/**
 * @author jeeva
 *
 */
public class JsonResponse {

	private List<OutputDTO> outputDTOs;
	
	private List<ReportingPeriod> reportingPeriods;
	
	private List<String> years;

	public List<OutputDTO> getOutputDTOs() {
		return outputDTOs;
	}

	public void setOutputDTOs(List<OutputDTO> outputDTOs) {
		this.outputDTOs = outputDTOs;
	}

	public List<ReportingPeriod> getReportingPeriods() {
		return reportingPeriods;
	}

	public void setReportingPeriods(List<ReportingPeriod> reportingPeriods) {
		this.reportingPeriods = reportingPeriods;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}
	
	
	
	
}
