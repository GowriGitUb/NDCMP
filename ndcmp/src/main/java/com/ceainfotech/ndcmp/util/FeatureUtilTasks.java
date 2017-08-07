/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.model.Feature;
import com.ceainfotech.ndcmp.service.FeatureService;
import com.ceainfotech.ndcmp.service.ModuleService;

/**
 * @author jeeva
 *
 */
@Service
public class FeatureUtilTasks {
	
	@Autowired
	private FeatureService featureService;
	@Autowired
	private ModuleService moduleService;
	
	public void save(Features features) {
		Feature feature = featureService.findByFeature(features.getName());
		if(feature == null || feature.getId() == null) {
			feature = new Feature();
			feature.setFeature(features.getName());
			featureService.save(feature);
		}
		
	}
	
	public void saveAll() {
		for(Features features : Features.values()) {
			save(features);
		}
	}

}
