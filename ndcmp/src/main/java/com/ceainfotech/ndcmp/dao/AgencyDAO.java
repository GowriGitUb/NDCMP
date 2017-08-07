/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.repository.AgencyRepository;

/**
 * @author Gowri
 *
 */
@Repository
public class AgencyDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AgencyDAO.class);
	
	private final  AgencyRepository agencyRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 
	 */
	@Autowired
	public AgencyDAO(final AgencyRepository agencyRepository) {
		this.agencyRepository = agencyRepository;
	}
	
	public List<Agency> listAllAgency() {
		LOGGER.info("list all the agency .. {}");
		return agencyRepository.findAllByOrderByCodeAsc();
	}

	public void addAgency(Agency agency) {
		LOGGER.info("Save new Agency .. {}", agency);
		agencyRepository.saveAndFlush(agency);
		/*if(agency != null){
			agencyRepository.save(agency);
		}*/
	}

	public void deleteAgency(Integer id) {
		LOGGER.info("Delete agency by id .. {}", id);
		if(id != null){
			agencyRepository.delete(id);
		}
	}

	public Agency findByAgencyId(Integer id) {
		LOGGER.info("Get one agency by Id");
		if(id != null){
			return agencyRepository.findOne(id);
		}
		return null;
	}

	public List<Agency> findByStatus(String status) {
		LOGGER.info("Get all the agency based on the status");
		if(status != null){
			return agencyRepository.findByStatus(status);
		}
		return null;
	}
	
	//find by country status
	public List<Agency> findByCountry(Countries country){
		if(country != null){
			return agencyRepository.findByCountry(country);
		}
		return null;
	}
	
	//find by state
	public List<Agency> findByState(States states){
		if(states != null){
			return agencyRepository.findByState(states);
		}
		return null;
	}
	
	//find by region
	public List<Agency> findByRegion(Region region){
		if(region != null){
			return agencyRepository.findByRegion(region);
		}
		return null;
	}
	
	public int getAgenciesCount() {
		return agencyRepository.findAgenciesCount();
	}
	
	@Transactional(readOnly = true)
	public List<Agency> findByAgencyAuthority(User user){
		if(user != null){
			return agencyRepository.findByAgencyAuthority(user);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Agency> getByAgencyPartnerAgency(Boolean partnerAgencyStatus) {
		if(partnerAgencyStatus != null){
			return agencyRepository.findByPartnerAgency(partnerAgencyStatus);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Agency> getByAgencyLeadAgency(Boolean leadAgencyStatus) {
		if(leadAgencyStatus != null){
			return agencyRepository.findByLeadAgency(leadAgencyStatus);
		}
		return null;
	}
	
	/**
	 * @author pushpa
	 * Find agency By code
	 * @param agencyCode
	 * @return
	 */
	@Transactional(readOnly = true)
	public Agency findByAgencyCode(String agencyCode){
		return agencyRepository.findByCode(agencyCode);
	}
	
	/**
	 * @author pushpa
	 * Find list of agencies by last synced date and current time ---> 	for web service
	 * @param lastSyncedTime
	 * @param currentTime
	 * @return
	 */
	@Transactional(readOnly = true)
	public Agency findAgencyByIdAndDate(Integer id, String lastSyncedTime, String currentTime){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastSyncedDate = null;
		Date currentDate = null;
		try {
			lastSyncedDate = sd.parse(lastSyncedTime);
			currentDate = sd.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return agencyRepository.findAgencyByIdAndDate(id,lastSyncedDate, currentDate);
	}

	/**
	 * @author pushpa
	 * Find Agency based on the login User
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = true)
	public Agency findByLoginUser(String username){
		return agencyRepository.findByLoginUser(username);
	}
	
	@Transactional(readOnly = true)
	public Agency findByName(String name){
		if(name != null){
			return agencyRepository.findByName(name);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public Agency findByIdAndCode(Integer id,String code){
		if(id != null && code != null){
			return agencyRepository.findByIdAndCode(id, code);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public Agency findByIdAndName(Integer id,String name){
	if(id != null && name != null){
		return agencyRepository.findByIdAndCode(id, name);
	}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Agency> findByStatus(String status,Sort sort){
		if(status != null){
			return agencyRepository.findByStatus(status, sort);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public void deleteUserAgencies(Integer userId, Integer agencyId) {
		agencyRepository.deleteUserAgencies(userId, agencyId);
	}
}
