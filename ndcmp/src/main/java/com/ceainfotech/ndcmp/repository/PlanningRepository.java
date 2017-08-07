/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubActivity;

/**
 * @author bosco
 */
public interface PlanningRepository extends JpaRepository<Planning, Integer>{

	public Planning getById(int id);

	@Query
	public Planning findBySubActivityAndReportingPeriod(SubActivity subActivity,ReportingPeriod reportingPeriod);

	public List<Planning> getByReportingPeriod(ReportingPeriod period);
	
	public List<Planning> findByStatusOfProgress(Boolean status);
	
	public List<Planning> findBySubActivity(SubActivity subActivity);
	
	
	
	/**
	 * @author pushpa
	 * Get Planning List based on the sub activity agencies
	 * @param leadAgency
	 * @param partnerAgency
	 * @return
	 */
	@Query(value = "SELECT p FROM Planning p LEFT JOIN p.subActivity.partnerAgency pa WHERE p.subActivity.leadAgency.id IN (:leadAgency) OR pa.id IN (:partnerAgency)")
	public List<Planning> getPlanningLeadAndPartnerAgency(@Param("leadAgency")List<Integer> leadAgency, @Param("partnerAgency")List<Integer> partnerAgency);

}
