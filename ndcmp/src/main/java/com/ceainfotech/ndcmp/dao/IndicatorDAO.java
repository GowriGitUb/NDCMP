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

import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.repository.IndicatorRepository;

/**
 * @author Samy
 * 
 */
@Repository
public class IndicatorDAO{

	private final IndicatorRepository indicatorRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorDAO.class);
	
	@Autowired
	public IndicatorDAO(final IndicatorRepository indicatorRepository) {
		this.indicatorRepository = indicatorRepository;
	}

	
	//get Indicator
	@Transactional(readOnly = true)
	public Indicator getById(Integer id) {
		LOGGER.info("Get one Indicator by id");
		if(id != null){
			return indicatorRepository.findOne(id);
		}
		return null;
	}
	
	//save Indicator
	@Transactional(readOnly = false)
	public void addIndicator(Indicator indicator) {
		LOGGER.info("Save Indicator");
		if(indicator != null){
			indicatorRepository.saveAndFlush(indicator);
		}
	}

	//delete Indicator
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		LOGGER.info("Delete Indicator");
		if(id != null){
			indicatorRepository.delete(id);
		}
	}

	//list all Indicator
	@Transactional(readOnly = true)
	public List<Indicator> listAllIndicators() {
		LOGGER.info("List indicator");
		return indicatorRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Indicator> findByOutput(Output output) {
		if(output != null){
			return indicatorRepository.findByOutput(output);
		}
		return null;
	}
	
	@Transactional(readOnly=true)
	public Indicator getByMessageAndOutput(String message,Output output){
		return indicatorRepository.findByMessageAndOutput(message, output);
	}
	
}
