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
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.repository.StateRepository;

/**
 * @author Samy
 * 
 */
@Repository
public class StatesDAO {

	private final StateRepository stateRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(StatesDAO.class);

	/**
	 * 
	 */
	@Autowired
	public StatesDAO(final StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

	/**
	 * List all the user by username
	 */
	@Transactional(readOnly = true)
	public List<States> getStates() {
		LOGGER.debug("Find All the States .. {}");
		return stateRepository.findAllByOrderByCodeAsc();
	}

	public void save(States states) {
		LOGGER.info("Add new state .. {}", states);
		if (states != null) {
			stateRepository.save(states);
		}
	}

	@Transactional(readOnly = true)
	public States getById(Integer id) {
		LOGGER.debug("Find By State id .. {}", id);
		return stateRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<States> getByCountryId(Integer countryId) {
		LOGGER.debug("Find By country id .. {}", countryId);
		return stateRepository.findByCountryIdOrderByNameAsc(countryId);
	}

	@Transactional(readOnly = true)
	public List<States> getByCountry(Countries country) {
		LOGGER.debug("Find By country id .. {}", country);
		return stateRepository.findByCountryOrderByNameAsc(country);
	}

	public void saveAndFlush(States states) {
		LOGGER.info("Update existing State .. {}", states);
		stateRepository.saveAndFlush(states);
	}

	public void delete(int id) {
		LOGGER.info("Deleting State .. {}", id);
		stateRepository.delete(id);
	}
	//to get the states based on the country
	@Transactional(readOnly = true)
	public List<States> findByCountry(Countries country){
		if(country != null){
			return stateRepository.findByCountryOrderByNameAsc(country);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public States findByCode(String code) {
		// TODO Auto-generated method stub
		return stateRepository.findByCode(code);
	}
	
	@Transactional(readOnly = true)
	public States findByName(String name) {
		// TODO Auto-generated method stub
		return stateRepository.findByName(name);
	}
	
	@Transactional(readOnly = true)
	public States findByIdAndCode(Integer id, String code) {
		// TODO Auto-generated method stub
		return stateRepository.findByIdAndCode(id,code);
	}
	
	@Transactional(readOnly = true)
	public States findByIdAndName(Integer id, String name) {
		// TODO Auto-generated method stub
		return stateRepository.findByIdAndName(id,name);
	}
	
	
	//To get the states based on the status
	@Transactional(readOnly = true)
	public List<States> findByStatus(String status){
		if(status != null){
			return stateRepository.findByStatusOrderByNameAsc(status);
		}
		return null;
	}
}
