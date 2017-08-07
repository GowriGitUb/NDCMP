/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ceainfotech.ndcmp.model.StatusTrackingLog;
import com.ceainfotech.ndcmp.repository.StatusTrackingLogRepository;

/**
 * @author pushpa
 *
 */
@Repository
public class StatusTrackingLogDao {
	
private static final Logger LOGGER = LoggerFactory.getLogger(StatusTrackingLogDao.class);
	
	@Autowired
	StatusTrackingLogRepository statusTrackingLogRepository;
	
	public void createStatusTrackingLog(StatusTrackingLog statusTrackingLog){
		LOGGER.debug("Creating new reviewer Tracking log record");
		statusTrackingLogRepository.saveAndFlush(statusTrackingLog);
	}

}
