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
@Table(name = "tbl_module")
public class Module extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "module_name")
	private String module;
	
	@Column(name = "order_id")
	private int orderId;
	
	public Module() {
		// TODO Auto-generated constructor stub
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "Module [module=" + module + ", orderId=" + orderId + "]";
	}
}
