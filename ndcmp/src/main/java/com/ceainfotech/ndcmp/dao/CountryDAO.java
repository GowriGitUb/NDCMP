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
import com.ceainfotech.ndcmp.repository.CountryRepository;

/**
 * @author Samy
 * 
 */
@Repository
public class CountryDAO {

	private final CountryRepository countryRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryDAO.class);

	/**
	 * 
	 */
	@Autowired
	public CountryDAO(final CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	/**
	 * List all the user by username
	 */
	@Transactional(readOnly = true)
	public List<Countries> getCountries() {
		LOGGER.debug("Find All the States .. {}");
		return countryRepository.findAllByOrderByCodeAsc();
	}

	public void save(Countries country) {
		LOGGER.debug("Add New  Country .. {}", country);
		countryRepository.save(country);
	}

	@Transactional(readOnly = true)
	public Countries getById(int id) {
		LOGGER.debug("Find By Id Country .. {}", id);
		return countryRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public void update(Countries country) {
		LOGGER.info("Update new Country", country);
		countryRepository.saveAndFlush(country);

	}

	@Transactional(readOnly = false)
	public void delete(int countryId) {
		LOGGER.info("Deleting Country by id", countryId);
		countryRepository.delete(countryId);
	}
	
	//get country based on the status
	@Transactional(readOnly = true)
	public List<Countries> findByStatus(String status){
		LOGGER.info("Get country based on the status");
		if(status != null){
			return countryRepository.findByStatusOrderByNameAsc(status);
		}
		return null;
	}
	
	//get country based on the code
	@Transactional(readOnly = true)
	public Countries findByCode(String code){
		if(code != null){
			return countryRepository.findByCode(code);
		}
		return null;
	}
	
	//get country based on the name
	@Transactional(readOnly = true)
	public Countries findByName(String name){
		if(name != null){
			return countryRepository.findByName(name);
		}
		return null;
	}
	
	//get by id and code
	@Transactional(readOnly = true)
	public Countries findByIdAndCode(Integer id,String code){
		if(id != null && code != null){
			return countryRepository.findByIdAndCode(id, code);
		}
		return null;
	}
	
	//get by id and name
	@Transactional(readOnly = true)
	public Countries findByIdAndName(Integer id,String name){
		if(id != null && name != null){
			return countryRepository.findByIdAndName(id, name);
		}
		return null;
	}
}
