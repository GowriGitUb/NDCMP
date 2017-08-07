/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubmitForReview;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.repository.SubmitForReviewRepository;

/**
 * @author bosco
 *
 */
@Repository
public class SubmitForReviewDAO {

	private final SubmitForReviewRepository submitForReviewRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(SubmitForReviewDAO.class);

	@Autowired
	public SubmitForReviewDAO(final SubmitForReviewRepository submitForReviewRepository) {
		this.submitForReviewRepository = submitForReviewRepository;
	}
	
	public void add(SubmitForReview submitForReview) {
		LOGGER.debug("Add new user .. {}", submitForReview);
		submitForReviewRepository.save(submitForReview);
	}
	
	public List<SubmitForReview> findByUser(User user) {
		LOGGER.debug("Get StatusTracking Details by User .. {}", user);
		return submitForReviewRepository.findByUser(user);
	}
	
	public SubmitForReview findByUserAndReportingPeriod(User user, ReportingPeriod reportingPeriod){
		return submitForReviewRepository.findByUserAndReportingPeriod(user, reportingPeriod);
	}
	
	public List<SubmitForReview> findByAgencyAndReportingPeriod(Agency agency, ReportingPeriod reportingPeriod){
		return submitForReviewRepository.findByAgencyAndReportingPeriod(agency, reportingPeriod);
	}

	public List<SubmitForReview> findByAgency(Set<Agency> agency){
		return submitForReviewRepository.findByAgencies(agency);
	}
	
	public List<SubmitForReview> findByAgency(Agency agency){
		return submitForReviewRepository.findByAgency(agency);
	}
	
	public List<SubmitForReview> findByAgencyAndModificationTime(Set<Agency> agency, String lastSyncedTime, String currentTime){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return submitForReviewRepository.findByAgencyAndModificationTime(agency,lastSyncedDate, currentDate);
	}
	
	public List<SubmitForReview> findByUserAndModificationTime(User user,String lastSyncedTime, String currentTime) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return submitForReviewRepository.findByUserAndModificationTime(user,lastSyncedDate, currentDate);
	}
	
	public List<SubmitForReview> findByReportingPeriodAndUserLevel(ReportingPeriod reportingPeriod, Integer userLevel){
		return submitForReviewRepository.findByReportingPeriodAndUserLevel(reportingPeriod, userLevel);
	}
	
	public SubmitForReview findByUserAndReportingPeriodAndUserLevel(User user, ReportingPeriod reportingPeriod, Integer userLevel){
		return submitForReviewRepository.findByUserAndReportingPeriodAndUserLevel(user, reportingPeriod, userLevel);
	}

	public SubmitForReview findByAgencyAndReportingPeriodAndUserLevel(Agency agency, ReportingPeriod reportingPeriod, Integer userLevel){
		return submitForReviewRepository.findByAgencyAndReportingPeriodAndUserLevel(agency, reportingPeriod, userLevel);
	}
	
	public List<SubmitForReview> findByUserLevelAndAgencyAndLeadReworkStatus(Set<Agency> agency) {
		return submitForReviewRepository.findByUserLevelAndAgencyAndLeadReworkStatus(agency);
	}
	
	@Transactional(readOnly = true)
	public List<SubmitForReview> findByUserLevelAndAgencyAndLeadReworkStatusAndLastModificationTime(Set<Agency> agency, String lastSyncedTime, String currentTime){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return submitForReviewRepository.findByUserLevelAndAgencyAndLeadReworkStatusAndLastModificationTime(agency,lastSyncedDate, currentDate);
	}
	
	public List<SubmitForReview> findByUserAndLeadReworkStatus(User user, boolean leadReworkStatus){
		return submitForReviewRepository.findByUserAndLeadReworkStatus(user, leadReworkStatus);
	}
	
	public List<SubmitForReview> findByAgencyAndLeadReworkStatus(Agency agency, boolean leadReworkStatus) {
		return submitForReviewRepository.findByAgencyAndLeadReworkStatus(agency, leadReworkStatus);
	}
}
