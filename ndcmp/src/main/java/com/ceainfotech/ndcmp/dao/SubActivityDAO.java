/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.repository.SubActivityRepository;

/**
 * @author Gowri
 *
 */
@Repository
public class SubActivityDAO {
	
	private final SubActivityRepository subActivityRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubActivityDAO.class);
	
	@Autowired
	public SubActivityDAO(final SubActivityRepository subActivityRepository) {
		this.subActivityRepository = subActivityRepository;
	}

	public List<SubActivity> listAllSubActivity() {
		return subActivityRepository.findAll();
	}

	public void addSubActivity(SubActivity subActivity) {
		subActivityRepository.saveAndFlush(subActivity);
	}

	public void deleteSubActivity(Integer id) {
		subActivityRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public SubActivity getById(Integer id) {
		return subActivityRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public SubActivity getByIdAndApproveAndCompletionStatus(Integer id) {
		return subActivityRepository.getByIdAndApproveAndCompletionStatus(id);
	}
	
	@Transactional(readOnly = true)
	public List<SubActivity> getByLeadAgency(Agency leadagency) {
		return subActivityRepository.findByLeadAgency(leadagency);
	}

	public List<SubActivity> findByStatus(String status){
		LOGGER.info("List By status");
		return subActivityRepository.findByStatus(status);
	}
	
	@Transactional(readOnly = true)
	public Integer getByKeyActivityId(KeyActivity keyActivity) {
		LOGGER.debug("Count Output By KeyActivity .. {}", keyActivity);
		return subActivityRepository.findByKeyActivityId(keyActivity);
	}
	
	@Transactional(readOnly = true)
	public List<SubActivity> findByKeyActivity(KeyActivity keyActivity){
//		LOGGER.info("List By keyActivity");
		return subActivityRepository.findByKeyActivity(keyActivity);
	}
	
	@Transactional(readOnly = true)
	public List<SubActivity> findByKeyActivityAndApproveAndCompletionStatus(KeyActivity keyActivity){
//		LOGGER.info("List By keyActivity");
		return subActivityRepository.findByKeyActivityAndApproveAndCompletionStatus(keyActivity);
	}
	
	@Transactional(readOnly = true)
	public List<SubActivity> findByPartnerAgency(Agency agency){
		if(agency != null){
			return subActivityRepository.findByPartnerAgency(agency);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<SubActivity> findBySyncStatus(boolean syncStatus){
		return subActivityRepository.findBySyncStatus(syncStatus);
	}
	
	@Transactional(readOnly = true)
	public List<SubActivity> findByPartnerAgencyAndSyncStatus(Agency agency,boolean syncStatus){
		return subActivityRepository.findByPartnerAgencyAndSyncStatus(agency, syncStatus);
	}
	
	public SubActivity getSubActivityByNameAndKeyActivity(String name,KeyActivity keyActivity){
		return subActivityRepository.findByNameAndKeyActivity(name, keyActivity);
	}
	
	public SubActivity getSubActivityByName(String name){
		return subActivityRepository.findByName(name);
	}
	
	
	/**
	 * @author pushpa
	 * @param agencyCode
	 * @return
	 */
	public List<SubActivity> findSubActivityByAgencyIds(List<Integer> agencyIds){
		return subActivityRepository.findSubActivityByAgencyIds(agencyIds, agencyIds);
	}
	
	/**
	 * @author pushpa
	 * @param agencyIds
	 * @param lastSyncedTime
	 * @param syncRequestedDate
	 * @return
	 */
	public List<SubActivity> findSubActivityByAgencyIdsAndDate(List<Integer> agencyIds, String lastSyncedTime, String currentTime){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return subActivityRepository.findSubActivityByAgencyIdsAndDate(lastSyncedDate, currentDate, agencyIds, agencyIds);
	}
	
	public List<SubActivity> getSubActivityByReportingPeriodIdAndAgencyId(Integer reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByOpenedReportingPeriodAndLeadAgency(reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getAllSubActivityByReportingPeriodIdAndAgencyId(Integer reportingperiodId, Integer agencyId) {
		return subActivityRepository.getAllSubActivitiesByOpenedReportingPeriodAndLeadAgency(reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndLeadAgency(List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillarAndOpenedReportingPeriodAndLeadAgency(Integer strategicPillerId,Integer reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByStrategicPillarAndOpenedReportingPeriodAndLeadAgency(strategicPillerId, reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillarAndReportingPeriodsAndLeadAgency(Integer strategicPillerId,List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByStrategicPillarAndReportingPeriodsAndLeadAgency(strategicPillerId, reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(Integer strategicPillerId,Integer reportingperiodId) {
		return subActivityRepository.getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(strategicPillerId, reportingperiodId);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillarAndReportingPeriods(Integer strategicPillerId,List<Integer> reportingperiodIds) {
		return subActivityRepository.getSubActivitiesByStrategicPillarAndReportingPeriods(strategicPillerId, reportingperiodIds);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillarsAndReportingPeriods(List<Integer> strategicPillerIds,List<Integer> reportingperiodIds) {
		return subActivityRepository.getSubActivitiesByStrategicPillarsAndReportingPeriods(strategicPillerIds, reportingperiodIds);
	}
	
	public List<SubActivity> getSubActivitiesByThemesAndReportingPeriods(List<Integer> themeIds,List<Integer> reportingperiodIds) {
		return subActivityRepository.getSubActivitiesByThemesAndReportingPeriods(themeIds, reportingperiodIds);
	}
	
	public List<SubActivity> getSubActivitiesByOutcomesAndReportingPeriods(List<Integer> outcomeIds,List<Integer> reportingperiodIds) {
		return subActivityRepository.getSubActivitiesByOutcomesAndReportingPeriods(outcomeIds, reportingperiodIds);
	}
	
	public List<SubActivity> getSubActivitiesByOutputsAndReportingPeriods(List<Integer> outputIds,List<Integer> reportingperiodIds) {
		return subActivityRepository.getSubActivitiesByOutputsAndReportingPeriods(outputIds, reportingperiodIds);
	}
	
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndLeadAgency(Integer themeId,Integer reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByThemeAndOpenedReportingPeriodAndLeadAgency(themeId, reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsAndLeadAgency(Integer themeId,List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByThemeAndReportingPeriodsAndLeadAgency(themeId, reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriodAndLeadAgency(Integer outcomeId,Integer reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByOutcomeAndOpenedReportingPeriodAndLeadAgency(outcomeId, reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingsPeriodAndLeadAgency(Integer outcomeId,List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByOutcomeAndReportingsPeriodAndLeadAgency(outcomeId, reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriodAndLeadAgency(Integer outputId,Integer reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByOutputAndOpenedReportingPeriodAndLeadAgency(outputId, reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndLeadAgency(KeyActivity keyActivity,Integer reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndLeadAgency(keyActivity,reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsAndLeadAgency(KeyActivity keyActivity,List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByKeyActivityAndReportingPeriodsAndLeadAgency(keyActivity,reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriod(KeyActivity keyActivity,Integer reportingperiodId) {
		return subActivityRepository.getSubActivitiesByKeyActivityAndOpenedReportingPeriod(keyActivity,reportingperiodId);
	}
	
	public List<SubActivity> getSubActivityByReportingIdAndSubActivities(Integer reportingId, List<SubActivity> subActivities) {
		return subActivityRepository.getSubActivitiesByOpenedReportingPeriodAndSubActivities(reportingId, subActivities);
	}
	
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndPartnerAgency(Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByOpenedReportingPeriodAndPartnerAgency(reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getAllSubActivitiesByOpenedReportingPeriodAndPartnerAgency(Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityRepository.getAllSubActivitiesByOpenedReportingPeriodAndPartnerAgency(reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndPartnerAgency(List<Integer> reportingperiodIds, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByReportingPeriodsAndPartnerAgency(reportingperiodIds, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriod(Integer reportingperiodId) {
		return subActivityRepository.getSubActivitiesByOpenedReportingPeriod(reportingperiodId);
	}
	
	public List<SubActivity> findByCompletedReportingPeriod(ReportingPeriod reportingPeriod){
		return subActivityRepository.findByCompletedReportingPeriod(reportingPeriod);
	}
	
	public SubActivity getSubActivitiesBySubActivityAndReportingPeriods(SubActivity subActivity, List<Integer> reportingPeriodIds){
		return subActivityRepository.getSubActivitiesBySubActivityAndReportingPeriods(subActivity, reportingPeriodIds);
	}
	
	public List<SubActivity> getSubActivitiesByReportingPeriods(List<Integer> reportingPeriodIds){
		return subActivityRepository.getSubActivitiesByReportingPeriods(reportingPeriodIds);
	}
	
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndNotCompleted(List<Integer> reportingPeriodIds){
		return subActivityRepository.getSubActivitiesByReportingPeriodsAndNotCompleted(reportingPeriodIds);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillerAndOpenedReportingPeriod(Integer strategicPillarId, Integer reportingPeriodId) {
		return subActivityRepository.getSubActivitiesByStrategicPillerAndOpenedReportingPeriod(strategicPillarId, reportingPeriodId);
	}
	
	public List<SubActivity> getSubActivitiesByListOfStrategicPillerAndListOfOpenedReportingPeriod(List<Integer> strategicPillarId, List<Integer> reportingPeriodId) {
		return subActivityRepository.getSubActivitiesByListOfStrategicPillerAndListOfOpenedReportingPeriod(strategicPillarId, reportingPeriodId);
	}
	
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriod(Integer themeId, Integer reportingPeriodId) {
		return subActivityRepository.getSubActivitiesByThemeAndOpenedReportingPeriod(themeId, reportingPeriodId);
	}
	
	public List<SubActivity> getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriod(List<Integer> theme, List<Integer> reportingPeriod) {
		return subActivityRepository.getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriod(theme, reportingPeriod);
	}
	
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriod(Integer outcomeId, Integer reportingPeriodId) {
		return subActivityRepository.getSubActivitiesByOutcomeAndOpenedReportingPeriod(outcomeId, reportingPeriodId);
	}
	
	public List<SubActivity> getSubActivitiesByListOfOutcomeAndListOfOpenedReportingPeriod(List<Integer> outcomeId, List<Integer> reportingPeriodId) {
		return subActivityRepository.getSubActivitiesByListOfOutcomeAndListOfOpenedReportingPeriod(outcomeId, reportingPeriodId);
	}
	
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriod(Integer outputId, Integer reportingPeriodId) {
		return subActivityRepository.getSubActivitiesByOutputAndOpenedReportingPeriod(outputId, reportingPeriodId);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillerAndOpenedReportingPeriodAndPartnerAgency(Integer strategicPillerId, Integer reportingperiodId,Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByStrategicPillerAndOpenedReportingPeriodAndPartnerAgency(strategicPillerId, reportingperiodId, partnerAgencyId);
	}
	/**
	 * @author PremKumar
	 * Partner agency filter
	 * @param strategicPillerId
	 * @param reportingperiodId
	 * @param partnerAgencyId
	 * @return
	 */
	public List<SubActivity> getSubActivitiesByListStrategicPillerAndListOpenedReportingPeriodAndPartnerAgency(List<Integer> strategicPillerId, List<Integer> reportingperiodId,Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByListStrategicPillerAndListOpenedReportingPeriodAndPartnerAgency(strategicPillerId, reportingperiodId, partnerAgencyId);
	}
	
	
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriodsAndPartnerAgency(Integer strategicPillerId, List<Integer> reportingperiodIds,Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByStrategicPillerAndReportingPeriodsAndPartnerAgency(strategicPillerId, reportingperiodIds, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(Integer themeId, Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(themeId, reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(Integer themeId, List<Integer> reportingperiodIds, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByThemeAndReportingPeriodsAndPartnerAgency(themeId, reportingperiodIds, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriodAndPartnerAgency(Integer outcomeId,Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByOutcomeAndOpenedReportingPeriodAndPartnerAgency(outcomeId, reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriodsAndPartnerAgency(Integer outcomeId,List<Integer> reportingperiodId, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByOutcomeAndReportingPeriodsAndPartnerAgency(outcomeId, reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriodAndPartnerAgency(Integer outputId, Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByOutputAndOpenedReportingPeriodAndPartnerAgency(outputId, reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutputAndReportingPeriodsAndPartnerAgency(Integer outputId, List<Integer> reportingperiodId, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByOutputAndReportingPeriodsAndPartnerAgency(outputId, reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndPartnerAgency(Integer keyActivityId,Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityRepository.getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndPartnerAgency(keyActivityId, reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsAndPartnerAgency(Integer keyActivityId,List<Integer> reportingperiodId, Integer partnerAgencyId){
		return subActivityRepository.getSubActivitiesByKeyActivityAndReportingPeriodsAndPartnerAgency(keyActivityId, reportingperiodId, partnerAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndPartnerAgencyAndLeadAgency(Integer reportingPeriodId, Integer partnerAgencyId, Integer leadAgencyId){
		return subActivityRepository.getSubActivitiesByOpenedReportingPeriodAndPartnerAgencyAndLeadAgency(reportingPeriodId, partnerAgencyId, leadAgencyId);
	}

	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriods(Integer strategicId, List<Integer> reportingPeriodIds) {
		return subActivityRepository.getSubActivitiesByStrategicPillerAndReportingPeriods(strategicId,reportingPeriodIds);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriodsNotCompleted(Integer strategicId, List<Integer> reportingPeriodIds) {
		return subActivityRepository.getSubActivitiesByStrategicPillerAndReportingPeriodsNotCompleted(strategicId,reportingPeriodIds);
	}
	
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriods(Integer themeId, List<Integer> reportingPeriodId) {
		return subActivityRepository.getSubActivitiesByThemeAndReportingPeriods(themeId, reportingPeriodId);
	}
	
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsNotCompleted(Integer themeId, List<Integer> reportingPeriodId) {
		return subActivityRepository.getSubActivitiesByThemeAndReportingPeriodsNotCompleted(themeId, reportingPeriodId);
	}

	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriods(Integer outcomeId, List<Integer> reportingPeriodIds) {
		return subActivityRepository.getSubActivitiesByOutcomeAndReportingPeriods(outcomeId,reportingPeriodIds);
	}
	
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriodsNotCompleted(Integer outcomeId, List<Integer> reportingPeriodIds) {
		return subActivityRepository.getSubActivitiesByOutcomeAndReportingPeriodsNotCompleted(outcomeId,reportingPeriodIds);
	}

	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriods(KeyActivity keyActivity, List<Integer> reportingPeriodIds) {
		return subActivityRepository.getSubActivitiesByKeyActivityAndReportingPeriods(keyActivity,reportingPeriodIds);
	}
	
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsNotCompleted(KeyActivity keyActivity, List<Integer> reportingPeriodIds) {
		return subActivityRepository.getSubActivitiesByKeyActivityAndReportingPeriodsNotCompleted(keyActivity,reportingPeriodIds);
	}

	/**
	 * purpose : to get all subactivity based on the partner agency
	 */
	public List<SubActivity> getSubActivitiesByPartnerAgency(Integer partnerAgencyId) {
		if(partnerAgencyId != null){
			return subActivityRepository.getSubActivitiesByPartnerAgency(partnerAgencyId);
		}
		return null;
	}

	public List<SubActivity> getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(
			List<Integer> theme, List<Integer> reportingPeriodId, Integer agency) {
		return subActivityRepository.getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(theme,reportingPeriodId,agency);
	}

	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndCurrentAgency(
			List<Integer> strategicPillerId, List<Integer> reportingperiodId,
			Integer agencyId) {
		// TODO Auto-generated method stub
		return subActivityRepository.getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndCurrentAgency(strategicPillerId,reportingperiodId,agencyId);
	}
	
	
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndLeadAgency(
			List<Integer> strategicPillerId, List<Integer> reportingperiodId,
			Integer agencyId) {
		return subActivityRepository.getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndLeadAgency(strategicPillerId,reportingperiodId,agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(List<Integer> strategicPillerIds,List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityRepository.getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(strategicPillerIds, reportingperiodId, agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(List<Integer> themeIds,List<Integer> reportingperiodIds, Integer leadAgencyId) {
		return subActivityRepository.getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(themeIds, reportingperiodIds, leadAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutcomesAndReportingPeriodsAndLeadAgency(List<Integer> outcomeId,List<Integer> reportingperiodIds, Integer leadAgencyId) {
		return subActivityRepository.getSubActivitiesByOutcomesAndReportingPeriodsAndLeadAgency(outcomeId, reportingperiodIds, leadAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByOutputsAndReportingPeriodsAndLeadAgency(List<Integer> outputIds,List<Integer> reportingperiodIds, Integer leadAgencyId) {
		return subActivityRepository.getSubActivitiesByOutputsAndReportingPeriodsAndLeadAgency(outputIds, reportingperiodIds, leadAgencyId);
	}
	
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(
			List<Integer> pillar,List<Integer>theme, List<Integer> reportingperiodId,
			Integer agencyId) {
		// TODO Auto-generated method stub
		return subActivityRepository.getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(pillar,theme,reportingperiodId,agencyId);
	}
	
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndLeadAgency(
			List<Integer> pillar,List<Integer>theme, List<Integer> reportingperiodId,
			Integer agencyId) {
		// TODO Auto-generated method stub
		return subActivityRepository.getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndLeadAgency(pillar,theme,reportingperiodId,agencyId);
	}


	
	
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndPartnerAgency(
			List<Integer> pillar, List<Integer> theme, List<Integer> outcome,
			List<Integer> reportingPeriodId, Integer agency) {
		// TODO Auto-generated method stub
		return subActivityRepository.getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndPartnerAgency(pillar, theme, outcome, reportingPeriodId, agency);
	}

	
	
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndLeadAgency(
			List<Integer> pillar, List<Integer> theme, List<Integer> outcome,
			List<Integer> reportingPeriodId, Integer agency) {
		// TODO Auto-generated method stub
		return subActivityRepository.getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndLeadAgency(pillar, theme, outcome, reportingPeriodId, agency);
	}
	
}
