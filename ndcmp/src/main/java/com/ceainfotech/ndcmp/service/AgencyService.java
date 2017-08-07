/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author Gowri
 *
 */
public interface AgencyService {

	public List<Agency> listAllAgency();

	public void addAgency(Agency agency);

	public void deleteAgency(Integer id);

	public Agency findByAgencyId(Integer id);

	public List<Agency> getByPartnerAgency(Boolean partnerAgencyStatus);

	public List<Agency> getByLeadAgency(Boolean leadAgencyStatus);

	public List<Agency> findByStatus(String status);

	public List<Agency> findByCountry(Countries country);// find by country
															// status

	public List<Agency> findByState(States states);// find by state

	public List<Agency> findByRegion(Region region);// find by region

	public int getAgenciesCount();

	public List<Agency> findByAgencyAuthority(User user);
	
	public List<Agency> findByStatus(String status,Sort sort);
	
	public void deleteUserAgencies(Integer userId,Integer agencyId);
	
	

	/**
	 * @author pushpa
	 * @param agencyCode
	 * @return
	 */
	public Agency findByAgencyCode(String agencyCode);

	/**
	 * @author pushpa Find agency between last synced date and currenct date -->
	 * For web service
	 * @param lastSyncedTime
	 * @param currentTime
	 * @return
	 */
	public Agency findAgencyByIdAndDate(Integer id, String lastSyncedTime,String currentTime);
	
	/**
	 * @author pushpa
	 * Find Agency based on the login User
	 * @param user
	 * @return
	 */
	public Agency findByLoginUser(String username);

	public Agency findByName(String name);

	public Agency findByIdAndCode(Integer id, String code);

	public Agency findByIdAndName(Integer id, String name);
	
}
