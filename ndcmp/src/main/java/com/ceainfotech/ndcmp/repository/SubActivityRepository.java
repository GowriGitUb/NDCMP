/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubActivity;

/**
 * @author Gowri
 *
 */
public interface SubActivityRepository extends JpaRepository<SubActivity, Integer> {

	public List<SubActivity> findByStatus(String status);
	
	public List<SubActivity> findByCompletedReportingPeriod(ReportingPeriod reportingPeriod);
	
	@Query(value = "SELECT COUNT(a) FROM SubActivity a WHERE a.keyActivity = ?1")
	public Integer findByKeyActivityId(KeyActivity keyActivity);
	
	@Query(value = "SELECT a FROM SubActivity a WHERE a.keyActivity = :keyactivity ORDER BY a.sequenceNumber+0, a.sequenceNumber")
	public List<SubActivity> findByKeyActivity(@Param("keyactivity")KeyActivity keyactivity);
	
	@Query(value = "SELECT a FROM SubActivity a WHERE a.keyActivity = :keyactivity ORDER BY a.sequenceNumber+0, a.sequenceNumber")
	public List<SubActivity> findByKeyActivityAndApproveAndCompletionStatus(@Param("keyactivity")KeyActivity keyactivity);
	
	public SubActivity findById(Integer id);
	
	@Query(value = "SELECT a FROM SubActivity a WHERE a.id = :id and a.approveORCompleteStatus=0 ORDER BY a.sequenceNumber+0, a.sequenceNumber")
	public SubActivity getByIdAndApproveAndCompletionStatus(@Param("id")Integer id);
	
	public List<SubActivity> findByLeadAgency(Agency leadagency);
	
	public List<SubActivity> findByPartnerAgency(Agency agency);
	
	public List<SubActivity> findByPartnerAgencyAndSyncStatus(Agency agency,boolean syncStatus);
	
	public List<SubActivity> findBySyncStatus(boolean syncStatus);
	
	/**
	 * @author pushpa
	 * Find all sub activity list based on the agency
	 * @param leadAgency
	 * @param partnerAgency
	 * @return
	 */
	@Query(value = "SELECT DISTINCT sa FROM SubActivity sa  INNER JOIN sa.partnerAgency pa  WHERE sa.leadAgency.id IN (:leadAgency) OR pa.id IN (:partnerAgency) ORDER BY sa.sequenceNumber+0, sa.sequenceNumber") // AND sa.status = 'ACTIVE'
	public List<SubActivity> findSubActivityByAgencyIds(@Param("leadAgency")List<Integer> leadAgency, @Param("partnerAgency")List<Integer> partnerAgency);
	
	/**
	 * @author pushpa
	 * Find all latest sub activity list based on the agency
	 * @param lastSyncedTime
	 * @param syncRequestedDate
	 * @param leadAgency
	 * @param partnerAgency
	 * @return
	 */
	@Query(value = "SELECT DISTINCT sa FROM SubActivity sa  LEFT JOIN sa.partnerAgency AS pa  WHERE sa.modificationTime BETWEEN :lastSyncedTime AND :syncRequestedDate AND (sa.leadAgency.id IN (:leadAgency) OR pa.id IN (:partnerAgency)) ORDER BY sa.sequenceNumber+0, sa.sequenceNumber") // AND sa.status = 'ACTIVE'
	public List<SubActivity> findSubActivityByAgencyIdsAndDate(@Param("lastSyncedTime")Date lastSyncedTime, @Param("syncRequestedDate")Date syncRequestedDate, @Param("leadAgency")List<Integer> leadAgency, @Param("partnerAgency")List<Integer> partnerAgency);
	
