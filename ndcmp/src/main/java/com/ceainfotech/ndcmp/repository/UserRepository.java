/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;

/**
 * @author Samy
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	
	public User findById(Integer id);
	
	/**
	 * 
	 * @author mani
	 */
	public List<User> findAllByOrderByFirstNameAsc();
	
	public List<User> findByStatus(String status);
	
	public void delete(User user);
	
	public User findByUserRoles(UserRole userrole);
	
	public List<User> findByCountry(Countries countries);
	
	public List<User> findByState(States state);//find by state
	
	public User findByEmail(String email);
	
	public User findByIdAndEmail(Integer id,String email);

	public User findByUsernameAndUserType(String username,String usertype);

	@Query(value = "SELECT COUNT(u.id) FROM User u WHERE u.status = 'ACTIVE'")
	public int findUsersCount();
	
	public List<User> getByUserRoles(UserRole userrole);
	
	@Query(value = "SELECT u FROM User u LEFT JOIN u.userRoles ur WHERE ur.name IN ('STATUS_UPDATER','STATUS_REVIEWER')")
	public List<User> getUsersByRoleStatusUpdaterAndReviewer();
	
	@Query(value = "SELECT u FROM User u LEFT JOIN u.userRoles ur WHERE ur.name IN ('STATUS_APPROVER')")
	public List<User> getUsersByRoleStatusApprover();
	
}
