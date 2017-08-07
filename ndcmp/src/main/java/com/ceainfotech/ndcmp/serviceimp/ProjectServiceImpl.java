/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.dao.ProjectDAO;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.service.ProjectService;

/**
 * @author bosco
 * 
 */

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectDAO projectDAO;

	@Override
	public List<Project> getProject() {
		return projectDAO.getProject();
	}

	@Override
	public Project getById(Integer projectId) {
		LOGGER.info("Get Project Details by project id");
		return projectDAO.getById(projectId);
	}
	
	@Override
	public Project getByName(String projectName) {
		LOGGER.info("Get Project Details by project name");
		return projectDAO.getByName(projectName);
	}

	@Override
	public void save(Project project) {
		LOGGER.info("Saving Project Details");
		if (project != null) {
			projectDAO.save(project);
		}
	}

	@Override
	public void delete(Integer projectId) {
		LOGGER.info("Deleting Project");
		if (projectId != null) {
			projectDAO.delete(projectId);
		}
	}

	@Override
	public void update(Project project) {
		LOGGER.info("Update Project");
		if (project != null) {
			projectDAO.update(project);
		}
	}

	@Override
	public List<Project> findByCountry(Countries country) {
		if(country != null){
			return projectDAO.findByCountry(country);
		}
		return null;
	}

	@Override
	public int getProjectCount() {
		return projectDAO.getProjectCount();
	}

	//get the project by state
	@Override
	public List<Project> findByStates(States states) {
		if(states != null){
			return projectDAO.findByStates(states);
		}
		return null;
	}
	
	@Override
	public List<Project> findBySyncStatus(boolean syncStatus) {
		return projectDAO.findBySyncStatus(syncStatus);
	}
}
