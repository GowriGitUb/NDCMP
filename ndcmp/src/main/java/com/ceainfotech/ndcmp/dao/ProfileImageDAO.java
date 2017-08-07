/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ceainfotech.ndcmp.model.ProfileImage;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.repository.ProfileImageRepository;

/**
 * @author bosco
 *
 */
@Repository
@Transactional
public class ProfileImageDAO {
	
	@Autowired
	ProfileImageRepository profileImageRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileImageDAO.class);

	/**
	 * 
	 */
	@Autowired
	public ProfileImageDAO(final ProfileImageRepository profileImageRepository) {
		this.profileImageRepository = profileImageRepository;
	}


	public void saveImage(ProfileImage proImage) {
		LOGGER.debug("Add new user profile image .. {}", proImage);
		if(proImage.getId() != 0){
			profileImageRepository.saveAndFlush(proImage);
		}else{
			profileImageRepository.save(proImage);	
		}
		
	}
	
	public ProfileImage getProfileByUser(User user){
		ProfileImage profileImage=new ProfileImage();
		if(user.getId() != 0){
			profileImage=profileImageRepository.findByUser(user);
		}
		return profileImage;
	}

}
