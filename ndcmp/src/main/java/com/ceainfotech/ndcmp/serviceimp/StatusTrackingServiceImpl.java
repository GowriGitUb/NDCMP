/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.dao.StatusTrackingDAO;
import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.StatusTrackingService;

/**
 * @author bosco
 *
 */
@Service("statusTrackingService")
@Transactional
public class StatusTrackingServiceImpl implements StatusTrackingService{

	@Autowired
	private StatusTrackingDAO statusTrackingDAO;
	
	@Override
	public StatusTracking add(StatusTracking statusTracking) {
		return statusTrackingDAO.add(statusTracking);
	}

	@Override
	public List<StatusTracking> getByUser(User user) {
		return statusTrackingDAO.getByUser(user);
	}
	
	@Override
	public List<StatusTracking> findByAgency(Agency agency) {
		return statusTrackingDAO.findByAgency(agency);
	}

	@Override
	public StatusTracking getBySubActivity(SubActivity subActivity) {
		return statusTrackingDAO.getBySubActivity(subActivity);
	}

	@Override
	public List<StatusTracking> findBysubActivityAndReportingPeriodAndUserLevel(SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel) {
		return statusTrackingDAO.findBysubActivityAndReportingPeriodAndUserLevel(subActivity, reportingPeriod, userLevel);
	}

	public List<StatusTracking> getByStatus(Statuss statuss) {
		return statusTrackingDAO.getByStatus(statuss);
	}

	@Override
	public void addAll(List<StatusTracking> statusTrackingList) {
		statusTrackingDAO.addStatusTrackingList(statusTrackingList);
	}

	@Override
	public StatusTracking getBySubActivityAndUserAndStatus(SubActivity subActivity, User user, Statuss statuss) {
		return statusTrackingDAO.getBySubActivityAndUserAndStatus(subActivity, user, statuss);
	}
	
	@Override
	public StatusTracking getBySubActivityAndUserAndComplete(SubActivity subActivity, User user, boolean complete) {
		return statusTrackingDAO.getBySubActivityAndUserAndComplete(subActivity, user, complete);
	}

	@Override
	public List<StatusTracking> findByUser(User user) {
		return statusTrackingDAO.findByUser(user);
	}
	
	@Override
	public List<StatusTracking> findBySubActivity(SubActivity subActivity) {
		if(subActivity != null){
			return statusTrackingDAO.findBySubActivity(subActivity);
		}
		return null;
	}

	@Override
	public StatusTracking getBySubActivityAndStatus(SubActivity subActivity,
			Statuss statuss) {
		return statusTrackingDAO.findBySubActivityAndStatus(subActivity,statuss);
	}

	@Override
	public StatusTracking findByUserAndSubActivityAndReportingPeriodAndUserLevel(User user,
			SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel) {
		return statusTrackingDAO.findByUserAndSubActivityAndReportingPeriodAndUserLevel(user, subActivity, reportingPeriod, userLevel);
	}

	@Override
	public List<StatusTracking> findByUserAndReportingPeriod(User user,ReportingPeriod reportingPeriod) {
		return statusTrackingDAO.findByUserAndReportingPeriod(user, reportingPeriod);
	}

	@Override
	public List<StatusTracking> findByUserAndReportingPeriodAndComplete(User user, ReportingPeriod reportingPeriod, boolean complete) {
		return statusTrackingDAO.findByUserAndReportingPeriodAndComplete(user, reportingPeriod, complete);
	}
	@Override
	public List<StatusTracking> findBySubActivityAgencyAndReportingPeriod(
			SubActivity subActivity, ReportingPeriod reportingPeriod) {
		return statusTrackingDAO.findBySubActivityAgencyAndReportingPeriod(subActivity, reportingPeriod);
	}

	@Override
	public StatusTracking getById(Integer id) {
		return statusTrackingDAO.getById(id);
	}

	@Override
	public StatusTracking getBySubActivityAndUser(SubActivity subActivity2,User user) {
		return statusTrackingDAO.getBySubActivityAndUser(subActivity2,user);
	}

	@Override
	public List<StatusTracking> findByReportingPeriodAndReviewerAndReviewStatus(
			ReportingPeriod reportingPeriod, User reviewedBy) {
		return statusTrackingDAO.findByReportingPeriodAndReviewerAndReviewStatus(reportingPeriod, reviewedBy);
	}

