/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.AllowedDeviceInfo;

/**
 * @author Gowri
 * 
 */

public interface AllowedDeviceService {

	public List<AllowedDeviceInfo> listAllAllowedDevice();

	public void deviceRegisteration(AllowedDeviceInfo allowedDeviceInfo);

	public AllowedDeviceInfo findByDeviceIdAndStaffId(String deviceId, String staffId);
	
	public AllowedDeviceInfo findByDeviceId(String deviceId);
	
	public AllowedDeviceInfo findByStaffId(String staffId);

	public AllowedDeviceInfo getById(Integer id);

	public void deleteById(Integer id);

	public int getActiveMobileDeiveCount(boolean deviceStatus);
	
	public List<AllowedDeviceInfo> findByDeviceStatus(boolean deviceStatus);
	
	/**
	 * @author pushpa
	 * @param deviceId
	 * @param tempSyncRequestedTime
	 * @return
	 */
	public AllowedDeviceInfo findByDeviceIdAndTempSyncRequestedTime(String deviceId, String tempSyncRequestedTime);
}
