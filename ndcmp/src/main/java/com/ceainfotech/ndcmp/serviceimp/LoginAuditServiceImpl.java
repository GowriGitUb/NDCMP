package com.ceainfotech.ndcmp.serviceimp;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.LoginAuditDAO;
import com.ceainfotech.ndcmp.model.LoginAudit;
import com.ceainfotech.ndcmp.service.LoginAuditService;

/**
 * 
 * @author Samy
 * 
 */

@Service("loginAuditService")
public class LoginAuditServiceImpl implements LoginAuditService {

	@Autowired
	private LoginAuditDAO loginAuditDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuditServiceImpl.class);

	@Override
	public List<LoginAudit> getLoginAuditByFromDateAndToDate(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return loginAuditDAO.getLoginAuditByFromDateAndToDate(fromDate, toDate);
	}

	
}
