/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author Samy
 * 
 */
public class SubActivityDTO {

	private Integer id;
	private String sequenceNumber;
	private String subActivity;
	private String status;
	private AgencyDTO leadAgency;
	private List<AgencyDTO> partnerAgencies = new ArrayList<AgencyDTO>();
	private Set<AgencyDTO> agencyDTOs = new HashSet<AgencyDTO>();
	private List<ReportingPeriodDTO> reportingPeriods = new ArrayList<ReportingPeriodDTO>();
	private String mov;
	private String colorCode;
	private String statusIcon;
	private boolean approveORCompleteStatus;
	private ReportingPeriod completedReportingPeriod;
	private boolean carryOverStatus;
	/**
	 * 
	 */
	public SubActivityDTO() {
	}

	/**
	 * @param id
	 * @param sequenceNumber
	 * @param subActivity
	 * @param status
	 * @param leadAgency
	 * @param partnerAgencies
	 * @param reportingPeriods
	 * @param mov
	 * @param reportingPeriodDTOs
	 */
	public SubActivityDTO(Integer id, String sequenceNumber, String subActivity, String status, AgencyDTO leadAgency,
			List<AgencyDTO> partnerAgencies, List<ReportingPeriodDTO> reportingPeriods, String mov,String colorCode) {
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.subActivity = subActivity;
		this.status = status;
		this.leadAgency = leadAgency;
		this.partnerAgencies = partnerAgencies;
		this.reportingPeriods = reportingPeriods;
		this.mov = mov;
		this.setColorCode(colorCode);
	}
	
	public SubActivityDTO(Integer id, String sequenceNumber, String subActivity, String status, AgencyDTO leadAgency,
			List<AgencyDTO> partnerAgencies, List<ReportingPeriodDTO> reportingPeriods, String mov,String colorCode,boolean approveORCompleteStatus,ReportingPeriod completedReportingPeriod) {
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.subActivity = subActivity;
		this.status = status;
		this.leadAgency = leadAgency;
		this.partnerAgencies = partnerAgencies;
		this.reportingPeriods = reportingPeriods;
		this.mov = mov;
		this.setColorCode(colorCode);
		this.approveORCompleteStatus=approveORCompleteStatus;
		this.completedReportingPeriod=completedReportingPeriod;
	}

	public SubActivityDTO(Integer id, String subActivity,Set<AgencyDTO> agencyDTOs,String colorCode,String sequenceNumber,boolean approveORCompleteStatus,ReportingPeriod completedReportingPeriod) {
		super();
		this.id = id;
		this.subActivity = subActivity;
		this.agencyDTOs = agencyDTOs;
		this.colorCode = colorCode;
		this.sequenceNumber = sequenceNumber;
		this.approveORCompleteStatus=approveORCompleteStatus;
		this.completedReportingPeriod=completedReportingPeriod;
	}
	
	public SubActivityDTO(Integer id, String subActivity,Set<AgencyDTO> agencyDTOs,String colorCode,String sequenceNumber) {
		super();
		this.id = id;
		this.subActivity = subActivity;
		this.agencyDTOs = agencyDTOs;
		this.colorCode = colorCode;
		this.sequenceNumber = sequenceNumber;
	}

	public SubActivityDTO(Integer id, String subActivity,Set<AgencyDTO> agencyDTOs,String colorCode,String sequenceNumber, String statusIcon , boolean carryOverStatus , boolean approveORCompleteStatus) {
		super();
		this.id = id;
		this.subActivity = subActivity;
		this.agencyDTOs = agencyDTOs;
		this.colorCode = colorCode;
		this.sequenceNumber = sequenceNumber;
		this.statusIcon = statusIcon;
		this.carryOverStatus = carryOverStatus;
		this.approveORCompleteStatus = approveORCompleteStatus;
	}
	
	public SubActivityDTO(Integer id, String subActivity,Set<AgencyDTO> agencyDTOs,String colorCode,String sequenceNumber, String statusIcon , boolean carryOverStatus) {
		super();
		this.id = id;
		this.subActivity = subActivity;
		this.agencyDTOs = agencyDTOs;
		this.colorCode = colorCode;
		this.sequenceNumber = sequenceNumber;
		this.statusIcon = statusIcon;
		this.carryOverStatus = carryOverStatus;
	}
	

	public SubActivityDTO(Integer id, String subActivity,Set<AgencyDTO> agencyDTOs,String colorCode,String sequenceNumber, String statusIcon) {
		super();
		this.id = id;
		this.subActivity = subActivity;
		this.agencyDTOs = agencyDTOs;
		this.colorCode = colorCode;
		this.sequenceNumber = sequenceNumber;
		this.statusIcon = statusIcon;
	}
	
	

	public ReportingPeriod getCompletedReportingPeriod() {
		return completedReportingPeriod;
	}

	public void setCompletedReportingPeriod(ReportingPeriod completedReportingPeriod) {
		this.completedReportingPeriod = completedReportingPeriod;
	}

	public boolean isApproveORCompleteStatus() {
		return approveORCompleteStatus;
	}

	public void setApproveORCompleteStatus(boolean approveORCompleteStatus) {
		this.approveORCompleteStatus = approveORCompleteStatus;
	}

	public String getStatusIcon() {
		return statusIcon;
	}

	public void setStatusIcon(String statusIcon) {
		this.statusIcon = statusIcon;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getSubActivity() {
		return subActivity;
	}

	public void setSubActivity(String subActivity) {
		this.subActivity = subActivity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AgencyDTO getLeadAgency() {
		return leadAgency;
	}

	public void setLeadAgency(AgencyDTO leadAgency) {
		this.leadAgency = leadAgency;
	}

	public List<AgencyDTO> getPartnerAgencies() {
		return partnerAgencies;
	}

	public void setPartnerAgencies(List<AgencyDTO> partnerAgencies) {
		this.partnerAgencies = partnerAgencies;
	}

	public String getMov() {
		return mov;
	}

	public void setMov(String mov) {
		this.mov = mov;
	}

	/**
	 * @return the reportingPeriods
	 */
	public List<ReportingPeriodDTO> getReportingPeriods() {
		return reportingPeriods;
	}

	/**
	 * @param reportingPeriods the reportingPeriods to set
	 */
	public void setReportingPeriods(List<ReportingPeriodDTO> reportingPeriods) {
		this.reportingPeriods = reportingPeriods;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Set<AgencyDTO> getAgencyDTOs() {
		return agencyDTOs;
	}

	public void setAgencyDTOs(Set<AgencyDTO> agencyDTOs) {
		this.agencyDTOs = agencyDTOs;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	/**
	 * @return the carryOverStatus
	 */
	public boolean isCarryOverStatus() {
		return carryOverStatus;
	}

	/**
	 * @param carryOverStatus the carryOverStatus to set
	 */
	public void setCarryOverStatus(boolean carryOverStatus) {
		this.carryOverStatus = carryOverStatus;
	}
}
