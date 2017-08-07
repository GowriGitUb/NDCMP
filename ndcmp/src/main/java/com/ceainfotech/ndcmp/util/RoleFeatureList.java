/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeeva
 *
 */
public class RoleFeatureList {
	
	private Integer roleId;
	
	private List<ModuleFeatureObject> featureList = new ArrayList<ModuleFeatureObject>();
	
	public RoleFeatureList() {
		// TODO Auto-generated constructor stub
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<ModuleFeatureObject> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(List<ModuleFeatureObject> featureList) {
		this.featureList = featureList;
	}
}
