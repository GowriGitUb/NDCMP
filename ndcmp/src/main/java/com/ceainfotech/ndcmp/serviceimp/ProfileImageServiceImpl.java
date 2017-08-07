/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ceainfotech.ndcmp.dao.ProfileImageDAO;
import com.ceainfotech.ndcmp.model.ProfileImage;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.ProfileImageService;

/**
 * @author bosco
 *
 */

@Service("profileImageService")
@Transactional
public class ProfileImageServiceImpl implements ProfileImageService {

	@Autowired
	private ProfileImageDAO profileImageDAO;
	
	@Override
	public void saveProfile(ProfileImage profileImage) {
		profileImageDAO.saveImage(profileImage);
	}


	@Override
	public ProfileImage findByUser(User user) {
		return profileImageDAO.getProfileByUser(user);
	}

}
