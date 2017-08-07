/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.UserDeviceSyncInfoDAO;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserDeviceSyncInfo;
import com.ceainfotech.ndcmp.service.UserDeviceSyncInfoService;

/**
 * @author Leo
 *
 */
@Service("userDeviceSyncInfoService")
@Transactional
public class UserDeviceSyncInfoServiceImpl implements UserDeviceSyncInfoService {

	@Autowired
	UserDeviceSyncInfoDAO userDeviceSyncInfoDAO;
	
	@Override
	public UserDeviceSyncInfo findByDeviceIdAndUser(String deviceId, User user) {
		return userDeviceSyncInfoDAO.findByDeviceIdAndUser(deviceId, user);
	}

	
	@Override
	public void saveUserDeviceSyncInfo(UserDeviceSyncInfo userDeviceSyncInfo) {
		userDeviceSyncInfoDAO.saveUserDeviceSyncInfo(userDeviceSyncInfo);
	}


	@Override
	public UserDeviceSyncInfo findByDeviceIdAndUserAndTempSyncRequestedTime(
			String deviceId, User user, String tempSyncRequestedTime) {
		return userDeviceSyncInfoDAO.findByDeviceIdAndUserAndTempSyncRequestedTime(deviceId, user, tempSyncRequestedTime);
	}

}
