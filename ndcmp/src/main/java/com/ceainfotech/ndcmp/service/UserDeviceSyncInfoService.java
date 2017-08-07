/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserDeviceSyncInfo;

/**
 * @author Leo
 *
 */
public interface UserDeviceSyncInfoService {
	public UserDeviceSyncInfo findByDeviceIdAndUser(String deviceId, User user);
	
	public UserDeviceSyncInfo findByDeviceIdAndUserAndTempSyncRequestedTime(String deviceId, User user, String tempSyncRequestedTime);
	
	public void saveUserDeviceSyncInfo(UserDeviceSyncInfo userDeviceSyncInfo);
}
