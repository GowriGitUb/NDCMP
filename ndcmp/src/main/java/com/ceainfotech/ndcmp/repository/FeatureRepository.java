/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.Feature;

/**
 * @author jeeva
 *
 */
public interface FeatureRepository extends JpaRepository<Feature, Integer>{

public Feature findByFeature(String name);
	
	@Query("SELECT f FROM Feature f WHERE f.id IN (SELECT r.featureId FROM RoleModuleFeature r WHERE r.roleId = :roleId AND r.moduleId = :moduleId)")
	public List<Feature> findFeatureByRoleIdAndModuleid(@Param("roleId") Integer roleId,@Param("moduleId") Integer moduleId);
	
	@Query("SELECT f FROM Feature f WHERE f.id IN (SELECT m.featureId FROM ModuleFeature m WHERE m.moduleId = :moduleId)")
	public List<Feature> findFeatureByModuleid(@Param("moduleId") Integer moduleId);
}
