/**
 * 
 */
package com.ceainfotech.ndcmp.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceainfotech.ndcmp.model.Feature;
import com.ceainfotech.ndcmp.model.RoleModuleFeature;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.UserRoleFeature;
import com.ceainfotech.ndcmp.service.FeatureService;
import com.ceainfotech.ndcmp.service.RoleModuleFeatureService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;

/**
 * @author jeeva
 *
 */
@Component
public class Helper {
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	FeatureService featureService;
	
	@Autowired
	RoleModuleFeatureService roleModuleFeatureService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * Get current user role feature for user role information
	 * @return
	 */
	
	public UserRoleFeature getCurrentUserRoleFeatureForProject(){
		UserRoleFeature userRoleFeature = new UserRoleFeature();
		User user = userService.findByUsername(principalUtil.getPrincipal());
		if(user != null){
			UserRole role = new UserRole();
			List<UserRole> roles = user.getUserRoles();
			for (UserRole role1 : roles) {
				role = userRoleService.findByRoleId(role1.getId());
			}
			List<RoleModuleFeature> roleModuleFeature = roleModuleFeatureService.getByRoleId(role.getId());
			if (roleModuleFeature.size() > 0) {
				for (RoleModuleFeature roleModuleFeature2 : roleModuleFeature) {
					if (roleModuleFeature2 != null && roleModuleFeature2.getFeatureId() != null) {
						Feature feature = featureService.findByFeatureId(roleModuleFeature2.getFeatureId());
						if(feature != null && feature.getFeature() != null && !feature.getFeature().isEmpty()) {
							if (feature.getFeature().equals(Features.VIEW_PROJECT.getName())) {
								userRoleFeature.setViewStatus(true);
							}
						}
					}
				}
			}
		}
		/*if(principalUtil.getCurrentUserCompanyId() != null){
			Company company = companyService.getByCompanyId(principalUtil.getCurrentUserCompanyId());
			if(company != null){
				User user = userServiceImpl.findByUsernameAndCompany(principalUtil.getPrincipal(), company.getCompanyCode());
				if(user != null) {
					Role userRole = new Role();
					Set<Role> roles = user.getRoles();
					for (Role role : roles) {
						userRole = roleService.findByRoleId(role.getId());
						break;
					}
					List<RoleModuleFeature> roleModuleFeature = roleModuleFeatureService.getByRoleId(userRole.getId());
					if (roleModuleFeature.size() > 0) {
						for (RoleModuleFeature roleModuleFeature2 : roleModuleFeature) {
							if (roleModuleFeature2 != null && roleModuleFeature2.getFeatureId() != null) {
								Feature feature = featureService.findByFeatureId(roleModuleFeature2.getFeatureId());
								if(feature != null && feature.getFeature() != null && !feature.getFeature().isEmpty()) {
									if (feature.getFeature().equals(Features.ADD_PROCESS.getName())) {
										userRoleFeature.setAdd(true);
									}
									if (feature.getFeature().equals(Features.EDIT_PROCESS.getName())) {
										userRoleFeature.setUpdate(true);
									}
									if (feature.getFeature().equals(Features.DELETE_PROCESS.getName())) {
										userRoleFeature.setDelete(true);;
									}
									if (feature.getFeature().equals(Features.VIEW_PROCESS.getName())) {
										userRoleFeature.setView(true);
									}
								}
							}
						}
					}
				}
			}
		}*/
		return userRoleFeature;
	}

}
