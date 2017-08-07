/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ceainfotech.ndcmp.model.Statuss;

/**
 * @author Samy
 * 
 */
public interface StatusRepository extends JpaRepository<Statuss, Integer> {

	/**
	 * @author mani
	 */
	public List<Statuss> findAllByOrderByNameAsc();
	
	public List<Statuss> findAllByOrderByStartRangeAsc();
	
	Statuss findByName(String name);

	Statuss getById(Integer id);

	public Statuss findByIdAndNameAndDescriptionAndStatusColor(Integer id, String name, String description,String statusColor);

	Statuss findByDescription(String description);

	Statuss findByStatusColor(String statusColor);

	Statuss findByIdAndName(Integer id, String name);

	Statuss findByIdAndDescription(Integer id, String description);

	Statuss findByIdAndStatusColor(Integer id, String statusColor);
	
	@Query(value = "SELECT s FROM Statuss s WHERE s.modificationTime BETWEEN :lastSyncedDate AND :currentDate")
	List<Statuss> findStatusListByDate(@Param("lastSyncedDate")Date lastSyncedDate, @Param("currentDate")Date currentDate);

	@Query(value = "SELECT COUNT(a) FROM Statuss a WHERE a.status = 'ACTIVE'")
	public Integer getStatussCount();
}
