/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * @author bosco
 *
 */
@Entity
@Table(name="tbl_profile_img")
public class ProfileImage  extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name="USER_ID",referencedColumnName="id",unique=true)
	private User user;
	
	@Column(name = "PROFILE_IMAGE")
	@Type(type = "text")
	private String profileimg;

	/**
	 * @return the profileimg
	 */
	public String getProfileimg() {
		return profileimg;
	}

	/**
	 * @param profileimg the profileimg to set
	 */
	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
