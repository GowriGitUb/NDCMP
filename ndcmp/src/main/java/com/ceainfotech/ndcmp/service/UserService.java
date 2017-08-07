package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;

/**
 * 
 * @author Samy
 * 
 */

public interface UserService {

	public List<User> listAllUsers();
	
	public User findByUsername(String username);

	public void delete(Integer id);

	public void deleteByUser(User user);

	public List<User> getUsers(String status);

	public User getById(Integer id);

	public void add(User user);

	public User getByProjectadmin(UserRole userRole);
	
	public List<User> findByCountry(Countries countries);
	
	public List<User> findByState(States state);
	
	public User findByEmail(String email);
	
	public User findByIdAndEmail(Integer id,String email);
	
	public boolean checkIfValidOldPassword(User user, String currentPassword);
	
	public User findByUsernameAndUserType(String username,String usertype);
	
	public int getUsersCount();
	
	public List<User> getByUserRoles(UserRole userrole);
	
	/**
	 * @author pushpa
	 * Get list of users by status updater and reviewer role
	 * @return
	 */
	public List<User> getUsersByRoleStatusUpdaterAndReviewer();
	
	/**
	 * @author PremKumar
	 * Get list of users by status approver
	 * @return
	 */
	public List<User> getUsersByRoleStatusApprover();

}