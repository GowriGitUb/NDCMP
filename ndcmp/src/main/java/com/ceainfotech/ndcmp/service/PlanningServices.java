/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubActivity;

/**
 * @author bosco
 *
 */
public interface PlanningServices {

	public List<Planning> getPlannings();

	public Planning getById(int id);

	public List<SubActivity> getPlanning();

	public void save(Integer subActivityId,Integer reportingPeriodId);

	public List<Planning> getByReportingPeriod(ReportingPeriod period);

	public List<Planning> getByPlanning(SubActivity subActivity);
	
	public SubActivity getSubActivityById(Integer subActId);
	
	public List<Planning> getPlanningListByAgency(List<Integer> agencyIds);
	
	public Planning findBySubActivityAndReportingPeriod(SubActivity subActivity,ReportingPeriod reportingPeriod);
	
	public void deletePlanning(Planning planning);
	
	/*public List<SubActivity> getSubActivityByReportingPeriodIdAndAgencyId(Integer reportingperiodId, Integer agencyId);*/
}
