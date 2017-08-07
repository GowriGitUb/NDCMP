/**
 * 
 */
package com.ceainfotech.ndcmp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Leo
 *
 */
@Entity
@Table(name = "tbl_user_device_sync_info")
public class UserDeviceSyncInfo extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	@OneToOne
	@JoinColumn(name="USER")
	private User user;
	
	@Column(name = "LASTSYNCEDTIME")
	private String lastSyncedTime;
	
	@Column(name = "TEMPSYNCREQUESTEDTIME")
	private String tempSyncRequestedTime;

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the lastSyncedTime
	 */
	public String getLastSyncedTime() {
		return lastSyncedTime;
	}

	/**
	 * @param lastSyncedTime the lastSyncedTime to set
	 */
	public void setLastSyncedTime(String lastSyncedTime) {
		this.lastSyncedTime = lastSyncedTime;
	}

	/**
	 * @return the tempSyncRequestedTime
	 */
	public String getTempSyncRequestedTime() {
		return tempSyncRequestedTime;
	}

	/**
	 * @param tempSyncRequestedTime the tempSyncRequestedTime to set
	 */
	public void setTempSyncRequestedTime(String tempSyncRequestedTime) {
		this.tempSyncRequestedTime = tempSyncRequestedTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDeviceSyncInfo [deviceId=" + deviceId + ", user=" + user
				+ ", lastSyncedTime=" + lastSyncedTime
				+ ", tempSyncRequestedTime=" + tempSyncRequestedTime + "]";
	}
}
