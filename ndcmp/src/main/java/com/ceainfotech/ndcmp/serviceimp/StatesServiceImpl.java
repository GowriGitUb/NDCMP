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

import com.ceainfotech.ndcmp.dao.StatesDAO;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.service.StatesService;

/**
 * @author Samy
 *
 */
@Transactional()
@Service("statesService")
public class StatesServiceImpl implements StatesService {
	
	@Autowired
	private StatesDAO statesDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(StatesServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StatesService#getStates()
	 */
	@Override
	public List<States> getStates() {
		return statesDAO.getStates();
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StatesService#getById(java.lang.Integer)
	 */
	@Override
	public States getById(Integer id) {
		return statesDAO.getById(id);
	}

	@Override
	public void save(States states) {
		// TODO Auto-generated method stub
		LOGGER.info("Adding State");
		if(states!=null){
			statesDAO.save(states);
		}
	}

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StatesService#findByCountryId(java.lang.Integer)
	 */
	@Override
	public List<States> findByCountryId(Integer countryId) {
		return statesDAO.getByCountryId(countryId);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StatesService#saveAndFlush(com.ceainfotech.ndcmp.model.States)
	 */
	@Override
	public void saveAndFlush(States states) {
		// TODO Auto-generated method stub
		LOGGER.info("Updating State");
		if(states != null){
			statesDAO.saveAndFlush(states);
		}
	}
	@Override
	public void deleteStatesById(int id) {
		// TODO Auto-generated method stub
		LOGGER.info("Deleting State");
		if(id != 0){
			statesDAO.delete(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.StatesService#getByCountry(com.ceainfotech.ndcmp.model.Countries)
	 */
	@Override
	public List<States> getByCountry(Countries country) {
		return statesDAO.getByCountry(country);
	}
	
	@Override
	public List<States> findByCountry(Countries country) {
		if(country != null){
			return statesDAO.findByCountry(country);
		}
		return null;
	}

	@Override
	public States findByCode(String code) {
		// TODO Auto-generated method stub
		return statesDAO.findByCode(code);
	}

	@Override
	public States findByName(String name) {
		// TODO Auto-generated method stub
		return statesDAO.findByName(name);
	}

	@Override
	public States findByIdAndCode(Integer id, String code) {
		// TODO Auto-generated method stub
		return statesDAO.findByIdAndCode(id,code);
	}

	@Override
	public States findByIdAndName(Integer id, String name) {
		// TODO Auto-generated method stub
		return statesDAO.findByIdAndName(id,name);
	}
	
	@Override
	public List<States> findByStatus(String status) {
		if(status != null){
			return statesDAO.findByStatus(status);
		}
		return null;
	}
}
