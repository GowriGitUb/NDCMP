/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.UserRoleDAO;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.service.UserRoleService;

/**
 * @author Gowri
 * 
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceimpl implements UserRoleService {

	@Autowired
	UserRoleDAO userRoleDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceimpl.class);
	
	@Override
	public List<UserRole> listAllRoles() {
		LOGGER.info("List of roles");
		return userRoleDAO.listAllRoles();
	}

	@Override
	public void addRole(UserRole role) {
		LOGGER.info("Add New Role");
		if(role != null){
			userRoleDAO.addRole(role);
		}
	}

	@Override
	public void deleteRole(Integer id) {
		LOGGER.info("Delete Role");
		if(id != null){
			userRoleDAO.deleteRole(id);
		}
	}

	@Override
	public UserRole findByRoleId(Integer id) {
		LOGGER.info("Get One Role");
		if(id != null){
			return userRoleDAO.findByRoleId(id);
		}
		return null;
	}
	
	@Override
	public List<UserRole> findByStatus(String status) {
		if(status != null){
			return userRoleDAO.findByStatus(status);
		}
		return null;
	}

	@Override
	public UserRole findByName(String name) {
		if(name != null){
			return userRoleDAO.findByName(name);
		}
		return null;
	}

	@Override
	public UserRole findByNameAndId(String name,Integer id) {
		if(name != null && id != null){
			return userRoleDAO.findByNameAndId(name,id);
		}
		return null;
	}

	@Override
	public List<UserRole> getAllRoles() {
		return userRoleDAO.getAllRoles();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.OutcomeService#getByThemeId(com.ceainfotech
	 * .ndcmp.model.userrole)
	 */
	@Override
	public Integer getByRoleId() {
		return userRoleDAO.getByRoleId();
	}
}
