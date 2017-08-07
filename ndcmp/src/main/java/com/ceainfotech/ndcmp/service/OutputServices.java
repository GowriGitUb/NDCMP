/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author bosco
 * 
 */
public interface OutputServices {

	Output getById(int id);
	
	public List<Output> getAll();

	void save(Output output);

	void saveAndFlush(Output output);

	void deleteOutput(int id);

	List<Output> getOutput(String status);
	
	public Integer getByOutcomeId(Outcome outcome);
	
	public List<Output> getByOutcome(Outcome outcome);
	
	public Output findByOutput(String ouputName);

	Output getByName(String output);
	
	public List<Output> findBySyncStatus(boolean syncStatus);
	
	public Output findByOutputAndOutcome(String output,Outcome outcome);

}