	/**
	 * @author pushpa
	 * Get all sub activity which has the lead agency as passed parameter
	 * @param leadAgency
	 * @return
	 */
	@Query(value = "SELECT sa FROM SubActivity sa WHERE sa.leadAgency.id = :leadAgency AND sa.status = 'ACTIVE' ORDER BY sa.sequenceNumber+0, sa.sequenceNumber")
	public List<SubActivity> findSubActivityByLeadAgencyId(@Param("leadAgency")Integer leadAgency);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillerAndOpenedReportingPeriod(@Param("strategicPillarId")Integer strategicPillarId ,@Param("reportingPeriodId")Integer reportingPeriodId);
	/**
	 * @author PremKumar
	 * @param strategicPillarId
	 * @param reportingPeriodId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN ( :strategicPillarId ) AND p.reportingPeriod.id IN ( :reportingPeriodId ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListOfStrategicPillerAndListOfOpenedReportingPeriod(@Param("strategicPillarId")List<Integer> strategicPillarId ,@Param("reportingPeriodId")List<Integer> reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id IN (:reportingPeriodIds) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriods(@Param("strategicPillarId")Integer strategicPillarId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodIds);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id IN (:reportingPeriodIds) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriodsNotCompleted(@Param("strategicPillarId")Integer strategicPillarId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodIds);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.id = :themeId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriod(@Param("themeId")Integer themeId ,@Param("reportingPeriodId")Integer reportingPeriodId);
	
	/**
	 * @author PremKumar
	 * @param theme
	 * @param reportingPeriod
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.id In( :theme) AND p.reportingPeriod.id In( :reportingPeriod) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriod(@Param("theme")List<Integer> theme ,@Param("reportingPeriod")List<Integer> reportingPeriod);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.id = :themeId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriods(@Param("themeId")Integer themeId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);

	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.id = :themeId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsNotCompleted(@Param("themeId")Integer themeId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);

	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.id = :outcomeId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriod(@Param("outcomeId")Integer outcomeId ,@Param("reportingPeriodId")Integer reportingPeriodId);
	
	/**
	 * @author PremKumar
	 * @param outcomeId
	 * @param reportingPeriodId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.id In( :outcomeId) AND p.reportingPeriod.id In( :reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListOfOutcomeAndListOfOpenedReportingPeriod(@Param("outcomeId")List<Integer> outcomeId ,@Param("reportingPeriodId")List<Integer> reportingPeriodId);

	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.id = :outcomeId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriods(@Param("outcomeId")Integer outcomeId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.id = :outcomeId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriodsNotCompleted(@Param("outcomeId")Integer outcomeId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.id = :outputId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriod(@Param("outputId")Integer outputId ,@Param("reportingPeriodId")Integer reportingPeriodId);

	
	/**
	 * @author pushpa
	 * Get Sub Activity List by passed Partner Agency Ids and Opened Report period
	 * @param reportingPeriodId
	 * @param partnerAgencyIds
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber ")
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndPartnerAgency(@Param("reportingPeriodId")Integer reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber ")
	public List<SubActivity> getAllSubActivitiesByOpenedReportingPeriodAndPartnerAgency(@Param("reportingPeriodId")Integer reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	/*
	 * Purpose: To get all subactivies based on the current agency
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber ")
	public List<SubActivity> getSubActivitiesByPartnerAgency(@Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber ")
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndPartnerAgency(@Param("reportingPeriodIds")List<Integer> reportingPeriodIds, @Param("partnerAgencyId")Integer partnerAgencyId);

	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriod(@Param("reportingPeriodId")Integer reportingPeriodId);
	
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByReportingPeriods(@Param("reportingPeriodIds")List<Integer> reportingPeriodIds);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity = :subActivity AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public SubActivity getSubActivitiesBySubActivityAndReportingPeriods(@Param("subActivity") SubActivity subActivity,@Param("reportingPeriodIds")List<Integer> reportingPeriodIds);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndNotCompleted(@Param("reportingPeriodIds")List<Integer> reportingPeriodIds);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillerAndOpenedReportingPeriodAndPartnerAgency(@Param("strategicPillarId")Integer StrategicPillarId,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriodsAndPartnerAgency(@Param("strategicPillarId")Integer StrategicPillarId,@Param("reportingPeriodIds")List<Integer> reportingPeriodIds, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.id = :themeId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(@Param("themeId")Integer themeId,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.id = :themeId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsAndPartnerAgency(@Param("themeId")Integer themeId,@Param("reportingPeriodIds")List<Integer> reportingPeriodIds, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.id = :outcomeId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriodAndPartnerAgency(@Param("outcomeId")Integer outcomeId,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.id = :outcomeId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriodsAndPartnerAgency(@Param("outcomeId")Integer outcomeId,@Param("reportingPeriodIds")List<Integer> reportingPeriodIds, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.id = :outputId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus=0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriodAndPartnerAgency(@Param("outputId")Integer outputId,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.id = :outputId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus=0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutputAndReportingPeriodsAndPartnerAgency(@Param("outputId")Integer outputId,@Param("reportingPeriodIds")List<Integer> reportingPeriodIds, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.id = :keyActivityId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus=0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndPartnerAgency(@Param("keyActivityId")Integer keyActivityId,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.id = :keyActivityId AND p.reportingPeriod.id IN ( :reportingPeriodId ) AND p.reportingPeriod.status = 'ACTIVE'  AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus=0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsAndPartnerAgency(@Param("keyActivityId")Integer keyActivityId,@Param("reportingPeriodId")List<Integer> reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.reportingPeriod.id = :reportingPeriodId AND p.subActivity.leadAgency.id = :leadAgencyId  AND p.reportingPeriod.status = 'ACTIVE'  AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE'  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndPartnerAgencyAndLeadAgency(@Param("reportingPeriodId")Integer reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId, @Param("leadAgencyId")Integer leadAgencyId);
	
	/**
	 * @author pushpa
	 * Get Sub Activity List from planning by passed Lead Agency and Opened Reporting period
	 * @param reportingPeriodId
	 * @param leadAgencyId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndLeadAgency(@Param("reportingPeriodId")Integer reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getAllSubActivitiesByOpenedReportingPeriodAndLeadAgency(@Param("reportingPeriodId")Integer reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	/**
	 * @author pushpa
	 * Get Sub Activity List from planning by passed Lead Agency and Opened Reporting period
	 * @param reportingPeriodId
	 * @param leadAgencyId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.reportingPeriod.id IN ( :reportingPeriodId ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndLeadAgency(@Param("reportingPeriodId")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	
	/**
	 * @author pushpa
	 * Get SubActivities By StrategicPillar Opened Reporting Period And LeadAgency
	 * @param StrategicPillarId
	 * @param reportingPeriodId
	 * @param leadAgencyId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE'  AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillarAndOpenedReportingPeriodAndLeadAgency(@Param("strategicPillarId")Integer StrategicPillarId ,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id IN ( :reportingPeriodId ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE'  AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillarAndReportingPeriodsAndLeadAgency(@Param("strategicPillarId")Integer StrategicPillarId ,@Param("reportingPeriodId")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(@Param("strategicPillarId")Integer StrategicPillarId ,@Param("reportingPeriodId")Integer reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id = :strategicPillarId AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillarAndReportingPeriods(@Param("strategicPillarId")Integer StrategicPillarId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillarsAndReportingPeriods(@Param("strategicPillarId")List<Integer> strategicPillarId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.id IN (:themeId) AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemesAndReportingPeriods(@Param("themeId")List<Integer> themeId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.id IN (:outcomeId) AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomesAndReportingPeriods(@Param("outcomeId")List<Integer> themeId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.id IN (:outputId) AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutputsAndReportingPeriods(@Param("outputId")List<Integer> outputId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.id = :themeId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndLeadAgency(@Param("themeId")Integer themeId ,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.id = :themeId AND p.reportingPeriod.id IN ( :reportingPeriodId ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsAndLeadAgency(@Param("themeId")Integer themeId ,@Param("reportingPeriodId")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.id = :outcomeId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE'  AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriodAndLeadAgency(@Param("outcomeId")Integer outcomeId ,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.id = :outcomeId AND p.reportingPeriod.id IN ( :reportingPeriodId ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE'  AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingsPeriodAndLeadAgency(@Param("outcomeId")Integer outcomeId ,@Param("reportingPeriodId")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.id = :outputId AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriodAndLeadAgency(@Param("outputId")Integer outputId ,@Param("reportingPeriodId")Integer reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	/**
	 * @author Mani
	 * Get Sub Activity  by passed Sub activity Name and Key Activity id
	 * @param Name
	 * @param eyActivityId 
	 */
	public SubActivity findByNameAndKeyActivity(String name,KeyActivity keyActivity);
	
