package com.ceainfotech.ndcmp.dao;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Auditing;
import com.ceainfotech.ndcmp.repository.AuditRepository;


@Repository("auditDao")
public class AuditDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuditDao.class);
	
	
	private final AuditRepository auditRepository;
	
	@Autowired
	public AuditDao(final AuditRepository auditRepository){
		this.auditRepository=auditRepository;
	}
	
	public void save(Auditing audit){
		try {
			LOGGER.debug("save Audit .. {}", audit);
			if(audit !=null){
				auditRepository.save(audit);
			}
		}
		catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		
	}
	
	
	
}
