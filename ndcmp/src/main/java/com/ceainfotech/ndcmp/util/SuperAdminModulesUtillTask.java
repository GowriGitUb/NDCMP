/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.model.Feature;
import com.ceainfotech.ndcmp.model.Module;
import com.ceainfotech.ndcmp.model.RoleModuleFeature;
import com.ceainfotech.ndcmp.service.FeatureService;
import com.ceainfotech.ndcmp.service.ModuleFeatureService;
import com.ceainfotech.ndcmp.service.ModuleService;
import com.ceainfotech.ndcmp.service.RoleModuleFeatureService;

/**
 * @author jeeva
 *
 */
@Service
public class SuperAdminModulesUtillTask {
	
	@Autowired
	private FeatureService featureService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private RoleModuleFeatureService roleModuleFeatureService;
	@Autowired
	private ModuleFeatureService moduleFeatureService;
	
	public void save(ModuleFeatures moduleFeatures) {
		Module module = moduleService.getByModule(moduleFeatures.getModuleName());
		Feature feature = featureService.findByFeature(moduleFeatures.getFeatureName());
		if(module != null && feature != null) {
			Integer roleId = new Integer(1);
			RoleModuleFeature roleModuleFeature = roleModuleFeatureService.findByRoleIdModuleIdFeatureId(roleId, module.getId(), feature.getId());
			if(roleModuleFeature == null || roleModuleFeature.getId() == null){
				RoleModuleFeature roleModuleFeature1 = new RoleModuleFeature();
				roleModuleFeature1.setFeatureId(feature.getId());
				roleModuleFeature1.setModuleId(module.getId());
				roleModuleFeature1.setRoleId(roleId);
				roleModuleFeatureService.addRolemoduleFeature(roleModuleFeature1);
			}
		}
		
	}
	
	public void saveAll() {
		for(ModuleFeatures moduleFeatures : ModuleFeatures.values()) {
			save(moduleFeatures);
		}
	}

}
