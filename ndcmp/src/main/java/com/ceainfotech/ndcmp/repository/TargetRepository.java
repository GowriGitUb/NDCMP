/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.Target;

/**
 * @author Gowri
 *
 */
public interface TargetRepository extends JpaRepository<Target, Integer>{

	List<Target> findByOutput(Output output);
	
	public Target findByMessageAndOutput(String message,Output output);

}
