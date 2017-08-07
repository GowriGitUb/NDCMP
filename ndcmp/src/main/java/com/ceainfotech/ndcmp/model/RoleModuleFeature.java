/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ceainfotech.ndcmp.model.BaseEntity;

/**
 * @author jeeva
 *
 */
@Entity
@Table(name = "tbl_rolefuturemodule")
public class RoleModuleFeature extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "module_id")
	private Integer moduleId;
	
	@Column(name = "feature_id")
	private Integer featureId;
	
	public RoleModuleFeature() {
		// TODO Auto-generated constructor stub
	}


	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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


	@Override
	public String toString() {
		return "RoleModuleFeature [roleId=" + roleId + ", moduleId=" + moduleId
				+ ", featureId=" + featureId + "]";
	}
	
}
