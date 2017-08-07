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

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.repository.RegionRepository;

/**
 * @author Samy
 * 
 */
@Repository
public class RegionDAO {

	private final RegionRepository regionRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(RegionDAO.class);

	/**
	 * 
	 */
	@Autowired
	public RegionDAO(final RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}

	/**
	 * List all the user by username
	 */
	@Transactional(readOnly = true)
	public List<Region> getRegions() {
		LOGGER.debug("Find All the Regions .. {}");
		return regionRepository.findAllByOrderByCodeAsc();
	}
	
	@Transactional(readOnly = true)
	public Region addRegion(Region region) {
		LOGGER.debug("Add new Region.. {}", region);
		return regionRepository.save(region);
	}
	
	@Transactional(readOnly = true)
	public Region getById(Integer id) {
		LOGGER.debug("Find Region by id .. {}", id);
		return regionRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Region getByCode(String code) {
		LOGGER.debug("Find Region By Code .. {}", code);
		return regionRepository.findByCode(code);
	}
	
	@Transactional(readOnly = true)
	public Region getByCodeAndId(String code, Integer id) {
		LOGGER.debug("Find Region By Code and Id.. {}", code, id);
		return regionRepository.findByCodeAndId(code, id);
	}
	
	public List<Region> findByStates(States states) {
		LOGGER.debug("List the region by states .. {}", states);
		return regionRepository.findByStatesOrderByName(states);
	}
	
	@Transactional(readOnly = true)
	public void deleteRegion(Integer id) {
		LOGGER.debug("delete Region.. {}", id);
		regionRepository.delete(id);
	}
	
	@Transactional(readOnly = true)
	public List<Region> findByCountry(Countries countries){
		LOGGER.info("get the details by country");
		if(countries != null){
			return regionRepository.findByCountryOrderByName(countries);
		}
		return null;
	}
	
	//to get the region based on the states
	@Transactional(readOnly = true)
	public List<Region> findByStatus(String status){
		if(status != null){
			return regionRepository.findByStatusOrderByName(status);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public Region findByName(String name){
		if(name != null){
			return regionRepository.findByName(name);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public Region findByNameAndId(String name, Integer id){
		if(name != null && id != null){
			return regionRepository.findByNameAndId(name, id);
		}
		return null;
	}
}
