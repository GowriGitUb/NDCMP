/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserDeviceSyncInfo;

/**
 * @author Leo
 *
 */
public interface UserDeviceSyncInfoRepository extends JpaRepository<UserDeviceSyncInfo, Integer> {
	
	public UserDeviceSyncInfo findByDeviceIdAndUser(String deviceId, User user);
	
	public UserDeviceSyncInfo findByDeviceIdAndUserAndTempSyncRequestedTime(String deviceId, User user, String tempSyncRequestedTime);

}
