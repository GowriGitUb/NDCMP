/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Module;
import com.ceainfotech.ndcmp.repository.ModuleRepository;
import com.ceainfotech.ndcmp.service.ModuleService;

/**
 * @author jeeva
 *
 */
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService{
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Override
	public Module getByModule(String module) {
		return moduleRepository.findByModule(module);
	}

	@Override
	public void save(Module module) {

		moduleRepository.saveAndFlush(module);
	}

	@Override
	public Module getByModuleId(Integer module) {
		return moduleRepository.findOne(module);
	}

	@Override
	public List<Module> getModuleByRoleAndCompany(Integer roleId) {
		return moduleRepository.getModuleByRoleAndCompany(roleId);
	}
	@Override
	public List<Module> getModuleByRole(Integer roleId) {
		return moduleRepository.getModuleByRole(roleId);
	}

}
