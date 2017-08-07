/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceainfotech.ndcmp.model.ModuleFeature;

/**
 * @author jeeva
 *
 */
public interface ModuleFeatureRepository extends JpaRepository<ModuleFeature, Integer>{
	
	public ModuleFeature findByModuleIdAndFeatureId(Integer mid,Integer fid);
	
	List<ModuleFeature> findByModuleId(Integer moduleid);
	
	public ModuleFeature findByFeatureId(Integer fid);

}
