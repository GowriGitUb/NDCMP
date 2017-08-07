/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.AgencyDAO;
import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.AgencyService;

/**
 * @author Gowri
 * 
 */
@Service("agencyService")
@Transactional
public class AgencyServiceImpl implements AgencyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AgencyServiceImpl.class);

	@Autowired
	AgencyDAO agencyDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ceainfotech.ndcmp.service.AgencyService#listAllAgency()
	 */
	@Override
	public List<Agency> listAllAgency() {
		LOGGER.info("list all agency");
		return agencyDAO.listAllAgency();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.AgencyService#addAgency(com.ceainfotech
	 * .ndcmp.model.Agency)
	 */
	@Override
	public void addAgency(Agency agency) {
		LOGGER.info("Save Agency");
		if (agency != null) {
			agencyDAO.addAgency(agency);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.AgencyService#deleteAgency(java.lang.Integer
	 * )
	 */
	@Override
	public void deleteAgency(Integer id) {
		LOGGER.info("Delete agency");
		if (id != null) {
			agencyDAO.deleteAgency(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.AgencyService#findByAgencyId(java.lang.
	 * Integer)
	 */
	@Override
	public Agency findByAgencyId(Integer id) {
		LOGGER.info("Get one agency");
		if (id != null) {
			return agencyDAO.findByAgencyId(id);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.AgencyService#findByStatus(java.lang.String
	 * )
	 */
	@Override
	public List<Agency> findByStatus(String status) {
		LOGGER.info("Get all the agency based on the status");
		if (status != null) {
			return agencyDAO.findByStatus(status);
		}
		return null;
	}
	

	@Override
	public List<Agency> findByCountry(Countries country) {
		if(country != null){
			return agencyDAO.findByCountry(country);
		}
		return null;
	}
	
	//find by state
	@Override
	public List<Agency> findByState(States states) {
		if(states != null){
			return agencyDAO.findByState(states);
		}
		return null;
	}
	
	//find by region
	@Override
	public List<Agency> findByRegion(Region region) {
		if(region != null){
			return agencyDAO.findByRegion(region);
		}
		return null;
	}

	@Override
	public int getAgenciesCount() {
		return agencyDAO.getAgenciesCount();
	}
	
	@Override
	public List<Agency> findByAgencyAuthority(User user) {
		if(user != null){
			return agencyDAO.findByAgencyAuthority(user);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.AgencyService#findByPartnerAgency(java.lang.Boolean)
	 */
	@Override
	public List<Agency> getByPartnerAgency(Boolean partnerAgencyStatus) {
		return agencyDAO.getByAgencyPartnerAgency(partnerAgencyStatus);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.AgencyService#findByPartnerAgency(java.lang.Boolean)
	 */
	@Override
	public List<Agency> getByLeadAgency(Boolean leadAgencyStatus) {
		return agencyDAO.getByAgencyLeadAgency(leadAgencyStatus);
	}

	@Override
	public Agency findByAgencyCode(String agencyCode) {
		return agencyDAO.findByAgencyCode(agencyCode);
	}

	@Override
	public Agency findAgencyByIdAndDate(Integer id, String lastSyncedTime, String currentTime) {
		return agencyDAO.findAgencyByIdAndDate(id, lastSyncedTime, currentTime);
	}

	@Override
	public Agency findByName(String name) {
		if(name != null){
			return agencyDAO.findByName(name);
		}
		return null;
	}

	@Override
	public Agency findByIdAndCode(Integer id, String code) {
		if(id != null && code != null){
			return agencyDAO.findByIdAndCode(id, code);
		}
		return null;
	}

	@Override
	public Agency findByIdAndName(Integer id, String name) {
		if(id != null && name != null){
			return agencyDAO.findByIdAndCode(id, name);
		}
		return null;
	}

	@Override
	public Agency findByLoginUser(String username) {
		return agencyDAO.findByLoginUser(username);
	}

	@Override
	public List<Agency> findByStatus(String status, Sort sort) {
		if(status != null){
			return agencyDAO.findByStatus(status, sort);
		}
		return null;
	}
	@Override
	public void deleteUserAgencies(Integer userId, Integer agencyId) {
		agencyDAO.deleteUserAgencies(userId, agencyId);
	}

}
