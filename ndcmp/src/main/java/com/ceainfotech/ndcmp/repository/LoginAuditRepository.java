package com.ceainfotech.ndcmp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.LoginAudit;

public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {

	@Query(value="SELECT la FROM LoginAudit la WHERE STR_TO_DATE(la.loginDate,'%d-%m-%Y') BETWEEN :fromDate and :toDate")
	public List<LoginAudit> getLoginAuditByFromDateAndToDate(@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
}
