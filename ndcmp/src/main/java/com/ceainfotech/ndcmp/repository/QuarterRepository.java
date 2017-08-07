/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceainfotech.ndcmp.model.Quarter;

/**
 * @author Gowri
 *
 */
public interface QuarterRepository extends JpaRepository<Quarter, Integer> {

	//public List<Quarter> findAllByOrderByNameAsc();
	public Quarter findByNameAndStatus(String name,String status);
	
	public Quarter findByIdAndNameAndStatus(Integer id,String name,String status);
}
