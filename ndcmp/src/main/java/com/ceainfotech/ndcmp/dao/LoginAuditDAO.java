package com.ceainfotech.ndcmp.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.LoginAudit;
import com.ceainfotech.ndcmp.repository.LoginAuditRepository;

/**
 * 
 * @author Mani
 *
 */

@Repository
public class LoginAuditDAO {

	LoginAuditRepository loginAuditRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

	/**
	 * 
	 */
	@Autowired
	public LoginAuditDAO(final LoginAuditRepository loginAuditRepository){
		this.loginAuditRepository=loginAuditRepository;
	}
	
	
	@Transactional(readOnly = true)
	public List<LoginAudit> getLoginAuditByFromDateAndToDate(String fromDate,String toDate) {
		return loginAuditRepository.getLoginAuditByFromDateAndToDate(fromDate, toDate);
	}
	
}
