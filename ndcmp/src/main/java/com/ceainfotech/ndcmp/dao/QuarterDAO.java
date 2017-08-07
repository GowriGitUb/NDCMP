/**
 * 
 */
package com.ceainfotech.ndcmp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceainfotech.ndcmp.model.Quarter;
import com.ceainfotech.ndcmp.repository.QuarterRepository;

/**
 * @author Gowri
 *
 */
@Repository
public class QuarterDAO{

private static final Logger LOGGER = LoggerFactory.getLogger(QuarterDAO.class);
	 
   @Autowired
	private final QuarterRepository quarterRepository;

	public QuarterDAO(final QuarterRepository quarterRepository) {
		this.quarterRepository = quarterRepository;
	}

	public Quarter getById(Integer id) {
		if(id != null){
			return quarterRepository.findOne(id);
		}
		return null;
	}

	public void addQuarter(Quarter quarter) {
		if(quarter != null){
			quarterRepository.saveAndFlush(quarter);
		}
	}

	public void delete(Integer id) {
		if(id != null){
			quarterRepository.delete(id);
		}
	}

	public List<Quarter> listAllQuarters() {
		return quarterRepository.findAll();
	}
	
	public Quarter findByNameAndStatus(String name, String status) {
		if(name != null && status != null){
			return quarterRepository.findByNameAndStatus(name, status);
		}
		return null;
	}
	
	public Quarter findByIdAndNameAndStatus(Integer id, String name,String status) {
		if(id != null && name != null && status != null){
			return quarterRepository.findByIdAndNameAndStatus(id, name, status);
		}
		return null;
	}
}
