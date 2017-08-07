/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author Samy
 * 
 */
public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {

	public Outcome findById(Integer id);

	public Outcome findByName(String name);

	@Query(value = "SELECT a FROM Outcome a WHERE a.theme = :theme ORDER BY a.sequenceNumber+0")
	public List<Outcome> findByTheme(@Param("theme")Theme theme);
	
	@Query(value = "SELECT COUNT(a) FROM Outcome a WHERE a.theme = ?1")
	Integer findByThemeId(Theme theme);
	
	public List<Outcome> findBySyncStatus(boolean syncStatus);
	
	public Outcome findBySequenceNumber(String sequenceNumber);
	
	public Outcome findByNameAndTheme(String name,Theme theme);
}
