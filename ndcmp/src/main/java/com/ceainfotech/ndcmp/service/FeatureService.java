/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Feature;

/**
 * @author jeeva
 *
 */
public interface FeatureService {
	
public Feature findByFeature(String name);
	
	public void save(Feature feature);
	
	public List<Feature> listAllFeatures();
	
	public Feature findByFeatureId(Integer id);
	
	public List<Feature> findByRoleIdAndModuleId(Integer roleId,Integer moduleId);

}
