/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.StrategicPillar;

/**
 * @author samy
 * 
 */
public interface StrategicPillarService {

	public List<StrategicPillar> getStrategicPillars();

	public void save(StrategicPillar strategicPillar);

	public void saveAndFlush(StrategicPillar strategicPillar);

	public StrategicPillar getById(Integer id);
	
	public StrategicPillar getByName(String name);

	public List<StrategicPillar> getByProject(Project project);
	
	public Integer getByProjectId(Project project);

	public void delete(int id);

	public List<StrategicPillar> findBySyncStatus(boolean syncStatus);
	
	public StrategicPillar getBySequenceNumber(String sequenceNumber);
	
//	Report Queries
	public List<StrategicPillar> getStrategicPliiersByReportingPeriods(List<Integer> reportingperiodIds);
	
	public List<StrategicPillar> getStrategicPliiersByReportingPeriodsAndLeadAgency(List<Integer> reportingperiodIds, Integer leadAgencyId);
	/**
	 * @author Premkumar
	 * @param reportingPeriods
	 * @param id
	 * @return
	 */
	public List<StrategicPillar> getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(
			List<Integer> reportingPeriods, Integer id);
	
//	End Report Queries
}
