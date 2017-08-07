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

import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.repository.ReportingPeriodRepository;

/**
 * @author bosco
 * 
 */
@Repository
public class ReportingPeriodDAO {

	private final ReportingPeriodRepository reportingPeriodRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(StatesDAO.class);

	/**
	 * 
	 */
	@Autowired
	public ReportingPeriodDAO(final ReportingPeriodRepository reportingPeriodRepository) {
		this.reportingPeriodRepository = reportingPeriodRepository;
	}

	public List<ReportingPeriod> getReportingPeriod() {
		LOGGER.debug("Find All the ReportingPeriods .. {}");
		return reportingPeriodRepository.findAllByOrderByYearAsc();
	}

	public void save(ReportingPeriod reportingPeriod) {
		LOGGER.debug("Add new ReportingPeriod .. {}", reportingPeriod);
		reportingPeriodRepository.save(reportingPeriod);
	}
	
	public List<ReportingPeriod> findByReportingPeriodByUser(User user) {
		return reportingPeriodRepository.findByReportingPeriodByUser(user);
	}
	
	public ReportingPeriod getById(Integer reportingPeriodId) {
		LOGGER.debug("Find ReportingPeriod by id .. {}", reportingPeriodId);
		return reportingPeriodRepository.getById(reportingPeriodId);
	}

	public void update(ReportingPeriod reportingPeriod) {
		LOGGER.debug("Update existing ReportingPeriod .. {}", reportingPeriod);
		reportingPeriodRepository.saveAndFlush(reportingPeriod);
	}

	public void deleteReportingPeriod(int reportingperiodId) {
		LOGGER.debug("Delete ReportingPeriod by id .. {}", reportingperiodId);
		reportingPeriodRepository.delete(reportingperiodId);
	}

	public List<String> getYears() {
		LOGGER.debug("Find the Years By Opened Reporting period.. {}");
		return reportingPeriodRepository.findYears();
	}
	
	public List<String> findAllYears() {
		LOGGER.debug("Find All the Years .. {}");
		return reportingPeriodRepository.findAllYears();
	}
	
	@Transactional(readOnly = true)
	public List<ReportingPeriod> getByYear(String year) {
		LOGGER.debug("Find ReportingPeriod by year .. {}");
		return reportingPeriodRepository.findByYear(year);
	}
	
	@Transactional(readOnly = true)
	public List<ReportingPeriod> getAll() {
		LOGGER.debug("Find ReportingPeriod .. {}");
		return reportingPeriodRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<ReportingPeriod> getAllStatusOpenAndClosed() {
		LOGGER.debug("Find ReportingPeriod .. {}");
		return reportingPeriodRepository.getAllStatusOpenAndClosed();
	}
	
	@Transactional(readOnly = true)
	public ReportingPeriod getByYearAndName(String year, String quater) {
		return reportingPeriodRepository.findByYearAndName(year,quater);
	}
	
	/**
	 * @author pushpa
	 * @param lastSyncedDate
	 * @param currentDate
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ReportingPeriod> getReportingPeriodListByDate(String lastSyncedTime, String currentTime) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reportingPeriodRepository.getReportingPeriodListByDate(lastSyncedDate,currentDate);
	}
	

	/**
	 * @author pushpa
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ReportingPeriod> getReportingPeriodListByStatusOpen(){
		return reportingPeriodRepository.getReportingPeriodListByStatusOpen();
	}
	
	@Transactional(readOnly = true)
	public List<ReportingPeriod> getReportingPeriodListByStatusOpenAndClosed(){
		return reportingPeriodRepository.getReportingPeriodListByStatusOpenAndClosed();
	}
	
	/**
	 * @author PremKumar
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ReportingPeriod> getAllReportingPeriodList(){
		return reportingPeriodRepository.getAllReportingPeriodList();
	}
	
	
	public List<ReportingPeriod> getPreviousReportingPeriodByCurrentReportingPeriod(Date startDate){
		return reportingPeriodRepository.getPreviousReportingPeriodByCurrentReportingPeriod(startDate);
	}
	
	public List<ReportingPeriod> getNextReportingPeriodByCurrentReportingPeriod(Date startDate){
		return reportingPeriodRepository.getNextReportingPeriodByCurrentReportingPeriod(startDate);
	}
	
	/**
	 * To get list based on the status
	 * @param reportingStatus
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ReportingPeriod> findByReportingStatus(String reportingStatus) {
		if(reportingStatus != null){
			return reportingPeriodRepository.findByReportingStatus(reportingStatus);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public ReportingPeriod findByIdAndReportingStatus(Integer id, String reportingStatus) {
		if(id != null && reportingStatus != null){
			return reportingPeriodRepository.findByIdAndReportingStatus(id, reportingStatus);
		}
		return null;
	}

	public List<ReportingPeriod> findAllReportingPeriodByYear(String year) {
		return reportingPeriodRepository.findAllReportingPeriodByYear(year);
	}
	
	public List<ReportingPeriod> findAllReportingPeriodByYears(List<String> year) {
		return reportingPeriodRepository.findAllReportingPeriodByYears(year);
	}
	
	@Transactional(readOnly = true)
	public ReportingPeriod findByIdAndNameAndYear(Integer id, String year,	String name) {
		if(id != null && year != null && name!= null){
			return reportingPeriodRepository.findByIdAndNameAndYear(id,year,name);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<ReportingPeriod> getReportingPeriodListByStartAndEndDate(String startdate, String enddate) {
		if(startdate != null && enddate != null){
			return reportingPeriodRepository.getReportingPeriodListByStartAndEndDate(startdate, enddate);
		}
		return null;
	}

	public List<ReportingPeriod> findAllByYear(String year) {
		// TODO Auto-generated method stub
		return reportingPeriodRepository.findAllByYear(year);
	}
}
