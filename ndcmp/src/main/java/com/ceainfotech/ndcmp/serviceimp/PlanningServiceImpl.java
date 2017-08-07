package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.PlanningDAO;
import com.ceainfotech.ndcmp.model.Planning;
import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.service.PlanningServices;

@Transactional()
@Service("planningServices")
public class PlanningServiceImpl implements PlanningServices{

	@Autowired
	PlanningDAO planningDAO;
	
	@Override
	public List<Planning> getPlannings() {
		// TODO Auto-generated method stub
		return planningDAO.getPlannings();
	}

	@Override
	public Planning getById(int id) {
		// TODO Auto-generated method stub
		return planningDAO.getById(id);
	}

	@Override
	public List<SubActivity> getPlanning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Integer subActivityId,Integer reportingPeriodId) {
		// TODO Auto-generated method stub
		planningDAO.save(subActivityId,reportingPeriodId);
	}

	@Override
	public List<Planning> getByReportingPeriod(ReportingPeriod period) {
		// TODO Auto-generated method stub
		return planningDAO.getByreportingPeriod(period);
	}

	@Override
	public List<Planning> getByPlanning(SubActivity subActivity) {
		// TODO Auto-generated method stub
		return planningDAO.getByPlanning(subActivity);
	}

	@Override
	public SubActivity getSubActivityById(Integer subActId) {
		// TODO Auto-generated method stub
		return planningDAO.getSubActivityById(subActId);
	}

	@Override
	public List<Planning> getPlanningListByAgency(List<Integer> agencyIds) {
		return planningDAO.getPlanningLeadAndPartnerAgency(agencyIds);
	}

	@Override
	public Planning findBySubActivityAndReportingPeriod(
			SubActivity subActivity, ReportingPeriod reportingPeriod) {
		// TODO Auto-generated method stub
		return planningDAO.findBySubActivityAndReportingPeriod(subActivity, reportingPeriod);
	}

	@Override
	public void deletePlanning(Planning planning) {
		// TODO Auto-generated method stub
		planningDAO.deletePlanning(planning);
	}

}
