package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceainfotech.ndcmp.dao.IndicatorDAO;
import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.service.IndicatorService;

/**
 * 
 * @author Gowri
 * 
 */

@Service("indicatorService")
public class IndicatorServiceImpl implements IndicatorService {

	@Autowired
	private IndicatorDAO indicatorDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorServiceImpl.class);
			
	//get Indicator by id
	@Override
	public Indicator getById(Integer id) {
		LOGGER.info("Get one Indicator by id");
		if(id != null){
			return indicatorDAO.getById(id);
		}
		return null;
	}

	//save Indicator
	@Override
	public void addIndicator(Indicator indicator) {
		if(indicator != null){
			indicatorDAO.addIndicator(indicator);
		}
	}

	//delete Indicator
	@Override
	public void delete(Integer id) {
		if(id != null){
			indicatorDAO.delete(id);
		}
	}

	//list all Indicator
	@Override
	public List<Indicator> listAllIndicators() {
		return indicatorDAO.listAllIndicators();
	}
	
	@Override
	public List<Indicator> findByOutput(Output output) {
		if(output != null){
			return indicatorDAO.findByOutput(output);
		}
		return null;
	}
	
	
	public Indicator getByMessageAndOutput(String message,Output output){
		return indicatorDAO.getByMessageAndOutput(message, output);
	}
}
