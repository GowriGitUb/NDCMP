/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Feature;
import com.ceainfotech.ndcmp.repository.FeatureRepository;
import com.ceainfotech.ndcmp.service.FeatureService;

/**
 * @author jeeva
 *
 */
@Service
@Transactional
public class FeatureServiceImpl implements FeatureService{

	@Autowired
	private FeatureRepository featureRepository;

	@Override
	public Feature findByFeature(String name) {
		return featureRepository.findByFeature(name);
	}

	@Override
	public void save(Feature feature) {

		featureRepository.saveAndFlush(feature);
	}

	@Override
	public List<Feature> listAllFeatures() {
		return featureRepository.findAll();
	}

	@Override
	public Feature findByFeatureId(Integer id) {
		return featureRepository.findOne(id);
	}

	@Override
	public List<Feature> findByRoleIdAndModuleId(Integer roleId, Integer moduleId) {
		return featureRepository.findFeatureByRoleIdAndModuleid(roleId, moduleId);
	}

}
