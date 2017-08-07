/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.Date;
import java.util.List;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author bosco
 *
 */
public interface StatusTrackingService {
	
	public StatusTracking getById(Integer id);

	public StatusTracking add(StatusTracking statusTracking);
	
	public List<StatusTracking> getByUser(User user);
	
	public List<StatusTracking> findByAgency(Agency agency);
	
	public List<StatusTracking> findByUser(User user);
	
	public StatusTracking getBySubActivity(SubActivity subActivity);
	
	public List<StatusTracking> findBysubActivityAndReportingPeriodAndUserLevel(SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel); 
	
	public List<StatusTracking> findByReportingPeriodAndSentForReviewAndUserLevel(ReportingPeriod reportingPeriod, boolean sentForReview, Integer userLevel);

	public List<StatusTracking> findByReportingPeriodAndSentForReviewOrReviewStatusAndUserLevel(ReportingPeriod reportingPeriod, boolean sentForReview, Integer reworkStatus, Integer userLevel);
	
	public List<StatusTracking> findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel, boolean  sentForReview);
	
	public List<StatusTracking> getByStatus(Statuss statuss);

	
	public StatusTracking getBySubActivityAndUserAndStatus(SubActivity subActivity,User user,Statuss statuss);
	
	public StatusTracking getBySubActivityAndUserAndComplete(SubActivity subActivity,User user,boolean complete);
	
	/**
	 * @author pushpa
	 * @param statusTracking
	 */
	public void addAll(List<StatusTracking> statusTrackingList);
	
	/**
	 * @author pushpa
	 * @param subActivity
	 * @return
	 */
	public List<StatusTracking> findBySubActivity(SubActivity subActivity);

	/**
	 * @param subActivity
	 * @param statuss
	 * @return
	 */
	public StatusTracking getBySubActivityAndStatus(SubActivity subActivity,Statuss statuss);
	
	/**
	 * @param subActivity
	 * @param reportingPeriod
	 * @return
	 */
	public List<StatusTracking> findBySubActivityAgencyAndReportingPeriod(SubActivity subActivity, ReportingPeriod reportingPeriod);
	
	/**
	 * @param subActivity
	 * @param reportingPeriod
	 * @param reviewStatus
	 * @return
	 */
	public List<StatusTracking> findBySubActivityAgencyAndReportingPeriodAndReviewedStatus(SubActivity subActivity, ReportingPeriod reportingPeriod, Integer reviewStatus);
	
	/**
	 * @author pushpa
	 * @param user
	 * @param subActivity
	 * @param reportingPeriod
	 * @return
	 */
	public StatusTracking findByUserAndSubActivityAndReportingPeriodAndUserLevel(User user, SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel);
	
	public List<StatusTracking> findByReportingPeriodAndUserLevel(ReportingPeriod reportingPeriod, Integer userLevel);
	
	
	public StatusTracking findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(Agency agency, SubActivity subActivity, ReportingPeriod reportingPeriod, Integer userLevel);
	
	/*public List<ReportingPeriod> getReportingPeriodByUser(User user);*/
	
	/**
	 * @author pushpa
	 * @param user
	 * @param reportingPeriod
	 * @return
	 */
	public List<StatusTracking> findByUserAndReportingPeriod(User user, ReportingPeriod reportingPeriod);
	
	public List<StatusTracking> findByUserAndReportingPeriodAndComplete(User user, ReportingPeriod reportingPeriod, boolean complete);
	
	public List<StatusTracking> findByAgencyAndReportingPeriodAndComplete(Agency agency, ReportingPeriod reportingPeriod, boolean complete);
	
	public List<StatusTracking> findByReportingPeriodAndUser(ReportingPeriod reportingPeriod, User user);

	public List<StatusTracking> findByReportingPeriodAndAgency(ReportingPeriod reportingPeriod, Agency agency);
	
	public List<StatusTracking> findByReportingPeriodAndReviewerAndReviewStatus(ReportingPeriod reportingPeriod, User reviewedBy);
	
	public List<StatusTracking> findByReviewerAndReviewStatus(User reviewedBy);
	
	public List<StatusTracking> findByReportingPeriodAndReviewerAndUserAndReviewStatus(ReportingPeriod reportingPeriod, User reviewedBy, User user);
	
	public List<StatusTracking> findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(ReportingPeriod reportingPeriod, User reviewedBy, Agency agency);
	
	public List<StatusTracking> findReworkDataByReportingPeriodAndUserAndReviewStatus(ReportingPeriod reportingPeriod, User user);
	
	public List<StatusTracking> findReworkDataByReportingPeriodAndUserAndReadyForReview(ReportingPeriod reportingPeriod, User user);
	
	public List<StatusTracking> findReworkDataByReportingPeriodAndAgencyAndReadyForReview(ReportingPeriod reportingPeriod, Agency agency);
	
	public List<StatusTracking> findReviewedDataByReportingPeriodAndReviewerAndModificationTime(ReportingPeriod reportingPeriod, User reviewedBy, Date modificationTime);
	
	public StatusTracking getBySubActivityAndUser(SubActivity subActivity2,User user);
	
	public List<StatusTracking> findByUserAndReportingPeriodAndReworkRequired(User user, ReportingPeriod reportingPeriod, boolean reworkRequired);
	
	public List<StatusTracking> findByAgencyAndReportingPeriodAndReworkRequired(Agency agency, ReportingPeriod reportingPeriod, boolean reworkRequired);
	
	public List<StatusTracking> findByUserAndReportingPeriodAndReworkRequiredAndComplete(User user, ReportingPeriod reportingPeriod, boolean reworkRequired,boolean complete);
	
	public List<StatusTracking> findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(Agency agency, ReportingPeriod reportingPeriod, boolean reworkRequired,boolean complete);

	public List<StatusTracking> findByUserAndSentForReviewAndReviewStatus(User user, boolean sentForReview, Integer reviewStatus);

	public List<StatusTracking> findByAgencyAndSentForReviewAndReviewStatus(Agency agency, boolean sentForReview, Integer reviewStatus);
	
	public List<StatusTracking> findByAgencyAndSentForReviewAndReportingPeriod(Agency agency, boolean sentForReview, ReportingPeriod reportingPeriod);
	
	public List<StatusTracking> findByAgencyAndSentForReviewAndReviewStatusAndReportingPeriod(Agency agency, boolean sentForReview, Integer reviewStatus, ReportingPeriod reportingPeriod);
	
	public List<StatusTracking> findReviewDataByLeadAgency(Integer leadAgency);
	
	public List<StatusTracking> findReviewDataForApprover();
	
	public List<StatusTracking> findStatusTrackingByAgencyIds(List<Integer> agency);
	
	public List<StatusTracking> findStatusTrackingByAgencyIdsAndModificationTime(List<Integer> agency, String lastSyncedTime, String currentTime);
	
	public List<StatusTracking> findStatusTrackingByUserAndModificationTime(User user, String lastSyncedTime, String currentTime);
}
