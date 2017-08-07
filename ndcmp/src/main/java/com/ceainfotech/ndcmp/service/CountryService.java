/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Countries;

/**
 * @author Samy
 * 
 */
public interface CountryService {

	public List<Countries> getCountries();

	public Countries getById(int id);

	public void save(Countries country);

	public void update(Countries country);

	public void deleteCountry(int countryId);

	// public Countries getCountryById(Long id);

	public List<Countries> findByStatus(String status);

	public Countries findByCode(String code);// get by country code

	public Countries findByName(String name);// get by country name

	public Countries findByIdAndCode(Integer id, String code); // by id and code

	public Countries findByIdAndName(Integer id, String name); // by id and name
}
