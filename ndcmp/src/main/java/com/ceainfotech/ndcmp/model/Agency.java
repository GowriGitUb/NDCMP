/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Gowri
 * 
 */
@Entity
@Table(name = "tbl_agency")
public class Agency extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "NAME", nullable = false)
	private String name;

	/*@Column(name = "AGENCY_TYPE", nullable = false)
	private String agencyType;*/

	@ManyToOne
	@JoinColumn(name = "COUNTRY")
	private Countries country;

	@ManyToOne
	@JoinColumn(name = "STATE")
	private States state;

	@ManyToOne
	@JoinColumn(name = "REGION")
	private Region region;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_agency_mapping", joinColumns = { @JoinColumn(name = "AGENCY_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private List<User> agencyAuthority = new ArrayList<User>();
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "AGENCY_STATUS")
	private Boolean agencystatus;
	
	@Column(name = "LEAD_AGENCY", columnDefinition="tinyint(1) default 0")
	private boolean leadAgency = false;
	
	@Column(name = "PARTNER_AGENCY", columnDefinition="tinyint(1) default 0")
	private boolean partnerAgency = false;
	
	@Transient
	private Integer userAgencyId;
	
	@Transient
	private Integer userRoleId;

	@Transient
	private Integer countryId;

	@Transient
	private Integer stateId;

	@Transient
	private Integer regionId;
	
	private transient List<Integer> roleIds;

	public Agency() {
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

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
	 * @return the country
	 */
	public Countries getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Countries country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public States getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(States state) {
		this.state = state;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the agencyAuthority
	 */
	public List<User> getAgencyAuthority() {
		return agencyAuthority;
	}

	/**
	 * @param agencyAuthority the agencyAuthority to set
	 */
	public void setAgencyAuthority(List<User> agencyAuthority) {
		this.agencyAuthority = agencyAuthority;
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
	 * @return the agencystatus
	 */
	public Boolean getAgencystatus() {
		return agencystatus;
	}

	/**
	 * @param agencystatus the agencystatus to set
	 */
	public void setAgencystatus(Boolean agencystatus) {
		this.agencystatus = agencystatus;
	}

	/**
	 * @return the userRoleId
	 */
	public Integer getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the stateId
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the regionId
	 */
	public Integer getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return the roleIds
	 */
	public List<Integer> getRoleIds() {
		return roleIds;
	}

	/**
	 * @param roleIds the roleIds to set
	 */
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * @return the leadAgency
	 */
	public boolean isLeadAgency() {
		return leadAgency;
	}

	/**
	 * @param leadAgency the leadAgency to set
	 */
	public void setLeadAgency(boolean leadAgency) {
		this.leadAgency = leadAgency;
	}

	/**
	 * @return the partnerAgency
	 */
	public boolean isPartnerAgency() {
		return partnerAgency;
	}

	/**
	 * @param partnerAgency the partnerAgency to set
	 */
	public void setPartnerAgency(boolean partnerAgency) {
		this.partnerAgency = partnerAgency;
	}
	
	/**
	 * @return the userAgencyId
	 */
	public Integer getUserAgencyId() {
		return userAgencyId;
	}

	/**
	 * @param userAgencyId the userAgencyId to set
	 */
	public void setUserAgencyId(Integer userAgencyId) {
		this.userAgencyId = userAgencyId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Agency [code=" + code + ", name=" + name + ", country=" + country + ", state=" + state + ", region=" + region + ", agencyAuthority="
				+ agencyAuthority + ", status=" + status + ", agencystatus=" + agencystatus + ", leadAgency=" + leadAgency + ", partnerAgency="
				+ partnerAgency + ", userAgencyId=" + userAgencyId + ", userRoleId=" + userRoleId + ", countryId=" + countryId + ", stateId="
				+ stateId + ", regionId=" + regionId + "]";
	}

}
