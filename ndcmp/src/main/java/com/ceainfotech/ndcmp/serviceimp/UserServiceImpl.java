package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.UserDAO;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.service.UserService;

/**
 * 
 * @author Samy
 * 
 */

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.UserService#findByUsername(java.lang.String
	 * )
	 */
	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.UserService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		userDAO.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.UserService#delete(java.lang.Integer)
	 */
	@Override
	public void deleteByUser(User user) {
		userDAO.deleteByUser(user);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.UserService#getUsers(java.lang.String)
	 */
	@Override
	public List<User> getUsers(String status) {
		return userDAO.getUsers(status);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.UserService#getById(java.lang.Integer)
	 */
	@Override
	public User getById(Integer id) {
		return userDAO.findById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.UserService#add(com.ceainfotech.ndcmp.model.User)
	 */
	@Override
	public void add(User user) {
		if(user != null){
			userDAO.add(user);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.ProjectService#getByProjectadmin(java.lang.String)
	 */
	@Override
	public User getByProjectadmin(UserRole userRole) {
		return userDAO.getByProjectadmin(userRole);
	}
	
	//get the users by country
	@Override
	public List<User> findByCountry(Countries countries) {
		if(countries != null){
			return  userDAO.findByCountry(countries);
		}
		return null;
	}

	@Override
	public User findByEmail(String email) {
		if(email != null){
			return userDAO.findByEmail(email);
		}
		return null;
	}

	@Override
	public User findByIdAndEmail(Integer id, String email) {
		if(id != null && email != null){
			return userDAO.findByIdAndEmail(id, email);
		}
		return null;
	}
	
	//get the user by state
	@Override
	public List<User> findByState(States state) {
		if(state != null){
			return userDAO.findByState(state);
		}
		return null;
	}
	
	@Override
	public boolean checkIfValidOldPassword(User user, String currentPassword) {
		if(user != null && currentPassword != null){
			return userDAO.checkIfValidOldPassword(user, currentPassword);
		}
		return false;
	}
	
	@Override
	public User findByUsernameAndUserType(String username, String usertype) {
		if(username != null && usertype != null){
			return userDAO.findByUsernameAndUserType(username, usertype);
		}
		return null;
	}

	@Override
	public List<User> listAllUsers() {
		return userDAO.listAllUsers();
	}

	@Override
	public int getUsersCount() {
		return userDAO.getUsersCount();
	}
	
	@Override
	public List<User> getByUserRoles(UserRole userrole) {
		if(userrole != null){
			return userDAO.getByUserRoles(userrole);
		}
		return null;
	}

	@Override
	public List<User> getUsersByRoleStatusUpdaterAndReviewer() {
		return userDAO.getUsersByRoleStatusUpdaterAndReviewer();
	}
	
	/**
	 * @author PremKumar
	 */
	@Override
	public List<User> getUsersByRoleStatusApprover() {
		return userDAO.getUsersByRoleStatusApprover();
	}
}
