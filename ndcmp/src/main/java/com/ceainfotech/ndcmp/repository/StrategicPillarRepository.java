/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.Theme;

import java.util.List;

/**
 * @author samy
 *
 */
public interface StrategicPillarRepository extends JpaRepository<StrategicPillar, Integer> {

	
	public StrategicPillar findById(Integer id);
	
	public StrategicPillar findByName(String name);
	
	public List<StrategicPillar> findByProject(Project project);
	 
	 @Query(value = "SELECT COUNT(a) FROM StrategicPillar a WHERE a.project = ?1")
	public Integer findByProjectId(Project project);
	 
	public List<StrategicPillar> findBySyncStatus(boolean syncStatus);
	
	public StrategicPillar findBySequenceNumber(String sequenceNumber);
	
	@Query(value = "SELECT p.subActivity.keyActivity.output.outcome.theme.strategicPillar FROM Planning p WHERE p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<StrategicPillar> getStrategicPliiersByReportingPeriods(@Param("reportingPeriodIds")List<Integer> reportingPeriodIds);
	
	@Query(value = "SELECT DISTINCT p.subActivity.keyActivity.output.outcome.theme.strategicPillar FROM Planning p WHERE p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.subActivity.leadAgency.id =:leadAgencyId  AND p.reportingPeriod.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<StrategicPillar> getStrategicPliiersByReportingPeriodsAndLeadAgency(@Param("reportingPeriodIds")List<Integer> reportingPeriodIds, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity.keyActivity.output.outcome.theme.strategicPillar FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber,p.subActivity.keyActivity.output.outcome.theme.strategicPillar")
	public List<StrategicPillar> getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(@Param("reportingPeriodIds")List<Integer> reportingPeriodIds, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value="SELECT sp FROM StrategicPillar sp WHERE sp.id IN (:strategicPillarIds)")
	public List<StrategicPillar> findByStrategicIds(@Param("strategicPillarIds") List<Integer> strategicPillarIds);
}
