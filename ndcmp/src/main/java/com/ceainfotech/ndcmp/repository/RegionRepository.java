/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;

/**
 * @author Samy
 * 
 */
public interface RegionRepository extends JpaRepository<Region, Integer> {

	public Region findById(Integer id);

	/**
	 * @author mani
	 */
	public List<Region> findAllByOrderByCodeAsc();
	
	public Region findByCode(String code);
	
	public Region findByName(String name);

	public Region findByCodeAndId(String code, Integer id);
	
	public Region findByNameAndId(String name, Integer id);

	public List<Region> findByStatesOrderByName(States states);

	/* public void delete(int id); */
	
	public List<Region> findByCountryOrderByName(Countries countries);
	
	public List<Region> findByStatusOrderByName(String status);
}
