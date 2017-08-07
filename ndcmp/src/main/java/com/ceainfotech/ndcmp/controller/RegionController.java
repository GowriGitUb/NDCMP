/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ceainfotech.ndcmp.model.Agency;
import com.ceainfotech.ndcmp.model.Countries;
import com.ceainfotech.ndcmp.model.Region;
import com.ceainfotech.ndcmp.model.States;
import com.ceainfotech.ndcmp.service.AgencyService;
import com.ceainfotech.ndcmp.service.CountryService;
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
public class RegionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegionController.class);

	@Autowired
	RegionService regionService;

	@Autowired
	StatesService statesService;

	@Autowired
	CountryService countryService;

	@Autowired
	private UserService userService;
	
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
	 * Listing All Regions
	 * 
	 * @param modelMap
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "/regionList", method = RequestMethod.GET)
	public ModelAndView listAllRegions(ModelMap modelMap, HttpServletRequest request) {
		LOGGER.info("Listing All Regions ");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			//getAccessRights(modelMap, username);
			modelMap.addAttribute("regionslist", regionService.getRegions());
			return new ModelAndView("region/regionlist");
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Get region details for adding or updating existing region information
	 * 
	 * @param region
	 * @param regionId
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "editRegion", method = RequestMethod.GET)
	public ModelAndView editRegion(@ModelAttribute Region region, @RequestParam int id) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView modelAndView = new ModelAndView("region/regionadd");
			if (id != 0) {
				region = regionService.getById(id);
			}
			modelAndView.addObject("region", region);
			List<Countries> countries1 = new ArrayList<Countries>();
			List<Countries> countries = countryService.findByStatus(Status.ACTIVE.getName());
			for (Countries countries2 : countries) {
				countries1.add(countries2);
			}
			modelAndView.addObject("countrylist", countries1);
			List<States> states = new ArrayList<States>();
			List<States> states2 = statesService.findByStatus(Status.ACTIVE.getName());
			for (States states3 : states2) {
				states.add(states3);
			}
			modelAndView.addObject("statelist", states);
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
	}

	/**
	 * Get region details
	 * 
	 * @param region
	 * @param regionId
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/getRegion", method = RequestMethod.GET)
	public ModelAndView getRegion(@ModelAttribute Region region, @RequestParam Long regionId, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("region/regionadd");

		List<Countries> countrylist = countryService.findByStatus(Status.ACTIVE.getName());
		List<States> statelist = statesService.findByStatus(Status.ACTIVE.getName());

		if (region.getId() != null) {
			region = regionService.getById(regionId.intValue());
			Countries country = countryService.getById(region.getCountry().getId());
			States states = statesService.getById(region.getStates().getId());
			region.setCountry(country);
			region.setStates(states);
			modelAndView.addObject("region", region);
		}
		modelAndView.addObject("countrylist", countrylist);
		modelAndView.addObject("statelist", statelist);

		if (request != null) {
			Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
			if (flashMap != null) {
				String errormsg = (String) flashMap.get("errormsg");
				modelAndView.addObject("errormsg", errormsg);
			}
		}

		return modelAndView;
	}

	/**
	 * Add or update region information
	 * 
	 * @param region
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "saveRegion", method = RequestMethod.POST)
	public ModelAndView createRegion(@ModelAttribute Region region) throws Exception {
		LOGGER.info("Save or update Region data {} : " + region);
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView modelAndView = new ModelAndView();
			if (region.getId() == null || region.getId() == 0) {
				Region regionCode  = regionService.getByCode(region.getCode());
				Region regionName = regionService.findByName(region.getName());
				if(regionCode != null){
					ModelAndView view = new ModelAndView("region/regionadd");
					view.addObject("codeExitMessage", "Region Code Already Exists");
					view.addObject("region", region);
					List<Countries> countries1 = new ArrayList<Countries>();
					List<Countries> countries = countryService.findByStatus(Status.ACTIVE.getName());
					for (Countries countries2 : countries) {
						countries1.add(countries2);
					}
					view.addObject("countrylist", countries1);
					List<States> states = new ArrayList<States>();
					List<States> states2 = statesService.findByStatus(Status.ACTIVE.getName());
					for (States states3 : states2) {
						states.add(states3);
					}
					view.addObject("statelist", states);
					return view;
				}
				
				if(regionName != null){
					ModelAndView view = new ModelAndView("region/regionadd");
					view.addObject("nameExitMessage", "Region Name Already Exists");
					view.addObject("region", region);
					List<Countries> countries1 = new ArrayList<Countries>();
					List<Countries> countries = countryService.findByStatus(Status.ACTIVE.getName());
					for (Countries countries2 : countries) {
						countries1.add(countries2);
					}
					view.addObject("countrylist", countries1);
					List<States> states = new ArrayList<States>();
					List<States> states2 = statesService.findByStatus(Status.ACTIVE.getName());
					for (States states3 : states2) {
						states.add(states3);
					}
					view.addObject("statelist", states);
					return view;
				}
				if(regionName == null && regionCode == null){
					region.setStatus(Status.ACTIVE.getName());
					if (region.getCountry() != null) {
						Countries country = countryService.getById(region.getCountry().getId());
						region.setCountry(country);
					}
					if (region.getStates() != null) {
						States states = statesService.getById(region.getStates().getId());
						region.setStates(states);
					}
					
					regionService.addRegion(region);
					
					action="Region "+region.getName()+" is Saved";
					module="Region Module";
					constantUtil.saveAudition(action, module, request);
					return new ModelAndView("redirect:regionList");
				}
			}else{
				Region regionCode  = regionService.getByCode(region.getCode());
				Region regionName = regionService.findByName(region.getName());
				Region regionCodeWithId = regionService.getByCodeAndId(region.getCode(), region.getId());
				Region regionNameWithId = regionService.findByNameAndId(region.getName(), region.getId());
				
				if(regionCodeWithId != null){
				}else if(regionCode != null){
					ModelAndView view = new ModelAndView("region/regionadd");
					view.addObject("codeExitMessage", "Region Code Already Exists");
					view.addObject("region", region);
					List<Countries> countries1 = new ArrayList<Countries>();
					List<Countries> countries = countryService.findByStatus(Status.ACTIVE.getName());
					for (Countries countries2 : countries) {
						countries1.add(countries2);
					}
					view.addObject("countrylist", countries1);
					List<States> states = new ArrayList<States>();
					List<States> states2 = statesService.findByStatus(Status.ACTIVE.getName());
					for (States states3 : states2) {
						states.add(states3);
					}
					view.addObject("statelist", states);
					return view;
				}
				if(regionNameWithId != null){
				}else if(regionName != null){
					ModelAndView view = new ModelAndView("region/regionadd");
					view.addObject("nameExitMessage", "Region Name Already Exists");
					view.addObject("region", region);
					List<Countries> countries1 = new ArrayList<Countries>();
					List<Countries> countries = countryService.findByStatus(Status.ACTIVE.getName());
					for (Countries countries2 : countries) {
						countries1.add(countries2);
					}
					view.addObject("countrylist", countries1);
					List<States> states = new ArrayList<States>();
					List<States> states2 = statesService.findByStatus(Status.ACTIVE.getName());
					for (States states3 : states2) {
						states.add(states3);
					}
					view.addObject("statelist", states);
					return view;
				}
				if (region.getCountry() != null) {
					Countries country = countryService.getById(region.getCountry().getId());
					region.setCountry(country);
				}
				if (region.getStates() != null) {
					States states = statesService.getById(region.getStates().getId());
					region.setStates(states);
				}
				region.setStatus(Status.ACTIVE.getName());
				regionService.addRegion(region);
				action="Region "+region.getName()+" is Updated";
				module="Region Module";
				constantUtil.saveAudition(action, module, request);
				return new ModelAndView("redirect:regionList");
			}
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
	}

	/**
	 * Deleting region details by region id
	 * 
	 * @param regionId
	 * @return
	 */

	@RequestMapping(value = "deleteRegion", method = RequestMethod.GET)
	public ModelAndView deleteRegion(ModelMap modelMap,@RequestParam int id) {
		LOGGER.info("Deleting Region info");
		Region region = regionService.getById(id);
		if(region != null){
			List<Agency> agencies = agencyService.findByRegion(region);
			if(agencies.isEmpty()){
				regionService.deleteRegion(id);
				return new ModelAndView("redirect:regionList");
			}else{
				modelMap.addAttribute("regionDeleteMsg", "Can not Delete Region , This Region Associate with Anothere Class");
				modelMap.addAttribute("regionslist", regionService.getRegions());
				return new ModelAndView("region/regionlist");
			}
		}
		return new ModelAndView("redirect:regionList");
	}

	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean REG_ADD = false;
		boolean REG_EDT = false;
		boolean REG_DEL = false;
		boolean REG_VIW = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.REG_ADD.toString())) {
						REG_ADD = true;
					}
					if (feature.equals(Modules.REG_VIW.toString())) {
						REG_VIW = true;
					}
					if (feature.equals(Modules.REG_EDT.toString())) {
						REG_EDT = true;
						;
					}
					if (feature.equals(Modules.REG_DEL.toString())) {
						REG_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("REG_ADD", REG_ADD);
		modelMap.addAttribute("REG_VIW", REG_VIW);
		modelMap.addAttribute("REG_EDT", REG_EDT);
		modelMap.addAttribute("REG_DEL", REG_DEL);*/
	}
	
	//Active region
		@RequestMapping(value = "activeRegion", method = RequestMethod.GET)
		public ModelAndView activeRegion(@RequestParam Integer id, Model model) throws Exception{
			if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
				if(id != null){
					Region region = regionService.getById(id);
					if(region != null){
						region.setRegionStatus(true);
						region.setStatus(Status.ACTIVE.getName());
						regionService.addRegion(region);
						
						action="Region "+region.getName()+" is Activated";
						module="Region Module";
						constantUtil.saveAudition(action, module, request);
						
						return new ModelAndView("redirect:regionList");
					}
				}
				return null;
			}else{
				return new ModelAndView("login");
			}
		}
		
		//Deactive region
		@RequestMapping(value = "deActiveRegion", method = RequestMethod.GET)
		public ModelAndView deActiveRegion(@RequestParam Integer id, Model model) throws Exception{
			if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
				if(id != null){
					Region region = regionService.getById(id);
					if(region != null){
						region.setRegionStatus(false);
						region.setStatus(Status.INACTIVE.getName());
						regionService.addRegion(region);
						
						action="Region "+region.getName()+" is deActivated";
						module="Region Module";
						constantUtil.saveAudition(action, module, request);
						
						return new ModelAndView("redirect:regionList");
					}
				}
				return null;
			}else{
				return new ModelAndView("login");
			}
			
		}
		
		/**
		 * Get region information based on region id
		 * @param regionId
		 * @return
		 */
		@RequestMapping(value = "getRegionInformation", method = RequestMethod.GET)
		public @ResponseBody Region getRegionInformation(@RequestParam Integer regionId) {
			Region region = new Region();
			if (regionId != 0) {
				region = regionService.getById(regionId);
			}
			return region;
		}
}
