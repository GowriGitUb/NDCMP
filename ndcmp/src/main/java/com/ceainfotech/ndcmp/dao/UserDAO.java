/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.UserRepository;

/**
 * @author Samy
 * 
 */
@Repository
public class UserDAO {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

	/**
	 * 
	 */
	@Autowired
	public UserDAO(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * List all the user by username
	 */
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		LOGGER.debug("Find user by name .. {}", username);
		return userRepository.findByUsername(username);
	}
	
	@Transactional(readOnly = true)
	public void delete(Integer id) {
		LOGGER.debug("Delete user by id .. {}", id);
		userRepository.delete(id);
	}
	
	@Transactional(readOnly = false)
	public void deleteByUser(User user) {
		LOGGER.debug("Delete user by user .. {}", user);
		try {
			userRepository.delete(user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Transactional(readOnly = true)
	public List<User> getUsers(String status) {
		LOGGER.debug("Find users by status .. {}", status);
		return userRepository.findByStatus(status);
	}
	
	@Transactional(readOnly = true)
	public User findById(Integer id) {
		LOGGER.debug("Find user by Id .. {}", id);
		return userRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public void add(User user) {
		LOGGER.debug("Add new user .. {}", user);
		userRepository.save(user);
	}
	
	public User getByProjectadmin(UserRole userRole) {
		LOGGER.info("Get project admin ... {}", userRole);
		return userRepository.findByUserRoles(userRole);
	}
	
	@Transactional(readOnly = true)
	public List<User> findByCountry(Countries countries){
		if(countries != null){
			return userRepository.findByCountry(countries);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public User findByEmail(String email){
		if(email != null){
			return userRepository.findByEmail(email);
		}
		return null;
	}
	
	//get user by id and eamil
	@Transactional(readOnly = true)
	public User findByIdAndEmail(Integer id,String email){
		if(id != null && email != null){
			return userRepository.findByIdAndEmail(id, email);
		}
		return null;
	}
	
	//get the user based on the state
	@Transactional(readOnly = true)
	public List<User> findByState(States state){
		if(state != null){
			return userRepository.findByState(state);
		}
		return null;
	}
	
	//check the password
	@Transactional(readOnly = true)
	public boolean checkIfValidOldPassword(User user, String currentPassword){
		if(user != null && currentPassword != null){
			return passwordEncoder.matches(currentPassword, user.getPassword());
		}
		return false;
	}
	
	//get user by username and usertype
	@Transactional(readOnly = true)
	public User findByUsernameAndUserType(String username, String usertype) {
		if(username != null && usertype != null){
			return userRepository.findByUsernameAndUserType(username, usertype);
		}
		return null;
	}
	
	//Load all user
	@Transactional(readOnly = true)
	public List<User> listAllUsers(){
		return userRepository.findAllByOrderByFirstNameAsc();
	}
	
	public int getUsersCount() {
		return userRepository.findUsersCount();
	}
	
	@Transactional(readOnly = true)
	public List<User> getByUserRoles(UserRole userrole){
		if(userrole != null){
			return userRepository.getByUserRoles(userrole);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<User> getUsersByRoleStatusUpdaterAndReviewer(){
		return userRepository.getUsersByRoleStatusUpdaterAndReviewer();
	}
	
	@Transactional(readOnly = true)
	public List<User> getUsersByRoleStatusApprover(){
		return userRepository.getUsersByRoleStatusApprover();
	}
	
}
