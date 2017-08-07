/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.model.Feature;
import com.ceainfotech.ndcmp.model.Module;
import com.ceainfotech.ndcmp.model.ModuleFeature;
import com.ceainfotech.ndcmp.service.FeatureService;
import com.ceainfotech.ndcmp.service.ModuleFeatureService;
import com.ceainfotech.ndcmp.service.ModuleService;

/**
 * @author jeeva
 *
 */
@Service
public class ModuleFeatureUtilsTask {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private FeatureService featureService;
	@Autowired
	private ModuleFeatureService moduleFeatureService;
	
	
	public void save(ModuleFeatures moduleFeatures) {
		Module module = moduleService.getByModule(moduleFeatures.getModuleName());
		Feature feature = featureService.findByFeature(moduleFeatures.getFeatureName());
		if(module != null && feature != null) {
			ModuleFeature moduleFeature = moduleFeatureService.getByModuleIdAndFeatureId(module.getId(),feature.getId());
			if(moduleFeature == null || moduleFeature.getId() == null) {
				moduleFeature = new ModuleFeature();
				moduleFeature.setModuleId(module.getId());
				moduleFeature.setFeatureId(feature.getId());
				moduleFeature.setModuleName(module.getModule());
				moduleFeature.setFeatureName(feature.getFeature());
				moduleFeatureService.save(moduleFeature);
			}
		}
		
	}
	
	public void saveAll() {
		for(ModuleFeatures moduleFeatures : ModuleFeatures.values()) {
			save(moduleFeatures);
		}
	}
}
