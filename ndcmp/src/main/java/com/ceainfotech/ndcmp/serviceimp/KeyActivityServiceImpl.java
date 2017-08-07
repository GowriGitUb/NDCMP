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

import com.ceainfotech.ndcmp.dao.KeyActivityDAO;
import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.service.KeyActivityService;

/**
 * @author Gowri
 *
 */
@Service("keyActivityService")
@Transactional
public class KeyActivityServiceImpl implements KeyActivityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeyActivityServiceImpl.class);
	
	@Autowired
	KeyActivityDAO keyActivityDAO;

	@Override
	public List<KeyActivity> listAllKeyActivity() {
		return keyActivityDAO.listAllKeyActivity();
	}

	@Override
	public void addActivity(KeyActivity keyActivity) {
		keyActivityDAO.addActivity(keyActivity);
	}

	@Override
	public void deleteActivity(Integer id) {
		keyActivityDAO.deleteActivity(id);
	}

	@Override
	public KeyActivity getById(Integer id) {
		return keyActivityDAO.getById(id);
	}

	@Override
	public List<KeyActivity> findByStatus(String status) {
		LOGGER.info("List all activity");
		return keyActivityDAO.findByStatus(status);
	}

	@Override
	public Integer findByOutputId(Output output) {
		return keyActivityDAO.findByOutputId(output);
	}
	
	@Override
	public KeyActivity findByName(String name) {
		return keyActivityDAO.findByName(name);
	}

	@Override
	public List<KeyActivity> findByOutput(Output output) {
		if(output != null){
			return keyActivityDAO.findByOutput(output);
		}
		return null;
	}

	@Override
	public Integer getKeyActivityCount() {
		return keyActivityDAO.getKeyActivityCount();
	}

	@Override
	public KeyActivity getByKeyId(int id) {
		return keyActivityDAO.getByKeyId(id);
	}
	
	@Override
	public List<KeyActivity> findBySyncStatus(boolean syncStatus) {
		return keyActivityDAO.findBySyncStatus(syncStatus);
	}

	@Override
	public KeyActivity getByNameAndOutput(String name, Output output) {
		// TODO Auto-generated method stub
		return keyActivityDAO.getByNameAndOutput(name,output);
	}

	@Override
	public List<KeyActivity> findKeyActivitiesByStrategicPillarsAndOpenedReportingPeriods(
			List<Integer> strategicPillarId, List<Integer> reportingPeriodId) {
		// TODO Auto-generated method stub
		return keyActivityDAO.findKeyActivitiesByStrategicPillarsAndOpenedReportingPeriods(strategicPillarId, reportingPeriodId);
	}
}
