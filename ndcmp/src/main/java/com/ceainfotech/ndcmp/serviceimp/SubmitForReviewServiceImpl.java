/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.SubmitForReviewDAO;
import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.SubmitForReviewService;

/**
 * @author bosco
 *
 */
@Service("submitForReviewService")
public class SubmitForReviewServiceImpl implements SubmitForReviewService{

	@Autowired
	private SubmitForReviewDAO submitForReviewDAO;
	
	@Override
	public void add(SubmitForReview submitForReview) {
		if(submitForReview != null){
			submitForReviewDAO.add(submitForReview);
		}
	}

	@Override
	public List<SubmitForReview> findByUser(User user) {
		return submitForReviewDAO.findByUser(user);
	}

	@Override
	public List<SubmitForReview> findByAgency(Agency agency) {
		return submitForReviewDAO.findByAgency(agency);
	}
	
	@Override
	public SubmitForReview findByUserAndReportingPeriod(User user, ReportingPeriod reportingPeriod) {
		return submitForReviewDAO.findByUserAndReportingPeriod(user, reportingPeriod);
	}
	
	@Override
	public List<SubmitForReview> findByAgencyAndReportingPeriod(Agency agency, ReportingPeriod reportingPeriod) {
		return submitForReviewDAO.findByAgencyAndReportingPeriod(agency, reportingPeriod);
	}

	@Override
	public List<SubmitForReview> findByAgency(Set<Agency> agency) {
		return submitForReviewDAO.findByAgency(agency);
	}

	@Override
	public List<SubmitForReview> findByAgencyAndModificationTime(Set<Agency> agency,String lastSyncedTime, String currentTime) {
		return submitForReviewDAO.findByAgencyAndModificationTime(agency, lastSyncedTime, currentTime);
	}
	
	@Override
	public List<SubmitForReview> findByUserAndModificationTime(User user,String lastSyncedTime, String currentTime) {
		return submitForReviewDAO.findByUserAndModificationTime(user, lastSyncedTime, currentTime);
	}

	@Override
	public List<SubmitForReview> findByReportingPeriodAndUserLevel(ReportingPeriod reportingPeriod, Integer userLevel) {
		return submitForReviewDAO.findByReportingPeriodAndUserLevel(reportingPeriod, userLevel);
	}

	@Override
	public SubmitForReview findByUserAndReportingPeriodAndUserLevel(User user,
			ReportingPeriod reportingPeriod, Integer userLevel) {
		return submitForReviewDAO.findByUserAndReportingPeriodAndUserLevel(user, reportingPeriod, userLevel);
	}
	
	@Override 
	public SubmitForReview findByAgencyAndReportingPeriodAndUserLevel(Agency agency, ReportingPeriod reportingPeriod, Integer userLevel) {
		return submitForReviewDAO.findByAgencyAndReportingPeriodAndUserLevel(agency, reportingPeriod, userLevel);
	}

	@Override
	public List<SubmitForReview> findByUserLevelAndAgencyAndLeadReworkStatus(Set<Agency> agency) {
		return submitForReviewDAO.findByUserLevelAndAgencyAndLeadReworkStatus(agency);
	}

	@Override
	public List<SubmitForReview> findByUserLevelAndAgencyAndLeadReworkStatusAndLastModificationTime(
			Set<Agency> agency, String lastSyncedTime, String currentTime) {
		return submitForReviewDAO.findByUserLevelAndAgencyAndLeadReworkStatusAndLastModificationTime(agency, lastSyncedTime, currentTime);
	}

	@Override
	public List<SubmitForReview> findByUserAndLeadReworkStatus(User user, boolean leadReworkStatus) {
		return submitForReviewDAO.findByUserAndLeadReworkStatus(user, leadReworkStatus);
	}
	
	@Override
	public List<SubmitForReview> findByAgencyAndLeadReworkStatus(Agency agency, boolean leadReworkStatus) {
		return submitForReviewDAO.findByAgencyAndLeadReworkStatus(agency, leadReworkStatus);
	}
}