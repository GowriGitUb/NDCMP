/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author bosco
 * 
 */
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

	List<Theme> findByStatus(String status);

	Theme getById(Integer id);
	
	public Theme getByName(String name);
	
	List<Theme> findByStrategicPillar(StrategicPillar strategicPillar);

	public List<Theme> findBySyncStatus(boolean syncStatus);
	
	public Theme findByNameAndStrategicPillar(String name,StrategicPillar strategicPillar);
}
