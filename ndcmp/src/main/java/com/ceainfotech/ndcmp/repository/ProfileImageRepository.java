package com.ceainfotech.ndcmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ceainfotech.ndcmp.model.ProfileImage;
import com.ceainfotech.ndcmp.model.User;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
	
	public ProfileImage findByUser(User user);
	
}
