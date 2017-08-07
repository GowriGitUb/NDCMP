/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Module;

/**
 * @author jeeva
 *
 */
public interface ModuleService {
	
	public Module getByModule(String module);
	
	public void save(Module module);
	
	public Module getByModuleId(Integer module);
	
	public List<Module> getModuleByRoleAndCompany(Integer roleId);
	
	public List<Module> getModuleByRole(Integer roleId);

}
