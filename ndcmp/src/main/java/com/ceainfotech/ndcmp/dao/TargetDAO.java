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

import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.repository.TargetRepository;

/**
 * @author Samy
 * 
 */
@Repository
public class TargetDAO{

	private final TargetRepository targetRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(TargetDAO.class);
	
	@Autowired
	public TargetDAO(final TargetRepository targetRepository) {
		this.targetRepository = targetRepository;
	}

	
	//get target
	@Transactional(readOnly = true)
	public Target getById(Integer id) {
		LOGGER.info("Get one Target by id");
		if(id != null){
			return targetRepository.findOne(id);
		}
		return null;
	}
	
	//save target
	@Transactional(readOnly = false)
	public void addTarget(Target target) {
		LOGGER.info("Save Target");
		if(target != null){
			targetRepository.save(target);
		}
	}

	//delete target
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		LOGGER.info("Delete Target");
		if(id != null){
			targetRepository.delete(id);
		}
	}

	//list all target
	@Transactional(readOnly = true)
	public List<Target> listAllTargets() {
		LOGGER.info("List Target");
		return targetRepository.findAll();
	}

	@Transactional(readOnly = true)
	public void updateTarget(Target target) {
		// TODO Auto-generated method stub
		LOGGER.info("Update Target");
		if(target != null){
			targetRepository.saveAndFlush(target);
		}
	}

	@Transactional(readOnly = true)
	public List<Target> findByOutput(Output output) {
		// TODO Auto-generated method stub
		return targetRepository.findByOutput(output);
	}
	
	@Transactional(readOnly=true)
	public Target getByMessageAndOutput(String message,Output output){
		return targetRepository.findByMessageAndOutput(message, output);
	}
}
