/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gowri
 * 
 */
@Entity
@Table(name = "tbl_auditing")
public class Auditing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUDITING_ID")
	private Long auditing_id;

	@Column(name = "USER", nullable = false)
	private String user;

	@Column(name = "MODULE_NAME", nullable = false)
	private String moduleName;

	@Column(name = "ACTION", nullable = false)
	private String action;

	@Column(name = "MODIFICATION_DATE", nullable = false)
	private Date modificationDate;

	@Column(name = "HOST", nullable = false)
	private String host;

	

	public Auditing() {
	}

	public Long getAuditing_id() {
		return auditing_id;
	}

	public void setAuditing_id(Long auditing_id) {
		this.auditing_id = auditing_id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Auditing [auditing_id=" + auditing_id + ", user=" + user
				+ ", moduleName=" + moduleName + ", action=" + action
				+ ", modificationDate=" + modificationDate + ", host=" + host
				+ "]";
	}

	

}