	/**
	 * @author PremKumar
	 * @param name
	 * @return
	 */
	public SubActivity findByName(String name);
	
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity = :keyActivity AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus =0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndLeadAgency(@Param("keyActivity")KeyActivity keyActivity, @Param("reportingPeriodId")Integer reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity = :keyActivity AND p.reportingPeriod.id IN ( :reportingPeriodId ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.leadAgency.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus =0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsAndLeadAgency(@Param("keyActivity")KeyActivity keyActivity, @Param("reportingPeriodId")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity = :keyActivity AND p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriod(@Param("keyActivity")KeyActivity keyActivity, @Param("reportingPeriodId")Integer reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity = :keyActivity AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriods(@Param("keyActivity")KeyActivity keyActivity, @Param("reportingPeriodIds")List<Integer> reportingPeriodIds);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity = :keyActivity AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsNotCompleted(@Param("keyActivity")KeyActivity keyActivity, @Param("reportingPeriodIds")List<Integer> reportingPeriodIds);
	
	/**
	 * @author pushpa
	 * Get Sub Activities from Planning table based on reporting period and SubActivities
	 * @param reportingPeriodId
	 * @param outputId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.reportingPeriod.id = :reportingPeriodId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity IN (:subActivities) ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndSubActivities(@Param("reportingPeriodId")Integer reportingPeriodId, @Param("subActivities")List<SubActivity> subActivities);

	/**
	 * @author PremKumar
	 * @param StrategicPillarId
	 * @param reportingPeriodId
	 * @param partnerAgencyId
	 * @return
	 */
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.reportingPeriod.id IN (:reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListStrategicPillerAndListOpenedReportingPeriodAndPartnerAgency(@Param("strategicPillarId")List<Integer> StrategicPillarId,@Param("reportingPeriodId")List<Integer> reportingPeriodId, @Param("partnerAgencyId")Integer partnerAgencyId);

	/**
	 * @author PremKumar
	 * @param theme
	 * @param reportingPeriodId
	 * @param agency
	 * @return
	 */
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.id IN (:theme) AND p.reportingPeriod.id IN (:reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(@Param("theme")List<Integer> theme, @Param("reportingPeriodId")List<Integer> reportingPeriodId, @Param("partnerAgencyId")Integer agency);
	
	/**
	 * @author PremKumar
	 * @param strategicPillerId
	 * @param reportingperiodId
	 * @param agencyId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.reportingPeriod.id IN (:reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndCurrentAgency(@Param("strategicPillarId")List<Integer> strategicPillerId,@Param("reportingPeriodId")List<Integer> reportingperiodId,@Param("partnerAgencyId")Integer agencyId);
	/**
	 * @author PremKumar
	 * @param strategicPillerId
	 * @param reportingperiodId
	 * @param agencyId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.reportingPeriod.id IN (:reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndLeadAgency(@Param("strategicPillarId")List<Integer> strategicPillerId,@Param("reportingPeriodId")List<Integer> reportingperiodId,@Param("leadAgencyId")Integer agencyId);
	
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.subActivity.leadAgency.id =:leadAgencyId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(@Param("strategicPillarId")List<Integer> strategicPillarId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.theme.id IN (:themeId) AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.subActivity.leadAgency.id =:leadAgencyId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(@Param("themeId")List<Integer> themeId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.outcome.id IN (:outcomeId) AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.subActivity.leadAgency.id =:leadAgencyId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutcomesAndReportingPeriodsAndLeadAgency(@Param("outcomeId")List<Integer> outcomeId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p WHERE p.subActivity.keyActivity.output.id IN (:outputId) AND p.reportingPeriod.id IN ( :reportingPeriodIds ) AND p.subActivity.leadAgency.id =:leadAgencyId AND p.reportingPeriod.status = 'ACTIVE' AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0 ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByOutputsAndReportingPeriodsAndLeadAgency(@Param("outputId")List<Integer> outputId ,@Param("reportingPeriodIds")List<Integer> reportingPeriodId, @Param("leadAgencyId")Integer leadAgencyId);
	
	/**
	 * @author PremKumar
	 * @param strategicPillerId
	 * @param theme
	 * @param reportingperiodId
	 * @param agencyId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.subActivity.keyActivity.output.outcome.theme.id IN (:themeId) AND p.reportingPeriod.id IN (:reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(@Param("strategicPillarId")List<Integer> strategicPillerId,@Param("themeId")List<Integer>theme,@Param("reportingPeriodId")List<Integer> reportingperiodId,@Param("partnerAgencyId")Integer agencyId);
	/**
	 * @author PremKumar
	 * @param strategicPillerId
	 * @param theme
	 * @param reportingperiodId
	 * @param agencyId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.leadAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.subActivity.keyActivity.output.outcome.theme.id IN (:themeId) AND p.reportingPeriod.id IN (:reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndLeadAgency(@Param("strategicPillarId")List<Integer> strategicPillerId,@Param("themeId")List<Integer>theme,@Param("reportingPeriodId")List<Integer> reportingperiodId,@Param("leadAgencyId")Integer agencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.partnerAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.subActivity.keyActivity.output.outcome.theme.id IN (:themeId) AND p.subActivity.keyActivity.output.outcome.id IN (:outcomeId) AND p.reportingPeriod.id IN (:reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :partnerAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndPartnerAgency(@Param("strategicPillarId")List<Integer> strategicPillerId,@Param("themeId")List<Integer>theme,@Param("outcomeId")List<Integer> outcomeId,@Param("reportingPeriodId")List<Integer> reportingperiodId,@Param("partnerAgencyId")Integer agencyId);
	
	@Query(value = "SELECT DISTINCT p.subActivity FROM Planning p LEFT JOIN p.subActivity.leadAgency AS pa WHERE p.subActivity.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) AND p.subActivity.keyActivity.output.outcome.theme.id IN (:themeId) AND p.subActivity.keyActivity.output.outcome.id IN (:outcomeId) AND p.reportingPeriod.id IN (:reportingPeriodId) AND p.reportingPeriod.status = 'ACTIVE' AND  pa.id = :leadAgencyId AND p.subActivity.status = 'ACTIVE' AND p.subActivity.approveORCompleteStatus = 0  ORDER BY p.subActivity.sequenceNumber+0, p.subActivity.sequenceNumber")
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndLeadAgency(@Param("strategicPillarId")List<Integer> strategicPillerId,@Param("themeId")List<Integer>theme,@Param("outcomeId")List<Integer> outcomeId,@Param("reportingPeriodId")List<Integer> reportingperiodId,@Param("leadAgencyId")Integer agencyId);
	
//	Report Queries
}
