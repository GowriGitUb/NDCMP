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

import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.repository.StrategicPillarRepository;

/**
 * @author Samy
 * 
 */
@Repository
public class StrategicPillarDAO {

	private final StrategicPillarRepository strategicPillarRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(StrategicPillarDAO.class);

	/**
	 * 
	 */
	@Autowired
	public StrategicPillarDAO(final StrategicPillarRepository strategicPillarRepository) {
		this.strategicPillarRepository = strategicPillarRepository;
	}

	@Transactional(readOnly = true)
	public List<StrategicPillar> getStrategicPillars() {
		LOGGER.debug("Find All the StrategicPillar .. {}");
		return strategicPillarRepository.findAll();
	}
	
	public void save(StrategicPillar strategicPillar) {
		LOGGER.info("Add new StrategicPillar .. {}", strategicPillar);
		if(strategicPillar != null){
			strategicPillarRepository.save(strategicPillar);
		}
	}
	
	@Transactional(readOnly = true)
	public StrategicPillar getById(Integer id) {
		LOGGER.debug("Find By StrategicPillar id .. {}", id);
		return strategicPillarRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public StrategicPillar getByName(String name) {
		LOGGER.debug("Find StrategicPillar By name .. {}", name);
		return strategicPillarRepository.findByName(name);
	}
	
	@Transactional(readOnly = true)
	public List<StrategicPillar> getByProject(Project project) {
		LOGGER.debug("Find By StrategicPillars Project .. {}", project);
		return strategicPillarRepository.findByProject(project);
	}
	
	public void saveAndFlush(StrategicPillar strategicPillar) {
		LOGGER.info("Update existing StrategicPillar .. {}", strategicPillar);
		strategicPillarRepository.saveAndFlush(strategicPillar);
	}

	public void delete(int id) {
		LOGGER.info("Deleting StrategicPillar by id .. {}", id);
		strategicPillarRepository.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Integer getByProjectId(Project project) {
		LOGGER.debug("Count Outcome By Theme .. {}", project);
		return strategicPillarRepository.findByProjectId(project);
	}
	
	@Transactional(readOnly = true)
	public List<StrategicPillar> findBySyncStatus(boolean syncStatus){
		return strategicPillarRepository.findBySyncStatus(syncStatus);
	}
	
	@Transactional(readOnly = true)
	public StrategicPillar getBySequenceNumber(String sequenceNumber){
		return strategicPillarRepository.findBySequenceNumber(sequenceNumber);
	}
	
	@Transactional(readOnly = true)
	public List<StrategicPillar> getStrategicPliiersByReportingPeriods(List<Integer> reportingperiodIds) {
		return strategicPillarRepository.getStrategicPliiersByReportingPeriods(reportingperiodIds);
	}
	
	@Transactional(readOnly = true)
	public List<StrategicPillar> getStrategicPliiersByReportingPeriodsAndLeadAgency(List<Integer> reportingperiodIds, Integer leadAgencyId) {
		return strategicPillarRepository.getStrategicPliiersByReportingPeriodsAndLeadAgency(reportingperiodIds, leadAgencyId);
	}
	
	@Transactional(readOnly = true)
	public List<StrategicPillar> getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(List<Integer> reportingperiodIds,Integer id) {
		return strategicPillarRepository.getStrategicPliiersByReportingPeriodAndCurrentPartnerAgency(reportingperiodIds,id);
	}

	@Transactional(readOnly=true)
	public List<StrategicPillar> findByStrategicIds(List<Integer> strategicPillarIds){
		return  strategicPillarRepository.findByStrategicIds(strategicPillarIds);
	}
}
