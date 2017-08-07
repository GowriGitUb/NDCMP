/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.service.DateTimeService;

/**
 * @author Thomas
 * 
 */
@Service
public class CurrentDateTimeService implements DateTimeService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.booking.genygolf.service.DateTimeService#getCurrentDateAndTime()
	 */
	@Override
	public Date getCurrentDateAndTime() {
		return new Date();
	}

}
