/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.repository.PlanningRepository;
import com.ceainfotech.ndcmp.repository.ReportingPeriodRepository;
import com.ceainfotech.ndcmp.repository.SubActivityRepository;

/**
 * @author bosco
 * 
 */
@Repository
public class PlanningDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StatesDAO.class);

	@Autowired
	PlanningRepository planningRepository;

	@Autowired
	SubActivityRepository subActivityRepository;

	@Autowired
	ReportingPeriodRepository reportingPeriodRepository;

	@Autowired
	public PlanningDAO(final PlanningRepository planningRepository,
			ReportingPeriodRepository reportingPeriodRepository,
			final SubActivityRepository subActivityRepository) {
		this.planningRepository = planningRepository;
		this.subActivityRepository = subActivityRepository;
		this.subActivityRepository = subActivityRepository;
	}

	@Transactional(readOnly = true)
	public List<Planning> getPlannings() {
		LOGGER.debug("Find All the Plans .. {}");
		return planningRepository.findByStatusOfProgress(true);
	}

	public Planning getById(int id) {
		LOGGER.debug("Find Plan By Id .. {}");
		return planningRepository.getById(id);
	}

	public void save(Integer subActivityId, Integer reportingPeriodId) {
		// TODO Auto-generated method stub
		SubActivity subActivity = subActivityRepository.getOne(subActivityId);
		ReportingPeriod reportingPeriod = reportingPeriodRepository
				.getOne(reportingPeriodId);

		Planning planning = planningRepository.findBySubActivityAndReportingPeriod(subActivity,
						reportingPeriod);
		if (planning == null) {
			Planning planning2=new Planning();
			planning2.setReportingPeriod(reportingPeriod);
			planning2.setSubActivity(subActivity);
			planning2.setStatusOfProgress(true);
			planningRepository.save(planning2);
		}/*else{
			planningRepository.delete(planning);
		}*/
		LOGGER.debug("Save Plan .. {}");

		
	}

	public List<Planning> getByreportingPeriod(ReportingPeriod period) {
		// TODO Auto-generated method stub
		return planningRepository.getByReportingPeriod(period);
	}

	public List<Planning> getByPlanning(SubActivity subActivity){
		return planningRepository.findBySubActivity(subActivity);
	}
	
	public SubActivity getSubActivityById(Integer subActId){
		SubActivity subActivity=subActivityRepository.findById(subActId);
		return subActivity;
	}
	
	/**
	 * @author pushpa
	 * @param agencyIds
	 * @return
	 */
	public List<Planning> getPlanningLeadAndPartnerAgency(List<Integer> agencyIds){
		return planningRepository.getPlanningLeadAndPartnerAgency(agencyIds, agencyIds);
	}
	
	public Planning findBySubActivityAndReportingPeriod(SubActivity subActivity,ReportingPeriod reportingPeriod){
		return planningRepository.findBySubActivityAndReportingPeriod(subActivity, reportingPeriod);
	}
	
	public void deletePlanning(Planning planning){
		planningRepository.delete(planning.getId());
	}
	
}
