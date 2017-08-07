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
@Table(name = "tbl_feature")
public class Feature extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "feature_name")
	private String feature;
	
	public Feature() {
		// TODO Auto-generated constructor stub
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	@Override
	public String toString() {
		return "Feature [feature=" + feature + "]";
	}

}
