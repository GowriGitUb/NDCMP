package com.ceainfotech.ndcmp.serviceimp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.AuditDao;
import com.ceainfotech.ndcmp.model.Auditing;
import com.ceainfotech.ndcmp.service.AuditService;


/**
 * 
 * @author albeena
 *
 */

@Service("auditService")
@Transactional
public class AuditServiceImpl implements AuditService {

	@Autowired
	AuditDao auditDao;
	
	public void save(Auditing audit) {
		// TODO Auto-generated method stub
		auditDao.save(audit);
	}

	

}
