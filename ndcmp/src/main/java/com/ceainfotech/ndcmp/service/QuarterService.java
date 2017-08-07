/**
 * 
 */
package com.ceainfotech.ndcmp.service;

import java.util.List;

import com.ceainfotech.ndcmp.model.Quarter;

/**
 * @author Gowri
 *
 */
public interface QuarterService {

	public Quarter getById(Integer id);

	public void addQuarter(Quarter quarter);
	
	public void delete(Integer id);

	public List<Quarter> listAllQuarters();
	
	public Quarter findByNameAndStatus(String name,String status);
	
	public Quarter findByIdAndNameAndStatus(Integer id,String name,String status);
}
