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

import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.UserRoleRepository;

/**
 * @author Gowri
 *
 */
@Repository
public class UserRoleDAO{
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleDAO.class);

	public List<UserRole> listAllRoles() {
		LOGGER.info("List of all userroles");
		return userRoleRepository.findAllByOrderByNameAsc();
	}
	/**
	 * Save Role
	 * @param role
	 */
	public void addRole(UserRole role) {
		LOGGER.info("Add New Role", role);
		if(role != null){
			userRoleRepository.save(role);
		}
	}

	/**
	 * Get Role By Id
	 * @param id
	 * @return
	 */
	public UserRole findByRoleId(Integer id) {
		LOGGER.info("Get One Role by id .. {}", id);
		if(id != null){
			return userRoleRepository.findOne(id);
		}
		return null;
	} 
	
	/**
	 * Get Roles
	 * @param status
	 * @return
	 */
	public List<UserRole> findByStatus(String status){
		LOGGER.info("Get Roles based on the status .. {}", status);
		if(status != null){
			return userRoleRepository.findByStatus(status);
		}
		return null;
		
	}
	
	/**
	 * Get Role based on the role name
	 * @param name
	 * @return
	 */
	public UserRole findByName(String name) {
		LOGGER.info("Get Roles based on the name .. {}", name);
		if(name != null){
			return userRoleRepository.findByName(name);
		}
		return null;
	}
	
	/**
	 * Get Role based on the name and id
	 * @param name
	 * @param id
	 * @return
	 */
	
	public UserRole findByNameAndId(String name,Integer id) {
		LOGGER.info("Get Roles based on the name and id .. {}", name, id);
		if(name != null && id != null) {
			return userRoleRepository.findByNameAndId(name,id);
		}
		return null;
	}
	

	/**
	 * Delete Role
	 * @param id
	 */
	public void deleteRole(Integer id) {
		LOGGER.info("Delete Role .. {}", id);
		if(id != null){
			userRoleRepository.delete(id);
		}
	}
	
	public List<UserRole> getAllRoles() {
		LOGGER.info("List of all userroles");
		return userRoleRepository.findByUserRoleOrderWise();
	}
	
	@Transactional(readOnly = true)
	public Integer getByRoleId() {
		LOGGER.debug("Count User Role By roleId .. {}");
		return userRoleRepository.findByRoleId();
	}

}
