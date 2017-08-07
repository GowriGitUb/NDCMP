/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.dao.StatusTrackingLogDao;
import com.ceainfotech.ndcmp.model.StatusTrackingLog;
import com.ceainfotech.ndcmp.service.StatusTrackingLogService;

/**
 * @author pushpa
 */

@Service("statusTrackingLogService")
@Transactional
public class StatusTrackingLogServiceImpl implements StatusTrackingLogService{

	@Autowired
	private StatusTrackingLogDao statusTrackingLogDao;
	
	@Override
	public void createStatusTrackingLog(StatusTrackingLog statusTrackingLog) {
		statusTrackingLogDao.createStatusTrackingLog(statusTrackingLog);
	}

}
