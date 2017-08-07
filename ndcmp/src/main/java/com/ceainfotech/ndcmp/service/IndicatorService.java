package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.Output;

/**
 * 
 * @author Samy
 * 
 */

public interface IndicatorService {

	public Indicator getById(Integer id);

	public void addIndicator(Indicator indicator);
	
	public void delete(Integer id);

	public List<Indicator> listAllIndicators();
	
	public List<Indicator> findByOutput(Output output);
	
	public Indicator getByMessageAndOutput(String message,Output output);
}