package com.ceainfotech.ndcmp.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import com.ceainfotech.ndcmp.util.Status;

@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "USERNAME", length = 25, nullable = false, unique = true)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "MOBILEPASSWORD", nullable = false)
	private String mobilePassword;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PHONE", nullable = false)
	@Pattern(regexp = "(^\\+?\\(?[0-9\\-?\\)]*$)", message = "Please enter a valid phone number")
	private String phone;

	@Column(name = "ADDRESS1", nullable = false)
	private String address1;

	@Column(name = "ADDRESS2", nullable = false)
	private String address2;

	@Column(name = "ADDRESS3", nullable = false)
	private String address3;
	
	@Column(name = "USER_TYPE")
	private String userType;

	@OneToOne
	@JoinColumn(name = "COUNTRY" , referencedColumnName = "id" )
	private Countries country;

	@OneToOne
	@JoinColumn(name = "STATE" , referencedColumnName = "id" )
	private States state;

	@Column(name = "ZIPCODE", nullable = false)
	private String zipcode;

	@Column(name = "STATUS", nullable = false)
	private String status = Status.ACTIVE.getStatus();

	private transient List<Integer> roleIds;
	
	private transient Integer roleId;
	
	private transient UserRole userRole;
	
	private transient List<Integer> userIds;
	
	private transient String userColorCode;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_user_mapping", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_GROUP_ID") })
	private List<UserRole> userRoles;
	
	@Column(name = "AGENCY")
	private Integer agencyId;
	
//	@OneToOne(mappedBy = "agencyAuthority")
//	private Agency agency;
	
	private transient String agencyName;
	
	@Column(name = "USER_STATUS")
	private boolean userStatus;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the userStatus
	 */
	public boolean isUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getMobilePassword() {
		return mobilePassword;
	}

	public void setMobilePassword(String mobilePassword) {
		this.mobilePassword = mobilePassword;
	}

	/**
	 * @return the userIds
	 */
	public List<Integer> getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds the userIds to set
	 */
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}

	/**
	 * @return the agencyId
	 */
	public Integer getAgencyId() {
		return agencyId;
	}

	/**
	 * @param agencyId the agencyId to set
	 */
	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public String getUserColorCode() {
		return userColorCode;
	}

	public void setUserColorCode(String userColorCode) {
		this.userColorCode = userColorCode;
	}
	
	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result
				+ ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result
				+ ((address3 == null) ? 0 : address3.hashCode());
		result = prime * result
				+ ((agencyId == null) ? 0 : agencyId.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((mobilePassword == null) ? 0 : mobilePassword.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((userRoles == null) ? 0 : userRoles.hashCode());
		result = prime * result + (userStatus ? 1231 : 1237);
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address1 == null) {
			if (other.address1 != null)
				return false;
		} else if (!address1.equals(other.address1))
			return false;
		if (address2 == null) {
			if (other.address2 != null)
				return false;
		} else if (!address2.equals(other.address2))
			return false;
		if (address3 == null) {
			if (other.address3 != null)
				return false;
		} else if (!address3.equals(other.address3))
			return false;
		if (agencyId == null) {
			if (other.agencyId != null)
				return false;
		} else if (!agencyId.equals(other.agencyId))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobilePassword == null) {
			if (other.mobilePassword != null)
				return false;
		} else if (!mobilePassword.equals(other.mobilePassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		if (userStatus != other.userStatus)
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password
				+ ", mobilePassword=" + mobilePassword + ", email=" + email
				+ ", phone=" + phone + ", address1=" + address1 + ", address2="
				+ address2 + ", address3=" + address3 + ", userType="
				+ userType + ", country=" + country + ", state=" + state
				+ ", zipcode=" + zipcode + ", status=" + status
				+ ", userRoles=" + userRoles + ", agencyId=" + agencyId
				+ ", userStatus=" + userStatus + "]";
	}
}
