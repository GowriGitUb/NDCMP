/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceainfotech.ndcmp.model.UserRole;

/**
 * @author Gowri
 *
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	
	public UserRole findByName(String name);
	
	public UserRole findByNameAndId(String name,Integer id);
	
	public List<UserRole> findByStatus(String status);

	public List<UserRole> findById(Integer id);
	
	/**
	 * 
	 * @author mani
	 */
	public List<UserRole> findAllByOrderByNameAsc();
	
	
	@Query("SELECT u FROM UserRole u ORDER BY u.id ASC")
	public List<UserRole> findByUserRoleOrderWise();
	
	//@Query(value = "SELECT COUNT(a) FROM UserRole a")
	@Query(value = "SELECT COUNT(a) FROM UserRole a WHERE a.status = 'ACTIVE'")
	Integer findByRoleId();
}