	@Override
	public List<StatusTracking> findByUserAndReportingPeriodAndReworkRequired(
			User user, ReportingPeriod reportingPeriod, boolean reworkRequired) {
		return statusTrackingDAO.findByUserAndReportingPeriodAndReworkRequired(user, reportingPeriod, reworkRequired);
	}
	
	@Override
	public List<StatusTracking> findByAgencyAndReportingPeriodAndReworkRequired(
			Agency agency, ReportingPeriod reportingPeriod, boolean reworkRequired) {
		return statusTrackingDAO.findByAgencyAndReportingPeriodAndReworkRequired(agency, reportingPeriod, reworkRequired);
	}

	@Override
	public List<StatusTracking> findByUserAndReportingPeriodAndReworkRequiredAndComplete(
			User user, ReportingPeriod reportingPeriod, boolean reworkRequired,
			boolean complete) {
		return statusTrackingDAO.findByUserAndReportingPeriodAndReworkRequiredAndComplete(user, reportingPeriod, reworkRequired, complete);
	}

	@Override
	public List<StatusTracking> findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(
			Agency agency, ReportingPeriod reportingPeriod, boolean reworkRequired,
			boolean complete) {
		return statusTrackingDAO.findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(agency, reportingPeriod, reworkRequired, complete);
	}
	
	@Override
	public List<StatusTracking> findReworkDataByReportingPeriodAndUserAndReviewStatus(
			ReportingPeriod reportingPeriod, User user) {
		return statusTrackingDAO.findReworkDataByReportingPeriodAndUserAndReviewStatus(reportingPeriod, user);
	}

	@Override
	public List<StatusTracking> findBySubActivityAgencyAndReportingPeriodAndReviewedStatus(
			SubActivity subActivity, ReportingPeriod reportingPeriod,
			Integer reviewStatus) {
		return statusTrackingDAO.findBySubActivityAgencyAndReportingPeriodAndReviewedStatus(subActivity, reportingPeriod, reviewStatus);
	}

	@Override
	public List<StatusTracking> findByReportingPeriodAndUserLevel(ReportingPeriod reportingPeriod, Integer userLevel) {
		return statusTrackingDAO.findByReportingPeriodAndUserLevel(reportingPeriod, userLevel);
	}

	@Override
	public List<StatusTracking> findReviewDataByLeadAgency(Integer leadAgency) {
		return statusTrackingDAO.findReviewDataByLeadAgency(leadAgency);
	}

	@Override
	public List<StatusTracking> findReviewDataForApprover() {
		return statusTrackingDAO.findReviewDataForApprover();
	}

	@Override
	public List<StatusTracking> findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(
			SubActivity subActivity, ReportingPeriod reportingPeriod,
			Integer userLevel, boolean sentForReview) {
		return statusTrackingDAO.findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(subActivity, reportingPeriod, userLevel, sentForReview);
	}

	@Override
	public List<StatusTracking> findByUserAndSentForReviewAndReviewStatus(
			User user, boolean sentForReview, Integer reviewStatus) {
		return statusTrackingDAO.findByUserAndSentForReviewAndReviewStatus(user, sentForReview, reviewStatus);
	}
	
	@Override
	public List<StatusTracking> findByAgencyAndSentForReviewAndReviewStatus(
			Agency agency, boolean sentForReview, Integer reviewStatus) {
		return statusTrackingDAO.findByAgencyAndSentForReviewAndReviewStatus(agency, sentForReview, reviewStatus);
	}
	
	@Override
	public List<StatusTracking> findByAgencyAndSentForReviewAndReviewStatusAndReportingPeriod(Agency agency, boolean sentForReview, Integer reviewStatus, ReportingPeriod reportingPeriod){
		return statusTrackingDAO.findByAgencyAndSentForReviewAndReviewStatusAndReportingPeriod(agency, sentForReview, reviewStatus, reportingPeriod);
	}

	@Override
	public List<StatusTracking> findByReportingPeriodAndReviewerAndUserAndReviewStatus(
			ReportingPeriod reportingPeriod, User reviewedBy, User user) {
		return statusTrackingDAO.findByReportingPeriodAndReviewerAndUserAndReviewStatus(reportingPeriod, reviewedBy, user);
	}
	
