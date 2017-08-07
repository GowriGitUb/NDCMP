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

import com.ceainfotech.ndcmp.dao.RegionDAO;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.service.RegionService;

/**
 * @author Samy
 *
 */
@Service("regionService")
@Transactional
public class RegionServiceImpl implements RegionService {
	
	@Autowired
	private RegionDAO regionDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.RegionService#getRegions()
	 */
	@Override
	public List<Region> getRegions() {
		return regionDAO.getRegions();
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.RegionService#getById(java.lang.Integer)
	 */
	@Override
	public Region getById(Integer id) {
		return regionDAO.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.RegionService#getByCode(java.lang.String)
	 */
	@Override
	public Region getByCode(String code) {
		return regionDAO.getByCode(code);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.RegionService#getByCodeAndId(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Region getByCodeAndId(String code, Integer id) {
		return regionDAO.getByCodeAndId(code, id);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.RegionService#addRegion(com.ceainfotech.ndcmp.model.Region)
	 */
	@Override
	public void addRegion(Region region) {
		LOGGER.info("Add New Region");
		if(region != null){
			regionDAO.addRegion(region);
		}
	}

	@Override
	public void deleteRegion(Integer id) {
		LOGGER.info("Delete Region");
		if(id != 0){
			regionDAO.deleteRegion(id);
		}
	}

	@Override
	public List<Region> findByStates(States states) {
		return regionDAO.findByStates(states);
	}

	@Override
	public List<Region> findByCountry(Countries countries) {
		if(countries != null){
		return regionDAO.findByCountry(countries);
		}
		return null;
	}
	
	@Override
	public List<Region> findByStatus(String status) {
		if(status != null){
			return regionDAO.findByStatus(status);
		}
		return null;
	}

	@Override
	public Region findByName(String name) {
		if(name != null){
			return regionDAO.findByName(name);
		}
		return null;
	}
	
	@Override
	public Region findByNameAndId(String name, Integer id) {
		if(name != null && id != null){
			return regionDAO.findByNameAndId(name, id);
		}
		return null;
	}
}
