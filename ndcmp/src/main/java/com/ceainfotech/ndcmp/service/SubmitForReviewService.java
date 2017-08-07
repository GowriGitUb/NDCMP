/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;
import java.util.Set;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author bosco
 *
 */
public interface SubmitForReviewService {

	public void add(SubmitForReview submitForReview);
	
	public List<SubmitForReview> findByUser(User user);
	
	public List<SubmitForReview> findByAgency(Agency agency);
	
	public List<SubmitForReview> findByAgency(Set<Agency> agency);
	
	public List<SubmitForReview> findByAgencyAndModificationTime(Set<Agency> agency,String lastSyncedTime, String currentTime);
	
	public List<SubmitForReview> findByUserAndModificationTime(User user,String lastSyncedTime, String currentTime);
	
	public List<SubmitForReview> findByUserLevelAndAgencyAndLeadReworkStatus(Set<Agency> agency);
	
	public List<SubmitForReview> findByUserLevelAndAgencyAndLeadReworkStatusAndLastModificationTime(Set<Agency> agency,String lastSyncedTime,String currentTime);
	
	public SubmitForReview findByUserAndReportingPeriod(User user, ReportingPeriod reportingPeriod);
	
	public List<SubmitForReview> findByAgencyAndReportingPeriod(Agency agency, ReportingPeriod reportingPeriod);
	
	public SubmitForReview findByUserAndReportingPeriodAndUserLevel(User user, ReportingPeriod reportingPeriod, Integer userLevel);
	
	public SubmitForReview findByAgencyAndReportingPeriodAndUserLevel(Agency agency, ReportingPeriod reportingPeriod, Integer userLevel);
	
	public List<SubmitForReview> findByReportingPeriodAndUserLevel(ReportingPeriod reportingPeriod, Integer userLevel);
	
	public List<SubmitForReview> findByUserAndLeadReworkStatus(User user, boolean leadReworkStatus);
	
	public List<SubmitForReview> findByAgencyAndLeadReworkStatus(Agency agency, boolean leadReworkStatus);
}
