/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.repository.OutcomeRepository;

/**
 * @author Samy
 * 
 */
@Repository
public class OutcomeDAO {

	private final OutcomeRepository outcomeRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(OutcomeDAO.class);

	/**
	 * 
	 */
	
	@Autowired
	public OutcomeDAO(final OutcomeRepository outcomeRepository) {
		this.outcomeRepository = outcomeRepository;
	}

	@Transactional(readOnly = true)
	public List<Outcome> getOutcomes() {
		LOGGER.debug("Find All the Outcomes .. {}");
		return outcomeRepository.findAll();
	}
	
	public void save(Outcome outcome) {
		LOGGER.info("Add new Outcome .. {}", outcome);
		if(outcome != null){
			outcomeRepository.save(outcome);
		}
	}
	
	@Transactional(readOnly = true)
	public Outcome getById(Integer id) {
		LOGGER.debug("Find By Outcome id .. {}", id);
		return outcomeRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Outcome getByName(String name) {
		LOGGER.debug("Find Outcome by name .. {}", name);
		return outcomeRepository.findByName(name);
	}
	
	@Transactional(readOnly = true)
	public List<Outcome> getByTheme(Theme theme) {
		LOGGER.debug("Find Outcome By Theme .. {}", theme);
		return outcomeRepository.findByTheme(theme);
	}
	
	@Transactional(readOnly = true)
	public Integer getByThemeId(Theme theme) {
		LOGGER.debug("Count Outcome By Theme .. {}", theme);
		return outcomeRepository.findByThemeId(theme);
	}

	public void saveAndFlush(Outcome outcome) {
		LOGGER.info("Update existing Outcome .. {}", outcome);
		outcomeRepository.saveAndFlush(outcome);
	}
	
	public void delete(int id) {
		LOGGER.info("Deleting Outcome by id .. {}", id);
		outcomeRepository.delete(id);
	}
	
	@Transactional(readOnly = true)
	public List<Outcome> findBySyncStatus(boolean syncStatus){
		return outcomeRepository.findBySyncStatus(syncStatus);
	}
	
	@Transactional(readOnly = true)
	public Outcome findBySequenceNumber(String sequenceNumber){
		return outcomeRepository.findBySequenceNumber(sequenceNumber);
	}
	
	@Transactional(readOnly = true)
	public Outcome findByNameAndTheme(String name,Theme theme){
		return outcomeRepository.findByNameAndTheme(name, theme);
	}
	
}
