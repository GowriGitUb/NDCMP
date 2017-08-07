/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.SubActivity;

import java.lang.Integer;
import java.lang.String;

/**
 * @author Gowri
 *
 */
public interface KeyActivityRepository extends JpaRepository<KeyActivity, Integer> {

	public List<KeyActivity> findByStatus(String status);
	
	@Query(value = "SELECT COUNT(a) FROM KeyActivity a WHERE a.output = ?1")
	public Integer findByOutputId(Output output);
	
	@Query(value = "SELECT COUNT(a) FROM KeyActivity a WHERE a.status = 'ACTIVE'")
	public Integer findKeyActivityCount();
	
	@Query(value = "SELECT a FROM KeyActivity a WHERE a.output = :output ORDER BY a.sequenceNumber+0")
	public List<KeyActivity> findByOutput(@Param("output")Output output);
	
	public KeyActivity findById(Integer id);
	
	public KeyActivity findByName(String name);
	
	@Query(value="SELECT k FROM KeyActivity k WHERE k.id IN(SELECT DISTINCT s.keyActivity.id FROM SubActivity s WHERE s.id = :id)")
	public KeyActivity findByKeyId(@Param("id") Integer id);
	
	//@Query("SELECT m FROM Module m WHERE m.id IN (SELECT DISTINCT r.moduleId FROM RoleModuleFeature r WHERE r.roleId = :roleId AND r.company =:company) ORDER BY m.orderId ASC")
	//public List<Module> getModuleByRoleAndCompany(@Param("roleId") Long roleId,@Param("company") Company company);	

	public List<KeyActivity> findBySyncStatus(boolean syncStatus);
	
	/**
	 * @author Mani
	 * Get Sub Activity  by passed Key activity Name and Output Id
	 * @param Name
	 * @param outputId 
	 */
	public KeyActivity findByNameAndOutput(String name,Output output);
	
	
/**
 * @author mani
 * @param strategicPillarId
 * @param reportingPeriodId
 * @return
 */
@Query(value = "SELECT DISTINCT sa.keyActivity FROM SubActivity sa WHERE sa.keyActivity.output.outcome.theme.strategicPillar.id IN (:strategicPillarId) ORDER BY sa.keyActivity.sequenceNumber+0, sa.keyActivity.sequenceNumber")
public List<KeyActivity> findKeyActivitiesByStrategicPillarsAndOpenedReportingPeriods(@Param("strategicPillarId")List<Integer> strategicPillarId);
}
