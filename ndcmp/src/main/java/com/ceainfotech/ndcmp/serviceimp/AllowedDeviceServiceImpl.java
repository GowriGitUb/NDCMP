/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.AllowedDeviceDAO;
import com.ceainfotech.ndcmp.model.AllowedDeviceInfo;
import com.ceainfotech.ndcmp.service.AllowedDeviceService;

/**
 * @author Gowri
 * 
 */
@Service("allowedDeviceService")
@Transactional
public class AllowedDeviceServiceImpl implements AllowedDeviceService {

	@Autowired
	AllowedDeviceDAO deviceDAO;
	

	@Override
	public List<AllowedDeviceInfo> listAllAllowedDevice() {
		return deviceDAO.listAllAllowedDevice();
	}

	@Override
	public void deviceRegisteration(AllowedDeviceInfo allowedDeviceInfo) {
		deviceDAO.deviceRegisteration(allowedDeviceInfo);
	}

	@Override
	public AllowedDeviceInfo findByDeviceIdAndStaffId(String deviceId, String staffId) {
		return deviceDAO.findByDeviceIdAndStaffId(deviceId, staffId);
	}

	@Override
	public AllowedDeviceInfo getById(Integer id) {
		return deviceDAO.getById(id);
	}

	@Override
	public void deleteById(Integer id) {
		deviceDAO.deleteById(id);
	}

	@Override
	public int getActiveMobileDeiveCount(boolean deviceStatus) {
		return deviceDAO.getActiveMobileDeiveCount(deviceStatus);
	}

	@Override
	public AllowedDeviceInfo findByDeviceIdAndTempSyncRequestedTime(
			String deviceId, String tempSyncRequestedTime) {
		return deviceDAO.findByDeviceIdAndTempSyncRequestedTime(deviceId, tempSyncRequestedTime);
	}

	@Override
	public AllowedDeviceInfo findByDeviceId(String deviceId) {
		return deviceDAO.findByDeviceId(deviceId);
	}

	@Override
	public AllowedDeviceInfo findByStaffId(String staffId) {
		return deviceDAO.findByStaffId(staffId);
	}

	@Override
	public List<AllowedDeviceInfo> findByDeviceStatus(boolean deviceStatus) {
		return deviceDAO.findByDeviceStatus(deviceStatus);
	}
}
