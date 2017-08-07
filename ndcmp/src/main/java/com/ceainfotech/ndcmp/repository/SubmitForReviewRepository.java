/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author bosco
 *
 */
public interface SubmitForReviewRepository extends JpaRepository<SubmitForReview, Integer> {
	
	/*public SubmitForReview findByUser(User user);*/
	
	public SubmitForReview findByUserAndReportingPeriod(User user, ReportingPeriod reportingPeriod);
	
	public List<SubmitForReview> findByAgencyAndReportingPeriod(Agency agency, ReportingPeriod reportingPeriod);
	
	public List<SubmitForReview> findByUser(User user);
	
	public List<SubmitForReview> findByAgency(Agency agency);
	
	public SubmitForReview findByUserAndReportingPeriodAndUserLevel(User user, ReportingPeriod reportingPeriod, Integer userLevel);
	
	public SubmitForReview findByAgencyAndReportingPeriodAndUserLevel(Agency agency, ReportingPeriod reportingPeriod, Integer userLevel);
	
	/**
	 * @author pushpa
	 * @param agency
	 * @return
	 */
	@Query(value = "SELECT sfr FROM SubmitForReview sfr WHERE sfr.agency IN (:agency) ")
	public List<SubmitForReview> findByAgencies(@Param("agency") Set<Agency> agency);
	
	@Query(value = "SELECT sfr FROM SubmitForReview sfr WHERE sfr.agency IN (:agency) AND sfr.modificationTime BETWEEN :lastSyncedTime AND :currentTime")
	public List<SubmitForReview> findByAgencyAndModificationTime(@Param("agency") Set<Agency> agency,@Param("lastSyncedTime")Date lastSyncedTime, @Param("currentTime")Date currentTime);
	
	@Query(value = "SELECT sfr FROM SubmitForReview sfr WHERE sfr.user = :user AND sfr.modificationTime BETWEEN :lastSyncedTime AND :currentTime")
	public List<SubmitForReview> findByUserAndModificationTime(@Param("user") User user, @Param("lastSyncedTime")Date lastSyncedTime, @Param("currentTime")Date currentTime);

	
	@Query(value = "SELECT sfr FROM SubmitForReview sfr WHERE sfr.leadReworkStatus = 1 AND sfr.partnerReworkStatus = 0 AND sfr.agency IN (:agency)")
	public List<SubmitForReview> findByUserLevelAndAgencyAndLeadReworkStatus(@Param("agency") Set<Agency> agency);
	
	@Query(value = "SELECT sfr FROM SubmitForReview sfr WHERE sfr.leadReworkStatus = 1 AND sfr.partnerReworkStatus = 0 AND sfr.agency IN (:agency) AND sfr.modificationTime BETWEEN :lastSyncedTime AND :currentTime ")
	public List<SubmitForReview> findByUserLevelAndAgencyAndLeadReworkStatusAndLastModificationTime(@Param("agency") Set<Agency> agency,@Param("lastSyncedTime")Date lastSyncedTime, @Param("currentTime")Date currentTime);
	
	public List<SubmitForReview> findByReportingPeriodAndUserLevel(ReportingPeriod reportingPeriod, Integer userLevel);
	
	public List<SubmitForReview> findByUserAndLeadReworkStatus(User user, boolean leadReworkStatus);
	
	public List<SubmitForReview> findByAgencyAndLeadReworkStatus(Agency agency, boolean leadReworkStatus);
}
