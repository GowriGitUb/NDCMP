/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ceainfotech.ndcmp.model.Module;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.service.ModuleService;
import com.ceainfotech.ndcmp.service.RoleModuleFeatureService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.serviceimp.UserServiceImpl;
import com.ceainfotech.ndcmp.util.PrincipalUtil;

/**
 * @author jeeva
 *
 */
@Controller
@RequestMapping(value = "/api/**")
public class ModuleController {
	
	@Autowired
	private RoleModuleFeatureService roleModuleFeatureService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private PrincipalUtil principalUtil;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRoleService userRoleService;
	
	/**
	 * To get the modules
	 */

	@RequestMapping(value = "getModules", method = RequestMethod.GET)
	public @ResponseBody List<Module> getModuleDetail(Model model) {
		/*try {
			if(principalUtil.getCurrentUserCompanyId() != null){
				Company company = companyService.getByCompanyId(principalUtil.getCurrentUserCompanyId());
				if(company != null){
					User user = userServiceImpl.findByUsernameAndCompany(principalUtil.getPrincipal(), company.getCompanyCode());
					if(user != null){
						List<Module> modules2 = new ArrayList<Module>();
						Set<Role> roles = user.getRoles();
						if(roles.size() > 0){
							Role role = new Role();
							for(Role role1 : roles){
								if(role1 != null){
									role = roleService.findByRoleId(role1.getId());
									if(role.getType().equals(ConstantUtil.QUANTITATIVE_ROLE)){
										modules2 = moduleService.getModuleByRole(role.getId());
									}else if(role.getType().equals(ConstantUtil.QUALITATIVE_ROLE)){
										modules2 = moduleService.getModuleByRole(role.getId());
									}else if(role.getType().equals(ConstantUtil.QUANTITATIVE_ROLE) && role.getType().equals(ConstantUtil.QUALITATIVE_ROLE)){
										modules2 = moduleService.getModuleByRole(role.getId());
									}else{
										modules2 = moduleService.getModuleByRoleAndCompany(role.getId(),company);
									}
								}
							}
							
							model.addAttribute("userType", role.getType());
							return modules2;
						}
					}
				}
		}else{
			User superadimUser = userServiceImpl.findByUsername(principalUtil.getPrincipal());
			if(superadimUser != null){
				Set<Role> roles = superadimUser.getRoles();
				if(roles.size() > 0){
					Role role = new Role();
					for(Role role1 : roles){
						if(role1 != null){
							role = roleService.findByRoleId(role1.getId());
						}
					}
					List<Module> modules2 = moduleService.getModuleByRole(role.getId());
					model.addAttribute("userType", role.getType());
					return modules2;
				}
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	
		User superadimUser = userServiceImpl.findByUsername(PrincipalUtil.getPrincipal());
		if(superadimUser != null){
			List<UserRole> roles = superadimUser.getUserRoles();
			if(roles.size() > 0){
				UserRole role = new UserRole();
				for(UserRole role1 : roles){
					if(role1 != null){
						role = userRoleService.findByRoleId(role1.getId());
					}
				}
				List<Module> modules2 = moduleService.getModuleByRole(role.getId());
				model.addAttribute("userType", role.getName());
				return modules2;
			}
		}	
		return null;
	}
	
	/**
	 * To get the Role
	 */

	@RequestMapping(value = "getRoles", method = RequestMethod.GET)
	public @ResponseBody UserRole getRoleDetail(Model model) {
		/*if(principalUtil.getCurrentUserCompanyId() != null){
			Company company = companyService.getByCompanyId(principalUtil.getCurrentUserCompanyId());
			if(company != null){
				User user = userServiceImpl.findByUsernameAndCompany(principalUtil.getPrincipal(), company.getCompanyCode());
				if(user != null){
					Set<Role> roles = user.getRoles();
					if(roles.size() > 0){
						Role role = new Role();
						for(Role role1 : roles){
							role = roleService.findByRoleId(role1.getId());
						}
						return role;
					}
				}
			}
			
		}else{
			User superadimUser = userServiceImpl.findByUsername(principalUtil.getPrincipal());
			if(superadimUser != null){
				Set<Role> roles = superadimUser.getRoles();
				if(roles.size() > 0){
					Role role = new Role();
					for(Role role1 : roles){
						role = roleService.findByRoleId(role1.getId());
					}
					return role;
				}
			}
		}*/
		User superadimUser = userServiceImpl.findByUsername(principalUtil.getPrincipal());
		if(superadimUser != null){
			List<UserRole> roles = superadimUser.getUserRoles();
			if(roles.size() > 0){
				UserRole role = new UserRole();
				for(UserRole role1 : roles){
					role = userRoleService.findByRoleId(role1.getId());
				}
				return role;
			}
		}
		return null;
	}
	
	/**
	 * To get the All Roles
	 */
	@RequestMapping(value = "getAllRoles", method = RequestMethod.GET)
	public @ResponseBody List<UserRole> getAllRoleDetail(Model model) {
		List<UserRole> userRoles = userRoleService.listAllRoles();
		if(userRoles.size() > 0){
			return userRoles;
		}
		return null;
	}
	
	/**
	 * To get the All Roles
	 */
	@RequestMapping(value = "getCurrentLoginUrlModuleName", method = RequestMethod.GET)
	public @ResponseBody String getCurrentLoginUrlModuleName(@RequestParam("moduleName") String moduleName) {
		/*if(principalUtil.getCurrentUserCompanyId() != null){
			if(moduleName.equals(ConstantUtil.INDUSTRY_LIST) ||
			   moduleName.equals(ConstantUtil.COMPANY_LIST) ||
			   moduleName.equals(ConstantUtil.TITLE_LIST)){
				return ConstantUtil.SUPERADMIN_ROLE;
			}else{
				return ConstantUtil.QUANTITATIVE_ROLE;
			}
		}else{
			if(moduleName.equals(ConstantUtil.INDUSTRY_LIST) ||
			   moduleName.equals(ConstantUtil.COMPANY_LIST) ||
			   moduleName.equals(ConstantUtil.TITLE_LIST)){
					return ConstantUtil.SUPERADMIN_ROLE;
			}
		}*/
		return null;
	}


}
