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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.model.UserRole;
import com.ceainfotech.ndcmp.model.dto.AgencyDTO;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.CountryService;
import com.ceainfotech.ndcmp.service.RegionService;
import com.ceainfotech.ndcmp.service.StatesService;
import com.ceainfotech.ndcmp.service.UserRoleService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;

/**
 * @author Gowri
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
@PropertySource(value = { "classpath:application.properties" })
public class AgencyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AgencyController.class);

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	CountryService countryService;

	@Autowired
	StatesService stateService;

	@Autowired
	RegionService regionService;

	@Autowired
	AgencyService agencyService;

	@Autowired
	UserService userService;
	
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	Environment environment;
	
	String action,module="";
	
	/**
	 * To list all the agencies
	 * 
	 * @param model
	 * @param request
	 * @param authenticationconfiguration
	 * @return
	 */

	@RequestMapping(value = "agencyList", method = RequestMethod.GET)
	public String userRoleDetailsPage(ModelMap model, HttpServletRequest request, Authentication authentication) {
		LOGGER.info("List all agency");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			List<Agency> agencies = agencyService.listAllAgency();
			/*List<Agency> listAgencies = new ArrayList<Agency>();
			List<Agency> agencies = agencyService.listAllAgency();
			if(agencies != null && agencies.size() > 0) {
				for(Agency agency : agencies) {
					if(agency != null && agency.getId() != null) {
						if(agency.getAgencyType().equals("LEAD_AGENCY")) {
							agency.setAgencyType("Lead Agency");
							listAgencies.add(agency);
						}
						if(agency.getAgencyType().equals("PARTNER_AGENCY")) {
							agency.setAgencyType("Partner Agency");
							listAgencies.add(agency);
						}
					}
				}
			}*/
			model.addAttribute("listAgencies", agencies);
			return "agency/agencyList";
		}else{
			return "login";
		}
	}

	/**
	 * Get the add page of the agency
	 * 
	 * @param agency
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/getAgency", method = RequestMethod.GET)
	public @ResponseBody AgencyDTO getAgency(@RequestParam Integer id) {
		LOGGER.info("Get Agency");
		
		String approverAgency = environment.getRequiredProperty("approver.agency");
		
		List<Agency> agencies = new ArrayList<Agency>();
		Agency agency = null;
		List<User> notAssignedUsers = new ArrayList<User>();
		List<Countries> countryList = countryService.findByStatus(Status.ACTIVE.getName());
		List<States> stateList = stateService.findByStatus(Status.ACTIVE.getName());
		List<Region> regionList = regionService.findByStatus(Status.ACTIVE.getName());
		List<User> users = new ArrayList<User>();
		if (id != 0) {// get agency for edit
			agency = agencyService.findByAgencyId(id);
			agency.setCountryId(agency.getCountry().getId());
			agency.setStateId(agency.getState().getId());
			agency.setRegionId(agency.getRegion().getId());
			List<Integer> ids = new ArrayList<Integer>();
			for (User user : agency.getAgencyAuthority()) {
				ids.add(user.getId());
			}
			agency.setRoleIds(ids);
			agencies.add(agency);
		}
//			List<User> users = userService.getUsersByRoleStatusUpdaterAndReviewer();
//			AgencyDTO agencyDTO = new AgencyDTO(users, countryList, stateList, regionList, agency);
//			return agencyDTO;
//		}  else {
			List<User> overAllUser = new ArrayList<User>();
			if(agency != null){
				if(agency.getCode().equals(approverAgency)){
					users = userService.getUsersByRoleStatusApprover();
			}else {
				users = userService.getUsersByRoleStatusUpdaterAndReviewer();
			}
			}
			
			
			if(users != null && users.size() > 0) {
				List<Agency> listAgency = agencyService.findByStatus(Status.ACTIVE.getName());
				if(listAgency != null && listAgency.size() > 0) {
					for(Agency userAgency : listAgency) {
						if(agency != null){
							if(userAgency.getId() != agency.getId()){
								List<User> agenyUser = userAgency.getAgencyAuthority();
								if(agenyUser != null && agenyUser.size() > 0) {
									for(User aUser : agenyUser) {
										overAllUser.add(aUser);
									}
								}
							}
						}else{
							List<User> agenyUser = userAgency.getAgencyAuthority();
							if(agenyUser != null && agenyUser.size() > 0) {
								for(User aUser : agenyUser) {
									overAllUser.add(aUser);
								}
							}
						}
					}
				}
			}
			List<Integer> overAllUsersIds = new ArrayList<Integer>();
			List<Integer> allUsersIds = new ArrayList<Integer>();
			for (User user1 : overAllUser) {
				overAllUsersIds.add(user1.getId());
			}
			for (User user1 : users) {
				allUsersIds.add(user1.getId());
			}
			List<Integer> removedUsersList = new ArrayList<Integer>(overAllUsersIds);
			removedUsersList.addAll(allUsersIds);
			List<Integer> intersectionUsers = new ArrayList<Integer>(overAllUsersIds);
			intersectionUsers.retainAll(allUsersIds);
			removedUsersList.removeAll(intersectionUsers);
			for (Integer integer : removedUsersList) {
				User user = userService.getById(integer);
				if(user != null){
					if(agency.getCode().equals(approverAgency)){
						for (User user2 : users) {
							if(user.getId() == user2.getId()){
								notAssignedUsers.add(user2);
							}
							
						}
					}else {
						Agency agency2 = agencyService.findByAgencyId(user.getAgencyId());
						if(agency2 != null && !agency2.getName().equals(approverAgency)){
							notAssignedUsers.add(user);
						}
						
					}
					
				}	
			}
		AgencyDTO agencyDTO = new AgencyDTO(notAssignedUsers, countryList, stateList, regionList, agency);
		return agencyDTO;
	}

	@RequestMapping(value = "/addAgency", method = RequestMethod.GET)
	public ModelAndView addAgency(@ModelAttribute Agency agency, @RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView("agency/agencyAdd");
			LOGGER.info("Add Agency");
//			getList(modelAndView);
			return modelAndView;
	}

	/**
	 * TO get the all the details
	 * 
	 * @param modelAndView
	 */

	/*private void getList(ModelAndView modelAndView) {
		List<UserRole> listRoles = userRoleService.findByStatus(Status.ACTIVE.getName());
		List<UserRole> roleList = new ArrayList<UserRole>();
		for (UserRole userRole : listRoles) {
			if (userRole.getName().equalsIgnoreCase("LEAD_AGENCY") || userRole.getName().equalsIgnoreCase("PARTNER_AGENCY")) {
				roleList.add(userRole);
			}
		}
		List<Countries> countryList = countryService.findByStatus(Status.ACTIVE.getName());
		List<States> stateList = stateService.findByStatus(Status.ACTIVE.getName());
		List<Region> regionList = regionService.findByStatus(Status.ACTIVE.getName());
//		List<User> userList = userService.getUsers(Status.ACTIVE.getName());
		List<User> userList = userService.getUsersByRoleStatusUpdaterAndReviewer();
//		modelAndView.addObject("roleList", roleList);
		modelAndView.addObject("countryList", countryList);
		modelAndView.addObject("stateList", stateList);
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("regionList", regionList);
	}*/

	/**
	 * Save the agency details
	 * 
	 * @param agency
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveAgency", method = RequestMethod.POST)
	public ModelAndView saveAgency(@ModelAttribute Agency agency) throws Exception {
		LOGGER.debug("Create Agency");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			if (agency.getId() == null || agency.getId() == 0) {
				Agency agencyCode = agencyService.findByAgencyCode(agency.getCode());
				Agency agencyName = agencyService.findByName(agency.getCode());
				if(agencyCode != null){
					ModelAndView view = new ModelAndView("agency/agencyAdd");
					view.addObject("codeExitMessage", "Agency Code Already Exists");
					List<UserRole> listRoles = userRoleService.findByStatus(Status.ACTIVE.getName());
					List<UserRole> roleList = new ArrayList<UserRole>();
					for (UserRole userRole : listRoles) {
						if (userRole.getName().equalsIgnoreCase("STATUS_REVIEWER") || userRole.getName().equalsIgnoreCase("STATUS_UPDATER")) {
							roleList.add(userRole);
						}
					}
					List<Countries> countryList = countryService.findByStatus(Status.ACTIVE.getName());
					List<States> stateList = stateService.findByStatus(Status.ACTIVE.getName());
					List<Region> regionList = regionService.findByStatus(Status.ACTIVE.getName());
					List<User> userList = userService.getUsers(Status.ACTIVE.getName());
					view.addObject("roleList", roleList);
					view.addObject("countryList", countryList);
					view.addObject("stateList", stateList);
					view.addObject("userList", userList);
					view.addObject("regionList", regionList);
					view.addObject("agency", agency);
					return view;
				}
				
				if(agencyName != null){
					ModelAndView view = new ModelAndView("agency/agencyAdd");
					view.addObject("nameExitMessage", "Agency Name Already Exists");
					List<UserRole> listRoles = userRoleService.findByStatus(Status.ACTIVE.getName());
					List<UserRole> roleList = new ArrayList<UserRole>();
					for (UserRole userRole : listRoles) {
						if (userRole.getName().equalsIgnoreCase("STATUS_REVIEWER") || userRole.getName().equalsIgnoreCase("STATUS_UPDATER")) {
							roleList.add(userRole);
						}
					}
					List<Countries> countryList = countryService.findByStatus(Status.ACTIVE.getName());
					List<States> stateList = stateService.findByStatus(Status.ACTIVE.getName());
					List<Region> regionList = regionService.findByStatus(Status.ACTIVE.getName());
					List<User> userList = userService.getUsers(Status.ACTIVE.getName());
					view.addObject("roleList", roleList);
					view.addObject("countryList", countryList);
					view.addObject("stateList", stateList);
					view.addObject("userList", userList);
					view.addObject("regionList", regionList);
					view.addObject("agency", agency);
					return view;
				}
			}else{
				Agency agencyCode = agencyService.findByAgencyCode(agency.getCode());
				Agency agencyName = agencyService.findByName(agency.getCode());
				Agency agencyCodeWithId = agencyService.findByIdAndCode(agency.getId(), agency.getCode());
				Agency agencyNameWithId = agencyService.findByIdAndName(agency.getId(), agency.getCode());
				
				if(agencyCodeWithId != null){
				}else if(agencyCode != null){
					ModelAndView view = new ModelAndView("agency/agencyAdd");
					view.addObject("codeExitMessage", "Agency Code Already Exists");
					List<UserRole> listRoles = userRoleService.findByStatus(Status.ACTIVE.getName());
					List<UserRole> roleList = new ArrayList<UserRole>();
					for (UserRole userRole : listRoles) {
						if (userRole.getName().equalsIgnoreCase("STATUS_REVIEWER") || userRole.getName().equalsIgnoreCase("STATUS_UPDATER")) {
							roleList.add(userRole);
						}
					}
					List<Countries> countryList = countryService.findByStatus(Status.ACTIVE.getName());
					List<States> stateList = stateService.findByStatus(Status.ACTIVE.getName());
					List<Region> regionList = regionService.findByStatus(Status.ACTIVE.getName());
					List<User> userList = userService.getUsers(Status.ACTIVE.getName());
					view.addObject("roleList", roleList);
					view.addObject("countryList", countryList);
					view.addObject("stateList", stateList);
					view.addObject("userList", userList);
					view.addObject("regionList", regionList);
					view.addObject("agency", agency);
					return view;
				}
				
				if(agencyNameWithId != null){
				}else if(agencyName != null){
					ModelAndView view = new ModelAndView("agency/agencyAdd");
					view.addObject("nameExitMessage", "Agency Name Already Exists");
					List<UserRole> listRoles = userRoleService.findByStatus(Status.ACTIVE.getName());
					List<UserRole> roleList = new ArrayList<UserRole>();
					for (UserRole userRole : listRoles) {
						if (userRole.getName().equalsIgnoreCase("STATUS_REVIEWER") || userRole.getName().equalsIgnoreCase("STATUS_UPDATER")) {
							roleList.add(userRole);
						}
					}
					List<Countries> countryList = countryService.findByStatus(Status.ACTIVE.getName());
					List<States> stateList = stateService.findByStatus(Status.ACTIVE.getName());
					List<Region> regionList = regionService.findByStatus(Status.ACTIVE.getName());
					List<User> userList = userService.getUsers(Status.ACTIVE.getName());
					view.addObject("roleList", roleList);
					view.addObject("countryList", countryList);
					view.addObject("stateList", stateList);
					view.addObject("userList", userList);
					view.addObject("regionList", regionList);
					view.addObject("agency", agency);
					return view;
				}
			}
			
			List<User> users = new ArrayList<User>();
//			List<Integer> previousIds = age
			Agency agency3 = agencyService.findByAgencyId(agency.getId());
			List<Integer> ids = agency.getRoleIds();
			if (ids != null && ids.size() > 0) {
				if(agency3 != null){
					for(User user : agency3.getAgencyAuthority()){
						int temp = 1;
						for (Integer id : ids) {
							User user2 = userService.getById(id);
							if(user2.getId() == user.getId()){
								temp = 0;
								user.setAgencyId(agency3.getId());
								userService.add(user);
							}
						}if(temp == 1){
							user.setAgencyId(null);
							userService.add(user);
						}
					}
				}
				for (Integer id : ids) {
					User user = userService.getById(id);
					user.setAgencyId(agency.getId());
					users.add(user);
					Agency agency2 = agencyService.findByLoginUser(user.getUsername());
					List<User> list = new ArrayList<User>();
					if(agency2 != null){
						for (User user2: agency2.getAgencyAuthority()) {
							if(user2.getId() != user.getId()){
								list.add(user2);
							}
						}agency2.setAgencyAuthority(list);
						agencyService.addAgency(agency2);
						userService.add(user);
					}else {
						userService.add(user);
					}
				}
			}else {
				if(agency3 != null){
					if(agency3.getAgencyAuthority() != null){
						List<User> user = agency3.getAgencyAuthority();
						for (User user2 : user) {
							user2.setAgencyId(null);
							userService.add(user2);
						}
					}
				}
			}
			Countries countries = countryService.getById(agency.getCountry().getId());
			States states = stateService.getById(agency.getState().getId());
			Region region = regionService.getById(agency.getRegion().getId());
			
			agency.setCountry(countries);
			agency.setState(states);
			agency.setRegion(region);
			agency.setAgencyAuthority(users);
			agency.setStatus(Status.ACTIVE.getName());
			agencyService.addAgency(agency);
			module="Agency Module";
			
			if(agency.getId() != 0){
				action="Agency "+agency.getCode()+" is Updated";
				constantUtil.saveAudition(action, module, request);
			}else{
				action="Agency "+agency.getCode()+" is Saved";
				constantUtil.saveAudition(action, module, request);
			}
			return new ModelAndView("redirect:agencyList");
		}else{
			return new ModelAndView("login");
		}
		
		
		/*if (agency != null) {
			// add agency
			Agency agency2 = new Agency();
			if (agency.getId() == 0) {
				agency2 = agencyService.findByNameAndAgencyType(agency.getCode(), agency.getAgencyType());
			} else if (agency.getId() != 0) {
				agency2 = agencyService.findByNameAndIdAndAgencyType(agency.getCode(), agency.getId().intValue(), agency.getAgencyType());
				if (agency2 != null) {
					if (agency2.getName().equals(agency.getCode())) {
						UserRole userRole = userRoleService.findByRoleId(Integer.parseInt(agency.getAgencyType()));
						Countries countries = countryService.getById(agency.getCountry().getId());
						States states = stateService.getById(agency.getState().getId());
						Region region = regionService.getById(agency.getRegion().getId());
						List<User> users = new ArrayList<User>();
						List<Integer> ids = agency.getRoleIds();
						if (ids != null && ids.size() > 0) {
							for (Integer id : ids) {
								User user = userService.getById(id);
								users.add(user);
							}
						}
						agency.setAgencyAuthority(users);
//						agency.setAgencyType(userRole.getName());
						agency.setCountry(countries);
						agency.setState(states);
						agency.setRegion(region);
						agency.setStatus(Status.ACTIVE.getName());
						agencyService.addAgency(agency);
						return new ModelAndView("redirect:agencyList");
					} else {
						UserRole userRole = userRoleService.findByRoleId(Integer.parseInt(agency.getAgencyType()));
						Countries countries = countryService.getById(agency.getCountry().getId());
						States states = stateService.getById(agency.getState().getId());
						Region region = regionService.getById(agency.getRegion().getId());
						List<User> users = new ArrayList<User>();
						List<Integer> ids = agency.getRoleIds();
						if (ids != null && ids.size() > 0) {
							for (Integer id : ids) {
								User user = userService.getById(id);
								users.add(user);
							}
						}
						agency.setAgencyAuthority(users);
						agency.setAgencyType(userRole.getName());
						agency.setCountry(countries);
						agency.setState(states);
						agency.setRegion(region);
						agency.setStatus(Status.ACTIVE.getName());
						agencyService.addAgency(agency);
						return new ModelAndView("redirect:agencyList");
					}
				} else {
					agency2 = agencyService.findByNameAndAgencyType(agency.getCode(), agency.getAgencyType());
					if (agency2 != null) {
						modelAndView.addObject("errormsg", "Agency Name Already Exists For This Agency Type");
						UserRole userRole = userRoleService.findByRoleId(Integer.parseInt(agency.getAgencyType()));
						Countries countries = countryService.getById(agency.getCountry().getId());
						States states = stateService.getById(agency.getState().getId());
						Region region = regionService.getById(agency.getRegion().getId());
						List<User> users = new ArrayList<User>();
						List<Integer> ids = agency.getRoleIds();
						if (ids != null && ids.size() > 0) {
							for (Integer id : ids) {
								User user = userService.getById(id);
								users.add(user);
							}
						}
						agency.setAgencyAuthority(users);
						agency.setAgencyType(userRole.getName());
						agency.setCountry(countries);
						agency.setState(states);
						agency.setRegion(region);
						getList(modelAndView);
						modelAndView.addObject("agency", agency);
						modelAndView.setViewName("agency/agencyAdd");
						return modelAndView;
					}
				}
			}

			if (agency2 != null) {
				modelAndView.addObject("errormsg", "Agency Name Already Exists For This Agency Type");
				UserRole userRole = userRoleService.findByRoleId(Integer.parseInt(agency.getAgencyType()));
				Countries countries = countryService.getById(agency.getCountry().getId());
				States states = stateService.getById(agency.getState().getId());
				Region region = regionService.getById(agency.getRegion().getId());
				List<User> users = new ArrayList<User>();
				List<Integer> ids = agency.getRoleIds();
				if (ids != null && ids.size() > 0) {
					for (Integer id : ids) {
						User user = userService.getById(id);
						users.add(user);
					}
				}
				agency.setAgencyType(userRole.getName());
				agency.setCountry(countries);
				agency.setState(states);
				agency.setRegion(region);
				agency.setAgencyAuthority(users);
				getList(modelAndView);
				modelAndView.addObject("agency", agency);
				modelAndView.setViewName("agency/agencyAdd");
				return modelAndView;
			}

			List<User> users = new ArrayList<User>();
			List<Integer> ids = agency.getRoleIds();
			if (ids != null && ids.size() > 0) {
				for (Integer id : ids) {
					User user = userService.getById(id);
					users.add(user);
				}
			}
			UserRole userRole = userRoleService.findByRoleId(Integer.parseInt(agency.getAgencyType()));
			Countries countries = countryService.getById(agency.getCountry().getId());
			States states = stateService.getById(agency.getState().getId());
			Region region = regionService.getById(agency.getRegion().getId());
			agency.setAgencyType(userRole.getName());
			agency.setCountry(countries);
			agency.setState(states);
			agency.setRegion(region);
			agency.setAgencyAuthority(users);
			agency.setStatus(Status.ACTIVE.getName());
			agencyService.addAgency(agency);
			return new ModelAndView("redirect:agencyList");
		}*/
