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
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.StatusTracking;
import com.ceainfotech.ndcmp.model.Statuss;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author bosco
 * 
 */
public interface StatusTrackingRepository extends
		JpaRepository<StatusTracking, Integer> {

	public List<StatusTracking> findByUser(User user);
	
	public List<StatusTracking> findByAgency(Agency agency);

	public StatusTracking findBysubActivity(SubActivity subActivity);

	public List<StatusTracking> findBysubActivityAndReportingPeriodAndUserLevel(
			SubActivity subActivity, ReportingPeriod reportingPeriod,
			Integer userLevel);
	
	public List<StatusTracking> findBysubActivityAndReportingPeriodAndUserLevelAndSentForReview(
			SubActivity subActivity, ReportingPeriod reportingPeriod,
			Integer userLevel, boolean  sentForReview);

	// Added By Jeeva
	public StatusTracking findBySubActivityAndUserAndStatuss(
			SubActivity subActivity, User user, Statuss statuss);

	public StatusTracking findBySubActivityAndUserAndComplete(
			SubActivity subActivity, User user, boolean complete);
	
	public List<StatusTracking> findByReportingPeriodAndUser(ReportingPeriod reportingPeriod, User user);

	public List<StatusTracking> findByReportingPeriodAndAgency(ReportingPeriod reportingPeriod, Agency agency);
	
	@Query(value = "SELECT s FROM StatusTracking s WHERE s.user = :user AND s.complete = true")
	public List<StatusTracking> findByUserAndCompleteStatus(
			@Param("user") User user);

	public List<StatusTracking> findByStatuss(Statuss statuss);

	public List<StatusTracking> findBySubActivity(SubActivity subActivity);

	public StatusTracking getBySubActivityAndUser(SubActivity subActivity2,
			User user);

	
	public List<StatusTracking> findByReportingPeriodAndSentForReviewAndUserLevel(ReportingPeriod reportingPeriod, boolean sentForReview, Integer userLevel);
	
	@Query(value = "SELECT s FROM StatusTracking s WHERE s.reportingPeriod = :reportingPeriod AND s.userLevel = :userLevel AND (s.sentForReview = :sentForReview OR s.reviewStatus = :reviewStatus)")
	public List<StatusTracking> findByReportingPeriodAndSentForReviewAndReviewStatusAndUserLevel(@Param("reportingPeriod") ReportingPeriod reportingPeriod, @Param("sentForReview") boolean sentForReview, @Param("reviewStatus") Integer reviewStatus, @Param("userLevel") Integer userLevel);
	
	@Query(value = "SELECT DISTINCT s FROM StatusTracking s INNER JOIN s.subActivity sa INNER JOIN sa.partnerAgency pa WHERE pa.id IN (:agency)")
	public List<StatusTracking> findStatusTrackingByAgencyIds(@Param("agency")List<Integer> agency);
	
	@Query(value = "SELECT DISTINCT s FROM StatusTracking s INNER JOIN s.subActivity sa INNER JOIN sa.partnerAgency pa WHERE pa.id IN (:agency) AND s.modificationTime BETWEEN :lastSyncedTime AND :currentTime")
	public List<StatusTracking> findStatusTrackingByAgencyIdsAndModificationTime(@Param("agency")List<Integer> agency, @Param("lastSyncedTime")Date lastSyncedTime, @Param("currentTime")Date currentTime);
	
	@Query(value = "SELECT DISTINCT s FROM StatusTracking s WHERE s.user = :user AND s.modificationTime BETWEEN :lastSyncedTime AND :currentTime")
	public List<StatusTracking> findStatusTrackingByUserAndModificationTime(@Param("user")User user, @Param("lastSyncedTime")Date lastSyncedTime, @Param("currentTime")Date currentTime);
	
	
	/**
	 * @param subActivity
	 * @param statuss
	 * @return
	 */
	public StatusTracking findBySubActivityAndStatuss(SubActivity subActivity,
			Statuss statuss);

	/**
	 * @param user
	 * @param reportingPeriod
	 * @return
	 */
	public List<StatusTracking> findByUserAndReportingPeriod(User user,
			ReportingPeriod reportingPeriod);

	/**
	 * @param user
	 * @param reportingPeriod
	 * @param complete
	 * @return
	 */
	public List<StatusTracking> findByUserAndReportingPeriodAndComplete(
			User user, ReportingPeriod reportingPeriod, boolean complete);
	
	/**
	 * @param agency
	 * @param reportingPeriod
	 * @param complete
	 * @return
	 */
	public List<StatusTracking> findByAgencyAndReportingPeriodAndComplete(
			Agency agency, ReportingPeriod reportingPeriod, boolean complete);

	/**
	 * @param reportingPeriod
	 * @param userLevel
	 * @return
	 */
	public List<StatusTracking> findByReportingPeriodAndUserLevel(
			ReportingPeriod reportingPeriod, Integer userLevel);

	/**
	 * @param user
	 * @param subActivity
	 * @param reportingPeriod
	 * @param userLevel
	 * @return
	 */
	public StatusTracking findByUserAndSubActivityAndReportingPeriodAndUserLevel(
			User user, SubActivity subActivity,
			ReportingPeriod reportingPeriod, Integer userLevel);
	
	/**
	 * @param agency
	 * @param subActivity
	 * @param reportingPeriod
	 * @param userLevel
	 * @return
	 */
	public StatusTracking findByAgencyAndSubActivityAndReportingPeriodAndUserLevel(
			Agency agency, SubActivity subActivity,
			ReportingPeriod reportingPeriod, Integer userLevel);

	/**
	 * @param user
	 * @param reportingPeriod
	 * @param reworkRequired
	 * @return
	 */
	public List<StatusTracking> findByUserAndReportingPeriodAndReworkRequired(
			User user, ReportingPeriod reportingPeriod, boolean reworkRequired);
	
	/**
	 * @param agency
	 * @param reportingPeriod
	 * @param reworkRequired
	 * @return
	 */
	public List<StatusTracking> findByAgencyAndReportingPeriodAndReworkRequired(
			Agency agency, ReportingPeriod reportingPeriod, boolean reworkRequired);

	/**
	 * @param user
	 * @param reportingPeriod
	 * @param reworkRequired
	 * @param complete
	 * @return
	 */
	public List<StatusTracking> findByUserAndReportingPeriodAndReworkRequiredAndComplete(
			User user, ReportingPeriod reportingPeriod, boolean reworkRequired,
			boolean complete);
	
	/**
	 * @param agency
	 * @param reportingPeriod
	 * @param reworkRequired
	 * @param complete
	 * @return
	 */
	public List<StatusTracking> findByAgencyAndReportingPeriodAndReworkRequiredAndComplete(
			Agency agency, ReportingPeriod reportingPeriod, boolean reworkRequired,
			boolean complete);
	
	public List<StatusTracking> findByUserAndSentForReviewAndReviewStatus(User user, boolean sentForReview, Integer reviewStatus);

	public List<StatusTracking> findByAgencyAndSentForReviewAndReviewStatus(Agency agency, boolean sentForReview, Integer reviewStatus);
	
	public List<StatusTracking> findByAgencyAndSentForReviewAndReportingPeriod(Agency agency, boolean sentForReview, ReportingPeriod reportingPeriod);
	
	public List<StatusTracking> findByAgencyAndSentForReviewAndReviewStatusAndReportingPeriod(Agency agency, boolean sentForReview, Integer reviewStatus, ReportingPeriod reportingPeriod);
	
	/*@Query(value = "SELECT DISTINCT st.reportingPeriod FROM StatusTracking st WHERE st.user = :user")
	public List<ReportingPeriod> findByReportingPeriodByUser(@Param("user") User user);*/

	/**
	 * @param subActivity
	 * @param reportingPeriod
	 * @return
	 */
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.subActivity = :subActivity AND st.reportingPeriod = :reportingPeriod AND st.userLevel = 1")
	public List<StatusTracking> findBySubActivityAgencyAndReportingPeriod(
			@Param("subActivity") SubActivity subActivity,
			@Param("reportingPeriod") ReportingPeriod reportingPeriod);

	/**
	 * @param subActivity
	 * @param reportingPeriod
	 * @param reviewStatus
	 * @return
	 */
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.subActivity = :subActivity AND st.reportingPeriod = :reportingPeriod AND st.reviewStatus = :reviewStatus AND st.userLevel = 1")
	public List<StatusTracking> findBySubActivityAgencyAndReportingPeriodAndReviewedStatus(
			@Param("subActivity") SubActivity subActivity,
			@Param("reportingPeriod") ReportingPeriod reportingPeriod,
			@Param("reviewStatus") Integer reviewStatus);

	/**
	 * @param reportingPeriod
	 * @param reviewedBy
	 * @return
	 */
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.reportingPeriod = :reportingPeriod AND st.reviewedBy = :reviewedBy AND st.reviewStatus = -1 AND st.complete = 1 ORDER BY st.user")
	public List<StatusTracking> findByReportingPeriodAndReviewerAndReviewStatus(
			@Param("reportingPeriod") ReportingPeriod reportingPeriod,
			@Param("reviewedBy") User reviewedBy);
	
	/**
	 * @param reportingPeriod
	 * @param reviewedBy
	 * @param user
	 * @return
	 */
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.reviewedBy = :reviewedBy AND st.reviewStatus = -1 AND st.complete = 1 ORDER BY st.user")
	public List<StatusTracking> findByReviewerAndReviewStatus(@Param("reviewedBy") User reviewedBy);
	
	
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.user = :user AND st.reportingPeriod = :reportingPeriod AND st.reviewedBy = :reviewedBy AND st.reviewStatus = -1 AND st.complete = 1 ORDER BY st.user")
	public List<StatusTracking> findByReportingPeriodAndReviewerAndUserAndReviewStatus(
			@Param("reportingPeriod") ReportingPeriod reportingPeriod,
			@Param("reviewedBy") User reviewedBy, @Param("user") User user);

	
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.agency = :agency AND st.reportingPeriod = :reportingPeriod AND st.reviewedBy = :reviewedBy AND st.reviewStatus = -1 AND st.complete = 1 ORDER BY st.user")
	public List<StatusTracking> findByReportingPeriodAndReviewerAndAgencyAndReviewStatus(
			@Param("reportingPeriod") ReportingPeriod reportingPeriod,
			@Param("reviewedBy") User reviewedBy, @Param("agency") Agency agency);

	
	/**
	 * @param reportingPeriod
	 * @param user
	 * @return
	 */
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.reportingPeriod = :reportingPeriod AND st.user = :user AND st.reviewStatus = -1 AND st.reworkRequired = 1 AND st.complete = 0")
	public List<StatusTracking> findReworkDataByReportingPeriodAndUserAndReviewStatus(
			@Param("reportingPeriod") ReportingPeriod reportingPeriod,
			@Param("user") User user);
	
	/**
	 * 
	 * @param reportingPeriod
	 * @param user
	 * @return
	 */
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.reportingPeriod = :reportingPeriod AND st.user = :user AND st.complete = 1")
	public List<StatusTracking> findReworkDataByReportingPeriodAndUserAndReadyForReview(@Param("reportingPeriod") ReportingPeriod reportingPeriod,@Param("user") User user);
	
	/**
	 * 
	 * @param reportingPeriod
	 * @param agency
	 * @return
	 */
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.reportingPeriod = :reportingPeriod AND st.agency = :agency AND st.complete = 1")
	public List<StatusTracking> findReworkDataByReportingPeriodAndAgencyAndReadyForReview(@Param("reportingPeriod") ReportingPeriod reportingPeriod,@Param("agency") Agency agency);
	
	
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.reportingPeriod = :reportingPeriod AND st.reviewedBy = :reviewedBy AND st.complete = 1 AND st.modificationTime > :modificationTime AND st.reviewStatus != 0")
	public List<StatusTracking> findReviewedDataByReportingPeriodAndReviewerAndModificationTime(@Param("reportingPeriod") ReportingPeriod reportingPeriod,@Param("reviewedBy") User reviewedBy, @Param("modificationTime")Date modificationTime);
	
	/**
	 * @param userId
	 * @return
	 */
	@Query(value = "SELECT DISTINCT st FROM StatusTracking st LEFT JOIN st.subActivity AS sa WHERE sa.leadAgency.id = :userId AND st.sentForReview = 1 AND st.reviewStatus = 0 AND st.userLevel = 1") 
	public List<StatusTracking> findReviewDataForAgency(@Param("userId") Integer userId);
	
	/**
	 * @return
	 */
	@Query(value = "SELECT st FROM StatusTracking st WHERE st.sentForReview = 1 AND st.reviewStatus = 0 AND st.userLevel = 2") 
	public List<StatusTracking> findReviewDataForApprover();
	
}
