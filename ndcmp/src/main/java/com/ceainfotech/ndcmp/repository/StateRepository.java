/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.States;

/**
 * @author Samy
 * 
 */
public interface StateRepository extends JpaRepository<States, Integer> {

	public States findByCode(String code);

	/**
	 * @author mani
	 */
	public List<States> findAllByOrderByCodeAsc();
	
	public States findById(Integer id);

	public List<States> findByCountryIdOrderByNameAsc(Integer countryId);

	public List<States> findByCountryOrderByNameAsc(Countries country);

	public States findByName(String name);

	public States findByIdAndCode(Integer id, String code);
	
	public States findByIdAndName(Integer id, String name);
	
	public List<States> findByStatusOrderByNameAsc(String status);


}