//		return modelAndView;
	}

	/**
	 * Delete the agency by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteAgency", method = RequestMethod.GET)
	public ModelAndView deleteAgency(@RequestParam Integer id) {
		LOGGER.info("Delete Agency");
		agencyService.deleteAgency(id);
		return new ModelAndView("redirect:agencyList");
	}
	
	//Active AGENCY
			@RequestMapping(value = "activeAgency", method = RequestMethod.GET)
			public ModelAndView activeAgency(@RequestParam Integer id, Model model) throws Exception{
				if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
					if(id != null){
						Agency agency = agencyService.findByAgencyId(id);
						if(agency != null){
							agency.setAgencystatus(true);
							agency.setStatus(Status.ACTIVE.getName());
							agencyService.addAgency(agency);
							
							action="Agency "+agency.getCode()+" is Activated";
							module="Agency Module";
							constantUtil.saveAudition(action, module, request);
							
							return new ModelAndView("redirect:agencyList");
						}
					}
				}
				return new ModelAndView("login");
				
			}
			
			//Deactive AGENCY
			@RequestMapping(value = "deActiveAgency", method = RequestMethod.GET)
			public ModelAndView deActiveAgency(@RequestParam Integer id, Model model) throws Exception{
				if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
					if(id != null){
						Agency agency = agencyService.findByAgencyId(id);
						if(agency != null){
							agency.setAgencystatus(false);
							agency.setStatus(Status.INACTIVE.getName());
							agencyService.addAgency(agency);
							
							action="Agency "+agency.getCode()+" is deActivated";
							module="Agency Module";
							constantUtil.saveAudition(action, module, request);
							
							return new ModelAndView("redirect:agencyList");
						}
					}
				}
				return new ModelAndView("login");
			}

			/*//to get the list of users 
			@RequestMapping(value = "getUsers" , method = RequestMethod.GET)
			public @ResponseBody List<User> getUsers(@RequestParam Integer userRoleId){
				List<User> users = new ArrayList<User>();
				if(userRoleId != null){
					UserRole userRole = userRoleService.findByRoleId(userRoleId);
					users = userService.getByUserRoles(userRole);
					}
				return users;
			}*/
	
			@RequestMapping(value = "getAgencyInformation", method = RequestMethod.GET)
			public @ResponseBody Agency getAgencyInformation(@RequestParam Integer agencyId) {
				Agency agency = new Agency();
				if (agencyId != 0) {
					agency = agencyService.findByAgencyId(agencyId);
				}
				return agency;
			}
}
