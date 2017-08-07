/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.Date;
import java.util.List;

import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author bosco
 * 
 */
public interface ReportingPeriodService {

	public List<ReportingPeriod> getReportingPeriod();

	public ReportingPeriod getById(Integer reportingPeriodId);

	public void save(ReportingPeriod reportingPeriod);

	public void update(ReportingPeriod reportingPeriod);

	public void deleteReportingPeriod(int reportingperiodId);
	
	public List<String> getYears();
	
	public List<String> findAllYears();
	
	public List<ReportingPeriod> getByYear(String year);
	
	public List<ReportingPeriod> getAll();
	
	public List<ReportingPeriod> getAllStatusOpenAndClosed();

	public ReportingPeriod getByYearAndName(String year, String quater);
	
	public List<ReportingPeriod> getReportingPeriodListByStatusOpen();
	
	public List<ReportingPeriod> getReportingPeriodListByStatusOpenAndClosed();
	/**
	 * @author pushpa
	 * Get reporting period list by last synced date and current date
	 * @param lastSyncedDate
	 * @param currentDate
	 * @return
	 */
	public List<ReportingPeriod> getReportingPeriodListByDate(String lastSyncedDate, String currentDate);
	
	/**
	 * @author pushpa
	 * Get reporting period list by status opened
	 * @return
	 */
	
	
	public List<ReportingPeriod> findByReportingPeriodByUser(User user);
	
	public List<ReportingPeriod> getPreviousReportingPeriodByCurrentReportingPeriod(Date startDate);
	
	public List<ReportingPeriod> getNextReportingPeriodByCurrentReportingPeriod(Date startDate);
	
	public List<ReportingPeriod> findByReportingStatus(String reportingStatus);
	
	public ReportingPeriod findByIdAndReportingStatus(Integer id,String reportingStatus);

	public List<ReportingPeriod> findAllReportingPeriodByYear(String year);
	
	public List<ReportingPeriod> findAllReportingPeriodByYears(List<String> year);
	
	public ReportingPeriod findByIdAndNameAndYear(Integer id,String name,String year);
	
	public List<ReportingPeriod> getReportingPeriodListByStartAndEndDate(String startdate,String enddate);

	List<ReportingPeriod> getAllReportingPeriodList();

	public List<ReportingPeriod> findAllByYear(String year);
	
}
