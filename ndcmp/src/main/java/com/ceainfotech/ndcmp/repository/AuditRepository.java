/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceainfotech.ndcmp.model.Auditing;

/**
 * 
 * @author albeena
 *
 */
public interface AuditRepository extends JpaRepository<Auditing, Integer> {

	
	
}
