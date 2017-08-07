/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;

/**
 * @author Gowri
 *
 */
public interface SubActivityService {

	public List<SubActivity> listAllSubActivity();

	public void addSubActivity(SubActivity subActivity);

	public void deleteSubActivity(Integer id);

	public SubActivity getById(Integer id);
	
	public SubActivity getByIdAndApproveAndCompletionStatus(Integer id);
	
	public List<SubActivity> getByLeadAgency(Agency leadAgency);
	
	public List<SubActivity> findByStatus(String status);
	
	public List<SubActivity> findByKeyActivity(KeyActivity keyActivity);
	
	public List<SubActivity> findByKeyActivityAndApproveAndCompletionStatus(KeyActivity keyActivity);
	
	public Integer getByKeyActivityId(KeyActivity keyActivity);
	
	public List<SubActivity> findByPartnerAgency(Agency agency);
	
	public List<SubActivity> findBySyncStatus(boolean syncStatus);
	
	public List<SubActivity> findByPartnerAgencyAndSyncStatus(Agency agency,boolean syncStatus);
	
	/**
	 * @author Mani
	 * Get Sub Activity  by passed Sub activity Name and Key Activity id
	 * @param Name
	 * @param eyActivityId 
	 */
	public SubActivity getSubActivityByNameAndKeyActivity(String name,KeyActivity keyActivity);
	
	/**
	 * @author PremKumar
	 * @param name
	 * @return
	 */
	
	public SubActivity getSubActivityByName(String name);
	
	/**
	 * @author pushpa
	 * Get list  by agencyIds
	 * @param agencyCode
	 * @return
	 */
	public List<SubActivity> findSubActivityByAgencyIds(List<Integer> agencyIds);
	
