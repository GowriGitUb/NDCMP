/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ceainfotech.ndcmp.model.BaseEntity;

/**
 * @author jeeva
 *
 */
@Entity
@Table(name = "tbl_modulefutures")
public class ModuleFeature extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer moduleId;
	
	private Integer featureId;
	
	private String moduleName;
	
	private String featureName;
	
	public ModuleFeature() {
		// TODO Auto-generated constructor stub
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

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	@Override
	public String toString() {
		return "ModuleFeature [moduleId=" + moduleId + ", featureId="
				+ featureId + ", moduleName=" + moduleName + ", featureName="
				+ featureName + "]";
	}

}