	@Override
	public List<StatusTracking> findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(ReportingPeriod reportingPeriod, User reviewedBy, Agency agency) {
		return statusTrackingDAO.findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(reportingPeriod, reviewedBy, agency);
	}

	@Override
	public List<StatusTracking> findReworkDataByReportingPeriodAndUserAndReadyForReview(
			ReportingPeriod reportingPeriod, User user) {
		return statusTrackingDAO.findReworkDataByReportingPeriodAndUserAndReadyForReview(reportingPeriod, user);
	}
	
	@Override
	public List<StatusTracking> findReworkDataByReportingPeriodAndAgencyAndReadyForReview(ReportingPeriod reportingPeriod, Agency agency) {
		return statusTrackingDAO.findReworkDataByReportingPeriodAndAgencyAndReadyForReview(reportingPeriod, agency);
	}

	
	
	@Override
	public List<StatusTracking> findByReviewerAndReviewStatus(User reviewedBy) {
		return statusTrackingDAO.findByReviewerAndReviewStatus(reviewedBy);
	}

	@Override
	public List<StatusTracking> findByReportingPeriodAndUser(ReportingPeriod reportingPeriod, User user) {
		return statusTrackingDAO.findByReportingPeriodAndUser(reportingPeriod, user);
	}
	
	@Override
	public List<StatusTracking> findByReportingPeriodAndAgency(ReportingPeriod reportingPeriod, Agency agency) {
		return statusTrackingDAO.findByReportingPeriodAndAgency(reportingPeriod, agency);
	}
	

	@Override
	public List<StatusTracking> findReviewedDataByReportingPeriodAndReviewerAndModificationTime(
			ReportingPeriod reportingPeriod, User reviewedBy,Date modificationTime) {
		return statusTrackingDAO.findReviewedDataByReportingPeriodAndReviewerAndModificationTime(reportingPeriod, reviewedBy, modificationTime);
	}

	@Override
	public List<StatusTracking> findByReportingPeriodAndSentForReviewAndUserLevel(
			ReportingPeriod reportingPeriod, boolean sentForReview,
			Integer userLevel) {
		return statusTrackingDAO.findByReportingPeriodAndSentForReviewAndUserLevel(reportingPeriod, sentForReview, userLevel);
	}

	@Override
	public List<StatusTracking> findByReportingPeriodAndSentForReviewOrReviewStatusAndUserLevel(
			ReportingPeriod reportingPeriod, boolean sentForReview,
			Integer reworkStatus, Integer userLevel) {
		return statusTrackingDAO.findByReportingPeriodAndSentForReviewOrReviewStatusAndUserLevel(reportingPeriod, sentForReview, reworkStatus, userLevel);
	}

	@Override
	public List<StatusTracking> findStatusTrackingByAgencyIds(List<Integer> agency) {
		return statusTrackingDAO.findStatusTrackingByAgencyIds(agency);
	}

	@Override
	public List<StatusTracking> findStatusTrackingByAgencyIdsAndModificationTime(
			List<Integer> agency, String lastSyncedTime, String currentTime) {
		return statusTrackingDAO.findStatusTrackingByAgencyIdsAndModificationTime(agency, lastSyncedTime, currentTime);
	}
	
	@Override
	public List<StatusTracking> findStatusTrackingByUserAndModificationTime(
			User user, String lastSyncedTime, String currentTime) {
		return statusTrackingDAO.findStatusTrackingByUserAndModificationTime(user, lastSyncedTime, currentTime);
	}

	@Override
	public StatusTracking findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(
			Agency agency, SubActivity subActivity,
			ReportingPeriod reportingPeriod, Integer userLevel) {
		return statusTrackingDAO.findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(agency, subActivity, reportingPeriod, userLevel);
	}

	@Override
	public List<StatusTracking> findByAgencyAndReportingPeriodAndComplete(
			Agency agency, ReportingPeriod reportingPeriod, boolean complete) {
		return statusTrackingDAO.findByAgencyAndReportingPeriodAndComplete(agency, reportingPeriod, complete);
	}

	@Override
	public List<StatusTracking> findByAgencyAndSentForReviewAndReportingPeriod(
			Agency agency, boolean sentForReview,ReportingPeriod reportingPeriod) {
		return statusTrackingDAO.findByAgencyAndSentForReviewAndReportingPeriod(agency,sentForReview,reportingPeriod);
	}
	
}