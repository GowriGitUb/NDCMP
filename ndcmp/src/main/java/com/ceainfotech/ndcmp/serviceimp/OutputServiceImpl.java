/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.OutputDAO;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.service.OutputServices;

/**
 * @author bosco
 * 
 */

@Service("outputService")
public class OutputServiceImpl implements OutputServices {

	@Autowired
	OutputDAO outputDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(OutcomeServiceImpl.class);

	@Override
	public Output getById(int id) {
		return outputDAO.getByid(id);
	}

	@Override
	public void save(Output output) {
		outputDAO.save(output);
	}

	@Override
	public void saveAndFlush(Output output) {
		outputDAO.saveAndFlush(output);
	}

	@Override
	public void deleteOutput(int id) {
		outputDAO.deleteOutput(id);
	}

	@Override
	public List<Output> getOutput(String string) {
		return outputDAO.getOutput(string);
	}
	
	@Override
	public List<Output> getAll() {
		return outputDAO.getAll();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.OutcomeService#getByThemeId(com.ceainfotech
	 * .ndcmp.model.Theme)
	 */
	@Override
	public Integer getByOutcomeId(Outcome outcome) {
		return outputDAO.getByOutcomeId(outcome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.OutputServices#getByOutcome(com.ceainfotech
	 * .ndcmp.model.Outcome)
	 */
	@Override
	public List<Output> getByOutcome(Outcome outcome) {
		return outputDAO.getByOutcome(outcome);
	}

	@Override
	public Output findByOutput(String ouputName) {
		if(ouputName != null){
			return outputDAO.findByOutput(ouputName);
		}
		return null;
	}

	@Override
	public Output getByName(String output) {
		// TODO Auto-generated method stub
		return outputDAO.findByOutput(output);
	}
	
	@Override
	public List<Output> findBySyncStatus(boolean syncStatus) {
		return outputDAO.findBySyncStatus(syncStatus);
	}

	@Override
	public Output findByOutputAndOutcome(String output, Outcome outcome) {
		// TODO Auto-generated method stub
		return outputDAO.findByOutputAndOutcome(output, outcome);
	}
}
