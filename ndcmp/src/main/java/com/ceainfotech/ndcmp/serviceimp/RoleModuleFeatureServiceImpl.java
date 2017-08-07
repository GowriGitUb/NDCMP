/**
 * 
 */
package com.ceainfotech.ndcmp.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceainfotech.ndcmp.model.Feature;
import com.ceainfotech.ndcmp.model.ModuleFeature;
import com.ceainfotech.ndcmp.model.RoleModuleFeature;
import com.ceainfotech.ndcmp.repository.RoleModuleFeatureRepository;
import com.ceainfotech.ndcmp.service.ModuleFeatureService;
import com.ceainfotech.ndcmp.service.RoleModuleFeatureService;
import com.ceainfotech.ndcmp.util.ModuleFeatureObject;
import com.ceainfotech.ndcmp.util.RoleFeatureList;

/**
 * @author jeeva
 *
 */
@Service
@Transactional
public class RoleModuleFeatureServiceImpl implements RoleModuleFeatureService{
	
	@Autowired
	RoleModuleFeatureRepository roleModuleFeatureRepository;
	
	@Autowired
	ModuleFeatureService moduleFeatureService;
	
//	@Autowired
//	private PrincipalUtil principalUtil;
	
	
	//AuditLog auditLog = null;

	@Override
	public List<RoleModuleFeature> getByRoleId(Integer roleId) {
		return roleModuleFeatureRepository.findByRoleId(roleId);
	}
	
	@Override
	public void addRolemoduleFeature(RoleModuleFeature roleModuleFeature) {
		roleModuleFeatureRepository.saveAndFlush(roleModuleFeature);
	}
	@Override
	public List<RoleModuleFeature> getAll() {
		return roleModuleFeatureRepository.findAll();
	}

	@Override
	public int save(RoleFeatureList roleFeatureList) {
		int result = 0;
			try {
				if(roleFeatureList != null) {
					Integer roleId = roleFeatureList.getRoleId();
					try {
						deleteByRoleId(roleId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<ModuleFeatureObject> moduleFeatureObjects = roleFeatureList.getFeatureList();
					for(ModuleFeatureObject object : moduleFeatureObjects) {
						for(Feature feature : object.getFeatures()) {
							ModuleFeature moduleFeature = moduleFeatureService.getFeatureId(feature.getId());
							RoleModuleFeature roleModuleFeature = new RoleModuleFeature();
							roleModuleFeature.setRoleId(roleId);
							roleModuleFeature.setModuleId(moduleFeature.getModuleId());
							roleModuleFeature.setFeatureId(feature.getId());
							roleModuleFeatureRepository.save(roleModuleFeature);
						}
					}
					return 1;
				} else {
					return 1;	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	@Override
	public RoleModuleFeature findByRoleIdModuleIdFeatureId(Integer roleId,Integer moduleId, Integer featureId) {
		return roleModuleFeatureRepository.findByRoleIdAndModuleIdAndFeatureId(roleId, moduleId, featureId);
	}

	@Override
	public void deleteByRoleId(Integer roleId) {
		roleModuleFeatureRepository.deleteByRoleId(roleId);
	}

	@Override
	public void deleteByRoleIdAndCompany(Integer roleId) {
		roleModuleFeatureRepository.deleteByRoleIdAndCompany(roleId);
	}

}
