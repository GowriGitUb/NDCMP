/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceainfotech.ndcmp.model.Indicator;
import com.ceainfotech.ndcmp.model.Output;

/**
 * @author Gowri
 *
 */
public interface IndicatorRepository extends JpaRepository<Indicator, Integer>{

	public List<Indicator> findByOutput(Output output);
	
	public List<Indicator> findBySyncStatus(boolean syncStatus);
	
	public Indicator findByMessageAndOutput(String message,Output output);
}
