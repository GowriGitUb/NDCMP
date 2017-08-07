/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import com.ceainfotech.ndcmp.model.ProfileImage;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author bosco
 *
 */
public interface ProfileImageService {
	
	public void saveProfile(ProfileImage profileImage);
	
	/**
	 * @author pushpa
	 * @param user
	 * @return
	 */
	public ProfileImage findByUser(User user);

}
