/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ceainfotech.ndcmp.model.Project;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.model.User;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.CountryService;
import com.ceainfotech.ndcmp.service.ProjectService;
import com.ceainfotech.ndcmp.service.RegionService;
import com.ceainfotech.ndcmp.service.StatesService;
import com.ceainfotech.ndcmp.service.UserService;
import com.ceainfotech.ndcmp.util.ConstantUtil;
import com.ceainfotech.ndcmp.util.PrincipalUtil;
import com.ceainfotech.ndcmp.util.Status;

/**
 * @author Samy
 * 
 */
@Controller
@RequestMapping(value = "/api/**")
public class StatesController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StatesController.class);

	@Autowired
	private StatesService statesService;

	@Autowired
	RegionService regionService;
	
	@Autowired
	private CountryService countryService;

	@Autowired
	private UserService userService;

	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	AgencyService agencyService; 
	
	@Autowired
	private ConstantUtil constantUtil;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	String action,module="";

	/**
	 * List All States
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "stateslist")
	public ModelAndView listStates(ModelMap modelMap, HttpServletRequest request) {

		LOGGER.info("Listing All States ");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
		//	getAccessRights(modelMap, username);

			modelMap.addAttribute("stateslist", statesService.getStates());
			return new ModelAndView("states/stateslist");
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Get edit details of the page
	 * 
	 * @param stateId
	 * @param states
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/editStates", method = RequestMethod.GET)
	public ModelAndView getStates(@ModelAttribute States states,@RequestParam int id) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView modelAndView = new ModelAndView("states/stateadd");
			if (states.getId() != 0) {
				states = statesService.getById(id);
			}
			List<Countries> countries1 = new ArrayList<Countries>();
			List<Countries> countries = countryService.findByStatus(Status.ACTIVE.getName());
			for (Countries countries2 : countries) {
				countries1.add(countries2);
			}
			modelAndView.addObject("countrylist", countries1);
			modelAndView.addObject("states", states);
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Get the add details of state to save
	 * 
	 * @param states
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/getStates", method = RequestMethod.GET)
	public String createState(@ModelAttribute States states, Model model) {

		LOGGER.info("Creating new state details with page {}: " + states);
		model.addAttribute("states", states);
		List<Countries> countries1 = new ArrayList<Countries>();
		List<Countries> countries = countryService.findByStatus(Status.ACTIVE.getName());
		for (Countries countries2 : countries) {
			countries1.add(countries2);
		}
		model.addAttribute("countrylist", countries1);
		return "states/stateadd";
	}

	/**
	 * Submit edit information or add new state information
	 * 
	 * @param states
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "createState", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute States states) throws Exception {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView modelAndView = new ModelAndView();
			if (states.getId() == null || states.getId() == 0) {
				States stateCode = statesService.findByCode(states.getCode());
				States stateName = statesService.findByName(states.getName());
				if(stateCode != null){
					ModelAndView view = new ModelAndView("states/stateadd");
					view.addObject("codeExitMessage", "State Code Already Exists");
					view.addObject("states",states);
					List<Countries>countries1 = new ArrayList<Countries>();
					List<Countries>countries = countryService.findByStatus(Status.ACTIVE.getName());
					for(Countries countries2 : countries){
						countries1.add(countries2);
					}
					view.addObject("countrylist",countries1);
					return view;
				}
				if(stateName != null){
					ModelAndView view = new ModelAndView("states/stateadd");
					view.addObject("nameExitMessage", "State Name Already Exists");
					view.addObject("states", states);
					List<Countries>countries1 = new ArrayList<Countries>();
					List<Countries>countries = countryService.findByStatus(Status.ACTIVE.getName());
					for(Countries countries2 : countries){
						countries1.add(countries2);
					}
					view.addObject("countrylist",countries1);
					return view;
				}
				states.setStatus(Status.ACTIVE.getName());
				statesService.save(states);
				
				action="State "+states.getName()+" is Saved";
				module="State Module";
				constantUtil.saveAudition(action, module, request);
				
				List<Countries>countries1 = new ArrayList<Countries>();
				List<Countries>countries = countryService.findByStatus(Status.ACTIVE.getName());
				for(Countries countries2 : countries){
					countries1.add(countries2);
				}
				modelAndView.addObject("countrylist",countries1);
				modelAndView.setViewName("redirect:stateslist");
			} else {
				States codeLocal = statesService.findByCode(states.getCode());
				States nameLocal = statesService.findByName(states.getName());
				States statesCode = statesService.findByIdAndCode(states.getId(), states.getCode());
				States statesName = statesService.findByIdAndName(states.getId(),states.getName());
				
				if(statesCode != null){
				}else if(codeLocal != null){
					ModelAndView view = new ModelAndView("states/stateadd");
					view.addObject("codeExitMessage", "State Code Already Exists");
					view.addObject("states", states);
					List<Countries>countries1 = new ArrayList<Countries>();
					List<Countries>countries = countryService.findByStatus(Status.ACTIVE.getName());
					for(Countries countries2 : countries){
						countries1.add(countries2);
					}
					view.addObject("countrylist",countries1);
					return view;
				}
				
				if(statesName != null){
				}else if(nameLocal != null){
					ModelAndView view = new ModelAndView("states/stateadd");
					view.addObject("nameExitMessage", "States Name Already Exists");
					view.addObject("states", states);
					List<Countries>countries1 = new ArrayList<Countries>();
					List<Countries>countries = countryService.findByStatus(Status.ACTIVE.getName());
					for(Countries countries2 : countries){
						countries1.add(countries2);
					}
					view.addObject("countrylist",countries1);
					return view;
				}
				states.setStatus(Status.ACTIVE.getName());
				statesService.saveAndFlush(states);
				
				action="State "+states.getName()+" is Updated";
				module="State Module";
				constantUtil.saveAudition(action, module, request);
				
				List<Countries>countries1 = new ArrayList<Countries>();
				List<Countries>countries = countryService.findByStatus(Status.ACTIVE.getName());
				for(Countries countries2 : countries){
					countries1.add(countries2);
				}
				modelAndView.addObject("countrylist",countries1);
				modelAndView.setViewName("redirect:stateslist");
			}
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Delete State information by using state id
	 * 
	 * @param stateId
	 * @return
	 */

	@RequestMapping(value = "deleteState", method = RequestMethod.GET)
	public ModelAndView deleteState(ModelMap modelMap,@RequestParam int id) {

		LOGGER.info("Deleting State info");
		States states = statesService.getById(id);
		if(states != null){
			List<Project> projects = projectService.findByStates(states);
			List<User> users = userService.findByState(states);
			List<Region> regions = regionService.findByStates(states);
			List<Agency> agencies = agencyService.findByState(states);
			if(projects.isEmpty() && users.isEmpty() && regions.isEmpty() && agencies.isEmpty()){
				statesService.deleteStatesById(id);
				return new ModelAndView("redirect:stateslist");
			}else{
				modelMap.addAttribute("stateslist", statesService.findByStatus(Status.ACTIVE.getName()));
				modelMap.addAttribute("stateDeleteMsg", "Can not Delete State , This State Associate with Anothere Class");
				return new ModelAndView("states/stateslist");
			}
		}
		return new ModelAndView("redirect:stateslist");
	}

	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean STT_ADD = false;
		boolean STT_VIW = false;
		boolean STT_EDT = false;
		boolean STT_DEL = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.STT_ADD.toString())) {
						STT_ADD = true;
					}
					if (feature.equals(Modules.STT_VIW.toString())) {
						STT_VIW = true;
					}
					if (feature.equals(Modules.STT_EDT.toString())) {
						STT_EDT = true;
						;
					}
					if (feature.equals(Modules.STT_DEL.toString())) {
						STT_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("STT_ADD", STT_ADD);
		modelMap.addAttribute("STT_VIW", STT_VIW);
		modelMap.addAttribute("STT_EDT", STT_EDT);
		modelMap.addAttribute("STT_DEL", STT_DEL);*/
	}
	
	//Active state
		@RequestMapping(value = "activeState", method = RequestMethod.GET)
		public ModelAndView activeState(@RequestParam Integer id, Model model) throws Exception{
			if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
				if(id != null){
					States states = statesService.getById(id);
					if(states != null){
						states.setStateStatus(true);
						states.setStatus(Status.ACTIVE.getName());
						statesService.save(states);
						
						action="State "+states.getName()+" is Activated";
						module="State Module";
						constantUtil.saveAudition(action, module, request);
						
						return new ModelAndView("redirect:stateslist");
					}
				}
				return null;
			}else{
				return new ModelAndView("login");
			}
		}
		
		//Deactive state
		@RequestMapping(value = "deActiveState", method = RequestMethod.GET)
		public ModelAndView deActiveState(@RequestParam Integer id, Model model) throws Exception{
			if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
				if(id != null){
					States states = statesService.getById(id);
					if(states != null){
						states.setStateStatus(false);
						states.setStatus(Status.INACTIVE.getName());
						statesService.save(states);
						
						action="State "+states.getName()+" is deActivated";
						module="State Module";
						constantUtil.saveAudition(action, module, request);
						
						return new ModelAndView("redirect:stateslist");
					}
				}
				return null;
			}else{
				return new ModelAndView("login");
			}
		}
		
		/**
		 * Get state information based on state id
		 * @param stateId
		 * @return
		 */
		@RequestMapping(value = "getStateInformation", method = RequestMethod.GET)
		public @ResponseBody States getState(@RequestParam Integer stateId) {
			States states = new States();
			if (stateId != 0) {
				states = statesService.getById(stateId);
			}
			return states;
		}
}
