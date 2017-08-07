/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.Calendar;

import org.springframework.data.auditing.DateTimeProvider;

import com.ceainfotech.ndcmp.service.DateTimeService;

/**
 * @author Thomas
 * 
 */
public class AuditingDetailsProvider implements DateTimeProvider {

	private final DateTimeService dateTimeService;

	/**
	 * 
	 */
	public AuditingDetailsProvider(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.auditing.DateTimeProvider#getNow()
	 */
	@Override
	public Calendar getNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTimeService.getCurrentDateAndTime());
		return calendar;
	}

}
