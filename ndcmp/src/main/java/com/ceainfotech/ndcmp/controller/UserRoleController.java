/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ceainfotech.ndcmp.model.Auditing;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.repository.UserRoleRepository;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.Helper;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;

/**
 * @author Gowri
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
public class UserRoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserRoleService userRoleServices;

	@Autowired
	private UserService userService;
	
	@Autowired
	private Helper helper;
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	/**
	 * List out all the user role details
	 * 
	 * @param model
	 * @param authentication
	 * @return
	 */

	@RequestMapping(value = "roleList", method = RequestMethod.GET)
	public String userRoleDetailsPage(ModelMap model, HttpServletRequest request, Authentication authentication) {
		LOGGER.info("Getting into user Role details page with user {} ");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			/*boolean accessRightsEdit = false;
			boolean accessRightsActivateOrDeactivate = false;
			boolean accessRightsView = false;*/
			//HttpSession session = request.getSession();
			//String username = (String) session.getAttribute("username");
			// getAccessRights(model, username);
			Integer userRoleCount = userRoleServices.getByRoleId();
			if(userRoleCount != null) {
				model.addAttribute("userRoleCount", userRoleCount);
			}
			List<UserRole> userRoles = userRoleServices.listAllRoles();
			List<UserRole> listRoles = new ArrayList<UserRole>();
			for (UserRole userRole : userRoles) {
				if (!userRole.getName().equalsIgnoreCase("SUPER_ADMIN")) {
					listRoles.add(userRole);
				}
			}
			/*UserRoleFeature userRoleFeature = helper.getCurrentUserRoleFeatureForUserRole();
			if(userRoleFeature != null) {
				accessRightsEdit = (userRoleFeature.getUpdate() != null) ? userRoleFeature.getUpdate() : false;
				accessRightsActivateOrDeactivate = (userRoleFeature.getActivateOrdeactivate() != null) ? userRoleFeature.getActivateOrdeactivate() : false;
				accessRightsView = (userRoleFeature.getView() != null) ? userRoleFeature.getView() : false;
			}*/
			model.addAttribute("roleList", listRoles);
			/*model.addAttribute("accessRightsEdit", accessRightsEdit);
			model.addAttribute("accessRightsActivateOrDeactivate", accessRightsActivateOrDeactivate);
			model.addAttribute("accessRightsView", accessRightsView);*/
			return "user/roleList";
		}else{
			return "login";
		}
		
	}

	/**
	 * Get User Role By ID
	 * 
	 * @param userRole
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getUserrole", method = RequestMethod.GET)
	public ModelAndView getUserRole(@ModelAttribute UserRole userRole, @RequestParam Integer id) {

		LOGGER.info("Get User Role By Id");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView modelAndView = new ModelAndView("user/roleadd");
			if (userRole.getId() != 0) {
				userRole = userRoleServices.findByRoleId(userRole.getId());
				if (userRole != null) {
					modelAndView.addObject("userRole", userRole.getDescription());
					//Added by mani
					modelAndView.addObject("role", userRole);
					return modelAndView;
				}
			}
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
	}

	/**
	 * Create User Role
	 * 
	 * @param userRole
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveUserrole", method = RequestMethod.POST)
	public ModelAndView createUserRole(@ModelAttribute UserRole userRole) throws Exception {
		String action,module="";
		LOGGER.info("Create User Role");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView modelAndView = new ModelAndView();
			if (userRole.getName() != null) {
				UserRole role = new UserRole();
				if (userRole.getId() == 0) {
					role = userRoleServices.findByName(userRole.getName());
				} else if (userRole.getId() != 0) {
					role = userRoleServices.findByNameAndId(userRole.getName(), userRole.getId().intValue());
					if (role != null) {
						if (role.getName().equals(userRole.getName())) {
							userRole.setStatus(Status.ACTIVE.getName());
							userRoleServices.addRole(userRole);
							
							action="User Role "+userRole.getName()+" is Updated";
							module="User Role Module";
							constantUtil.saveAudition(action, module, request);
							
							return new ModelAndView("redirect:roleList");
						} else {
							userRole.setStatus(Status.ACTIVE.getName());
							userRoleServices.addRole(userRole);
							return new ModelAndView("redirect:roleList");
						}
					} else {
						role = userRoleServices.findByName(userRole.getName());
						if (role != null) {
							modelAndView.addObject("errormsg", "Role Already Exists");
							modelAndView.addObject("userRole", userRole);
							modelAndView.setViewName("user/roleadd");
							return modelAndView;
						}
					}
				}
				if (role != null) {
					modelAndView.addObject("errormsg", "Role Already Exists");
					modelAndView.addObject("userRole", userRole);
					modelAndView.setViewName("user/roleadd");
					return modelAndView;
				}
			}
			userRole.setStatus(Status.ACTIVE.getName());
			userRoleServices.addRole(userRole);
			action="User Role "+userRole.getName()+" is Saved";
			module="User Role Module";
			constantUtil.saveAudition(action, module, request);

			return new ModelAndView("redirect:roleList");
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Delete User Role
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteUserRole", method = RequestMethod.GET)
	public ModelAndView deleteUserRole(@RequestParam Integer id) {

		LOGGER.info("Delete User Role");
		userRoleServices.deleteRole(id);
		return new ModelAndView("redirect:roleList");
	}

	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean ROL_ADD = false;
		boolean ROL_VIW = false;
		boolean ROL_EDT = false;
		boolean ROL_DEL = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.ROL_ADD.toString())) {
						ROL_ADD = true;
					}
					if (feature.equals(Modules.ROL_VIW.toString())) {
						ROL_VIW = true;
					}
					if (feature.equals(Modules.ROL_EDT.toString())) {
						ROL_EDT = true;
						;
					}
					if (feature.equals(Modules.ROL_DEL.toString())) {
						ROL_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("ROL_ADD", ROL_ADD);
		modelMap.addAttribute("ROL_VIW", ROL_VIW);
		modelMap.addAttribute("ROL_EDT", ROL_EDT);
		modelMap.addAttribute("ROL_DEL", ROL_DEL);*/
	}
	
	
	//Active user Role
		@RequestMapping(value = "activeUserRole", method = RequestMethod.GET)
		public ModelAndView activeUserRole(@RequestParam Integer id, Model model) throws Exception{
			String action,module="";
			if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
				if(id != null){
					UserRole userRole= userRoleServices.findByRoleId(id);
					if(userRole != null){
						userRole.setUserRoleStatus(true);
						userRole.setStatus(Status.ACTIVE.getName());
						userRoleServices.addRole(userRole);
						
						action="User Role "+userRole.getName()+" is Activated";
						module="User Role Module";
						constantUtil.saveAudition(action, module, request);
						return new ModelAndView("redirect:roleList");
					}
				}
				return null;
			}else{
				return new ModelAndView("login");
			}
			
		}
		
		//Deactive user role
		@RequestMapping(value = "deActiveUserRole", method = RequestMethod.GET)
		public ModelAndView deActiveUserRole(@RequestParam Integer id, Model model) throws Exception{
			String action,module="";
			if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
				if(id != null){
					UserRole userRole= userRoleServices.findByRoleId(id);
					if(userRole != null){
						userRole.setUserRoleStatus(false);
						userRole.setStatus(Status.INACTIVE.getName());
						userRoleServices.addRole(userRole);
						
						action="User Role "+userRole.getName()+" is deActivated";
						module="User Role Module";
						constantUtil.saveAudition(action, module, request);
						
						return new ModelAndView("redirect:roleList");
					}
				}
				return null;
			}else{
				return new ModelAndView("login");
			}
		}

		/**
		 * Get role information based on role id
		 * @param roleId
		 * @return
		 */
		@RequestMapping(value = "getRoleInformation", method = RequestMethod.GET)
		public @ResponseBody UserRole getUserRole(@RequestParam Integer roleId) {
			UserRole userRole = new UserRole();
			if (roleId != 0) {
				userRole = userRoleServices.findByRoleId(roleId);
			}
			return userRole;
		}
}
