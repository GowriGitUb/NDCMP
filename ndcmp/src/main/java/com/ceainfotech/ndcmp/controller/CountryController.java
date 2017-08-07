/**
 * 
 */
package com.ceainfotech.ndcmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class CountryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	CountryService countryService;

	@Autowired
	StatesService stateService;

	@Autowired
	private UserService userService;


	@Autowired
	RegionService regionService; 
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	AgencyService agencyService; 
	

	@Autowired
	private ConstantUtil constantUtil;
	
	@Autowired
	PrincipalUtil principalUtil;
	
	@Autowired
	private HttpServletRequest request;
	
	String action,module="";
	/**
	 * Listing All Countries
	 * 
	 * @param modelMap
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "countryList", method = RequestMethod.GET)
	public ModelAndView listAllCountries(ModelMap modelMap, Authentication authentication, HttpServletRequest request) {
		LOGGER.info("Listing All Countries ");
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			//getAccessRights(modelMap, username);
			List<Countries> countries = countryService.getCountries();
			modelMap.addAttribute("countrylist", countries);
			return new ModelAndView("country/countrylist");
		}else{
			return new ModelAndView("login");
		}
		
	}

	/**
	 * Get the add page of country
	 * 
	 * @param country
	 * @param countryId
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/getCountry", method = RequestMethod.GET)
	public ModelAndView getCountry(@ModelAttribute Countries country, @RequestParam Integer countryId, HttpServletRequest request) {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView view = new ModelAndView("country/countryadd");
			if (countryId != 0) {
				country = countryService.getById(countryId);
			}
			view.addObject("country", country);
			return view;
		}else{
			return new ModelAndView("login");
		}
	}

	/**
	 * Add the new country
	 * 
	 * @param country
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "createCountry", method = RequestMethod.POST)
	public ModelAndView createCountry(@ModelAttribute Countries country) throws Exception {
		if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
			ModelAndView modelAndView = new ModelAndView();
			if (country.getId() == null || country.getId() == 0) {
				Countries countryCode = countryService.findByCode(country.getCode());
				Countries countryName = countryService.findByName(country.getName());
				if(countryCode != null){
					ModelAndView view = new ModelAndView("country/countryadd");
					view.addObject("codeExitMessage", "Country Code Already Exists");
					view.addObject("country", country);
					return view;
				}
				if(countryName != null){
					ModelAndView view = new ModelAndView("country/countryadd");
					view.addObject("nameExitMessage", "Country Name Already Exists");
					view.addObject("country", country);
					return view;
				}
				country.setStatus(Status.ACTIVE.getName());
				countryService.save(country);
				
				action="Country "+country.getName()+" is Saved";
				module="Country Module";
				constantUtil.saveAudition(action, module, request);
				
				modelAndView.setViewName("redirect:countryList");
			} else {
				Countries codeLocal = countryService.findByCode(country.getCode());
				Countries nameLocal = countryService.findByName(country.getName());
				Countries countryCode = countryService.findByIdAndCode(country.getId(), country.getCode());
				Countries countryName = countryService.findByIdAndName(country.getId(),country.getName());
				
				if(countryCode != null){
				}else if(codeLocal != null){
					ModelAndView view = new ModelAndView("country/countryadd");
					view.addObject("codeExitMessage", "Country Code Already Exists");
					view.addObject("country", country);
					return view;
				}
				
				if(countryName != null){
				}else if(nameLocal != null){
					ModelAndView view = new ModelAndView("country/countryadd");
					view.addObject("nameExitMessage", "Country Name Already Exists");
					view.addObject("country", country);
					return view;
				}
				country.setStatus(Status.ACTIVE.getName());
				countryService.update(country);
				
				action="Country "+country.getName()+" is Updated";
				module="Country Module";
				constantUtil.saveAudition(action, module, request);
				
				modelAndView.setViewName("redirect:countryList");
			}
			return modelAndView;
		}else{
			return new ModelAndView("login");
		}
	}

	/**
	 * Delete the country by id
	 * 
	 * @param countryId
	 * @return
	 */

	@RequestMapping(value = "deleteCountry", method = RequestMethod.GET)
	public ModelAndView deleteCountry(ModelMap modelMap,@RequestParam int countryId) {
		LOGGER.info("Deleting country info");
		Countries country = countryService.getById(countryId);
		if(country != null){
			List<States> states = stateService.findByCountry(country);
			List<User> users = userService.findByCountry(country);
			List<Region> regions = regionService.findByCountry(country);
			List<Project> projects = projectService.findByCountry(country);
			List<Agency> agencies = agencyService.findByCountry(country);
			
			if(states.isEmpty() && users.isEmpty() && regions.isEmpty() && projects.isEmpty() && agencies.isEmpty()){
				countryService.deleteCountry(countryId);
				return new ModelAndView("redirect:countryList");
			}else{
				List<Countries> countries = countryService.getCountries();
				modelMap.addAttribute("countrylist",countries);
				modelMap.addAttribute("coutryDeleteMsg", "Can not Delete Country , This Country Associate with Anothere Class");
				return new ModelAndView("country/countrylist");
			}
		}
		return new ModelAndView("redirect:countryList");
	}

	public void getAccessRights(ModelMap modelMap, String username) {

		/*User user = userService.findByUsername(username);
		List<AccessRights> accessrightslist = new ArrayList<AccessRights>();
		// List<AccessRights> accessrightslist =
		// accessRightsService.listAccessRightsByRole(user.getUserRole().getId().intValue());
		List<String> features = new ArrayList<>();
		boolean COU_ADD = false;
		boolean COU_VIW = false;
		boolean COU_EDT = false;
		boolean COU_DEL = false;

		if (accessrightslist != null) {
			for (AccessRights accessRights : accessrightslist) {
				features.add(accessRights.getFeatures().getFeaturecode());
			}
			if (features != null) {
				for (String feature : features) {
					if (feature.equals(Modules.COU_ADD.toString())) {
						COU_ADD = true;
					}
					if (feature.equals(Modules.COU_VIW.toString())) {
						COU_VIW = true;
					}
					if (feature.equals(Modules.COU_EDT.toString())) {
						COU_EDT = true;
						;
					}
					if (feature.equals(Modules.COU_DEL.toString())) {
						COU_DEL = true;
					}
				}
			}
		}
		modelMap.addAttribute("COU_ADD", COU_ADD);
		modelMap.addAttribute("COU_VIW", COU_VIW);
		modelMap.addAttribute("COU_EDT", COU_EDT);
		modelMap.addAttribute("COU_DEL", COU_DEL);*/
	}
	
	//Active Country
		@RequestMapping(value = "activeCountry", method = RequestMethod.GET)
		public ModelAndView activeCountry(@RequestParam Integer id, Model model) throws Exception{
			if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
				if(id != null){
					Countries countries = countryService.getById(id);
					if(countries != null){
						countries.setCountryStatus(true);
						countries.setStatus(Status.ACTIVE.getName());
						countryService.save(countries);
						
						action="Country "+countries.getName()+" is Activated";
						module="Country Module";
						constantUtil.saveAudition(action, module, request);
						
						return new ModelAndView("redirect:countryList");
					}
				}
				//return null;
			}/*else{
				return new ModelAndView("login");
			}*/
			return new ModelAndView("login");
			
		}
		
		//Deactive Country
		@RequestMapping(value = "deActiveCountry", method = RequestMethod.GET)
		public ModelAndView deActiveCountry(@RequestParam Integer id, Model model) throws Exception{
			if(principalUtil.getCurrentUserRole().equals(ConstantUtil.SUPERADMIN_ROLE)){
				if(id != null){
					Countries countries = countryService.getById(id);
					if(countries != null){
						countries.setCountryStatus(false);
						countries.setStatus(Status.INACTIVE.getName());
						countryService.save(countries);
						

						action="Country "+countries.getName()+" is deActivated";
						module="Country Module";
						constantUtil.saveAudition(action, module, request);
						
						return new ModelAndView("redirect:countryList");
					}
				}
				//return null;
			}/*else{
				return new ModelAndView("login");
			}*/
			return new ModelAndView("login");
		}
		
		@RequestMapping(value = "getCountryInformation", method = RequestMethod.GET)
		public @ResponseBody Countries getCountryInformation(@RequestParam Integer countryId) {
			Countries countries = new Countries();
			if (countryId != 0) {
				countries = countryService.getById(countryId);
			}
			return countries;
		}

}
