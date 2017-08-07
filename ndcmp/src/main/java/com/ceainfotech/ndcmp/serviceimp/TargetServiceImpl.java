package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.TargetDAO;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Target;
import com.ceainfotech.ndcmp.service.TargetService;

/**
 * 
 * @author Gowri
 * 
 */

@Service("targetService")
public class TargetServiceImpl implements TargetService {

	@Autowired
	private TargetDAO targetDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TargetServiceImpl.class);
			
	//get target by id
	@Override
	public Target getById(Integer id) {
		LOGGER.info("Get one Target by id");
		if(id != null){
			return targetDAO.getById(id);
		}
		return null;
	}

	//save target
	@Override
	public void addTarget(Target target) {
		if(target != null){
			targetDAO.addTarget(target);
		}
	}

	//delete target
	@Override
	public void delete(Integer id) {
		if(id != null){
			targetDAO.delete(id);
		}
	}

	//list all target
	@Override
	public List<Target> listAllTargets() {
		return targetDAO.listAllTargets();
	}

	@Override
	public void updateTarget(Target target) {
		if(target != null){
			targetDAO.updateTarget(target);
		}
		
	}

	@Override
	public List<Target> findByOutput(Output output) {
		// TODO Auto-generated method stub
		return targetDAO.findByOutput(output);
	}

	@Override
	public Target getByMessageAndOutput(String message, Output output) {
		// TODO Auto-generated method stub
		return targetDAO.getByMessageAndOutput(message, output);
	}
}
