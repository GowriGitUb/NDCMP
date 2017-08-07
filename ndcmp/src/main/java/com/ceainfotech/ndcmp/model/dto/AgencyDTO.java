/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;

import java.util.List;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;

/**
 * @author Samy
 * 
 */
public class AgencyDTO {

	private Integer id;
	private String agency;
	private String type;
	private String status;
	//Added By Jeeva
	private List<UserRole> userRoleNames;
	private List<User> userNames;
	private List<Countries> countrieNames;
	private List<States> stateNames;
	private List<Region> regionNamess;
	private Agency agency2;
	private List<Agency> partnerAgency;

	private Integer userId;
	private String userName;
	private String colorCode;
	
	private String statusIcon;
	/**
	 * 
	 */
	public AgencyDTO() {
	}

	/**
	 * @param id
	 * @param agency
	 * @param type
	 * @param status
	 */
	public AgencyDTO(Integer id, String agency, String type, String status) {
		this.id = id;
		this.agency = agency;
		this.type = type;
		this.status = status;
	}
	

	public AgencyDTO(List<UserRole> userRoleNames, List<User> userNames,
			List<Countries> countrieNames, List<States> stateNames,
			List<Region> regionNamess,List<Agency> partnerAgency) {
		super();
		this.userRoleNames = userRoleNames;
		this.userNames = userNames;
		this.countrieNames = countrieNames;
		this.stateNames = stateNames;
		this.regionNamess = regionNamess;
		this.partnerAgency = partnerAgency;
	}

	public AgencyDTO(List<User> userNames, List<Countries> countrieNames,
			List<States> stateNames, List<Region> regionNamess, Agency agency2) {
		super();
		this.userNames = userNames;
		this.countrieNames = countrieNames;
		this.stateNames = stateNames;
		this.regionNamess = regionNamess;
		this.agency2 = agency2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatusIcon() {
		return statusIcon;
	}

	public void setStatusIcon(String statusIcon) {
		this.statusIcon = statusIcon;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserRole> getUserRoleNames() {
		return userRoleNames;
	}

	public void setUserRoleNames(List<UserRole> userRoleNames) {
		this.userRoleNames = userRoleNames;
	}

	public List<User> getUserNames() {
		return userNames;
	}

	public void setUserNames(List<User> userNames) {
		this.userNames = userNames;
	}

	public List<Countries> getCountrieNames() {
		return countrieNames;
	}

	public void setCountrieNames(List<Countries> countrieNames) {
		this.countrieNames = countrieNames;
	}

	public List<States> getStateNames() {
		return stateNames;
	}

	public void setStateNames(List<States> stateNames) {
		this.stateNames = stateNames;
	}

	public List<Region> getRegionNamess() {
		return regionNamess;
	}

	public void setRegionNamess(List<Region> regionNamess) {
		this.regionNamess = regionNamess;
	}

	/**
	 * @return the agency2
	 */
	public Agency getAgency2() {
		return agency2;
	}

	/**
	 * @param agency2 the agency2 to set
	 */
	public void setAgency2(Agency agency2) {
		this.agency2 = agency2;
	}

	/**
	 * @return the partnerAgency
	 */
	public List<Agency> getPartnerAgency() {
		return partnerAgency;
	}

	/**
	 * @param partnerAgency the partnerAgency to set
	 */
	public void setPartnerAgency(List<Agency> partnerAgency) {
		this.partnerAgency = partnerAgency;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the colorCode
	 */
	public String getColorCode() {
		return colorCode;
	}

	/**
	 * @param colorCode the colorCode to set
	 */
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	

}
