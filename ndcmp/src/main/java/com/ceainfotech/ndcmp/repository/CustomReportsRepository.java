/**
 * 
 */
package com.ceainfotech.ndcmp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceainfotech.ndcmp.model.KeyActivity;
import com.ceainfotech.ndcmp.model.Outcome;
import com.ceainfotech.ndcmp.model.Output;
import com.ceainfotech.ndcmp.model.StrategicPillar;
import com.ceainfotech.ndcmp.model.SubActivity;
import com.ceainfotech.ndcmp.model.Theme;

/**
 * @author pushpa
 */

@Repository
public class CustomReportsRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	/**
	 * @param approverReportSPQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StrategicPillar> getStrategicPillersByReportParameters(String approverReportSPQuery){
		Query query = getEntityManager().createQuery(approverReportSPQuery);
		List<StrategicPillar> listStrategicPillers = query.getResultList();
		return listStrategicPillers;
	}
	
	/**
	 * @param approverReportThemesQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Theme> getThemesByReportParameters(String approverReportThemesQuery){
		Query query = getEntityManager().createQuery(approverReportThemesQuery);
		List<Theme> listThemes = query.getResultList();
		return listThemes;
	}
	
	/**
	 * @param approverReportOutcomessQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Outcome> getOutcomesByReportParameters(String approverReportOutcomesQuery){
		Query query = getEntityManager().createQuery(approverReportOutcomesQuery);
		List<Outcome> listOutcomes = query.getResultList();
		return listOutcomes;
	}
	
	/**
	 * @param approverReportOutputsQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Output> getOutputsByReportParameters(String approverReportOutputsQuery){
		Query query = getEntityManager().createQuery(approverReportOutputsQuery);
		List<Output> listOutputs = query.getResultList();
		return listOutputs;
	}
	
	/**
	 * @param approverReportingPeriodQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KeyActivity> getKeyActivitiesByReportParameters(String approverReportKeyactivitiesPeriodQuery){
		Query query = getEntityManager().createQuery(approverReportKeyactivitiesPeriodQuery);
		List<KeyActivity> listKeyActivities = query.getResultList();
		return listKeyActivities;
	}
	
	/**
	 * @param approverReportingPeriodQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SubActivity> getSubActivitiesByReportParameters(String approverReportingPeriodQuery){
		Query query = getEntityManager().createQuery(approverReportingPeriodQuery);
		List<SubActivity> listSubActivities = query.getResultList();
		return listSubActivities;
	}
}
