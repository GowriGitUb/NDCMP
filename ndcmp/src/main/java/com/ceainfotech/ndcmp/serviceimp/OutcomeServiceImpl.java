/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.OutcomeDAO;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Theme;
import com.ceainfotech.ndcmp.service.OutcomeService;

/**
 * @author Samy
 * 
 */
@Transactional()
@Service("outcomeService")
public class OutcomeServiceImpl implements OutcomeService {

	@Autowired
	private OutcomeDAO outcomeDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(OutcomeServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.StrategicPillarService#getStrategicPillars
	 * ()
	 */
	@Override
	public List<Outcome> getOutcomes() {
		return outcomeDAO.getOutcomes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.StrategicPillarService#getById(java.lang
	 * .Integer)
	 */
	@Override
	public Outcome getById(Integer id) {
		return outcomeDAO.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.StrategicPillarService#save(com.ceainfotech
	 * .ndcmp.model.StrategicPillar)
	 */
	@Override
	public void save(Outcome outcome) {
		outcomeDAO.save(outcome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.StrategicPillarService#saveAndFlush(com
	 * .ceainfotech.ndcmp.model.StrategicPillar)
	 */
	@Override
	public void saveAndFlush(Outcome outcome) {
		outcomeDAO.saveAndFlush(outcome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.StrategicPillarService#getByProject(com
	 * .ceainfotech.ndcmp.model.StrategicPillar)
	 */
	@Override
	public List<Outcome> getByTheme(Theme theme) {
		return outcomeDAO.getByTheme(theme);
	}
	
	/* (non-Javadoc)
	 * @see com.ceainfotech.ndcmp.service.OutcomeService#getByName(java.lang.String)
	 */
	@Override
	public Outcome getByName(String name) {
		return outcomeDAO.getByName(name);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ceainfotech.ndcmp.service.OutcomeService#getByThemeId(com.ceainfotech
	 * .ndcmp.model.Theme)
	 */
	@Override
	public Integer getByThemeId(Theme theme) {
		return outcomeDAO.getByThemeId(theme);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ceainfotech.ndcmp.service.StrategicPillarService#delete(int)
	 */
	@Override
	public void delete(int id) {
		outcomeDAO.delete(id);
	}
	
	@Override
	public List<Outcome> findBySyncStatus(boolean syncStatus) {
		return outcomeDAO.findBySyncStatus(syncStatus);
	}

	@Override
	public Outcome getBySequenceNumber(String sequenceNumber) {
		// TODO Auto-generated method stub
		return outcomeDAO.findBySequenceNumber(sequenceNumber);
	}

	@Override
	public Outcome findByNameAndTheme(String name, Theme theme) {
		// TODO Auto-generated method stub
		return outcomeDAO.findByNameAndTheme(name, theme);
	}
}
