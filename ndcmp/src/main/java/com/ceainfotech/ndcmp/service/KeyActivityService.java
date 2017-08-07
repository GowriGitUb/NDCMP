/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Output;

/**
 * @author Gowri
 *
 */
public interface KeyActivityService {

	public List<KeyActivity> listAllKeyActivity();

	public void addActivity(KeyActivity keyActivity);

	public void deleteActivity(Integer id);

	public KeyActivity getById(Integer id);
	
	public List<KeyActivity> findByStatus(String status);
	
	public Integer findByOutputId(Output output);
	
	public Integer getKeyActivityCount();
	
	public KeyActivity findByName(String name);
	
	public List<KeyActivity> findByOutput(Output output);
	
	public KeyActivity getByKeyId(int id);
	
	public List<KeyActivity> findBySyncStatus(boolean syncStatus);
	
	public KeyActivity getByNameAndOutput(String name,Output output);
	
	public List<KeyActivity> findKeyActivitiesByStrategicPillarsAndOpenedReportingPeriods(List<Integer> strategicPillarId,List<Integer> reportingPeriodId);
	
}
