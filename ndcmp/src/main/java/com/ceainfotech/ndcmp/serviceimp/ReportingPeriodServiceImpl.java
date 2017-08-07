/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.ReportingPeriodDAO;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.ReportingPeriodService;

/**
 * @author bosco
 *
 */

@Service("reportingPeriodService")
@Transactional
public class ReportingPeriodServiceImpl implements ReportingPeriodService{

	@Autowired
	ReportingPeriodDAO reportingPeriodDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportingPeriodServiceImpl.class);
	
	@Override
	public List<ReportingPeriod> getReportingPeriod() {
		return reportingPeriodDAO.getReportingPeriod();
	}

	@Override
	public ReportingPeriod getById(Integer reportingPeriodId) {
		return reportingPeriodDAO.getById(reportingPeriodId);
	}

	@Override
	public void save(ReportingPeriod reportingPeriod) {
		reportingPeriodDAO.save(reportingPeriod);
	}

	@Override
	public void update(ReportingPeriod reportingPeriod) {
		reportingPeriodDAO.update(reportingPeriod);
	}

	@Override
	public void deleteReportingPeriod(int reportingperiodId) {
		reportingPeriodDAO.deleteReportingPeriod(reportingperiodId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ceainfotech.ndcmp.service.ReportingPeriodService#getYears()
	 */
	@Override
	public List<String> getYears() {
		return reportingPeriodDAO.getYears();
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.ReportingPeriodService#getByYear(java.lang.String)
	 */
	@Override
	public List<ReportingPeriod> getByYear(String year) {
		return reportingPeriodDAO.getByYear(year);
	}
	

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.ReportingPeriodService#getByYear(java.lang.String)
	 */
	@Override
	public List<ReportingPeriod> getAll() {
		return reportingPeriodDAO.getAll();
	}
	
	@Override
	public List<ReportingPeriod> getAllStatusOpenAndClosed() {
		return reportingPeriodDAO.getAllStatusOpenAndClosed();
	}

	@Override
	public ReportingPeriod getByYearAndName(String year, String quater) {
		return reportingPeriodDAO.getByYearAndName(year,quater);
	}

	@Override
	public List<ReportingPeriod> getReportingPeriodListByDate(String lastSyncedDate, String currentDate) {
		return reportingPeriodDAO.getReportingPeriodListByDate(lastSyncedDate,currentDate);
	}

	@Override
	public List<ReportingPeriod> getReportingPeriodListByStatusOpen() {
		return reportingPeriodDAO.getReportingPeriodListByStatusOpen();
	}
	
	@Override
	public List<ReportingPeriod> getReportingPeriodListByStatusOpenAndClosed() {
		return reportingPeriodDAO.getReportingPeriodListByStatusOpenAndClosed();
	}

	@Override
	public List<ReportingPeriod> getAllReportingPeriodList() {
		return reportingPeriodDAO.getAllReportingPeriodList();
	}
	
	@Override
	public List<String> findAllYears() {
		return reportingPeriodDAO.findAllYears();
	}

	@Override
	public List<ReportingPeriod> findByReportingPeriodByUser(User user) {
		return reportingPeriodDAO.findByReportingPeriodByUser(user);
	}
	
	/**
	 * To get list based on the status
	 * @param reportingStatus
	 * @return
	 */
	@Override
	public List<ReportingPeriod> findByReportingStatus(String reportingStatus) {
		if(reportingStatus != null){
			return reportingPeriodDAO.findByReportingStatus(reportingStatus);
		}
		return null;
	}
	
	@Override
	public ReportingPeriod findByIdAndReportingStatus(Integer id, String reportingStatus) {
		if(id != null && reportingStatus != null){
			return reportingPeriodDAO.findByIdAndReportingStatus(id, reportingStatus);
		}
		return null;
	}

	@Override
	public List<ReportingPeriod> getPreviousReportingPeriodByCurrentReportingPeriod(Date startDate) {
		return reportingPeriodDAO.getPreviousReportingPeriodByCurrentReportingPeriod(startDate);
	}
	
	@Override
	public List<ReportingPeriod> getNextReportingPeriodByCurrentReportingPeriod(Date startDate) {
		return reportingPeriodDAO.getNextReportingPeriodByCurrentReportingPeriod(startDate);
	}

	@Override
	public List<ReportingPeriod> findAllReportingPeriodByYear(String year) {
		return reportingPeriodDAO.findAllReportingPeriodByYear(year);
	}
	
	@Override
	public List<ReportingPeriod> findAllReportingPeriodByYears(List<String> year) {
		return reportingPeriodDAO.findAllReportingPeriodByYears(year);
	}
	
	@Override
	public ReportingPeriod findByIdAndNameAndYear(Integer id,String name,String year) {
		if(id != null && year != null && name!= null){
			return reportingPeriodDAO.findByIdAndNameAndYear(id, name, year);
		}
		return null;
	}

	@Override
	public List<ReportingPeriod> getReportingPeriodListByStartAndEndDate(String startdate, String enddate) {
		if(startdate != null && enddate != null){
			return reportingPeriodDAO.getReportingPeriodListByStartAndEndDate(startdate, enddate);
		}
		return null;
	}

	@Override
	public List<ReportingPeriod> findAllByYear(String year) {
		// TODO Auto-generated method stub
		return reportingPeriodDAO.findAllByYear(year);
	}
}
