/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author Samy
 * 
 */
public interface OutcomeService {

	public List<Outcome> getOutcomes();

	public void save(Outcome outcome);

	public void saveAndFlush(Outcome outcome);

	public Outcome getById(Integer id);

	public List<Outcome> getByTheme(Theme theme);

	public void delete(int id);
	
	public Integer getByThemeId(Theme theme);
	
	public Outcome getByName(String name);
	
	public List<Outcome> findBySyncStatus(boolean syncStatus);
	
	public Outcome getBySequenceNumber(String sequenceNumber);
	
	public Outcome findByNameAndTheme(String name,Theme theme);
}
