/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.repository.OutputRepository;
import com.ceainfotech.ndcmp.repository.ThemeRepository;

/**
 * @author bosco
 *
 */
@Repository
public class OutputDAO {

	private final OutputRepository outputRepository;
	
	public OutputDAO(final OutputRepository outputRepository){
		this.outputRepository = outputRepository;
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
	
	@Transactional(readOnly = true)
	public Output getByid(int id) {
		// TODO Auto-generated method stub
		return outputRepository.getById(id);
	}
	@Transactional(readOnly = false)
	public void save(Output output) {
		// TODO Auto-generated method stub
		outputRepository.save(output);
	}
	@Transactional(readOnly = false)
	public void saveAndFlush(Output output) {
		// TODO Auto-generated method stub
		outputRepository.saveAndFlush(output);
	}

	@Transactional(readOnly = false)
	public void deleteOutput(int id) {
		// TODO Auto-generated method stub
		outputRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public List<Output> getOutput(String string) {
		// TODO Auto-generated method stub
		return outputRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Output> getAll() {
		return outputRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Output> getByOutcome(Outcome outcome) {
		return outputRepository.findByOutcome(outcome);
	}
	
	@Transactional(readOnly = true)
	public Integer getByOutcomeId(Outcome outcome) {
		LOGGER.debug("Count Output By outcome .. {}", outcome);
		return outputRepository.findByOutcomeId(outcome);
	}
	
	@Transactional(readOnly = true)
	public Output findByOutput(String ouputName){
		if(ouputName != null){
			return outputRepository.findByOutput(ouputName);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Output> findBySyncStatus(boolean syncStatus){
		return outputRepository.findBySyncStatus(syncStatus);
	}
	
	@Transactional(readOnly = true)
	public Output findByOutputAndOutcome(String output,Outcome outcome){
			return outputRepository.findByOutputAndOutcome(output,outcome);
	}
	
}
