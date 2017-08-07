/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.Feature;
import com.ceainfotech.ndcmp.model.RoleModuleFeature;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.service.FeatureService;
import com.ceainfotech.ndcmp.service.ModuleFeatureService;
import com.ceainfotech.ndcmp.service.RoleModuleFeatureService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.serviceimp.UserServiceImpl;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.ModulesFeatures;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.RoleFeatureList;

/**
 * @author Samy
 * 
 */
@RestController
@RequestMapping(value = "/api/**")
public class AccessRightsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccessRightsController.class);
	
	@Autowired
	ModuleFeatureService moduleFeatureService;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	RoleModuleFeatureService roleModuleFeatureService;
	@Autowired
	private PrincipalUtil principalUtil;
	
	@Autowired
	FeatureService featureService;
	
	@Autowired
	UserRoleService userRoleService;

	@RequestMapping(value = "accessrightsList", method = RequestMethod.GET)
	public ModelAndView listAccessRights(ModelMap modelMap, Authentication authentication) {

		LOGGER.info("Getting Access Role List");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			 modelMap.addAttribute("accessrightslist", userRoleService.getAllRoles());
				return new ModelAndView("accessrights/accessrightsList");
		}else{
			return new ModelAndView("login");
		}
		
	}
	
	@RequestMapping("/assign")
	public ModelAndView assignRoles(Model model, @RequestParam Integer id) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView view = new ModelAndView("accessrights/listModules");
			if(id != null){
				UserRole userRole = userRoleService.findByRoleId(id);
				view.addObject("roleModuleFeature", new RoleModuleFeature());
				view.addObject("roleId", id);
				view.addObject("roleName", userRole.getName());
				boolean superadminflag = true;
				if(userRole.getName().equals("SUPER_ADMIN")){
					superadminflag = false;
				}
				view.addObject("superadminflag", superadminflag);
				return view;
			}
			return null;
		}else{
			return new ModelAndView("login");
		}
		
		/*if(principalUtil.getCurrentUserCompanyId() != null){
			if(id != null){
				Role role = roleService.findByRoleId(id);
				view.addObject("roleModuleFeature", new RoleModuleFeature());
				view.addObject("roleId", id);
				view.addObject("roleName", role.getType());
				boolean superadminflag = true;
				if(role.getType().equals(ConstantUtil.QUANTITATIVE_ROLE) || role.getType().equals(ConstantUtil.QUALITATIVE_ROLE)){
					superadminflag = false;
				}
				view.addObject("superadminflag", superadminflag);
				return view;
			}
		}else{
			Role role = roleService.findByRoleId(id);
			view.addObject("roleModuleFeature", new RoleModuleFeature());
			view.addObject("roleId", id);
			view.addObject("roleName", role.getType());
			boolean superadminflag = true;
			User user = userServiceImpl.findByUsername(principalUtil.getPrincipal());
			if(user != null){
				Set<Role> roles = user.getRoles();
				for (Role role2 : roles) {
					if(role2.getType().equals(ConstantUtil.QUANTITATIVE_ROLE) || role2.getType().equals(ConstantUtil.QUALITATIVE_ROLE)){
						superadminflag = false;
						break;
					}
				}
			}
			view.addObject("superadminflag", superadminflag);
			return view;
		}*/
	}
	
	@RequestMapping("/assignRoles")
	public @ResponseBody List<RoleModuleFeature> assignRolesandFeatures() {
		/*if(principalUtil.getCurrentUserCompanyId() != null){
			Company company = companyService.getByCompanyId(principalUtil.getCurrentUserCompanyId());
			if(company != null){
				User user = userServiceImpl.findByUsernameAndCompany(principalUtil.getPrincipal(), company.getCompanyCode());
				if(user != null){
					if(!user.getUsername().equals(ConstantUtil.SUPERADMIN_USERNAME)){
						Role role = new Role(); 
						Set<Role> roles = user.getRoles();
						for (Role role1 : roles) {
							role = roleService.findByRoleId(role1.getId());
						}
						return roleModuleFeatureService.getByRoleId(role.getId());
					}
				}
			}
		}else{
			List<RoleModuleFeature> roleModuleFeatures = null;
			return roleModuleFeatures;
		}*/
		List<RoleModuleFeature> roleModuleFeatures = null;
		return roleModuleFeatures;
	}
	
	/**
	 * Get Current user roles
	 * @param modelMap
	 * @return
	 */
	
	@RequestMapping(value = "/getCurrentUserRoles", method = RequestMethod.GET)
	public @ResponseBody List<UserRole> getCurrentUserRoles(){
		/*if(principalUtil.getCurrentUserCompanyId() != null){
			Company company = companyService.getByCompanyId(principalUtil.getCurrentUserCompanyId());
			if(company != null){
				User user = userServiceImpl.findByUsernameAndCompany(principalUtil.getPrincipal(), company.getCompanyCode());
				if(user != null){
					List<Role> roles1 = new ArrayList<Role>();
					Set<Role> roles = user.getRoles();
					for(Role role1 : roles){
						Role role = roleService.findByRoleId(role1.getId());
						roles1.add(role);
					}
					return roles1;
				}
			}
		}else{
			User user = userServiceImpl.findByUsername(principalUtil.getPrincipal());
			List<Role> roles1 = new ArrayList<Role>();
			if(user != null){
				Set<Role> roles = user.getRoles();
				for(Role role1 : roles){
					Role role = roleService.findByRoleId(role1.getId());
					roles1.add(role);
				}
			}
			return roles1;
		}*/
		User user = userServiceImpl.findByUsername(principalUtil.getPrincipal());
		List<UserRole> userRoles = new ArrayList<UserRole>();
		if(user != null){
			List<UserRole> userRoles2 = user.getUserRoles();
			for (UserRole userRole : userRoles2) {
				UserRole userRole2 = userRoleService.findByRoleId(userRole.getId());
				userRoles.add(userRole2);
			}
		}
		return userRoles;
		
	}
	
	/**
	 * Get ModuleFeatures
	 * @param modelMap
	 * @return
	 */
	
	@RequestMapping(value = "/getmodulefeatures", method = RequestMethod.GET)
	public @ResponseBody List<ModulesFeatures> getModuleFeatures(@RequestParam Integer roleId){
		/*if(principalUtil.getCurrentUserCompanyId() != null){
			Company company = companyService.getByCompanyId(principalUtil.getCurrentUserCompanyId());
			if(company != null){
				User user = userServiceImpl.findByUsernameAndCompany(principalUtil.getPrincipal(), company.getCompanyCode());
				if(user != null){
					List<ModulesFeatures> modulesFeatures = null;
					if(!user.getUsername().equals(ConstantUtil.SUPERADMIN_USERNAME)){
						User user2 = userServiceImpl.findByUserId(company.getAdminUser());
						Set<Role> roles = user2.getRoles();
						for(Role role1 : roles){
							Role role = new Role();
							role = roleService.findByRoleId(role1.getId());
							if(role.getType().equals(ConstantUtil.QUANTITATIVE_ROLE)){
								modulesFeatures = moduleFeatureService.getModulesFeatures(role.getId());
							}else if(role.getType().equals(ConstantUtil.QUALITATIVE_ROLE)){
								modulesFeatures = moduleFeatureService.getModulesFeatures(role.getId());
							}else if(role.getType().equals(ConstantUtil.QUANTITATIVE_ROLE) && role.getType().equals(ConstantUtil.QUALITATIVE_ROLE)){
								modulesFeatures = moduleFeatureService.getModulesFeatures(role.getId());
							}
						}
					}
					return modulesFeatures;
				}
			}
		}else{
			List<ModulesFeatures> modulesFeatures;
			modulesFeatures = moduleFeatureService.findModulesFeatures();
			return modulesFeatures;
		}*/
		if(roleId != null){
			UserRole userRole = userRoleService.findByRoleId(roleId);
			List<ModulesFeatures> modulesFeatures = new ArrayList<ModulesFeatures>();
			List<ModulesFeatures> modulesFeatures1 = new ArrayList<ModulesFeatures>();
			if(userRole != null){
				if(userRole.getName().equals("SUPER_ADMIN")){
					modulesFeatures = moduleFeatureService.findModulesFeatures();
					return modulesFeatures;
				}else{
					modulesFeatures = moduleFeatureService.findModulesFeatures();
					for (ModulesFeatures modulesFeatures2 : modulesFeatures) {
						if(userRole.getName().equals(ConstantUtil.PROJECTADMIN_ROLE) || userRole.getName().equals(ConstantUtil.PROJECT_PLANNER)){
							if(modulesFeatures2.getModule().getModule().equals("Project") ||
							   modulesFeatures2.getModule().getModule().equals("Dashboard") ||
							   modulesFeatures2.getModule().getModule().equals("Reports")||
							   modulesFeatures2.getModule().getModule().equals("Quarter")||
							   modulesFeatures2.getModule().getModule().equals("Reporting Period")){
								modulesFeatures1.add(modulesFeatures2);
							}
						}else{
							if(modulesFeatures2.getModule().getModule().equals("Project") ||
							   modulesFeatures2.getModule().getModule().equals("Dashboard") ||
							   modulesFeatures2.getModule().getModule().equals("Reports")){
								modulesFeatures1.add(modulesFeatures2);
							}
						}
					}
				}
				return modulesFeatures1;
			}
		}
		return null;
	}
	
	/**
	 * Get Current user roles
	 * @param modelMap
	 * @return
	 */
	
	@RequestMapping(value = "/getUserRoleName", method = RequestMethod.GET)
	public @ResponseBody UserRole getUserRoleName(@RequestParam Integer roleId){
		if(roleId != null){
			UserRole userRole = userRoleService.findByRoleId(roleId);
			return userRole;
		}
		return null;
		
	}
	
	/**
	 * Get Features by roleid and moduleId
	 * @param modelMap
	 * @return
	 */
	
	@RequestMapping(value = "/getfeaturesByRoleidAndmoduleId", method = RequestMethod.GET)
	public @ResponseBody List<Feature> getfeaturesByRoleidAndmoduleId(@RequestParam("id") Integer roleId,@RequestParam("mid") Integer moduleId){
		/*if(principalUtil.getCurrentUserCompanyId() != null){
			Company company = companyService.getByCompanyId(principalUtil.getCurrentUserCompanyId());
			if(company != null){
				User user = userServiceImpl.findByUsernameAndCompany(principalUtil.getPrincipal(), company.getCompanyCode());
				if(user != null){
					if(!user.getUsername().equals(ConstantUtil.SUPERADMIN_USERNAME)){
						User user2 = userServiceImpl.findByUserId(company.getAdminUser());
						List<Feature> features = null;
						if(user2 != null){
							Set<Role> roles = user2.getRoles();
							for (Role role : roles) {
								if(role.getType().equals(ConstantUtil.QUANTITATIVE_ROLE)){
									if(role.getId() == roleId){
										features = featureService.findByRoleIdAndModuleId(role.getId(), moduleId);
									}else{
										features = featureService.findByRoleIdAndModuleId(roleId, moduleId);
										if(features.size() == 0){
											features = featureService.findByRoleIdAndModuleId(role.getId(), moduleId);
										}
								}
								}else if(role.getType().equals(ConstantUtil.QUALITATIVE_ROLE)){
									if (role.getId() == roleId) {
										features = featureService.findByRoleIdAndModuleId(role.getId(), moduleId);
									} else {
										features = featureService.findByRoleIdAndModuleId(roleId, moduleId);
										if (features.size() == 0) {
											features = featureService.findByRoleIdAndModuleId(role.getId(),moduleId);
										}
									}
								}
							}
						}
						return features;
					}
				}
			}
		}else{
			List<Feature> features = null;
			features = featureService.findByRoleIdAndModuleId(roleId, moduleId);
			return features;
		}*/
		List<Feature> features = null;
		features = featureService.findByRoleIdAndModuleId(roleId, moduleId);
		return features;
	}
	
	@RequestMapping(value = "/updaterolefeatures", method = RequestMethod.POST)
	public @ResponseBody int updateFeatureList(@RequestBody RoleFeatureList roleFeatureList) {
		return roleModuleFeatureService.save(roleFeatureList);
	}
	
}
