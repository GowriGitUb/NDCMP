/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Statuss;

/**
 * @author Samy
 * 
 */
public interface StatusService {

	public Statuss findStatusByName(String name);

	public void addStatus(Statuss status);

	public Statuss findByIdAndNameAndStatusColor(Integer id, String name,  String description, String statusColor);

	public void updateStatus(Statuss status);

	public List<Statuss> listAllStatus();

	public Statuss getStatusById(Integer id);

	public void deleteStatusById(Integer id);

	public Statuss findStatusByDescription(String description);

	public Statuss findStatusByColor(String statusColor);

	public Statuss getByIdAndName(Integer id, String name);

	public Statuss statusIdAndDescription(Integer id, String description);

	public Statuss statusIdAndStatusColor(Integer id, String statusColor);
	
	/**
	 * @author pushpa
	 * @param lastSyncedTime
	 * @param currentTime
	 * @return
	 */
	public List<Statuss> findStatusListByDate(String lastSyncedTime, String currentTime);
	
	public Integer getStatussCount();
	
	public List<Statuss> findAllByOrderByStartRangeAsc();

}
