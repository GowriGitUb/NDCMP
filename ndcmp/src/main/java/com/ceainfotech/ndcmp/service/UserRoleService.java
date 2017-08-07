/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.UserRole;

/**
 * @author Gowri
 * 
 */
public interface UserRoleService {

	public List<UserRole> listAllRoles();
	
	public List<UserRole> getAllRoles();

	public void addRole(UserRole role);

	public void deleteRole(Integer id);

	public UserRole findByRoleId(Integer id);

	public List<UserRole> findByStatus(String status);

	public UserRole findByName(String name);

	public UserRole findByNameAndId(String name, Integer id);
	
	public Integer getByRoleId();

}
