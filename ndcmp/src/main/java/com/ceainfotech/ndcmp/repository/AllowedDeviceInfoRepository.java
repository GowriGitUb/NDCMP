/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ceainfotech.ndcmp.model.AllowedDeviceInfo;

/**
 * @author Gowri
 */
public interface AllowedDeviceInfoRepository extends JpaRepository<AllowedDeviceInfo, Integer> {

	@Query(value = "SELECT COUNT(a.id) FROM AllowedDeviceInfo a WHERE a.deviceStatus = :deviceStatus AND a.deleteStatus = false")
	public int getActiveMobileDeviceCount(@Param("deviceStatus") boolean deviceStatus);

	public AllowedDeviceInfo findByDeviceIdAndStaffIdAndDeleteStatus(String deviceId, String staffId, boolean deleteSatus);
	
	public AllowedDeviceInfo findByDeviceIdAndDeleteStatus(String deviceId, boolean deleteSatus);
	
	public AllowedDeviceInfo findByStaffIdAndDeleteStatus(String staffId, boolean deleteSatus);
	
	public AllowedDeviceInfo findByDeviceIdAndTempSyncRequestedTimeAndDeleteStatus(String deviceId, String tempSyncRequestedTime, boolean deleteSatus);
	
	/**
	 * @author mani
	 */
	public List<AllowedDeviceInfo> findByDeleteStatusOrderByAgencyCodesAsc(boolean deleteStatus);
	
	public List<AllowedDeviceInfo> findByDeleteStatus(boolean deleteStatus);
	
	public List<AllowedDeviceInfo> findByDeviceStatusAndDeleteStatus(boolean deviceStatus, boolean deleteStatus);

}
