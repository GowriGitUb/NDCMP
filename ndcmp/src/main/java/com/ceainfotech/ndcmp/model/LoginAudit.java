package com.ceainfotech.ndcmp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Mani
 * 
 */
@Entity
@Table(name = "tbl_login_auditing")
public class LoginAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOGIN_AUDITING_ID")
	private Long loginAuditId;
	
	@Column(name = "LOGGED_IN_USER")
	private String loginUser;
	
	@Column(name = "USER_ROLE")
	private String userRole;
	
	
	@Column(name="AGENCY_CODE")
	private String agencyCode;

	@Column(name = "HOST", nullable = false)
	private String host;

	@Column(name = "ACCESSED_FROM", nullable = false)
	private String accessedFrom;

	
	@Column(name = "LOGIN_DATE",updatable=false, nullable = false)
	private String loginDate;

	public Long getLoginAuditId() {
		return loginAuditId;
	}

	public void setLoginAuditId(Long loginAuditId) {
		this.loginAuditId = loginAuditId;
	}

	

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getAccessedFrom() {
		return accessedFrom;
	}

	public void setAccessedFrom(String accessedFrom) {
		this.accessedFrom = accessedFrom;
	}
	
}
