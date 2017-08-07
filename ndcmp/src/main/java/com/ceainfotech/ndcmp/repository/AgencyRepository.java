/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author Gowri
 * 
 */
public interface AgencyRepository extends JpaRepository<Agency, Integer> {
	
	/**
	 * 
	 * @author mani
	 */
	public List<Agency> findAllByOrderByCodeAsc();

	public List<Agency> findByStatus(String status);
	
	public List<Agency> findByStatus(String status,Sort sort);
	
	public List<Agency> findByCountry(Countries country);//find by country status
	
	public List<Agency> findByState(States states);//find by state
	
	public List<Agency> findByRegion(Region region); //find by region
	
	@Query(value = "SELECT COUNT(a) FROM Agency a WHERE a.status = 'ACTIVE'")
	public int findAgenciesCount();
	
	//to get list of agency based on the user
	public List<Agency> findByAgencyAuthority(User user);
	
	public List<Agency> findByPartnerAgency(Boolean partnerAgencyStatus);
	
	public List<Agency> findByLeadAgency(Boolean leadAgencyStatus);
	
	/**
	 * @author pushpa
	 * Find single agency by code
	 * @param code
	 * @return
	 */
	public Agency findByCode(String code);
	
	/**
	 * @author pushpa
	 * Find Agency list between last synced time and current time
	 * @param lastSyncedTime
	 * @param currentTime
	 * @return
	 */
	@Query(value = "SELECT a FROM Agency a WHERE a.id = :id AND a.modificationTime BETWEEN :lastSyncedTime AND :currentTime)")
	public Agency findAgencyByIdAndDate(@Param("id")Integer id,@Param("lastSyncedTime")Date lastSyncedTime, @Param("currentTime")Date currentTime);
	
	/**
	 * @author pushpa
	 * Find Agency based on the login User
	 * @param user
	 * @return
	 */
	@Query(value = "SELECT a FROM Agency a INNER JOIN a.agencyAuthority aa WHERE aa.username = :username")
	public Agency findByLoginUser(@Param("username")String username);
	
	public Agency findByName(String name);
	
	public Agency findByIdAndCode(Integer id,String code);
	
	 /* @Query(value = "SELECT * FROM todos t WHERE t.title = 'title'",
	            nativeQuery=true
	    )*/
	@Modifying
	@Transactional
	@Query(value="DELETE FROM tbl_agency_mapping WHERE USER_ID = :userId AND AGENCY_ID = :agencyId",nativeQuery = true)
	public void deleteUserAgencies(@Param("userId") Integer userId,@Param("agencyId")Integer agencyId);
	
	
//	@Query(value = "SELECT * FROM tbl_agency_mapping a WHERE a.USER_ID = ?1")
//	public Agency getUserFromAgencyMapping(User user);
	
//	DELETE FROM Transaction e WHERE e IN (:transactions)
//	DELETE FROM Agency a INNER JOIN a.agencyAuthority in aa WHERE aa.id = :id
	/*@Query(value = "DELETE aa FROM Agency a INNER JOIN a.agencyAuthority ON aa.id = a.agencyAuthority.id WHERE (aa.id = id)")
	public void deleteMappedUser(Integer id);*/
	
//	@Modifying
//	@Query("update Agency a INNER JOIN a.agencyAuthority aa set aa.firstname = ?1 where a.lastname = ?2")
//	int setFixedFirstnameFor(String firstname, String lastname);
	
	//@Query(value="DELETE FROM Agency a WHERE a.agencyAuthority.id=:userId")
	//public void removeAgencyByUserAndAgency(@Param("userId") Integer userId,@Param("agencyId") Integer agencyId);
}
