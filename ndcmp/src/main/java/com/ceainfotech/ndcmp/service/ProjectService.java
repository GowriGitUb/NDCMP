package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.States;

public interface ProjectService {

	public List<Project> getProject();

	public Project getById(Integer projectId);
	
	public Project getByName(String projectName);

	public void save(Project project);

	public void delete(Integer projectId);

	public void update(Project project);
	
	public List<Project> findByCountry(Countries country);//find by country
	
	public List<Project> findByStates(States states);//find by states
	
	public int getProjectCount();
	
	public List<Project> findBySyncStatus(boolean syncStatus);

}
