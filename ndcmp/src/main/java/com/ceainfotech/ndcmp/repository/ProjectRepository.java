package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.States;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	public Project findById(Integer projectId);
	
	public Project findByName(String projectName);
	
	public List<Project> findByCountry(Countries country);//find by country
	
	public List<Project> findByStates(States states);//find by state
	
	//find the count of project
	@Query(value = "SELECT COUNT(p.id) FROM Project p WHERE p.status = 'ACTIVE'")
	public int getProjectCount();
	
	public List<Project> findBySyncStatus(boolean syncStatus);

}
