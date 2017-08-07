/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.Date;
import java.util.List;

import com.ceainfotech.ndcmp.model.LoginAudit;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author Mani
 * 
 */
public interface LoginAuditService {

	public List<LoginAudit> getLoginAuditByFromDateAndToDate(String fromDate,String toDate);
}
