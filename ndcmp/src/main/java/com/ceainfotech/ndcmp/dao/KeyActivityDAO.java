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

import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.repository.KeyActivityRepository;

/**
 * @author Gowri
 *
 */
@Repository
public class KeyActivityDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeyActivityDAO.class);
	
	@Autowired
	KeyActivityRepository keyActivityRepository;

	public List<KeyActivity> listAllKeyActivity() {
		LOGGER.info("List all activity");
		return keyActivityRepository.findAll();
	}

	public void addActivity(KeyActivity keyActivity) {
		keyActivityRepository.saveAndFlush(keyActivity);
	}

	public void deleteActivity(Integer id) {
		keyActivityRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public KeyActivity getById(Integer id) {
		return keyActivityRepository.findById(id);
	}
	
	public List<KeyActivity> findByStatus(String status){
		return keyActivityRepository.findByStatus(status);
	}
	
	public Integer findByOutputId(Output output){
		return keyActivityRepository.findByOutputId(output);
	}
	
	@Transactional(readOnly = true)
	public KeyActivity findByName(String name){
		return keyActivityRepository.findByName(name);
	}
	
	public List<KeyActivity> findByOutput(Output output) {
		if(output != null){
			return keyActivityRepository.findByOutput(output);
		}
		return null;
	}
	
	public Integer getKeyActivityCount(){
		return keyActivityRepository.findKeyActivityCount();
	}
	
	public KeyActivity getByKeyId(int id){
		return keyActivityRepository.findByKeyId(id);
	}
	
	public List<KeyActivity> findBySyncStatus(boolean syncStatus){
		return keyActivityRepository.findBySyncStatus(syncStatus);
	}
	
	public KeyActivity getByNameAndOutput(String name,Output output){
		return keyActivityRepository.findByNameAndOutput(name,output);
	}
	
	public List<KeyActivity> findKeyActivitiesByStrategicPillarsAndOpenedReportingPeriods(List<Integer> strategicPillarId,List<Integer> reportingPeriodId){
		return keyActivityRepository.findKeyActivitiesByStrategicPillarsAndOpenedReportingPeriods(strategicPillarId);
	}
}
