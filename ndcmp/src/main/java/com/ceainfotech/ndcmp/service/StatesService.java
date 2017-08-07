/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.States;

/**
 * @author Samy
 * 
 */
public interface StatesService {

	public List<States> getStates();

	public void save(States states);

	public void saveAndFlush(States states);

	States getById(Integer id);

	public List<States> findByCountryId(Integer countryId);

	public List<States> getByCountry(Countries country);

	public void deleteStatesById(int id);

	public List<States> findByCountry(Countries country);

	public States findByCode(String code);

	public States findByName(String name);

	public States findByIdAndCode(Integer id, String code);

	public States findByIdAndName(Integer id, String name);
	
	public List<States> findByStatus(String status);

}
