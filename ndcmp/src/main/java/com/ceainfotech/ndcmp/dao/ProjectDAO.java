package com.ceainfotech.ndcmp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.repository.ProjectRepository;

@Repository
public class ProjectDAO {

	private final ProjectRepository projectRepository;

	@Autowired
	public ProjectDAO(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDAO.class);

	@Transactional(readOnly = true)
	public List<Project> getProject() {
		LOGGER.debug("Find All the Projects .. {}");
		return projectRepository.findAll();

	}

	public void save(Project project) {
		LOGGER.info("Save new Project .. {}", project);
		projectRepository.save(project);
	}

	public Project getById(Integer projectId) {
		LOGGER.debug("Find StrategicPillar by id .. {}", projectId);
		return projectRepository.findById(projectId);
	}
	
	public Project getByName(String projectName) {
		LOGGER.debug("Find StrategicPillar by name .. {}", projectName);
		return projectRepository.findByName(projectName);
	}

	public void update(Project project) {
		LOGGER.info("Update existing Project Details .. {}", project);
		projectRepository.saveAndFlush(project);
	}

	public void delete(Integer projectId) {
		LOGGER.info("Delete Project by id .. {}", projectId);
		projectRepository.delete(projectId);
	}
	//find by country
	@Transactional(readOnly = true)
	public List<Project> findByCountry(Countries country){
		if(country != null){
			return projectRepository.findByCountry(country);
		}
		return null;
	}

	//To get Project count
	public int getProjectCount(){
		return projectRepository.getProjectCount();
	}
	
	//get the project based on the state
	public List<Project> findByStates(States states){
		if(states != null){
			return projectRepository.findByStates(states);
		}
		return null;
	}
	
	public List<Project> findBySyncStatus(boolean syncStatus){
		return projectRepository.findBySyncStatus(syncStatus);
	}
}
