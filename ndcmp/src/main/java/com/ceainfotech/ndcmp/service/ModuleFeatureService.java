/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.ModuleFeature;
import com.ceainfotech.ndcmp.util.ModulesFeatures;

/**
 * @author jeeva
 *
 */
public interface ModuleFeatureService {
	
	public void save(ModuleFeature moduleFeature);
	
	public ModuleFeature getByModuleIdAndFeatureId(Integer mid,Integer fid);
	
	public List<ModulesFeatures> findModulesFeatures();
	
	public List<ModulesFeatures> getModulesFeatures(Integer roleId);
	
	public List<ModulesFeatures> getModulesFeaturesByRoleIdAndCompany(Integer roleId);
	
	public List<ModuleFeature> getModulesId(Integer moduleId);
	
	public ModuleFeature getFeatureId(Integer fid);
	
	public List<ModulesFeatures> getFeatures(Integer rid,Integer mid);
	
	public List<ModulesFeatures> getModulesFeaturesList(Integer roleId);


}
