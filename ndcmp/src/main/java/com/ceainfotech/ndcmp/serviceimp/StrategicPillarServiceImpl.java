/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.StrategicPillarDAO;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.service.StrategicPillarService;

/**
 * @author samy
 *
 */
@Transactional()
@Service("strategicPillarService")
public class StrategicPillarServiceImpl implements StrategicPillarService {
	
	@Autowired
	private StrategicPillarDAO strategicPillarDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(StrategicPillarServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#getStrategicPillars()
	 */
	@Override
	public List<StrategicPillar> getStrategicPillars() {
		return strategicPillarDAO.getStrategicPillars();
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#getById(java.lang.Integer)
	 */
	@Override
	public StrategicPillar getById(Integer id) {
		return strategicPillarDAO.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#getByName(java.lang.String)
	 */
	@Override
	public StrategicPillar getByName(String name) {
		return strategicPillarDAO.getByName(name);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#save(com.ceainfotech.ndcmp.model.StrategicPillar)
	 */
	@Override
	public void save(StrategicPillar strategicPillar) {
		strategicPillarDAO.save(strategicPillar);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#saveAndFlush(com.ceainfotech.ndcmp.model.StrategicPillar)
	 */
	@Override
	public void saveAndFlush(StrategicPillar strategicPillar) {
		strategicPillarDAO.saveAndFlush(strategicPillar);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#getByProject(com.ceainfotech.ndcmp.model.StrategicPillar)
	 */
	@Override
	public List<StrategicPillar> getByProject(Project project) {
		return strategicPillarDAO.getByProject(project);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#delete(int)
	 */
	@Override
	public void delete(int id) {
		strategicPillarDAO.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#getByProjectId(com.ceainfotech.ndcmp.model.Project)
	 */
	@Override
	public Integer getByProjectId(Project project) {
		return strategicPillarDAO.getByProjectId(project);
	}
	
	@Override
	public List<StrategicPillar> findBySyncStatus(boolean syncStatus) {
		return strategicPillarDAO.findBySyncStatus(syncStatus);
	}

	@Override
	public StrategicPillar getBySequenceNumber(String sequenceNumber) {
		// TODO Auto-generated method stub
		return strategicPillarDAO.getBySequenceNumber(sequenceNumber);
	}
	
	@Override
	public List<StrategicPillar> getStrategicPliiersByReportingPeriods(List<Integer> reportingperiodIds) {
		return strategicPillarDAO.getStrategicPliiersByReportingPeriods(reportingperiodIds);
	}

	@Override
	public List<StrategicPillar> getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(
			List<Integer> reportingPeriods, Integer id) {
		// TODO Auto-generated method stub
		return strategicPillarDAO.getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(reportingPeriods,id);
	}

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#getStrategicPliiersByReportingPeriodsAndLeadAgency(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<StrategicPillar> getStrategicPliiersByReportingPeriodsAndLeadAgency(List<Integer> reportingperiodIds, Integer leadAgencyId) {
		return strategicPillarDAO.getStrategicPliiersByReportingPeriodsAndLeadAgency(reportingperiodIds, leadAgencyId);
	}
}
