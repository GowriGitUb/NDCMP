/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.SubActivityDAO;
import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.service.SubActivityService;

/**
 * @author Gowri
 *
 */
@Service("subActivityService")
@Transactional
public class SubActivityServiceImpl implements SubActivityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubActivityServiceImpl.class);
	
	@Autowired
	SubActivityDAO subActivityDAO;

	@Override
	public List<SubActivity> listAllSubActivity() {
		return subActivityDAO.listAllSubActivity();
	}

	@Override
	public void addSubActivity(SubActivity subActivity) {
		subActivityDAO.addSubActivity(subActivity);
	}

	@Override
	public void deleteSubActivity(Integer id) {
		subActivityDAO.deleteSubActivity(id);
	}

	@Override
	public SubActivity getById(Integer id) {
		return subActivityDAO.getById(id);
	}
	

	@Override
	public SubActivity getByIdAndApproveAndCompletionStatus(Integer id) {
		return subActivityDAO.getByIdAndApproveAndCompletionStatus(id);
	}
	
	@Override
	public List<SubActivity> getByLeadAgency(Agency leadagency) {
		return subActivityDAO.getByLeadAgency(leadagency);
	}

	@Override
	public List<SubActivity> findByStatus(String status) {
		LOGGER.info("List By status");
		return subActivityDAO.findByStatus(status);
	}
	
	@Override
	public List<SubActivity> findByKeyActivity(KeyActivity keyActivity) {
//		LOGGER.info("List By keyActivity");
		return subActivityDAO.findByKeyActivity(keyActivity);
	}
	
	
	@Override
	public List<SubActivity> findByKeyActivityAndApproveAndCompletionStatus(KeyActivity keyActivity) {
//		LOGGER.info("List By keyActivity");
		return subActivityDAO.findByKeyActivityAndApproveAndCompletionStatus(keyActivity);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.OutcomeService#getByThemeId(com.ceainfotech
	 * .ndcmp.model.Theme)
	 */
	@Override
	public Integer getByKeyActivityId(KeyActivity keyActivity) {
		return subActivityDAO.getByKeyActivityId(keyActivity);
	}
	
	@Override
	public List<SubActivity> findByPartnerAgency(Agency agency) {
		if(agency != null){
			return subActivityDAO.findByPartnerAgency(agency);
		}
		return null;
	}
	
	@Override
	public List<SubActivity> findBySyncStatus(boolean syncStatus) {
		return subActivityDAO.findBySyncStatus(syncStatus);
	}
	
	@Override
	public List<SubActivity> findByPartnerAgencyAndSyncStatus(Agency agency, boolean syncStatus) {
		return subActivityDAO.findByPartnerAgencyAndSyncStatus(agency, syncStatus);
	}

	@Override
	public List<SubActivity> findSubActivityByAgencyIds(List<Integer> agencyIds) {
		return subActivityDAO.findSubActivityByAgencyIds(agencyIds);
	}

	@Override
	public List<SubActivity> findSubActivityByAgencyIdsAndDate(List<Integer> agencyIds, String lastSyncedTime, String syncRequestedDate) {
		return subActivityDAO.findSubActivityByAgencyIdsAndDate(agencyIds, lastSyncedTime, syncRequestedDate);
	}
	
	@Override
	public List<SubActivity> getSubActivityByReportingPeriodIdAndAgencyId(Integer reportingperiodId, Integer agencyId) {
		return subActivityDAO.getSubActivityByReportingPeriodIdAndAgencyId(reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getAllSubActivityByReportingPeriodIdAndAgencyId(Integer reportingperiodId, Integer agencyId) {
		return subActivityDAO.getAllSubActivityByReportingPeriodIdAndAgencyId(reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndLeadAgency(List<Integer> reportingperiodId, Integer agencyId){
		return subActivityDAO.getSubActivitiesByReportingPeriodsAndLeadAgency(reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndLeadAgency(KeyActivity keyActivity,Integer reportingperiodId, Integer agencyId)  {
		return subActivityDAO.getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndLeadAgency(keyActivity,reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsAndLeadAgency(KeyActivity keyActivity,List<Integer> reportingperiodId, Integer agencyId){
		return subActivityDAO.getSubActivitiesByKeyActivityAndReportingPeriodsAndLeadAgency(keyActivity, reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivityByReportingIdAndSubActivities(Integer reportingId, List<SubActivity> subActivities) {
		return subActivityDAO.getSubActivityByReportingIdAndSubActivities(reportingId, subActivities);
	}

	@Override
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndPartnerAgency(Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityDAO.getSubActivitiesByOpenedReportingPeriodAndPartnerAgency(reportingperiodId, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getAllSubActivitiesByOpenedReportingPeriodAndPartnerAgency(Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityDAO.getAllSubActivitiesByOpenedReportingPeriodAndPartnerAgency(reportingperiodId, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndPartnerAgency(List<Integer> reportingperiodIds, Integer partnerAgencyId){
		return subActivityDAO.getSubActivitiesByReportingPeriodsAndPartnerAgency(reportingperiodIds, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriod(Integer reportingperiodId) {
		return subActivityDAO.getSubActivitiesByOpenedReportingPeriod(reportingperiodId);
	}
	
	@Override
	public List<SubActivity> findByCompletedReportingPeriod(ReportingPeriod reportingPeriod){
		return subActivityDAO.findByCompletedReportingPeriod(reportingPeriod);
	}
	
	@Override
	public SubActivity getSubActivitiesBySubActivityAndReportingPeriods(SubActivity subActivity, List<Integer> reportingPeriodIds){
		return subActivityDAO.getSubActivitiesBySubActivityAndReportingPeriods(subActivity, reportingPeriodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByReportingPeriods(List<Integer> reportingPeriodIds){
		return subActivityDAO.getSubActivitiesByReportingPeriods(reportingPeriodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByReportingPeriodsAndNotCompleted(List<Integer> reportingPeriodIds){
		return subActivityDAO.getSubActivitiesByReportingPeriodsAndNotCompleted(reportingPeriodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillerAndOpenedReportingPeriod(Integer strategicPillarId, Integer reportingPeriodId){
		return subActivityDAO.getSubActivitiesByStrategicPillerAndOpenedReportingPeriod(strategicPillarId, reportingPeriodId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByListOfStrategicPillerAndListOfOpenedReportingPeriod(List<Integer> strategicPillarId, List<Integer> reportingPeriodId){
		return subActivityDAO.getSubActivitiesByListOfStrategicPillerAndListOfOpenedReportingPeriod(strategicPillarId, reportingPeriodId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriod(Integer themeId, Integer reportingPeriodId) {
		return subActivityDAO.getSubActivitiesByThemeAndOpenedReportingPeriod(themeId, reportingPeriodId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriod(List<Integer> theme, List<Integer> reportingPeriod) {
		return subActivityDAO.getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriod(theme, reportingPeriod);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriod(Integer outcomeId, Integer reportingPeriodId) {
		return subActivityDAO.getSubActivitiesByOutcomeAndOpenedReportingPeriod(outcomeId, reportingPeriodId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByListOfOutcomeAndListOfOpenedReportingPeriod(List<Integer> outcomeId, List<Integer> reportingPeriodId) {
		return subActivityDAO.getSubActivitiesByListOfOutcomeAndListOfOpenedReportingPeriod(outcomeId, reportingPeriodId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriod(Integer outputId, Integer reportingPeriodId) {
		return subActivityDAO.getSubActivitiesByOutputAndOpenedReportingPeriod(outputId, reportingPeriodId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillerAndOpenedReportingPeriodAndPartnerAgency(Integer strategicPillerId, Integer reportingperiodId,Integer partnerAgencyId){
		return subActivityDAO.getSubActivitiesByStrategicPillerAndOpenedReportingPeriodAndPartnerAgency(strategicPillerId,reportingperiodId, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriodsAndPartnerAgency(Integer strategicPillerId, List<Integer> reportingperiodIds,Integer partnerAgencyId){
		return subActivityDAO.getSubActivitiesByStrategicPillerAndReportingPeriodsAndPartnerAgency(strategicPillerId, reportingperiodIds, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(Integer themeId, Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityDAO.getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(themeId,reportingperiodId, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(Integer themeId, List<Integer> reportingperiodIds, Integer partnerAgencyId){
		return subActivityDAO.getSubActivitiesByThemeAndOpenedReportingPeriodAndPartnerAgency(themeId, reportingperiodIds, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriodAndPartnerAgency(Integer outcomeId,Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityDAO.getSubActivitiesByOutcomeAndOpenedReportingPeriodAndPartnerAgency(outcomeId,reportingperiodId, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriodsAndPartnerAgency(Integer outcomeId,List<Integer> reportingperiodId, Integer partnerAgencyId){
		return subActivityDAO.getSubActivitiesByOutcomeAndReportingPeriodsAndPartnerAgency(outcomeId, reportingperiodId, partnerAgencyId);
	}

	@Override
	public SubActivity getSubActivityByNameAndKeyActivity(String name,
			KeyActivity keyActivity) {
		return subActivityDAO.getSubActivityByNameAndKeyActivity(name,keyActivity);
	}

	
	@Override
	public SubActivity getSubActivityByName(String name) {
		return subActivityDAO.getSubActivityByName(name);
	}

	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillarAndOpenedReportingPeriodAndLeadAgency(
			Integer strategicPillerId, Integer reportingperiodId,Integer agencyId) {
		return subActivityDAO.getSubActivitiesByStrategicPillarAndOpenedReportingPeriodAndLeadAgency(strategicPillerId, reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillarAndReportingPeriodsAndLeadAgency(Integer strategicPillerId,List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityDAO.getSubActivitiesByStrategicPillarAndReportingPeriodsAndLeadAgency(strategicPillerId, reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(
			Integer strategicPillerId, Integer reportingperiodId) {
		return subActivityDAO.getSubActivitiesByStrategicPillarAndOpenedReportingPeriod(strategicPillerId, reportingperiodId);
	}
	
	/**
	 * @author PremKumar
	 */
	
	@Override
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndCurrentAgency(
			List<Integer> strategicPillerId, List<Integer> reportingperiodId,Integer agencyId) {
		return subActivityDAO.getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndCurrentAgency(strategicPillerId, reportingperiodId,agencyId);
	}
	
	/**
	 * @author PremKumar
	 */
	
	@Override
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndLeadAgency(
			List<Integer> strategicPillerId, List<Integer> reportingperiodId,Integer agencyId) {
		return subActivityDAO.getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriodAndLeadAgency(strategicPillerId, reportingperiodId,agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillarAndReportingPeriods(Integer strategicPillerId,List<Integer> reportingperiodIds){
		return subActivityDAO.getSubActivitiesByStrategicPillarAndReportingPeriods(strategicPillerId, reportingperiodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillarsAndReportingPeriods(List<Integer> strategicPillerIds,List<Integer> reportingperiodIds){
		return subActivityDAO.getSubActivitiesByStrategicPillarsAndReportingPeriods(strategicPillerIds, reportingperiodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByThemesAndReportingPeriods(List<Integer> themeIds,List<Integer> reportingperiodIds){
		return subActivityDAO.getSubActivitiesByThemesAndReportingPeriods(themeIds, reportingperiodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutcomesAndReportingPeriods(List<Integer> outcomeIds,List<Integer> reportingperiodIds){
		return subActivityDAO.getSubActivitiesByOutcomesAndReportingPeriods(outcomeIds, reportingperiodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutputsAndReportingPeriods(List<Integer> outputIds,List<Integer> reportingperiodIds){
		return subActivityDAO.getSubActivitiesByOutputsAndReportingPeriods(outputIds, reportingperiodIds);
	}

	@Override
	public List<SubActivity> getSubActivitiesByThemeAndOpenedReportingPeriodAndLeadAgency(
			Integer themeId, Integer reportingperiodId, Integer agencyId) {
		return subActivityDAO.getSubActivitiesByThemeAndOpenedReportingPeriodAndLeadAgency(themeId, reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsAndLeadAgency(Integer themeId,List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityDAO.getSubActivitiesByThemeAndReportingPeriodsAndLeadAgency(themeId, reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingsPeriodAndLeadAgency(Integer outcomeId,List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityDAO.getSubActivitiesByOutcomeAndReportingsPeriodAndLeadAgency(outcomeId, reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutcomeAndOpenedReportingPeriodAndLeadAgency(
			Integer outcomeId, Integer reportingperiodId, Integer agencyId) {
		return subActivityDAO.getSubActivitiesByOutcomeAndOpenedReportingPeriodAndLeadAgency(outcomeId, reportingperiodId, agencyId);
	}

	@Override
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriodAndLeadAgency(
			Integer outputId, Integer reportingperiodId, Integer agencyId) {
		return subActivityDAO.getSubActivitiesByOutputAndOpenedReportingPeriodAndLeadAgency(outputId, reportingperiodId, agencyId);
	}

	@Override
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndPartnerAgency(
			Integer keyActivityId, Integer reportingperiodId,
			Integer partnerAgencyId) {
		return subActivityDAO.getSubActivitiesByKeyActivityAndOpenedReportingPeriodAndPartnerAgency(keyActivityId, reportingperiodId, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsAndPartnerAgency(Integer keyActivityId,List<Integer> reportingperiodId, Integer partnerAgencyId){
		return subActivityDAO.getSubActivitiesByKeyActivityAndReportingPeriodsAndPartnerAgency(keyActivityId, reportingperiodId, partnerAgencyId);
	}

	@Override
	public List<SubActivity> getSubActivitiesByOutputAndOpenedReportingPeriodAndPartnerAgency(
			Integer outputId, Integer reportingperiodId, Integer partnerAgencyId) {
		return subActivityDAO.getSubActivitiesByOutputAndOpenedReportingPeriodAndPartnerAgency(outputId, reportingperiodId, partnerAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutputAndReportingPeriodsAndPartnerAgency(Integer outputId, List<Integer> reportingperiodId, Integer partnerAgencyId){
		return subActivityDAO.getSubActivitiesByOutputAndReportingPeriodsAndPartnerAgency(outputId, reportingperiodId, partnerAgencyId);
	}

	@Override
	public List<SubActivity> getSubActivitiesByOpenedReportingPeriodAndPartnerAgencyAndLeadAgency(
			Integer reportingPeriodId, Integer partnerAgencyId,
			Integer leadAgencyId) {
		return subActivityDAO.getSubActivitiesByOpenedReportingPeriodAndPartnerAgencyAndLeadAgency(reportingPeriodId, partnerAgencyId, leadAgencyId);
	}

	@Override
	public List<SubActivity> getSubActivitiesByKeyActivityAndOpenedReportingPeriod(
			KeyActivity keyActivity, Integer reportingperiodId) {
		return subActivityDAO.getSubActivitiesByKeyActivityAndOpenedReportingPeriod(keyActivity, reportingperiodId);
	}

	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriods(
			Integer strategicId, List<Integer> reportingPeriodIds) {
		return subActivityDAO.getSubActivitiesByStrategicPillerAndReportingPeriods(strategicId,reportingPeriodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillerAndReportingPeriodsNotCompleted(
			Integer strategicId, List<Integer> reportingPeriodIds) {
		return subActivityDAO.getSubActivitiesByStrategicPillerAndReportingPeriodsNotCompleted(strategicId,reportingPeriodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriods(
			Integer themeId,List<Integer> reportingPeriodId) {
		return subActivityDAO.getSubActivitiesByThemeAndReportingPeriods(themeId, reportingPeriodId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByThemeAndReportingPeriodsNotCompleted(
			Integer themeId,List<Integer> reportingPeriodId) {
		return subActivityDAO.getSubActivitiesByThemeAndReportingPeriodsNotCompleted(themeId, reportingPeriodId);
	}

	@Override
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriodsNotCompleted(
			Integer outcomeId, List<Integer> reportingPeriodIds) {
		return subActivityDAO.getSubActivitiesByOutcomeAndReportingPeriodsNotCompleted(outcomeId,reportingPeriodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutcomeAndReportingPeriods(
			Integer outcomeId, List<Integer> reportingPeriodIds) {
		return subActivityDAO.getSubActivitiesByOutcomeAndReportingPeriods(outcomeId,reportingPeriodIds);
	}

	@Override
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriods(
			KeyActivity keyActivity, List<Integer> reportingPeriodIds) {
		return subActivityDAO.getSubActivitiesByKeyActivityAndReportingPeriods(keyActivity,reportingPeriodIds);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByKeyActivityAndReportingPeriodsNotCompleted(
			KeyActivity keyActivity, List<Integer> reportingPeriodIds) {
		return subActivityDAO.getSubActivitiesByKeyActivityAndReportingPeriodsNotCompleted(keyActivity,reportingPeriodIds);
	}

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.SubActivityService#getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriod(java.util.List, java.util.List)
	 */
	@Override
	public List<SubActivity> getSubActivitiesBylistOfStrategicPillarAndOpenedReportingPeriod(List<Integer> strategicPillerId,
			List<Integer> reportingPeriodId) {
		return null;
	}

	
	/**
	 * purpose : to get all subactivity based on the partner agency
	 */
	@Override
	public List<SubActivity> getSubActivitiesByPartnerAgency(Integer partnerAgencyId) {
		if(partnerAgencyId != null){
			return subActivityDAO.getSubActivitiesByPartnerAgency(partnerAgencyId);
		}
		return null;
	}

	@Override
	public List<SubActivity> getSubActivitiesByListStrategicPillerAndListOpenedReportingPeriodAndPartnerAgency(
			List<Integer> strategicPillarIds, List<Integer> reportingPeriodIds,
			Integer agency) {
		return subActivityDAO.getSubActivitiesByListStrategicPillerAndListOpenedReportingPeriodAndPartnerAgency(strategicPillarIds,reportingPeriodIds,agency);
	}

	@Override
	public List<SubActivity> getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(
			List<Integer> theme, List<Integer> reportingPeriodId, Integer agency) {
		return subActivityDAO.getSubActivitiesByListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(theme,reportingPeriodId,agency);
	}

	
	@Override
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndPartnerAgency(
			List<Integer> pillar,List<Integer> theme,List<Integer> outcome,List<Integer> reportingPeriodId,Integer agency) {
		return subActivityDAO.getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndPartnerAgency(pillar,theme,outcome,reportingPeriodId,agency);
	}

	@Override
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndLeadAgency(
			List<Integer> pillar,List<Integer> theme,List<Integer> outcome,List<Integer> reportingPeriodId,Integer agency) {
		return subActivityDAO.getSubActivitiesByListOfPillarListOfThemeListOfOutcomeAndListOfOpenedReportingPeriodAndLeadAgency(pillar,theme,outcome,reportingPeriodId,agency);
	}
	
	
	
	@Override
	public List<SubActivity> getSubActivitiesByListOutcomeAndReportingPeriodsAndPartnerAgency(
			List<Integer> outcomeId, List<Integer> reportingperiodId,
			Integer partnerAgencyId) {
		return null;
	}

	@Override
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(
			List<Integer> pillar, List<Integer> theme,
			List<Integer> reportingPeriodId, Integer agency) {
		return subActivityDAO.getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndPartnerAgency(pillar,theme,reportingPeriodId,agency);
	}
	
	
	@Override
	public List<SubActivity> getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndLeadAgency(
			List<Integer> pillar, List<Integer> theme,
			List<Integer> reportingPeriodId, Integer agency) {
		return subActivityDAO.getSubActivitiesByListOfPillarListOfThemeAndListOfOpenedReportingPeriodAndLeadAgency(pillar,theme,reportingPeriodId,agency);
	}
	

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.SubActivityService#getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(java.util.List, java.util.List, java.lang.Integer)
	 */
	@Override
	public List<SubActivity> getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(List<Integer> strategicPillerIds,
			List<Integer> reportingperiodId, Integer agencyId) {
		return subActivityDAO.getSubActivitiesByStrategicPillarsAndReportingPeriodsAndLeadAgency(strategicPillerIds, reportingperiodId, agencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(List<Integer> themeIds,List<Integer> reportingperiodIds, Integer leadAgencyId){
		return subActivityDAO.getSubActivitiesByThemesAndReportingPeriodsAndLeadAgency(themeIds, reportingperiodIds, leadAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutcomesAndReportingPeriodsAndLeadAgency(List<Integer> outcomeId,List<Integer> reportingperiodIds, Integer leadAgencyId){
		return subActivityDAO.getSubActivitiesByOutcomesAndReportingPeriodsAndLeadAgency(outcomeId, reportingperiodIds, leadAgencyId);
	}
	
	@Override
	public List<SubActivity> getSubActivitiesByOutputsAndReportingPeriodsAndLeadAgency(List<Integer> outputIds,List<Integer> reportingperiodIds, Integer leadAgencyId){
		return subActivityDAO.getSubActivitiesByOutputsAndReportingPeriodsAndLeadAgency(outputIds, reportingperiodIds, leadAgencyId);
	}
}
