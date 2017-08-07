/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceainfotech.ndcmp.model.ReportingPeriod;
import com.ceainfotech.ndcmp.repository.ReportingPeriodRepository;
import com.ceainfotech.ndcmp.repository.UserRepository;

/**
 * @author bosco
 *
 */
@Component
public class ReportingPeriodUtil {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReportingPeriodRepository reportingPeriodRepository;
	
	public void saveReport(String year) {
		//ReportingPeriod reportingPeriod = reportingPeriodRepository.findByYear(year);
		/*List<ReportingPeriod> reportingPeriod = reportingPeriodRepository.findByYear(year);
		if(reportingPeriod.isEmpty()){
			ReportingPeriod reportingPeriods = new ReportingPeriod();
			reportingPeriods.setYear(year);
			reportingPeriodRepository.save(reportingPeriods);
		}
		*/
		/*if(reportingPeriod == null || reportingPeriod.getId() == null){
			reportingPeriod = new ReportingPeriod();
			reportingPeriod.setYear(year);
			reportingPeriodRepository.save(reportingPeriod);
		}*/
	}
}