	/**
	 * @author pushpa
	 * Get list between dates and agencyIds
	 * @param agencyIds
	 * @param lastSyncedTime
	 * @param syncRequestedDate
	 * @return
	 */
	public List<SubActivity> findSubActivityByAgencyIdsAndDate(List<Integer> agencyIds, String lastSyncedTime, String syncRequestedDate);
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndLeadAgency(KeyActivity keyActivity,Integer reportingperiodId, Integer agencyId) ;
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsAndLeadAgency(KeyActivity keyActivity,List<Integer> reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriod(KeyActivity keyActivity,Integer reportingperiodId) ;
	
	public List<SubActivity> getSubActivityByReportingPeriodIdAndAgencyId(Integer reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getAllSubActivityByReportingPeriodIdAndAgencyId(Integer reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndLeadAgency(List<Integer> reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndPartnerAgency(Integer reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getAllSubActivitiesByOpenedReportingPeriodAndPartnerAgency(Integer reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndPartnerAgency(List<Integer> reportingperiodIds, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByReportingPeriods(List<Integer> reportingPeriodIds);
	
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndNotCompleted(List<Integer> reportingPeriodIds);
	
	public SubActivity getSubActivitiesBySubActivityAndReportingPeriods(SubActivity subActivity, List<Integer> reportingPeriodIds);
	
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriod(Integer reportingperiodId);
	
	public List<SubActivity> findByCompletedReportingPeriod(ReportingPeriod reportingPeriod);
	
	public List<SubActivity> getSubActivitiesByStrategicPillerAndOpenedReportingPeriod(Integer strategicPillarId, Integer reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesByListOfStrategicPillerAndListOfOpenedReportingPeriod(List<Integer> strategicPillarId, List<Integer> reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriod(Integer themeId, Integer reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriod(List<Integer> theme, List<Integer> reportingPeriodId);
	/**
	 * @author PremKumar
	 * @param theme
	 * @param reportingPeriodId
	 * @return
	 */
	public List<SubActivity> getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(List<Integer> theme, List<Integer> reportingPeriodId,Integer agency);
	
	/**
	 * @author PremKumar
	 * @param theme
	 * @param reportingPeriodId
	 * @return
	 */
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndPartnerAgency(List<Integer> pillar,List<Integer> theme,List<Integer> outcome,List<Integer> reportingPeriodId,Integer agency);
	
	/**
	 * @author PremKumar
	 * @param pillar
	 * @param theme
	 * @param outcome
	 * @param reportingPeriodId
	 * @param agency
	 * Load output in Leadagency filter
	 * @return
	 */
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndLeadAgency(List<Integer> pillar,List<Integer> theme,List<Integer> outcome,List<Integer> reportingPeriodId,Integer agency);
	
	/**
	 * @author PremKumar
	 * @param theme
	 * @param reportingPeriodId
	 * @return
	 */
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(List<Integer>pillar,List<Integer> theme, List<Integer> reportingPeriodId,Integer agency);
	
	/**
	 * @author PremKumar
	 * @param theme
	 * @param reportingPeriodId
	 * by Lead agency
	 * @return
	 */
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndLeadAgency(List<Integer>pillar,List<Integer> theme, List<Integer> reportingPeriodId,Integer agency);
	
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriod(Integer outcomeId, Integer reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesByListOfOutcomeAndListOfOpenedReportingPeriod(List<Integer> outcomeId, List<Integer> reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriod(Integer outputId, Integer reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesByStrategicPillerAndOpenedReportingPeriodAndPartnerAgency(Integer strategicPillerId, Integer reportingperiodId,Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriodsAndPartnerAgency(Integer strategicPillerId, List<Integer> reportingperiodIds,Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndPartnerAgency(Integer keyActivityId,Integer reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsAndPartnerAgency(Integer keyActivityId,List<Integer> reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(Integer themeId, Integer reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(Integer themeId, List<Integer> reportingperiodIds, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriodAndPartnerAgency(Integer outputId, Integer reportingperiodId, Integer partnerAgencyId) ;
	
	public List<SubActivity> getSubActivitiesByOutputAndReportingPeriodsAndPartnerAgency(Integer outputId, List<Integer> reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriodAndPartnerAgency(Integer outcomeId,Integer reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriodsAndPartnerAgency(Integer outcomeId,List<Integer> reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByListOutcomeAndReportingPeriodsAndPartnerAgency(List<Integer> outcomeId,List<Integer> reportingperiodId, Integer partnerAgencyId);
	
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndPartnerAgencyAndLeadAgency(Integer reportingPeriodId, Integer partnerAgencyId, Integer leadAgencyId);
	
	public List<SubActivity> getSubActivitiesByStrategicPillarAndOpenedReportingPeriodAndLeadAgency(Integer strategicPillerId,Integer reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByStrategicPillarAndReportingPeriodsAndLeadAgency(Integer strategicPillerId,List<Integer> reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(Integer strategicPillerId,Integer reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriod(List<Integer> strategicPillerId,List<Integer> reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndCurrentAgency(List<Integer> strategicPillerId,List<Integer> reportingPeriodId,Integer agencyId);
	
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndLeadAgency(List<Integer> strategicPillerId,List<Integer> reportingPeriodId,Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByStrategicPillarAndReportingPeriods(Integer strategicPillerId,List<Integer> reportingperiodIds);
	
	public List<SubActivity> getSubActivitiesByStrategicPillarsAndReportingPeriods(List<Integer> strategicPillerIds,List<Integer> reportingperiodIds);
	
	public List<SubActivity> getSubActivitiesByThemesAndReportingPeriods(List<Integer> themeIds,List<Integer> reportingperiodIds);
	
	public List<SubActivity> getSubActivitiesByOutcomesAndReportingPeriods(List<Integer> outcomeIds,List<Integer> reportingperiodIds);
	
	public List<SubActivity> getSubActivitiesByOutputsAndReportingPeriods(List<Integer> outputIds,List<Integer> reportingperiodIds);
	
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndLeadAgency(Integer themeId,Integer reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsAndLeadAgency(Integer themeId,List<Integer> reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriodAndLeadAgency(Integer outcomeId,Integer reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingsPeriodAndLeadAgency(Integer outcomeId,List<Integer> reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriodAndLeadAgency(Integer outputId,Integer reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivityByReportingIdAndSubActivities(Integer reportingId,List<SubActivity> subActivities);

	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriods(Integer strategicId, List<Integer> reportingPeriodIds);
	
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriodsNotCompleted(Integer strategicId, List<Integer> reportingPeriodIds);
	
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriods(Integer themeId, List<Integer> reportingPeriodId);

	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsNotCompleted(Integer themeId, List<Integer> reportingPeriodId);
	
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriods(Integer outcomeId, List<Integer> reportingPeriodIds);
	
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriodsNotCompleted(Integer outcomeId, List<Integer> reportingPeriodIds);

	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriods(
			KeyActivity keyActivity, List<Integer> reportingPeriodIds);
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsNotCompleted(
			KeyActivity keyActivity, List<Integer> reportingPeriodIds);
	/**
	 * purpose : to get all subactivity based on the partner agency
	 */
	public List<SubActivity> getSubActivitiesByPartnerAgency(Integer partnerAgencyId);

	/**
	 * @author PremKumar
	 * partner agency filter 
	 * @param strategicPillarIds
	 * @param reportingPeriodIds
	 * @param agency
	 * @return
	 */
	public List<SubActivity> getSubActivitiesByListStrategicPillerAndListOpenedReportingPeriodAndPartnerAgency(
			List<Integer> strategicPillarIds, List<Integer> reportingPeriodIds,
			Integer agency);
	
	public List<SubActivity> getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(List<Integer> strategicPillerIds,List<Integer> reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(List<Integer> themeIds,List<Integer> reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByOutcomesAndReportingPeriodsAndLeadAgency(List<Integer> outcomeIds,List<Integer> reportingperiodId, Integer agencyId);
	
	public List<SubActivity> getSubActivitiesByOutputsAndReportingPeriodsAndLeadAgency(List<Integer> outputIds,List<Integer> reportingperiodId, Integer agencyId);
	
}
