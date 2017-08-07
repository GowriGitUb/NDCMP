/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.ArrayList;
import java.util.List;

import com.ceainfotech.ndcmp.model.Feature;
import com.ceainfotech.ndcmp.model.Module;
import com.ceainfotech.ndcmp.model.ModuleFeature;

/**
 * @author jeeva
 *
 */
public class ModulesFeatures {
	
	private Module module;
	private Feature feature;
	private List<ModuleFeature> features = new ArrayList<>();
	
	public ModulesFeatures() {
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public List<ModuleFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<ModuleFeature> features) {
		this.features = features;
	}

}
