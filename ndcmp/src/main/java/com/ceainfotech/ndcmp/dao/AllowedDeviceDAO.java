/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.AllowedDeviceInfo;
import com.ceainfotech.ndcmp.repository.AllowedDeviceInfoRepository;

/**
 * @author Gowri
 * 
 */
@Repository
public class AllowedDeviceDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AllowedDeviceDAO.class);

	@Autowired
	AllowedDeviceInfoRepository repository;

	public List<AllowedDeviceInfo> listAllAllowedDevice() {
		LOGGER.info("List all the Allowed Devices .. {}");
		//return repository.findAll();
		return repository.findByDeleteStatusOrderByAgencyCodesAsc(false);
	}

	@Transactional(readOnly = true)
	public AllowedDeviceInfo findByDeviceIdAndStaffId(String deviceId, String staffId) {
		LOGGER.info("Find the allowed device by id .. {}", deviceId);
		return repository.findByDeviceIdAndStaffIdAndDeleteStatus(deviceId, staffId, false);
	}
	
	@Transactional(readOnly = true)
	public AllowedDeviceInfo findByDeviceId(String deviceId) {
		return repository.findByDeviceIdAndDeleteStatus(deviceId, false);
	}
	
	@Transactional(readOnly = true)
	public AllowedDeviceInfo findByStaffId(String staffId) {
		return repository.findByStaffIdAndDeleteStatus(staffId, false);
	}

	public AllowedDeviceInfo getById(Integer id) {
		LOGGER.info("Find the allowed device by id .. {}", id);
		return repository.findOne(id);
	}

	public int getActiveMobileDeiveCount(boolean deviceStatus) {
		LOGGER.info("get total Count of Allowed devices .. {}");
		return repository.getActiveMobileDeviceCount(deviceStatus);
	}
	
	public void deviceRegisteration(AllowedDeviceInfo allowedDeviceInfo) {
		LOGGER.info("Delete the allowed Devices .. {}", allowedDeviceInfo);
		repository.saveAndFlush(allowedDeviceInfo);
	}
	
	public void deleteById(Integer id) {
		LOGGER.info("Delete the allowed Devices by id .. {}", id);
		repository.delete(id);
	}
	
	public List<AllowedDeviceInfo> findByDeviceStatus(boolean deviceStatus) {
		return repository.findByDeviceStatusAndDeleteStatus(deviceStatus, false);
	}
	
	/**
	 * @author pushpa
	 * Find device by device Id and requested sync time
	 * @param deviceId
	 * @return
	 */
	@Transactional(readOnly = true)
	public AllowedDeviceInfo findByDeviceIdAndTempSyncRequestedTime(String deviceId, String tempSyncRequestedTime) {
		LOGGER.debug("Find the allowed device by device id .. {}", deviceId + "requested sync time .. " + tempSyncRequestedTime);
		return repository.findByDeviceIdAndTempSyncRequestedTimeAndDeleteStatus(deviceId, tempSyncRequestedTime, false);
	}

}
