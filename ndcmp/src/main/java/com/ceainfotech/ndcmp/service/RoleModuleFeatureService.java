/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.RoleModuleFeature;
import com.ceainfotech.ndcmp.util.RoleFeatureList;

/**
 * @author jeeva
 *
 */
public interface RoleModuleFeatureService {

public List<RoleModuleFeature> getByRoleId(Integer roleId);
	
	public List<RoleModuleFeature> getAll();

	public int save(RoleFeatureList roleFeatureList);
	
	public RoleModuleFeature findByRoleIdModuleIdFeatureId(Integer roleId, Integer moduleId,Integer featureId);
	
	public void deleteByRoleId(Integer roleId);
	
	public void addRolemoduleFeature(RoleModuleFeature roleModuleFeature);
	
	public void deleteByRoleIdAndCompany(Integer roleId);
}
