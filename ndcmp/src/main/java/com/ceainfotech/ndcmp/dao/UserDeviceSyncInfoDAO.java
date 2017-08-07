/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserDeviceSyncInfo;
import com.ceainfotech.ndcmp.repository.UserDeviceSyncInfoRepository;

/**
 * @author Leo
 *
 */
@Repository
public class UserDeviceSyncInfoDAO {

	@Autowired
	UserDeviceSyncInfoRepository userDeviceSyncInfoRepository;
	
	public UserDeviceSyncInfo findByDeviceIdAndUser(String deviceId, User user) {
		return userDeviceSyncInfoRepository.findByDeviceIdAndUser(deviceId, user);
	}
	
	public void saveUserDeviceSyncInfo(UserDeviceSyncInfo userDeviceSyncInfo) {
		userDeviceSyncInfoRepository.saveAndFlush(userDeviceSyncInfo);
	}
	
	public UserDeviceSyncInfo findByDeviceIdAndUserAndTempSyncRequestedTime(
			String deviceId, User user, String tempSyncRequestedTime) {
		return userDeviceSyncInfoRepository.findByDeviceIdAndUserAndTempSyncRequestedTime(deviceId, user, tempSyncRequestedTime);
	}
}
