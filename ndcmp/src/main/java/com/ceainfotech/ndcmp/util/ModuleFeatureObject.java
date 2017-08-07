/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.ArrayList;
import java.util.List;

import com.ceainfotech.ndcmp.model.Feature;

/**
 * @author jeeva
 *
 */
public class ModuleFeatureObject {
	
	private Integer moduleId;
	
	private Integer featureId;

	private List<Feature> features = new ArrayList<Feature>();
	
	public ModuleFeatureObject() {
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

}
