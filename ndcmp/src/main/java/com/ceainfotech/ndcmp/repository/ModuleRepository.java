/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.Module;

/**
 * @author jeeva
 *
 */
public interface ModuleRepository extends JpaRepository<Module, Integer>{
	
public Module findByModule(String module);
	
	@Query("SELECT m FROM Module m WHERE m.id IN (SELECT DISTINCT r.moduleId FROM RoleModuleFeature r WHERE r.roleId = :roleId) ORDER BY m.orderId ASC")
	public List<Module> getModuleByRole(@Param("roleId") Integer roleId);
	
	@Query("SELECT m FROM Module m WHERE m.id IN (SELECT DISTINCT r.moduleId FROM RoleModuleFeature r WHERE r.roleId = :roleId) ORDER BY m.id ASC")
	public List<Module> getModuleByRoleId(@Param("roleId") Integer roleId);
	
	@Query("SELECT m FROM Module m WHERE m.id IN (SELECT DISTINCT r.moduleId FROM RoleModuleFeature r WHERE r.roleId = :roleId) ORDER BY m.orderId ASC")
	public List<Module> getModuleByRoleAndCompany(@Param("roleId") Integer roleId);

}
