package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Target;

/**
 * 
 * @author Samy
 * 
 */

public interface TargetService {

	public Target getById(Integer id);

	public void addTarget(Target target);
	
	public void delete(Integer id);

	public List<Target> listAllTargets();

	public void updateTarget(Target target);

	public List<Target> findByOutput(Output output);
	
	public Target getByMessageAndOutput(String message,Output output);
}