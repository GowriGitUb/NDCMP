/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.RoleModuleFeature;

/**
 * @author jeeva
 *
 */
public interface RoleModuleFeatureRepository extends JpaRepository<RoleModuleFeature, Integer>{
	
List<RoleModuleFeature> findByRoleId(@Param("roleId") Integer roleId);
	
	List<RoleModuleFeature> findByFeatureId(Integer featureid);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM RoleModuleFeature r WHERE r.roleId = :roleId")
	void deleteByRoleId(@Param("roleId") Integer roleId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM RoleModuleFeature r WHERE r.roleId = :roleId")
	void deleteByRoleIdAndCompany(@Param("roleId") Integer roleId);
	
	public RoleModuleFeature findByRoleIdAndModuleIdAndFeatureId(Integer roleId, Integer moduleId,Integer featureId);

}
