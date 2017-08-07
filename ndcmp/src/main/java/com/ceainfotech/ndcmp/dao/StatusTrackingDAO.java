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

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.repository.StatusTrackingRepository;

/**
 * @author bosco
 *
 */
@Repository
public class StatusTrackingDAO {

	private final StatusTrackingRepository statusTrackingRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusTrackingDAO.class);

	@Autowired
	public StatusTrackingDAO(final StatusTrackingRepository statusTrackingRepository) {
		this.statusTrackingRepository = statusTrackingRepository;
	}

	public StatusTracking getById(Integer id){
		return statusTrackingRepository.findOne(id);
	}
	
	public StatusTracking add(StatusTracking statusTracking) {
		LOGGER.debug("Add new user .. {}", statusTracking);
		return statusTrackingRepository.save(statusTracking);
	}
	
	public List<StatusTracking> getByUser(User user) {
		LOGGER.debug("Get StatusTracking Details by User .. {}", user);
		return statusTrackingRepository.findByUser(user);
	}
	
	public List<StatusTracking> findByAgency(Agency agency) {
		return statusTrackingRepository.findByAgency(agency);
	}
	
	public StatusTracking getBySubActivity(SubActivity subActivity) {
		LOGGER.debug("Get StatusTracking Details by SubActivity .. {}", subActivity);
		return statusTrackingRepository.findBysubActivity(subActivity);
	}
	
	public List<StatusTracking> findBysubActivityAndReportingPeriodAndUserLevel(SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel) {
		return statusTrackingRepository.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, userLevel);
	}
	
	public List<StatusTracking> findByReportingPeriodAndSentForReviewAndUserLevel(ReportingPeriod reportingPeriod, boolean sentForReview, Integer userLevel) {
		return statusTrackingRepository.findByReportingPeriodAndSentForReviewAndUserLevel(reportingPeriod, sentForReview, userLevel);
	}
	
	public List<StatusTracking> findByReportingPeriodAndSentForReviewOrReviewStatusAndUserLevel(ReportingPeriod reportingPeriod, boolean sentForReview, Integer reworkStatus, Integer userLevel) {
		return statusTrackingRepository.findByReportingPeriodAndSentForReviewAndReviewStatusAndUserLevel(reportingPeriod, sentForReview, reworkStatus, userLevel);
	}
	
	public List<StatusTracking> findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(
			SubActivity subActivity, ReportingPeriod reportingPeriod,
			Integer userLevel, boolean  sentForReview) {
		return statusTrackingRepository.findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(subActivity, reportingPeriod, userLevel, sentForReview);
	}
	
	
	public StatusTracking getBySubActivityAndUserAndStatus(SubActivity subActivity,User user, Statuss statuss) {
		return statusTrackingRepository.findBySubActivityAndUserAndStatuss(subActivity,user, statuss);
	}
	
	public StatusTracking getBySubActivityAndUserAndComplete(SubActivity subActivity,User user, boolean complete) {
		return statusTrackingRepository.findBySubActivityAndUserAndComplete(subActivity,user, complete);
	}
	
	public List<StatusTracking> findByReportingPeriodAndUser(ReportingPeriod reportingPeriod, User user) {
		return statusTrackingRepository.findByReportingPeriodAndUser(reportingPeriod, user);
	}
	
	public List<StatusTracking> findByReportingPeriodAndAgency(ReportingPeriod reportingPeriod, Agency agency) {
		return statusTrackingRepository.findByReportingPeriodAndAgency(reportingPeriod, agency);
	}
	
	public List<StatusTracking> findStatusTrackingByAgencyIds(List<Integer> agency) {
		return statusTrackingRepository.findStatusTrackingByAgencyIds(agency);
	}
	
	public List<StatusTracking> findStatusTrackingByAgencyIdsAndModificationTime(List<Integer> agency, String lastSyncedTime, String currentTime) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return statusTrackingRepository.findStatusTrackingByAgencyIdsAndModificationTime(agency, lastSyncedDate, currentDate);
	}
	
	public List<StatusTracking> findStatusTrackingByUserAndModificationTime(User user, String lastSyncedTime, String currentTime) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return statusTrackingRepository.findStatusTrackingByUserAndModificationTime(user, lastSyncedDate, currentDate);
	}
	
	
	public void addStatusTrackingList(List<StatusTracking> statusTrackingList){
		LOGGER.debug("Add list of status tracking");
		statusTrackingRepository.save(statusTrackingList);
	}


	public List<StatusTracking> getByStatus(Statuss statuss) {
		return statusTrackingRepository.findByStatuss(statuss);
	}
	
	public List<StatusTracking> findByUser(User user) {
		return statusTrackingRepository.findByUserAndCompleteStatus(user);
	}

	public List<StatusTracking> findBySubActivity(SubActivity subActivity){
		if(subActivity != null){
			return statusTrackingRepository.findBySubActivity(subActivity);
		}
		return null;
	}

	public StatusTracking findBySubActivityAndStatus(SubActivity subActivity,
			Statuss statuss) {
		return statusTrackingRepository.findBySubActivityAndStatuss(subActivity,statuss);
	}
	
	public StatusTracking findByUserAndSubActivityAndReportingPeriodAndUserLevel(User user, SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel){
		return statusTrackingRepository.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user, subActivity, reportingPeriod, userLevel);
	}
	
	public StatusTracking findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(Agency agency, SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel) {
		return statusTrackingRepository.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, userLevel);
	}
	
	public List<StatusTracking> findByUserAndReportingPeriod(User user, ReportingPeriod reportingPeriod){
		return statusTrackingRepository.findByUserAndReportingPeriod(user, reportingPeriod);
	}
	
	public List<StatusTracking> findByUserAndReportingPeriodAndComplete(User user, ReportingPeriod reportingPeriod, boolean complete) {
		return statusTrackingRepository.findByUserAndReportingPeriodAndComplete(user, reportingPeriod, complete);
	}

	public List<StatusTracking> findByAgencyAndReportingPeriodAndComplete(Agency agency, ReportingPeriod reportingPeriod, boolean complete) {
		return statusTrackingRepository.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, complete);
	}
	
	public List<StatusTracking> findBySubActivityAgencyAndReportingPeriod(SubActivity subActivity, ReportingPeriod reportingPeriod){
		return statusTrackingRepository.findBySubActivityAgencyAndReportingPeriod(subActivity, reportingPeriod);
	}
	
	public List<StatusTracking> findBySubActivityAgencyAndReportingPeriodAndReviewedStatus(SubActivity subActivity, ReportingPeriod reportingPeriod, Integer reviewStatus){
		return statusTrackingRepository.findBySubActivityAgencyAndReportingPeriodAndReviewedStatus(subActivity, reportingPeriod, reviewStatus);
	}
	
	public StatusTracking getBySubActivityAndUser(SubActivity subActivity2,User user) {
		return statusTrackingRepository.getBySubActivityAndUser(subActivity2,user);
	}
	
	public List<StatusTracking> findByReportingPeriodAndReviewerAndReviewStatus(ReportingPeriod reportingPeriod, User reviewedBy){
		return statusTrackingRepository.findByReportingPeriodAndReviewerAndReviewStatus(reportingPeriod, reviewedBy);
	}
	
	public List<StatusTracking> findByReviewerAndReviewStatus(User reviewedBy){
		return statusTrackingRepository.findByReviewerAndReviewStatus(reviewedBy);
	}
	
	public List<StatusTracking> findByReportingPeriodAndReviewerAndUserAndReviewStatus(ReportingPeriod reportingPeriod, User reviewedBy, User user) {
		return statusTrackingRepository.findByReportingPeriodAndReviewerAndUserAndReviewStatus(reportingPeriod, reviewedBy, user);
	}
	
	public List<StatusTracking> findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(ReportingPeriod reportingPeriod, User reviewedBy, Agency agency) {
		return statusTrackingRepository.findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(reportingPeriod, reviewedBy, agency);
	}
	
	public List<StatusTracking> findByUserAndReportingPeriodAndReworkRequired(User user, ReportingPeriod reportingPeriod, boolean reworkRequired){
		return statusTrackingRepository.findByUserAndReportingPeriodAndReworkRequired(user, reportingPeriod, reworkRequired);
	}
	
	public List<StatusTracking> findByAgencyAndReportingPeriodAndReworkRequired(Agency agency, ReportingPeriod reportingPeriod, boolean reworkRequired){
		return statusTrackingRepository.findByAgencyAndReportingPeriodAndReworkRequired(agency, reportingPeriod, reworkRequired);
	}
	
	public List<StatusTracking> findByUserAndReportingPeriodAndReworkRequiredAndComplete(User user, ReportingPeriod reportingPeriod, boolean reworkRequired,boolean complete){
		return statusTrackingRepository.findByUserAndReportingPeriodAndReworkRequiredAndComplete(user, reportingPeriod, reworkRequired, complete);
	}

	public List<StatusTracking> findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(Agency agency, ReportingPeriod reportingPeriod, boolean reworkRequired,boolean complete){
		return statusTrackingRepository.findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(agency, reportingPeriod, reworkRequired, complete);
	}
	
	public List<StatusTracking> findByUserAndSentForReviewAndReviewStatus(User user, boolean sentForReview, Integer reviewStatus) {
		return statusTrackingRepository.findByUserAndSentForReviewAndReviewStatus(user, sentForReview, reviewStatus);
	}
	
	public List<StatusTracking> findByAgencyAndSentForReviewAndReviewStatus(Agency agency, boolean sentForReview, Integer reviewStatus) {
		return statusTrackingRepository.findByAgencyAndSentForReviewAndReviewStatus(agency, sentForReview, reviewStatus);
	}
	
	public List<StatusTracking> findByAgencyAndSentForReviewAndReportingPeriod(Agency agency, boolean sentForReview, ReportingPeriod reportingPeriod) {
		return statusTrackingRepository.findByAgencyAndSentForReviewAndReportingPeriod(agency, sentForReview, reportingPeriod);
	}
	
	public List<StatusTracking> findByAgencyAndSentForReviewAndReviewStatusAndReportingPeriod(Agency agency, boolean sentForReview, Integer reviewStatus, ReportingPeriod reportingPeriod){
		return statusTrackingRepository.findByAgencyAndSentForReviewAndReviewStatusAndReportingPeriod(agency, sentForReview, reviewStatus, reportingPeriod);
	}
	
	public List<StatusTracking> findReworkDataByReportingPeriodAndUserAndReviewStatus(ReportingPeriod reportingPeriod, User user){
		return statusTrackingRepository.findReworkDataByReportingPeriodAndUserAndReviewStatus(reportingPeriod, user);
	}
	
	public List<StatusTracking> findReworkDataByReportingPeriodAndUserAndReadyForReview(ReportingPeriod reportingPeriod, User user){
		return statusTrackingRepository.findReworkDataByReportingPeriodAndUserAndReadyForReview(reportingPeriod, user);
	}
	
	public List<StatusTracking> findReworkDataByReportingPeriodAndAgencyAndReadyForReview(ReportingPeriod reportingPeriod, Agency agency){
		return statusTrackingRepository.findReworkDataByReportingPeriodAndAgencyAndReadyForReview(reportingPeriod, agency);
	}
	
	
	
	public List<StatusTracking> findReviewedDataByReportingPeriodAndReviewerAndModificationTime(ReportingPeriod reportingPeriod, User reviewedBy, Date modificationTime){
		return statusTrackingRepository.findReviewedDataByReportingPeriodAndReviewerAndModificationTime(reportingPeriod, reviewedBy, modificationTime);
	}
	
	public List<StatusTracking> findByReportingPeriodAndUserLevel(ReportingPeriod reportingPeriod, Integer userLevel){
		return statusTrackingRepository.findByReportingPeriodAndUserLevel(reportingPeriod, userLevel);
	}
	
	public List<StatusTracking> findReviewDataByLeadAgency(Integer leadAgency) {
		return statusTrackingRepository.findReviewDataForAgency(leadAgency);
	}
	
	public List<StatusTracking> findReviewDataForApprover() {
		return statusTrackingRepository.findReviewDataForApprover();
	}
}
