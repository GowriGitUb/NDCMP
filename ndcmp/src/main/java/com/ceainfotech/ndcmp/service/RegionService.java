/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;

/**
 * @author Samy
 * 
 */
public interface RegionService {

	public List<Region> getRegions();

	public Region getById(Integer id);

	public Region getByCode(String code);

	public Region getByCodeAndId(String code, Integer id);

	public void addRegion(Region region);

	public void deleteRegion(Integer id);

	public List<Region> findByStates(States states);
	
	public List<Region> findByCountry(Countries countries);
	
	public List<Region> findByStatus(String status);
	
	public Region findByName(String name);
	
	public Region findByNameAndId(String name, Integer id);
}
