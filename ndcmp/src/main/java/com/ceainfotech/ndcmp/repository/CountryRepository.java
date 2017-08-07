/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.User;

/**
 * @author Samy
 * 
 */
public interface CountryRepository extends JpaRepository<Countries, Integer> {

	public Countries findByCode(String code);

	public Countries findById(int id);

	/**
	 * @author mani
	 */
	public List<Countries> findAllByOrderByCodeAsc();
	
	@Override
	public void delete(Integer countryId);
	
	public List<Countries> findByStatusOrderByNameAsc(String status);
	
	public Countries findByName(String name);

	public Countries findByIdAndCode(Integer id,String code);
	
	public Countries findByIdAndName(Integer id,String name);
	
}
