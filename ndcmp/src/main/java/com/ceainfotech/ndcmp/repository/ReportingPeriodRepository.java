/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author bosco
 * 
 */
public interface ReportingPeriodRepository extends JpaRepository<ReportingPeriod, Integer> {

	@Query(value = "SELECT DISTINCT a FROM ReportingPeriod a WHERE a.year = :year AND a.reportingStatus = 'Open' AND a.status = 'ACTIVE'")
	public List<ReportingPeriod> findByYear(@Param("year")String year);
	
	/**
	 *@author PremKumar
	 * @return
	 */
	
	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE'")
	public List<ReportingPeriod> getAllStatusOpenAndClosed();

	/**
	 * @author Manigandan
	 */
	
	public List<ReportingPeriod> findAllByOrderByYearAsc();
	
	/**
	 * @author PremKumar
	 * @param year
	 * @return
	 */
	@Query(value = "SELECT DISTINCT a FROM ReportingPeriod a WHERE a.year = :year AND a.status = 'ACTIVE'")
	public List<ReportingPeriod> findAllByYear(@Param("year")String year);
	
	@Query(value = "SELECT DISTINCT a FROM ReportingPeriod a WHERE a.year = :year AND a.status = 'ACTIVE'")
	public List<ReportingPeriod> findAllReportingPeriodByYear(@Param("year")String year);
	
	@Query(value = "SELECT DISTINCT a FROM ReportingPeriod a WHERE a.year IN (:year) AND a.status = 'ACTIVE'")
	public List<ReportingPeriod> findAllReportingPeriodByYears(@Param("year")List<String> year);
	
	public ReportingPeriod getById(Integer reportingPeriodId);
	
	@Query(value = "SELECT DISTINCT a.year FROM ReportingPeriod a WHERE a.status = 'ACTIVE' AND a.reportingStatus = 'Open'")
	public List<String> findYears();
	
	@Query(value = "SELECT DISTINCT a.year FROM ReportingPeriod a WHERE a.status = 'ACTIVE'")
	public List<String> findAllYears();

	public ReportingPeriod findByYearAndName(String year, String quater);
	
	@Query(value = "SELECT DISTINCT st.reportingPeriod FROM StatusTracking st WHERE st.user = :user")
	public List<ReportingPeriod> findByReportingPeriodByUser(@Param("user") User user);
	
	/**
	 * @author pushpa
	 * @param lastSyncedDate
	 * @param currentDate
	 * @return
	 */
	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE' AND rp.reportingStatus = 'Open' AND rp.modificationTime BETWEEN :lastSyncedDate AND :currentDate")
	public List<ReportingPeriod> getReportingPeriodListByDate(@Param("lastSyncedDate")Date lastSyncedDate, @Param("currentDate")Date currentDate);
	
	/**
	 * @author pushpa
	 * @return
	 */
	
	
	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE' AND rp.reportingStatus = 'Open'")
	public List<ReportingPeriod> getReportingPeriodListByStatusOpen();

	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE' AND rp.reportingStatus IN ('Open','CLOSED')")
	public List<ReportingPeriod> getReportingPeriodListByStatusOpenAndClosed();
	
	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE'")
	public List<ReportingPeriod> getAllReportingPeriodList();
	
	
	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.startdate < :startdate AND rp.status = 'ACTIVE' AND rp.reportingStatus != 'Not-Started' ORDER BY rp.startdate DESC" )
	public List<ReportingPeriod> getPreviousReportingPeriodByCurrentReportingPeriod(@Param("startdate")Date startDate);
	
	/*
	 * purpose : to get list of reporting period based on the date
	 */
	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.startdate > :startdate AND rp.status = 'ACTIVE' ORDER BY rp.startdate DESC" )
	public List<ReportingPeriod> getNextReportingPeriodByCurrentReportingPeriod(@Param("startdate")Date startDate);
	
	/**
	 * Purpose : To get the list based on the reporting period status
	 */
	public List<ReportingPeriod> findByReportingStatus(String reportingStatus);
	
	/*
	 * Purpose : To get the bean based on the id and reporting status
	 */
	public ReportingPeriod findByIdAndReportingStatus(Integer id,String reportingStatus);
	
	public ReportingPeriod findByIdAndNameAndYear(Integer id,String name,String year);
	
	/*
	 * Purpose : To get the list of data based on the start date and end date
	 */
	/*@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE' AND (DATE(rp.startdate) BETWEEN :startdate AND :enddate) AND (DATE(rp.enddate) BETWEEN :startdate AND :enddate)")
	public List<ReportingPeriod> getReportingPeriodListByStartAndEndDate(@Param("startdate")Date startdate, @Param("enddate")Date enddate);
	*/
	
	/*
	 * purpose : To get all the details based on the start date and end date
	 */
	//@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE' AND (DATE(rp.startdate) BETWEEN STR_TO_DATE(:startdate, '%Y-%m-%d') AND STR_TO_DATE(:enddate, '%Y-%m-%d')) OR (DATE(rp.enddate) BETWEEN STR_TO_DATE(:startdate, '%Y-%m-%d') AND STR_TO_DATE(:enddate, '%Y-%m-%d'))")
	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE' AND (DATE(rp.startdate) <= STR_TO_DATE(:startdate, '%Y-%m-%d') AND DATE(rp.enddate) >= STR_TO_DATE(:startdate, '%Y-%m-%d')) OR (DATE(rp.startdate) <= STR_TO_DATE(:enddate, '%Y-%m-%d') AND DATE(rp.enddate) >= STR_TO_DATE(:enddate, '%Y-%m-%d'))")
	public List<ReportingPeriod> getReportingPeriodListByStartAndEndDate(@Param("startdate")String startdate, @Param("enddate")String enddate);
	
	//@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE' AND rp.id= :id AND (DATE(rp.startdate) BETWEEN STR_TO_DATE(:startdate, '%Y-%m-%d') AND STR_TO_DATE(:enddate, '%Y-%m-%d')) AND (DATE(rp.enddate) BETWEEN STR_TO_DATE(:startdate, '%Y-%m-%d') AND STR_TO_DATE(:enddate, '%Y-%m-%d'))")
	@Query(value = "SELECT rp FROM ReportingPeriod rp WHERE rp.status = 'ACTIVE' AND rp.id= :id AND (DATE(rp.startdate) <= STR_TO_DATE(:startdate, '%Y-%m-%d') AND DATE(rp.enddate) >= STR_TO_DATE(:startdate, '%Y-%m-%d')) OR (DATE(rp.startdate) <= STR_TO_DATE(:enddate, '%Y-%m-%d') AND DATE(rp.enddate) >= STR_TO_DATE(:enddate, '%Y-%m-%d'))")
	public List<ReportingPeriod> getReportingPeriodListByIdStartAndEndDate(@Param("id")Integer id,@Param("startdate")String startdate, @Param("enddate")String enddate);
}
