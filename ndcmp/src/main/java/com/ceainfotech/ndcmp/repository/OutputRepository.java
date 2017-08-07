/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;

/**
 * @author bosco
 * 
 */
public interface OutputRepository extends JpaRepository<Output, Integer> {

	Output getById(int id);

	@Query(value = "SELECT a FROM Output a WHERE a.outcome = :outcome ORDER BY a.sequenceNumber+0")
	public List<Output> findByOutcome(@Param("outcome")Outcome outcome);
	
	@Query(value = "SELECT COUNT(a) FROM Output a WHERE a.outcome = ?1")
	Integer findByOutcomeId(Outcome outcome);
	
	public Output findByOutput(String ouputName);
	
	public List<Output> findBySyncStatus(boolean syncStatus);
	
	public Output findByOutputAndOutcome(String output,Outcome outcome);

}
