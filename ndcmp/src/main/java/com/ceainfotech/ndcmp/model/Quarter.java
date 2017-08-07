/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gowri
 *
 */
@Entity
@Table(name = "tbl_quarter")
public class Quarter extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ORDER_NUMBER")
	private Integer orderNumber;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the orderNumber
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Quarter [name=" + name + ", status=" + status
				+ ", orderNumber=" + orderNumber + "]";
	}
}
