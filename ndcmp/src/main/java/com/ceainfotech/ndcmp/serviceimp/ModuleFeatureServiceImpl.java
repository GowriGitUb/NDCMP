/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Module;
import com.ceainfotech.ndcmp.model.ModuleFeature;
import com.ceainfotech.ndcmp.repository.ModuleFeatureRepository;
import com.ceainfotech.ndcmp.repository.ModuleRepository;
import com.ceainfotech.ndcmp.service.ModuleFeatureService;
import com.ceainfotech.ndcmp.service.ModuleService;
import com.ceainfotech.ndcmp.util.ModulesFeatures;

/**
 * @author jeeva
 *
 */
@Service
@Transactional
public class ModuleFeatureServiceImpl implements ModuleFeatureService{
	
	@Autowired
	ModuleFeatureRepository moduleFeatureRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	ModuleService moduleService;

	@Override
	public void save(ModuleFeature moduleFeature) {
		moduleFeatureRepository.saveAndFlush(moduleFeature);
		
	}
	@Override
	public List<ModuleFeature> getModulesId(Integer moduleId) {
		return moduleFeatureRepository.findByModuleId(moduleId);
	}
	@Override
	public ModuleFeature getByModuleIdAndFeatureId(Integer mid, Integer fid) {
		return moduleFeatureRepository.findByModuleIdAndFeatureId(mid, fid);
	}
	
	@Override
	public List<ModulesFeatures> findModulesFeatures() {
		List<ModulesFeatures> modulesFeatures = new ArrayList<>();
		List<Module> modules = new ArrayList<Module>();
		modules = moduleRepository.findAll();
		for(Module module :  modules) {
			ModulesFeatures modulesFeature = new ModulesFeatures();
			modulesFeature.setModule(module);
			List<ModuleFeature> features = moduleFeatureRepository.findByModuleId(module.getId());
			modulesFeature.setFeatures(features);
			modulesFeatures.add(modulesFeature);
		}
		return modulesFeatures;
	}

	@Override
	public List<ModulesFeatures> getModulesFeatures(Integer roleId) {
		List<ModulesFeatures> modulesFeatures = new ArrayList<>();
		List<Module> modules = new ArrayList<Module>();
		modules = moduleRepository.getModuleByRole(roleId);
		for(Module module :  modules) {
			ModulesFeatures modulesFeature = new ModulesFeatures();
			modulesFeature.setModule(module);
			List<ModuleFeature> features = moduleFeatureRepository.findByModuleId(module.getId());
			modulesFeature.setFeatures(features);
			modulesFeatures.add(modulesFeature);
		}
		return modulesFeatures;
	}

	@Override
	public ModuleFeature getFeatureId(Integer fid) {
		return moduleFeatureRepository.findByFeatureId(fid);
	}
	@Override
	public List<ModulesFeatures> getFeatures(Integer rid,Integer mid) {
		List<ModulesFeatures> modulesFeatures = new ArrayList<>();
		Module module = moduleService.getByModuleId(mid);
		List<ModuleFeature> features = moduleFeatureRepository.findByModuleId(module.getId());
		ModulesFeatures modulesFeature = new ModulesFeatures();
		modulesFeature.setModule(module);
		modulesFeature.setFeatures(features);
		modulesFeatures.add(modulesFeature);
		return modulesFeatures;
	}
	@Override
	public List<ModulesFeatures> getModulesFeaturesList(Integer roleId) {
		List<ModulesFeatures> modulesFeatures = new ArrayList<>();
		List<Module> modules = new ArrayList<Module>();
		modules = moduleRepository.getModuleByRoleId(roleId);
		for(Module module :  modules) {
			ModulesFeatures modulesFeature = new ModulesFeatures();
			modulesFeature.setModule(module);
			List<ModuleFeature> features = moduleFeatureRepository.findByModuleId(module.getId());
			modulesFeature.setFeatures(features);
			modulesFeatures.add(modulesFeature);
		}
		return modulesFeatures;
	}
	@Override
	public List<ModulesFeatures> getModulesFeaturesByRoleIdAndCompany(Integer roleId) {
		List<ModulesFeatures> modulesFeatures = new ArrayList<>();
		List<Module> modules = new ArrayList<Module>();
		modules = moduleRepository.getModuleByRoleAndCompany(roleId);
		for(Module module :  modules) {
			ModulesFeatures modulesFeature = new ModulesFeatures();
			modulesFeature.setModule(module);
			List<ModuleFeature> features = moduleFeatureRepository.findByModuleId(module.getId());
			modulesFeature.setFeatures(features);
			modulesFeatures.add(modulesFeature);
		}
		return modulesFeatures;
	}

}
