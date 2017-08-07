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

import com.ceainfotech.ndcmp.dao.CountryDAO;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.service.CountryService;

/**
 * @author Samy
 * 
 */
@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Autowired
	private CountryDAO countryDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ceainfotech.ndcmp.service.CountryService#getCountries()
	 */
	@Override
	public List<Countries> getCountries() {
		return countryDAO.getCountries();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.CountryService#getById(java.lang.Integer)
	 */
	@Override
	public Countries getById(int id) {
		return countryDAO.getById(id);
	}

	@Override
	public void save(Countries country) {
		// TODO Auto-generated method stub
		LOGGER.info("Adding country");
		if (country != null) {
			countryDAO.save(country);
		}
	}

	@Override
	public void update(Countries country) {
		// TODO Auto-generated method stub
		LOGGER.info("Updating Country");
		if (country != null) {
			countryDAO.update(country);
		}

	}

	@Override
	public void deleteCountry(int countryId) {
		// TODO Auto-generated method stub
		LOGGER.info("Deleting Country");
		if (countryId != 0) {
			countryDAO.delete(countryId);
		}
	}

	//get country based on the status
	@Override
	public List<Countries> findByStatus(String status) {
		if(status != null){
			return countryDAO.findByStatus(status);
		}
		return null;
	}

	@Override
	public Countries findByCode(String code) {
		if(code != null){
			return countryDAO.findByCode(code);
		}
		return null;
	}

	@Override
	public Countries findByName(String name) {
		if(name != null){
			return countryDAO.findByName(name);
		}
		return null;
	}

	@Override
	public Countries findByIdAndCode(Integer id, String code) {
		if(id != null && code != null){
			return countryDAO.findByIdAndCode(id, code);
		}
		return null;
	}

	@Override
	public Countries findByIdAndName(Integer id, String name) {
		if(id != null && name != null){
			return countryDAO.findByIdAndName(id, name);
		}
		return null;
	}
}
